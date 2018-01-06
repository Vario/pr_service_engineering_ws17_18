package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.ReportResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.joda.time.DateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RevisionRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-06T16:52:57.027Z")

public class RevisionRequest   {
  @JsonProperty("id")
  private DateTime id = null;

  @JsonProperty("file")
  private UUID file = null;

  @JsonProperty("reports")
  private List<ReportResponse> reports = null;

  public RevisionRequest id(DateTime id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")

  @Valid

  public DateTime getId() {
    return id;
  }

  public void setId(DateTime id) {
    this.id = id;
  }

  public RevisionRequest file(UUID file) {
    this.file = file;
    return this;
  }

   /**
   * File UUID (compare /files API)
   * @return file
  **/
  @ApiModelProperty(example = "3efc7632-e833-40cd-b002-abdfaf426e6c", value = "File UUID (compare /files API)")

  @Valid

  public UUID getFile() {
    return file;
  }

  public void setFile(UUID file) {
    this.file = file;
  }

  public RevisionRequest reports(List<ReportResponse> reports) {
    this.reports = reports;
    return this;
  }

  public RevisionRequest addReportsItem(ReportResponse reportsItem) {
    if (this.reports == null) {
      this.reports = new ArrayList<ReportResponse>();
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

  public List<ReportResponse> getReports() {
    return reports;
  }

  public void setReports(List<ReportResponse> reports) {
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
    RevisionRequest revisionRequest = (RevisionRequest) o;
    return Objects.equals(this.id, revisionRequest.id) &&
        Objects.equals(this.file, revisionRequest.file) &&
        Objects.equals(this.reports, revisionRequest.reports);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, file, reports);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RevisionRequest {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

