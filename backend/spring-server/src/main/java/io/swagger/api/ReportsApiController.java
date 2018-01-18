package io.swagger.api;

import at.jku.se.pr.rest.qualityapi.comparison.RenderChanges;
import at.jku.se.pr.rest.qualityapi.exceptions.MultipleResultsException;
import at.jku.se.pr.rest.qualityapi.files.FileHelpers;
import at.jku.se.pr.rest.qualityapi.integrations.ZallyIntegration;
import at.jku.se.pr.rest.qualityapi.mongodb.MongoDBRequest;
import at.jku.se.pr.rest.qualityapi.settings.SettingsHelpers;
import com.deepoove.swagger.diff.SwaggerDiff;
import com.google.gson.Gson;
import io.swagger.model.ComparisonReportRequest;
import io.swagger.model.ComparisonReportResponse;

import io.swagger.annotations.*;

import io.swagger.model.ViolationReportRequest;
import io.swagger.model.ViolationReportResponse;
import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-10T17:37:35.998+01:00")

@Controller
public class ReportsApiController implements ReportsApi {


    public ResponseEntity<ComparisonReportResponse> reportsComparisonPost(@ApiParam(value = "Report Creation" ,required=true )  @Valid @RequestBody ComparisonReportRequest file) {
        List<UUID> fileIds = file.getFileIds();

        if(fileIds.size() != 2){
            System.out.println("For comparison are two revisions needed");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        UUID settingsId;
        try {
            UUID apiId = FileHelpers.getApiIdForFileIds(fileIds);
            settingsId = SettingsHelpers.getSettingsForApi(apiId);
        } catch (MultipleResultsException e) {
            System.out.println("Given apis to compare must have the same settings");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //Changes von doc1 zu doc2
        FileHelpers fileHelpers = new FileHelpers();
        Object s = fileHelpers.getSwaggerDocForFileId(fileIds.get(0));
        System.out.println(s);

        Object doc1 = FileHelpers.getSwaggerDocForFileId(fileIds.get(0));
        Object doc2 = FileHelpers.getSwaggerDocForFileId(fileIds.get(1));

        String json1 = new Gson().toJson(doc1);
        String json2 = new Gson().toJson(doc2);

        RenderChanges renderChanges = new RenderChanges(json1, json2);
        Object o = renderChanges.render();

        return new ResponseEntity<ComparisonReportResponse>(HttpStatus.OK);
    }

    public ResponseEntity<ViolationReportResponse> reportsViolationPost(@ApiParam(value = "Report Creation" ,required=true )  @Valid @RequestBody ViolationReportRequest file) {
        UUID fileId = file.getFileId();

        if (fileId == null) {
            System.out.println("No file-id given");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        UUID apiId = FileHelpers.getApiIdForFileId(fileId);
        UUID settingsId = SettingsHelpers.getSettingsForApi(apiId);

        /* Request Violations from ZallyIntegration */
        ZallyIntegration zally = ZallyIntegration.getInstance();
        Object violations = zally.getViolations(
                FileHelpers.getSwaggerDocForFileId(fileId),
                SettingsHelpers.getSettingForSettingsId(settingsId)
        );

        /* Prepare Response */
        ViolationReportResponse reportResponse = new ViolationReportResponse();
        reportResponse.setViolations(violations);


        /* Create Report in MongoDB */
        Document docToInsert = new Document()
                .append("type", "violation")
                .append("violations", violations);
        MongoDBRequest mongo = new MongoDBRequest("files");
        mongo.createAndAddToSet("file-id", fileId, "reports", docToInsert);

        return new ResponseEntity<ViolationReportResponse>(reportResponse,HttpStatus.OK);
    }

}
