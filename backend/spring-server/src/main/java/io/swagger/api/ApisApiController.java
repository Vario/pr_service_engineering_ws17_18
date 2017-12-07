package io.swagger.api;

import io.swagger.model.ApplicationError;
import io.swagger.model.Document;
import io.swagger.model.DocumentCreation;
import java.util.List;
import io.swagger.model.UpdateSuccess;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-07T17:54:08.344Z")

@Controller
public class ApisApiController implements ApisApi {



    public ResponseEntity<List<Document>> apisGet() {
        // do some magic!
        return new ResponseEntity<List<Document>>(HttpStatus.OK);
    }

    public ResponseEntity<UpdateSuccess> apisPost(@ApiParam(value = "The documents to add" ,required=true )  @Valid @RequestBody List<DocumentCreation> apis) {
        // do some magic!
        return new ResponseEntity<UpdateSuccess>(HttpStatus.OK);
    }

}
