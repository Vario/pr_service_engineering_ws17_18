package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RevisionReports
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-18T19:22:35.965Z")

public class RevisionReports   {
  @JsonProperty("violation")
  private Object violation = null;

  @JsonProperty("comparison")
  private List<Object> comparison = null;

  public RevisionReports violation(Object violation) {
    this.violation = violation;
    return this;
  }

   /**
   * Get violation
   * @return violation
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Object getViolation() {
    return violation;
  }

  public void setViolation(Object violation) {
    this.violation = violation;
  }

  public RevisionReports comparison(List<Object> comparison) {
    this.comparison = comparison;
    return this;
  }

  public RevisionReports addComparisonItem(Object comparisonItem) {
    if (this.comparison == null) {
      this.comparison = new ArrayList<Object>();
    }
    this.comparison.add(comparisonItem);
    return this;
  }

   /**
   * Get comparison
   * @return comparison
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Object> getComparison() {
    return comparison;
  }

  public void setComparison(List<Object> comparison) {
    this.comparison = comparison;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RevisionReports revisionReports = (RevisionReports) o;
    return Objects.equals(this.violation, revisionReports.violation) &&
        Objects.equals(this.comparison, revisionReports.comparison);
  }

  @Override
  public int hashCode() {
    return Objects.hash(violation, comparison);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RevisionReports {\n");
    
    sb.append("    violation: ").append(toIndentedString(violation)).append("\n");
    sb.append("    comparison: ").append(toIndentedString(comparison)).append("\n");
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

