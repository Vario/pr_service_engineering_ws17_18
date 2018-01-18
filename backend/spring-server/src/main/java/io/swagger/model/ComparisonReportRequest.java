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
 * ComparisonReportRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-18T11:43:31.507Z")

public class ComparisonReportRequest   {
  @JsonProperty("file-ids")
  private List<UUID> fileIds = null;

  public ComparisonReportRequest fileIds(List<UUID> fileIds) {
    this.fileIds = fileIds;
    return this;
  }

  public ComparisonReportRequest addFileIdsItem(UUID fileIdsItem) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ComparisonReportRequest comparisonReportRequest = (ComparisonReportRequest) o;
    return Objects.equals(this.fileIds, comparisonReportRequest.fileIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ComparisonReportRequest {\n");
    
    sb.append("    fileIds: ").append(toIndentedString(fileIds)).append("\n");
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

