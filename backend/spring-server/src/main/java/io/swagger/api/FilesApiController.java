package io.swagger.api;

import io.swagger.model.ApplicationError;
import io.swagger.model.FileDescription;
import io.swagger.model.FileUpload;

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
public class FilesApiController implements FilesApi {



    public ResponseEntity<FileDescription> filesPost(@ApiParam(value = "The file to upload" ,required=true )  @Valid @RequestBody FileUpload file) {
        // do some magic!
        return new ResponseEntity<FileDescription>(HttpStatus.OK);
    }

}
