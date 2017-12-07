package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Version;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Document
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-07T17:54:08.344Z")

public class Document   {
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("versions")
  private List<Version> versions = null;

  public Document id(UUID id) {
    this.id = id;
    return this;
  }

   /**
   * Unique Identifier of the document (server-generated)
   * @return id
  **/
  @ApiModelProperty(value = "Unique Identifier of the document (server-generated)")

  @Valid

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Document name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Title of the API
   * @return name
  **/
  @ApiModelProperty(value = "Title of the API")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Document versions(List<Version> versions) {
    this.versions = versions;
    return this;
  }

  public Document addVersionsItem(Version versionsItem) {
    if (this.versions == null) {
      this.versions = new ArrayList<Version>();
    }
    this.versions.add(versionsItem);
    return this;
  }

   /**
   * Get versions
   * @return versions
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Version> getVersions() {
    return versions;
  }

  public void setVersions(List<Version> versions) {
    this.versions = versions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Document document = (Document) o;
    return Objects.equals(this.id, document.id) &&
        Objects.equals(this.name, document.name) &&
        Objects.equals(this.versions, document.versions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, versions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Document {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    versions: ").append(toIndentedString(versions)).append("\n");
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

