package io.swagger.api;

import at.jku.se.pr.rest.qualityapi.integrations.SwaggerDiffIntegration;
import at.jku.se.pr.rest.qualityapi.exceptions.MultipleResultsException;
import at.jku.se.pr.rest.qualityapi.files.FileHelpers;
import at.jku.se.pr.rest.qualityapi.integrations.ZallyIntegration;
import at.jku.se.pr.rest.qualityapi.mongodb.MongoDBRequest;
import at.jku.se.pr.rest.qualityapi.settings.SettingsHelpers;
import com.mongodb.client.model.Updates;
import io.swagger.model.*;

import io.swagger.annotations.*;

import org.bson.Document;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import static com.mongodb.client.model.Filters.eq;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-10T17:37:35.998+01:00")

@Controller
public class ReportsApiController implements ReportsApi {


    public ResponseEntity<?> reportsComparisonPost(@ApiParam(value = "Report Creation" ,required=true )  @Valid @RequestBody ComparisonReportRequest file) {
        List<UUID> fileIds = file.getFileIds();

        if(fileIds.size() != 2){
            ApplicationError error = new ApplicationError();
            error.setCode(400);
            error.setMessage("For comparison are exactly two files needed");
            return new ResponseEntity<>(error ,HttpStatus.BAD_REQUEST);
        }

        String url1 = ControllerLinkBuilder.linkTo(
                methodOn(FilesApiController.class).filesIdGet(fileIds.get(0))
        ).toUri().toString();

        String url2 = ControllerLinkBuilder.linkTo(
                methodOn(FilesApiController.class).filesIdGet(fileIds.get(1))
        ).toUri().toString();

        SwaggerDiffIntegration swaggerDiffIntegration = new SwaggerDiffIntegration(url1,url2);
        HashMap<String,List<Change>> changes = swaggerDiffIntegration.render();
        ComparisonReportResponse response = new ComparisonReportResponse();

        ComparisonReportResponsePaths paths = new ComparisonReportResponsePaths();
        paths.setChanged(changes.get("changed"));
        paths.setNew(changes.get("new"));
        paths.setRemoved(changes.get("removed"));

        response.setFileIds(file.getFileIds());
        response.setPaths(paths);

        /* Add to Database */
        Document docToInsert = new Document()
                .append("file-ids", fileIds)
                .append("paths", paths.toBsonDocument());
        MongoDBRequest mongo = new MongoDBRequest("files");
        mongo.createAndAddToSet("file-id", fileIds.get(0), "comparison-reports", docToInsert);

        return new ResponseEntity<ComparisonReportResponse>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> reportsViolationPost(@ApiParam(value = "Report Creation" ,required=true )  @Valid @RequestBody ViolationReportRequest file) {
        UUID fileId = file.getFileId();

        if (fileId == null) {
            ApplicationError error = new ApplicationError();
            error.setCode(400);
            error.setMessage("No file-id given");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
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
        MongoDBRequest mongo = new MongoDBRequest("files");
        mongo.update(
                eq("file-id", fileId),
                Updates.set("violation-report", violations)
        );


        return new ResponseEntity<ViolationReportResponse>(reportResponse,HttpStatus.OK);
    }

}
