package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.RevisionRequest;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * VersionRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-08T13:49:07.180+01:00")

public class VersionRequest   {
  @JsonProperty("number")
  private String number = null;

  @JsonProperty("revisions")
  private List<RevisionRequest> revisions = null;

  public VersionRequest number(String number) {
    this.number = number;
    return this;
  }

   /**
   * API Versioning
   * @return number
  **/
  @ApiModelProperty(example = "v1", value = "API Versioning")


  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public VersionRequest revisions(List<RevisionRequest> revisions) {
    this.revisions = revisions;
    return this;
  }

  public VersionRequest addRevisionsItem(RevisionRequest revisionsItem) {
    if (this.revisions == null) {
      this.revisions = new ArrayList<RevisionRequest>();
    }
    this.revisions.add(revisionsItem);
    return this;
  }

   /**
   * Get revisions
   * @return revisions
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<RevisionRequest> getRevisions() {
    return revisions;
  }

  public void setRevisions(List<RevisionRequest> revisions) {
    this.revisions = revisions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VersionRequest versionRequest = (VersionRequest) o;
    return Objects.equals(this.number, versionRequest.number) &&
        Objects.equals(this.revisions, versionRequest.revisions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(number, revisions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VersionRequest {\n");
    
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    revisions: ").append(toIndentedString(revisions)).append("\n");
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

