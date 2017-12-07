package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Revision;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Version
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-07T17:54:08.344Z")

public class Version   {
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("number")
  private String number = null;

  @JsonProperty("revisions")
  private List<Revision> revisions = null;

  public Version id(UUID id) {
    this.id = id;
    return this;
  }

   /**
   * Unique Identifier of the API version (server-generated)
   * @return id
  **/
  @ApiModelProperty(value = "Unique Identifier of the API version (server-generated)")

  @Valid

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Version number(String number) {
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
    return Objects.equals(this.id, version.id) &&
        Objects.equals(this.number, version.number) &&
        Objects.equals(this.revisions, version.revisions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, number, revisions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Version {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

