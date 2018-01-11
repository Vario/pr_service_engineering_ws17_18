package io.swagger.api;

import at.jku.se.pr.rest.qualityapi.exceptions.MultipleResultsException;
import at.jku.se.pr.rest.qualityapi.files.FileHelpers;
import at.jku.se.pr.rest.qualityapi.integrations.ZallyIntegration;
import at.jku.se.pr.rest.qualityapi.mongodb.MongoDBRequest;
import at.jku.se.pr.rest.qualityapi.settings.SettingsHelpers;
import io.swagger.model.LintingResponse;
import io.swagger.model.ReportRequest;
import io.swagger.model.ReportResponse;

import io.swagger.annotations.*;

import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import static com.mongodb.client.model.Filters.and;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-10T17:37:35.998+01:00")

@Controller
public class ReportsApiController implements ReportsApi {

    private ResponseEntity<ReportResponse> handleValidation(ReportRequest file, UUID settingsId){
        /* Inputs */
        UUID fileId = file.getFileIds().get(0);

        /* Request Violations from ZallyIntegration */
        ZallyIntegration zally = ZallyIntegration.getInstance();
        Object violations = zally.getViolations(
                FileHelpers.getSwaggerDocForFileId(fileId),
                SettingsHelpers.getSettingForSettingsId(settingsId)
        );

        /* Prepare Response */
        ReportResponse reportResponse = new ReportResponse();
        reportResponse.setType(file.getType());
        reportResponse.setViolations(violations);


        /* Create Report in MongoDB */
        Document docToInsert = new Document()
                .append("type",reportResponse.getType())
                .append("violations", violations);
        MongoDBRequest mongo = new MongoDBRequest("files");
        mongo.createAndAddToSet("file-id", fileId, "reports", docToInsert);

        return new ResponseEntity<ReportResponse>(reportResponse,HttpStatus.OK);
    }

    private ResponseEntity<ReportResponse> handleComparison(ReportRequest file){
        // TODO: Not Implemented
        ReportResponse reportResponse = new ReportResponse();
        return new ResponseEntity<ReportResponse>(reportResponse,HttpStatus.OK);
    }

    public ResponseEntity<ReportResponse> reportsPost(@ApiParam(value = "Report Creation" ,required=true )  @Valid @RequestBody ReportRequest file) {
        String reportType = file.getType();
        List<UUID> fileIds = file.getFileIds();

        if(fileIds == null){
            System.out.println("No file-ids given");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            for (UUID fileId : fileIds){
                if(fileId == null){
                    System.out.println("Invalid file id");
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }
        }

        UUID settingsId;
        try {
            String apiId = FileHelpers.getApiIdForFileIds(fileIds);
            settingsId = SettingsHelpers.getSettingsForApi(apiId);
        } catch (MultipleResultsException e) {
            System.out.println("Given apis to compare must have the same settings");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if("validation".compareTo(reportType) == 0){
            if(fileIds.size() != 1){
                System.out.println("Report Type: " + reportType +
                        " needs exactly one file as input (" + fileIds.size() + " given)");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                return handleValidation(file, settingsId);
            }
        } else if("comparison".compareTo(reportType) == 0){
            if(fileIds.size() != 2){
                System.out.println("Report Type: " + reportType +
                        " needs exactly two files as input (" + fileIds.size() + " given)");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                return handleComparison(file);
            }
        } else {
            System.out.println("Report Type: " + reportType + " is not supported");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
