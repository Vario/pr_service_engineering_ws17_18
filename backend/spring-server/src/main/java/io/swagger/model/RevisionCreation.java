package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import org.joda.time.DateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RevisionCreation
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-07T17:54:08.344Z")

public class RevisionCreation   {
  @JsonProperty("version")
  private UUID version = null;

  @JsonProperty("json")
  private String json = null;

  @JsonProperty("created")
  private DateTime created = null;

  public RevisionCreation version(UUID version) {
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

  public RevisionCreation json(String json) {
    this.json = json;
    return this;
  }

   /**
   * Swagger JSON Specification
   * @return json
  **/
  @ApiModelProperty(value = "Swagger JSON Specification")


  public String getJson() {
    return json;
  }

  public void setJson(String json) {
    this.json = json;
  }

  public RevisionCreation created(DateTime created) {
    this.created = created;
    return this;
  }

   /**
   * Get created
   * @return created
  **/
  @ApiModelProperty(value = "")

  @Valid

  public DateTime getCreated() {
    return created;
  }

  public void setCreated(DateTime created) {
    this.created = created;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RevisionCreation revisionCreation = (RevisionCreation) o;
    return Objects.equals(this.version, revisionCreation.version) &&
        Objects.equals(this.json, revisionCreation.json) &&
        Objects.equals(this.created, revisionCreation.created);
  }

  @Override
  public int hashCode() {
    return Objects.hash(version, json, created);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RevisionCreation {\n");
    
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    json: ").append(toIndentedString(json)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
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

