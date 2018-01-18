package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.RevisionReports;
import java.util.UUID;
import org.joda.time.DateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Revision
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-18T19:22:35.965Z")

public class Revision   {
  @JsonProperty("id")
  private DateTime id = null;

  @JsonProperty("file")
  private UUID file = null;

  @JsonProperty("url")
  private String url = null;

  @JsonProperty("reports")
  private RevisionReports reports = null;

  public Revision id(DateTime id) {
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

  public Revision file(UUID file) {
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

  public Revision url(String url) {
    this.url = url;
    return this;
  }

   /**
   * Get url
   * @return url
  **/
  @ApiModelProperty(example = "http://quality.rest.patrickbuchner.at/api/v1/files/3efc7632-e833-40cd-b002-abdfaf426e6c", value = "")


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Revision reports(RevisionReports reports) {
    this.reports = reports;
    return this;
  }

   /**
   * Get reports
   * @return reports
  **/
  @ApiModelProperty(value = "")

  @Valid

  public RevisionReports getReports() {
    return reports;
  }

  public void setReports(RevisionReports reports) {
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
    return Objects.equals(this.id, revision.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Revision {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    file: ").append(toIndentedString(file)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
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

