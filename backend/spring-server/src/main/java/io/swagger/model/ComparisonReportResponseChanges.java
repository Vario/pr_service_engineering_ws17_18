package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Change;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ComparisonReportResponseChanges
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-26T10:05:32.415Z")

public class ComparisonReportResponseChanges   {
  @JsonProperty("errors")
  private List<Change> errors = null;

  @JsonProperty("warnings")
  private List<Change> warnings = null;

  @JsonProperty("infos")
  private List<Change> infos = null;

  public ComparisonReportResponseChanges errors(List<Change> errors) {
    this.errors = errors;
    return this;
  }

  public ComparisonReportResponseChanges addErrorsItem(Change errorsItem) {
    if (this.errors == null) {
      this.errors = new ArrayList<Change>();
    }
    this.errors.add(errorsItem);
    return this;
  }

   /**
   * Get errors
   * @return errors
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Change> getErrors() {
    return errors;
  }

  public void setErrors(List<Change> errors) {
    this.errors = errors;
  }

  public ComparisonReportResponseChanges warnings(List<Change> warnings) {
    this.warnings = warnings;
    return this;
  }

  public ComparisonReportResponseChanges addWarningsItem(Change warningsItem) {
    if (this.warnings == null) {
      this.warnings = new ArrayList<Change>();
    }
    this.warnings.add(warningsItem);
    return this;
  }

   /**
   * Get warnings
   * @return warnings
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Change> getWarnings() {
    return warnings;
  }

  public void setWarnings(List<Change> warnings) {
    this.warnings = warnings;
  }

  public ComparisonReportResponseChanges infos(List<Change> infos) {
    this.infos = infos;
    return this;
  }

  public ComparisonReportResponseChanges addInfosItem(Change infosItem) {
    if (this.infos == null) {
      this.infos = new ArrayList<Change>();
    }
    this.infos.add(infosItem);
    return this;
  }

   /**
   * Get infos
   * @return infos
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Change> getInfos() {
    return infos;
  }

  public void setInfos(List<Change> infos) {
    this.infos = infos;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ComparisonReportResponseChanges comparisonReportResponseChanges = (ComparisonReportResponseChanges) o;
    return Objects.equals(this.errors, comparisonReportResponseChanges.errors) &&
        Objects.equals(this.warnings, comparisonReportResponseChanges.warnings) &&
        Objects.equals(this.infos, comparisonReportResponseChanges.infos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errors, warnings, infos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ComparisonReportResponseChanges {\n");
    
    sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
    sb.append("    warnings: ").append(toIndentedString(warnings)).append("\n");
    sb.append("    infos: ").append(toIndentedString(infos)).append("\n");
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

