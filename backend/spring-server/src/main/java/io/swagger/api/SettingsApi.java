/**
 * NOTE: This class is auto generated by the swagger code generator program (2.2.3).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.ApplicationError;
import io.swagger.model.Setting;
import io.swagger.model.SettingsCreationRequest;
import io.swagger.model.Setting;
import io.swagger.model.SettingsListItem;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-05T17:09:31.244Z")

@Api(value = "settings", description = "the settings API")
public interface SettingsApi {

    @ApiOperation(value = "Create all setting sets", notes = "", response = SettingsListItem.class, responseContainer = "List", tags={ "Settings API", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Settings successfully created", response = SettingsListItem.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "Unexpected error", response = ApplicationError.class) })
    
    @RequestMapping(value = "/settings",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<List<SettingsListItem>> settingsGet();


    @ApiOperation(value = "Get a settings set", notes = "", response = Setting.class, tags={ "Settings API", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Settings successfully created", response = Setting.class),
        @ApiResponse(code = 500, message = "Unexpected error", response = ApplicationError.class) })
    
    @RequestMapping(value = "/settings/{id}",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<Setting> settingsIdGet(@ApiParam(value = "Settings ID",required=true ) @PathVariable("id") UUID id);


    @ApiOperation(value = "Update a Settings Set", notes = "", response = Setting.class, tags={ "Settings API", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Settings successfully updated", response = Setting.class),
        @ApiResponse(code = 400, message = "Bad Request (e.g. not a json, required fields not available (title, version))", response = ApplicationError.class),
        @ApiResponse(code = 500, message = "Unexpected error", response = ApplicationError.class) })
    
    @RequestMapping(value = "/settings/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Setting> settingsIdPut(@ApiParam(value = "Settings ID",required=true ) @PathVariable("id") UUID id, @ApiParam(value = "Report Creation" ,required=true )  @Valid @RequestBody SettingsCreationRequest settings);


    @ApiOperation(value = "Create a new Settings Set", notes = "", response = Setting.class, tags={ "Settings API", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Settings successfully created", response = Setting.class),
        @ApiResponse(code = 400, message = "Bad Request (e.g. not a json, required fields not available (title, version))", response = ApplicationError.class),
        @ApiResponse(code = 500, message = "Unexpected error", response = ApplicationError.class) })
    
    @RequestMapping(value = "/settings",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Setting> settingsPost(@ApiParam(value = "Report Creation" ,required=true )  @Valid @RequestBody SettingsCreationRequest settings);

}
