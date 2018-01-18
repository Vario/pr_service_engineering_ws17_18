package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.ComparisonReportResponsePaths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ComparisonReportResponse
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-18T11:43:31.507Z")

public class ComparisonReportResponse   {
  @JsonProperty("file-ids")
  private List<UUID> fileIds = null;

  @JsonProperty("paths")
  private ComparisonReportResponsePaths paths = null;

  public ComparisonReportResponse fileIds(List<UUID> fileIds) {
    this.fileIds = fileIds;
    return this;
  }

  public ComparisonReportResponse addFileIdsItem(UUID fileIdsItem) {
    if (this.fileIds == null) {
      this.fileIds = new ArrayList<UUID>();
    }
    this.fileIds.add(fileIdsItem);
    return this;
  }

   /**
   * Get fileIds
   * @return fileIds
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<UUID> getFileIds() {
    return fileIds;
  }

  public void setFileIds(List<UUID> fileIds) {
    this.fileIds = fileIds;
  }

  public ComparisonReportResponse paths(ComparisonReportResponsePaths paths) {
    this.paths = paths;
    return this;
  }

   /**
   * Get paths
   * @return paths
  **/
  @ApiModelProperty(value = "")

  @Valid

  public ComparisonReportResponsePaths getPaths() {
    return paths;
  }

  public void setPaths(ComparisonReportResponsePaths paths) {
    this.paths = paths;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ComparisonReportResponse comparisonReportResponse = (ComparisonReportResponse) o;
    return Objects.equals(this.fileIds, comparisonReportResponse.fileIds) &&
        Objects.equals(this.paths, comparisonReportResponse.paths);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileIds, paths);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ComparisonReportResponse {\n");
    
    sb.append("    fileIds: ").append(toIndentedString(fileIds)).append("\n");
    sb.append("    paths: ").append(toIndentedString(paths)).append("\n");
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

