/**
 * NOTE: This class is auto generated by the swagger code generator program (2.2.3).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.ApplicationError;
import io.swagger.model.ComparisonReportRequest;
import io.swagger.model.ComparisonReportResponse;
import io.swagger.model.ViolationReportRequest;
import io.swagger.model.ViolationReportResponse;

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
import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-18T11:43:31.507Z")

@Api(value = "reports", description = "the reports API")
public interface ReportsApi {

    @ApiOperation(value = "Create a new comparison report", notes = "", response = ComparisonReportResponse.class, tags={ "Reports API", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Documents successfully uploaded", response = ComparisonReportResponse.class),
        @ApiResponse(code = 400, message = "Bad Request (e.g. not a json, required fields not available (title, version))", response = ApplicationError.class),
        @ApiResponse(code = 500, message = "Unexpected error", response = ApplicationError.class) })
    
    @RequestMapping(value = "/reports/comparison",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<ComparisonReportResponse> reportsComparisonPost(@ApiParam(value = "Report Creation" ,required=true )  @Valid @RequestBody ComparisonReportRequest file);


    @ApiOperation(value = "Create a new violation report", notes = "", response = ViolationReportResponse.class, tags={ "Reports API", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Documents successfully uploaded", response = ViolationReportResponse.class),
        @ApiResponse(code = 400, message = "Bad Request (e.g. not a json, required fields not available (title, version))", response = ApplicationError.class),
        @ApiResponse(code = 500, message = "Unexpected error", response = ApplicationError.class) })
    
    @RequestMapping(value = "/reports/violation",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<ViolationReportResponse> reportsViolationPost(@ApiParam(value = "Report Creation" ,required=true )  @Valid @RequestBody ViolationReportRequest file);

}
