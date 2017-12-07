package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ReportRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-07T17:54:08.344Z")

public class ReportRequest   {
  @JsonProperty("type")
  private String type = null;

  @JsonProperty("files")
  private List<UUID> files = null;

  public ReportRequest type(String type) {
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

  public ReportRequest files(List<UUID> files) {
    this.files = files;
    return this;
  }

  public ReportRequest addFilesItem(UUID filesItem) {
    if (this.files == null) {
      this.files = new ArrayList<UUID>();
    }
    this.files.add(filesItem);
    return this;
  }

   /**
   * Get files
   * @return files
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<UUID> getFiles() {
    return files;
  }

  public void setFiles(List<UUID> files) {
    this.files = files;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReportRequest reportRequest = (ReportRequest) o;
    return Objects.equals(this.type, reportRequest.type) &&
        Objects.equals(this.files, reportRequest.files);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, files);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReportRequest {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    files: ").append(toIndentedString(files)).append("\n");
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

