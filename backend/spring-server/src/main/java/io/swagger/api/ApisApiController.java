package io.swagger.api;

import at.jku.se.pr.rest.qualityapi.mongodb.MongoDBRequest;

import at.jku.se.pr.rest.qualityapi.settings.SettingsHelpers;
import com.mongodb.client.model.Updates;
import io.swagger.model.*;
import io.swagger.annotations.*;

import java.util.*;

import io.swagger.model.Api;
import org.bson.Document;
import org.joda.time.DateTime;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.not;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-07T18:40:11.066Z")

@Controller
public class ApisApiController implements ApisApi {

    public ResponseEntity<ApiTitle> apisIdTitlePut(@ApiParam(value = "",required=true ) @PathVariable("id") UUID id,
                                                   @ApiParam(value = ""  )  @Valid @RequestBody ApiTitle title) {
        MongoDBRequest collection = new MongoDBRequest("files");
        collection.updateMany(
                eq("api-id", id),
                Updates.set("title", title.getTitle())
        );
        return new ResponseEntity<ApiTitle>(title, HttpStatus.OK);
    }

    public ResponseEntity<SettingsId> apisIdSettingsPut(@ApiParam(value = "API ID",required=true ) @PathVariable("id") UUID id,
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

    public ResponseEntity<List<Api>> apisGet() {
        ArrayList<Api> apis = new ArrayList<>();

        /* Database */
        MongoDBRequest request = new MongoDBRequest("files");
        List<Document> results = request.find(new Document());

        for (Document r : results) {
            /* API */
            String title = (String) r.get("title");
            UUID apiId = (UUID) r.get("api-id");
            SettingsHelpers settingsHelpers = new SettingsHelpers();
            UUID settingsId = settingsHelpers.getSettingsForApi(apiId);

            Api api = new Api();
            api.setTitle(title);
            api.setApiId(apiId);
            api.setSettingsId(settingsId);

            if(apis.contains(api)){
                api = apis.get(apis.indexOf(api));
            } else {
                api.setVersions(new ArrayList<Version>());
                apis.add(api);
            }

            /* Version */
            String versionId = (String) r.get("version");

            List<Version> versions = api.getVersions();
            Version version = new Version();
            version.setId(versionId);

            if(versions.contains(version)){
                version = versions.get(versions.indexOf(version));
            } else {
                version.setRevisions(new ArrayList<Revision>());
                versions.add(version);
            }

            /* Revision */
            UUID fileId = (UUID) r.get("file-id");
            Date revisionId = (Date) r.get("timestamp");
            Object violationReport = r.get("violation-report");
            List<Object> comparisonReports = (List<Object>) r.get("comparison-reports");

            List<Revision> revisions = version.getRevisions();
            Revision revision = new Revision();
            revision.setId(new DateTime(revisionId));
            revision.setFile(fileId);
            ControllerLinkBuilder linkBuilder = ControllerLinkBuilder.linkTo(
                methodOn(FilesApiController.class).filesIdGet(revision.getFile())
            );
            revision.setUrl(linkBuilder.toUri().toString());

            if(revisions.contains(revision)){
                revision = revisions.get(revisions.indexOf(revision));
            } else {
                RevisionReports revisionReports = new RevisionReports();
                revisionReports.setViolation(violationReport);
                revisionReports.setComparison(comparisonReports);
                revision.setReports(revisionReports);
                revisions.add(revision);
            }
        }


        return new ResponseEntity<List<Api>>(apis, HttpStatus.OK);
    }

    //public ResponseEntity<List<Api>> apisGet() {
    //    /* Database */
    //    MongoDBRequest request = new MongoDBRequest("files");
    //    List<Document> results = request.find(new Document());
    //
    //    /* API ID / TITLE Mapping */
    //    Map<UUID, String> apiIdTitleMap = new HashMap<>();
    //
    //    /* APIS / VERSIONS / REVISIONS / REPORTS */
    //    HashMap<UUID, /* APIs */
    //            HashMap<String, /* Versions */
    //                    HashMap<RevisionKey, /* Revisions */
    //                            List<ReportResponse>>>> apis = new HashMap<>();
    //    for (Document r : results) {
    //        String title = (String) r.get("title");
    //        UUID apiId = (UUID) r.get("api-id");
    //        apiIdTitleMap.put(apiId, title);
    //        UUID fileId = (UUID) r.get("file-id");
    //        Date timestamp = (Date) r.get("timestamp");
    //        String versionId = (String) r.get("version");
    //        List<Document> reports = (List<Document>) r.get("reports");
    //
    //        HashMap<String, HashMap<RevisionKey, List<ReportResponse>>> api;
    //        if(apis.containsKey(apiId)){
    //            /* API exists */
    //            api = apis.get(apiId);
    //        } else {
    //            /* Create a new API */
    //            apis.put(apiId,new HashMap<String, HashMap<RevisionKey, List<ReportResponse>>>());
    //            api = apis.get(apiId);
    //        }
    //
    //        HashMap<RevisionKey, List<ReportResponse>> version;
    //        if(api.containsKey(versionId)){
    //            /* Version exists */
    //            version = (HashMap) api.get(versionId);
    //        } else {
    //            /* Create a new Version */
    //            api.put(versionId, new HashMap<RevisionKey, List<ReportResponse>>());
    //            version = (HashMap) api.get(versionId);
    //        }
    //
    //        List<ReportResponse> revision;
    //        if(version.containsKey(new RevisionKey(timestamp,fileId))){
    //            /* RevisionKey exists */
    //            revision = version.get(new RevisionKey(timestamp,fileId));
    //        } else {
    //            /* Create a new RevisionKey */
    //            version.put(new RevisionKey(timestamp,fileId), new ArrayList<ReportResponse>());
    //            revision = version.get(new RevisionKey(timestamp,fileId));
    //        }
    //
    //        ReportResponse tmpReportResponse;
    //        if(reports != null){
    //            ArrayList<ReportResponse> reportResponses = new ArrayList<>();
    //            for(Document d : reports){
    //                tmpReportResponse = new ReportResponse();
    //                tmpReportResponse.setType((String) d.get("type"));
    //                tmpReportResponse.setViolations(d.get("violations"));
    //                reportResponses.add(tmpReportResponse);
    //            }
    //            revision.addAll(reportResponses);
    //        }
    //    }
    //
    //    /* Prepare Response */
    //    List<Api> Apis = new ArrayList<>();
    //    Api tmpApi;
    //    Version tmpVersionRequest;
    //    Revision tmpRevision;
    //    RevisionReports tmpRevisionReports;
    //
    //    /*            API KEY       VERSION KEY     REVISION KEY*/
    //    for(Map.Entry<UUID, HashMap<String, HashMap<RevisionKey, List<ReportResponse>>>> api : apis.entrySet()){
    //        tmpApi = new Api();
    //        Apis.add(tmpApi);
    //        tmpApi.setApiId(api.getKey());
    //
    //        SettingsHelpers settingsHelpers = new SettingsHelpers();
    //        UUID settingsId = settingsHelpers.getSettingsForApi(api.getKey());
    //        tmpApi.setSettingsId(settingsId);
    //        tmpApi.setTitle(apiIdTitleMap.get(api.getKey()));
    //
    //        for (Map.Entry<String, HashMap<RevisionKey, List<ReportResponse>>> version : api.getValue().entrySet()){
    //            tmpVersionRequest = new Version();
    //            tmpApi.addVersionsItem(tmpVersionRequest);
    //            tmpVersionRequest.setNumber(version.getKey());
    //
    //            for (Map.Entry<RevisionKey, List<ReportResponse>> revision : version.getValue().entrySet()){
    //                tmpRevision = new RevisionRequest();
    //                tmpVersionRequest.addRevisionsItem(tmpRevision);
    //                tmpRevision.setId(new DateTime(revision.getKey().getRevisionId()));
    //                tmpRevision.setFile(revision.getKey().getFileId());
    //                ControllerLinkBuilder linkBuilder = ControllerLinkBuilder.linkTo(
    //                                methodOn(FilesApiController.class).filesIdGet(tmpRevision.getFile()));
    //                tmpRevision.setUrl(linkBuilder.toUri().toString());
    //
    //                tmpRevision.setReports(revision.getValue());
    //
    //                if(tmpRevision.getReports() == null){
    //                    tmpRevision.setReports(new ArrayList<ReportResponse>());
    //                }
    //            } /* End Revisions */
    //
    //            if(tmpVersionRequest.getRevisions() == null){
    //                tmpVersionRequest.setRevisions(new ArrayList<RevisionRequest>());
    //            }
    //        } /* End Versions */
    //
    //        if(tmpApi.getVersions() == null){
    //            tmpApi.setVersions(new ArrayList<Version>());
    //        }
    //    }
    //
    //    return new ResponseEntity<List<Api>>(Apis, HttpStatus.OK);
    //}

}
