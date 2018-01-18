package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

/**
 * Version
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-06T16:52:57.027Z")

public class Version {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("revisions")
  private List<Revision> revisions = null;

  public Version id(String id) {
    this.id = id;
    return this;
  }

   /**
   * API Versioning
   * @return id
  **/
  @ApiModelProperty(example = "v1", value = "API Versioning")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Version revisions(List<Revision> revisions) {
    this.revisions = revisions;
    return this;
  }

  public Version addRevisionsItem(Revision revisionsItem) {
    if (this.revisions == null) {
      this.revisions = new ArrayList<Revision>();
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

  public List<Revision> getRevisions() {
    return revisions;
  }

  public void setRevisions(List<Revision> revisions) {
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
    Version version = (Version) o;
    return Objects.equals(this.id, version.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Version {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

