package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ReportResponse
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-08T13:49:07.180+01:00")

public class ReportResponse   {
  @JsonProperty("type")
  private String type = null;

  @JsonProperty("violations")
  private Integer violations = null;

  public ReportResponse type(String type) {
    this.type = type;
    return this;
  }

   /**
   * Type of Report (Validation, Comparison)
   * @return type
  **/
  @ApiModelProperty(example = "Validation", value = "Type of Report (Validation, Comparison)")


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ReportResponse violations(Integer violations) {
    this.violations = violations;
    return this;
  }

   /**
   * Number of Rule Violations
   * @return violations
  **/
  @ApiModelProperty(value = "Number of Rule Violations")


  public Integer getViolations() {
    return violations;
  }

  public void setViolations(Integer violations) {
    this.violations = violations;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReportResponse reportResponse = (ReportResponse) o;
    return Objects.equals(this.type, reportResponse.type) &&
        Objects.equals(this.violations, reportResponse.violations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, violations);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReportResponse {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    violations: ").append(toIndentedString(violations)).append("\n");
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

