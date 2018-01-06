package io.swagger.api;

import at.jku.se.pr.rest.qualityapi.mongodb.MongoDBRequest;

import com.mongodb.client.model.Updates;
import io.swagger.model.*;
import io.swagger.annotations.*;

import java.util.*;

import org.bson.Document;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.not;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-07T18:40:11.066Z")

@Controller
public class ApisApiController implements ApisApi {

    public ResponseEntity<SettingsId> apisIdSettingsPut(@ApiParam(value = "API ID",required=true ) @PathVariable("id") String id,
                                                        @ApiParam(value = "The settings to use" ,required=true )  @Valid @RequestBody SettingsId file) {

        MongoDBRequest collection = new MongoDBRequest("settings");
        List<Document> results = collection.find(new Document().append("id", file.getId()));
        if (results.size() == 0) {
            System.out.println(String.format("Settings ID %s not found".format(String.valueOf(file.getId()))));
            return new ResponseEntity<SettingsId>(HttpStatus.NOT_FOUND);
        }

        long updateCountRemoveFromArray = collection.updateMany(
                //elemMatch("used-in", eq(id)),
                not(eq("id", file.getId())),
                Updates.pull("used-in", id)
        );

        long updateCountAddToArray = collection.update(
                eq("id", file.getId()),
                Updates.addToSet("used-in", id)
        );
        if(updateCountRemoveFromArray == 0 && updateCountAddToArray == 0)
            return new ResponseEntity<SettingsId>(file, HttpStatus.OK);
        else
            return new ResponseEntity<SettingsId>(file, HttpStatus.CREATED);
    }

    public ResponseEntity<List<ApiRequest>> apisGet() {
        /* Database */
        MongoDBRequest request = new MongoDBRequest("files");
        List<Document> results = request.find(new Document());

        /* APIS / VERSIONS / REVISIONS / REPORTS */
        HashMap<String, /* APIs */
                HashMap<String, /* Versions */
                        HashMap<Revision, /* Revisions */
                                List<ReportResponse>>>> apis = new HashMap<>();
        for (Document r: results) {
            String apiId = (String) r.get("api-id");
            UUID fileId = (UUID) r.get("file-id");
            Date timestamp = (Date) r.get("timestamp");
            String versionId = (String) r.get("version");
            List<Document> reports = (List<Document>) r.get("reports");

            HashMap<String, HashMap<Revision, List<ReportResponse>>> api;
            if(apis.containsKey(apiId)){
                /* API exists */
                System.out.println(apiId + " exists");
                api = apis.get(apiId);
            } else {
                /* Create a new API */
                System.out.println("creating api " + apiId);
                apis.put(apiId,new HashMap<String, HashMap<Revision, List<ReportResponse>>>());
                api = apis.get(apiId);
            }

            HashMap<Revision, List<ReportResponse>> version;
            if(api.containsKey(versionId)){
                /* Version exists */
                System.out.println(versionId + " exists");
                version = (HashMap) api.get(versionId);
            } else {
                /* Create a new Version */
                System.out.println("creating version " + versionId);
                api.put(versionId, new HashMap<Revision, List<ReportResponse>>());
                version = (HashMap) api.get(versionId);
            }

            List<ReportResponse> revision;
            if(version.containsKey(new Revision(timestamp,fileId))){
                /* Revision exists */
                System.out.println(timestamp + " exists");
                revision = version.get(new Revision(timestamp,fileId));
            } else {
                /* Create a new Revision */
                System.out.println("creating revision " + timestamp);
                version.put(new Revision(timestamp,fileId), new ArrayList<ReportResponse>());
                revision = version.get(new Revision(timestamp,fileId));
            }

            ReportResponse tmpReportResponse;
            if(reports != null){
                ArrayList<ReportResponse> reportResponses = new ArrayList<>();
                for(Document d : reports){
                    tmpReportResponse = new ReportResponse();
                    tmpReportResponse.setType((String) d.get("type"));
                    tmpReportResponse.setViolations((Integer) d.get("violations"));
                    reportResponses.add(tmpReportResponse);
                }
                revision.addAll(reportResponses);
            }
        }

        /* Prepare Response */
        List<ApiRequest> apiRequests = new ArrayList<>();
        ApiRequest tmpApiRequest;
        VersionRequest tmpVersionRequest;
        RevisionRequest tmpRevisionRequest;
        ReportResponse tmpReportResponse;

        /*            API KEY         VERSION KEY     REVISION KEY*/
        for(Map.Entry<String, HashMap<String, HashMap<Revision, List<ReportResponse>>>> api : apis.entrySet()){
            System.out.println("Api: " + api.getKey());

            tmpApiRequest = new ApiRequest();
            apiRequests.add(tmpApiRequest);
            tmpApiRequest.setApiId(api.getKey());
            for (Map.Entry<String, HashMap<Revision, List<ReportResponse>>> version : api.getValue().entrySet()){
                System.out.println("Version: " + version.getKey());

                tmpVersionRequest = new VersionRequest();
                tmpApiRequest.addVersionsItem(tmpVersionRequest);
                tmpVersionRequest.setNumber(version.getKey());

                for (Map.Entry<Revision, List<ReportResponse>> revision : version.getValue().entrySet()){
                    System.out.println("Revision: " + revision.getKey());

                    tmpRevisionRequest = new RevisionRequest();
                    tmpVersionRequest.addRevisionsItem(tmpRevisionRequest);
                    tmpRevisionRequest.setId(new DateTime(revision.getKey().getRevisionId()));
                    tmpRevisionRequest.setFile(revision.getKey().getFileId());

                    tmpRevisionRequest.setReports(revision.getValue());

                    if(tmpRevisionRequest.getReports() == null){
                        tmpRevisionRequest.setReports(new ArrayList<ReportResponse>());
                    }
                } /* End Revisions */

                if(tmpVersionRequest.getRevisions() == null){
                    tmpVersionRequest.setRevisions(new ArrayList<RevisionRequest>());
                }
            } /* End Versions */

            if(tmpApiRequest.getVersions() == null){
                tmpApiRequest.setVersions(new ArrayList<VersionRequest>());
            }
        }

        return new ResponseEntity<List<ApiRequest>>(apiRequests, HttpStatus.OK);
    }

}
