package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Linting Response
 */
@ApiModel(description = "Linting Response")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-07T11:37:20.557Z")

public class LintingResponse   {
  @JsonProperty("violations")
  private List<Violation> violations = new ArrayList<Violation>();

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("violations_count")
  private ViolationsCount violationsCount = null;

  public LintingResponse violations(List<Violation> violations) {
    this.violations = violations;
    return this;
  }

  public LintingResponse addViolationsItem(Violation violationsItem) {
    this.violations.add(violationsItem);
    return this;
  }

   /**
   * Get violations
   * @return violations
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<Violation> getViolations() {
    return violations;
  }

  public void setViolations(List<Violation> violations) {
    this.violations = violations;
  }

  public LintingResponse message(String message) {
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

  public LintingResponse violationsCount(ViolationsCount violationsCount) {
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LintingResponse lintingResponse = (LintingResponse) o;
    return Objects.equals(this.violations, lintingResponse.violations) &&
        Objects.equals(this.message, lintingResponse.message) &&
        Objects.equals(this.violationsCount, lintingResponse.violationsCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(violations, message, violationsCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LintingResponse {\n");
    
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
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

