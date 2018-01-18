package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Violation;
import io.swagger.model.ViolationsCount;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Linting Response
 */
@ApiModel(description = "Linting Response")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-18T11:43:31.507Z")

public class ViolationReportResponse   {
  @JsonProperty("violations")
  private Object violations = new Object();

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("violations_count")
  private ViolationsCount violationsCount = null;

  public ViolationReportResponse violations(Object violations) {
    this.violations = violations;
    return this;
  }

   /**
   * Get violations
   * @return violations
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Object getViolations() {
    return violations;
  }

  public void setViolations(Object violations) {
    this.violations = violations;
  }

  public ViolationReportResponse message(String message) {
    this.message = message;
    return this;
  }

   /**
   * Get message
   * @return message
  **/
  @ApiModelProperty(value = "")


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ViolationReportResponse violationsCount(ViolationsCount violationsCount) {
    this.violationsCount = violationsCount;
    return this;
  }

   /**
   * Get violationsCount
   * @return violationsCount
  **/
  @ApiModelProperty(value = "")

  @Valid

  public ViolationsCount getViolationsCount() {
    return violationsCount;
  }

  public void setViolationsCount(ViolationsCount violationsCount) {
    this.violationsCount = violationsCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ViolationReportResponse violationReportResponse = (ViolationReportResponse) o;
    return Objects.equals(this.violations, violationReportResponse.violations) &&
        Objects.equals(this.message, violationReportResponse.message) &&
        Objects.equals(this.violationsCount, violationReportResponse.violationsCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(violations, message, violationsCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ViolationReportResponse {\n");
    
    sb.append("    violations: ").append(toIndentedString(violations)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    violationsCount: ").append(toIndentedString(violationsCount)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

