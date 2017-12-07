package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Report;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Revision
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-07T17:54:08.344Z")

public class Revision   {
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("version")
  private UUID version = null;

  @JsonProperty("file")
  private UUID file = null;

  @JsonProperty("reports")
  private List<Report> reports = null;

  public Revision id(UUID id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")

  @Valid

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Revision version(UUID version) {
    this.version = version;
    return this;
  }

   /**
   * ID of the version where the revision belongs to
   * @return version
  **/
  @ApiModelProperty(value = "ID of the version where the revision belongs to")

  @Valid

  public UUID getVersion() {
    return version;
  }

  public void setVersion(UUID version) {
    this.version = version;
  }

  public Revision file(UUID file) {
    this.file = file;
    return this;
  }

   /**
   * File UUID (compare /files API)
   * @return file
  **/
  @ApiModelProperty(value = "File UUID (compare /files API)")

  @Valid

  public UUID getFile() {
    return file;
  }

  public void setFile(UUID file) {
    this.file = file;
  }

  public Revision reports(List<Report> reports) {
    this.reports = reports;
    return this;
  }

  public Revision addReportsItem(Report reportsItem) {
    if (this.reports == null) {
      this.reports = new ArrayList<Report>();
    }
    this.reports.add(reportsItem);
    return this;
  }

   /**
   * Get reports
   * @return reports
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Report> getReports() {
    return reports;
  }

  public void setReports(List<Report> reports) {
    this.reports = reports;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Revision revision = (Revision) o;
    return Objects.equals(this.id, revision.id) &&
        Objects.equals(this.version, revision.version) &&
        Objects.equals(this.file, revision.file) &&
        Objects.equals(this.reports, revision.reports);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, version, file, reports);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Revision {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    file: ").append(toIndentedString(file)).append("\n");
    sb.append("    reports: ").append(toIndentedString(reports)).append("\n");
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

