package io.swagger.api;

import at.jku.se.pr.rest.qualityapi.mongodb.MongoDBRequest;
import io.swagger.model.ApplicationError;
import io.swagger.model.FileRequest;
import io.swagger.model.FileResponse;

import io.swagger.annotations.*;

import org.bson.Document;
import org.joda.time.DateTime;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-07T18:40:11.066Z")

@Controller
public class FilesApiController implements FilesApi {



    public ResponseEntity<FileResponse> filesPost(@ApiParam(value = "The file to upload" ,required=true )  @Valid @RequestBody FileRequest file) {
        LinkedHashMap<String,Object> swagger = (LinkedHashMap<String,Object>) file.getSwagger();
        LinkedHashMap<String,String> info = (LinkedHashMap<String,String>) swagger.get("info");

        /* Input Validation */
        try {
            if(file.getApiId() == null)
                file.setApiId(info.get("title"));

            if(file.getVersion() == null)
                file.setVersion(info.get("version"));

        } catch (NullPointerException n){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(file.getApiId() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(file.getVersion() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        /* Prepare Response */
        FileResponse response = new FileResponse();
        response.setApiId(file.getApiId());
        response.setFileId(UUID.randomUUID());
        response.setTimestamp(DateTime.now());
        response.setVersion(file.getVersion());

        /* Database */
        MongoDBRequest request = new MongoDBRequest("files");
        request.insert(new Document()
                .append("api-id", response.getApiId())
                .append("file-id", response.getFileId())
                .append("timestamp", response.getTimestamp().toDate())
                .append("version", response.getVersion())
                .append("swagger", file.getSwagger())
        );

        return new ResponseEntity<FileResponse>(response, HttpStatus.OK);
    }

}
