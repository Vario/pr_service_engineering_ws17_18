package io.swagger.api;

import at.jku.se.pr.rest.qualityapi.files.FileHelpers;
import at.jku.se.pr.rest.qualityapi.mongodb.MongoDBRequest;
import io.swagger.model.FileRequest;
import io.swagger.model.FileResponse;

import io.swagger.annotations.*;

import org.bson.Document;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-07T18:40:11.066Z")

@Controller
public class FilesApiController implements FilesApi {

    public ResponseEntity<FileResponse> filesPost(@ApiParam(value = "The file to upload" ,required=true )  @Valid @RequestBody FileRequest file) {
        LinkedHashMap<String,Object> swagger = (LinkedHashMap<String,Object>) file.getSwagger();
        LinkedHashMap<String,String> info = (LinkedHashMap<String,String>) swagger.get("info");

        /* Input Validation */
        try {
            if(file.getTitle() == null)
                file.setTitle(info.get("title"));

            if(file.getVersion() == null)
                file.setVersion(info.get("version"));

        } catch (NullPointerException n){
            System.out.println("NullPointerException");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(file.getTitle() == null){
            System.out.println("title missing");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(file.getVersion() == null){
            System.out.println("VERSION ID missing");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        /* Decide if adding this file to an existing API or creating a new API */
        UUID apiId = FileHelpers.getApiIdForApiTitle(file.getTitle());
        if(apiId == null)
            apiId = UUID.randomUUID();


        /* Prepare Response */
        FileResponse response = new FileResponse();
        response.setTitle(file.getTitle());
        response.setApiId(apiId);
        response.setFileId(UUID.randomUUID());
        response.setTimestamp(DateTime.now());
        response.setVersion(file.getVersion());
        response.setSettingsId(file.getSettingsId());

        /* Database */
        MongoDBRequest request = new MongoDBRequest("files");
        request.insert(new Document()
                .append("title", response.getTitle())
                .append("api-id", response.getApiId())
                .append("file-id", response.getFileId())
                .append("timestamp", response.getTimestamp().toDate())
                .append("version", response.getVersion())
                .append("swagger", file.getSwagger())
        );

        return new ResponseEntity<FileResponse>(response, HttpStatus.OK);
    }

    public ResponseEntity<Object> filesIdGet(@ApiParam(value = "",required=true ) @PathVariable("id") UUID id) {
        MongoDBRequest collection = new MongoDBRequest("files");
        List<Document> results = collection.find(new Document().append("file-id", id));
        if (results.size() == 0) {
            System.out.println(String.format("File ID %s not found".format(String.valueOf(id))));
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        } else if(results.size() > 1){
            System.out.println(String.format("Multiple Files for File ID %s found!".format(String.valueOf(id))));
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Object response = results.get(0).get("swagger");

        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

}
