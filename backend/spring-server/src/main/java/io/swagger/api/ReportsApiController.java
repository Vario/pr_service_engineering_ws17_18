package io.swagger.api;

import at.jku.se.pr.rest.qualityapi.mongodb.MongoDBRequest;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import io.swagger.model.ApplicationError;
import io.swagger.model.ReportRequest;
import io.swagger.model.ReportResponse;

import io.swagger.annotations.*;

import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.*;
import javax.validation.Valid;

import static com.mongodb.client.model.Filters.and;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-10T17:37:35.998+01:00")

@Controller
public class ReportsApiController implements ReportsApi {

    private ResponseEntity<ReportResponse> handleValidation(ReportRequest file){
        UUID fileId = file.getFileIds().get(0);
        ReportResponse reportResponse = new ReportResponse();
        reportResponse.setType(file.getType());
        reportResponse.setViolations(0);

        Document docToInsert = new Document()
                .append("type",reportResponse.getType())
                .append("violations", reportResponse.getViolations());

        MongoDBRequest mongo = new MongoDBRequest("files");

        FindIterable<Document> results = mongo.find(
                and(
                        Filters.eq("file-id", fileId),
                        Filters.exists("reports")
                )
        );

        Iterator iterator = results.iterator();
        if(iterator.hasNext() == false){
            ArrayList<Document> reports = new ArrayList<>();
            reports.add(docToInsert);
            mongo.update(
                    Filters.eq("file-id", fileId),
                    Updates.set("reports", reports)
            );
        } else {
            mongo.update(
                    Filters.eq("file-id", fileId),
                    Updates.addToSet("reports", docToInsert)
            );
        }

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

        if("validation".compareTo(reportType) == 0){
            if(fileIds.size() != 1){
                System.out.println("Report Type: " + reportType +
                        " needs exactly one file as input (" + fileIds.size() + " given)");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                return handleValidation(file);
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
