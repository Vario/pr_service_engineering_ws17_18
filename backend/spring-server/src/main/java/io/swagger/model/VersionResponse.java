package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * VersionResponse
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-08T13:49:07.180+01:00")

public class VersionResponse   {
  @JsonProperty("number")
  private String number = null;

  @JsonProperty("document")
  private UUID document = null;

  public VersionResponse number(String number) {
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

  public VersionResponse document(UUID document) {
    this.document = document;
    return this;
  }

   /**
   * ID of the document where the version belongs to
   * @return document
  **/
  @ApiModelProperty(value = "ID of the document where the version belongs to")

  @Valid

  public UUID getDocument() {
    return document;
  }

  public void setDocument(UUID document) {
    this.document = document;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VersionResponse versionResponse = (VersionResponse) o;
    return Objects.equals(this.number, versionResponse.number) &&
        Objects.equals(this.document, versionResponse.document);
  }

  @Override
  public int hashCode() {
    return Objects.hash(number, document);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VersionResponse {\n");
    
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    document: ").append(toIndentedString(document)).append("\n");
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

