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
 * FileRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-11T16:25:58.568Z")

public class FileRequest   {
  @JsonProperty("title")
  private String title = null;

  @JsonProperty("version")
  private String version = null;

  @JsonProperty("swagger")
  private Object swagger = null;

  @JsonProperty("settings-id")
  private UUID settingsId = null;

  public FileRequest title(String title) {
    this.title = title;
    return this;
  }

   /**
   * Get title
   * @return title
  **/
  @ApiModelProperty(example = "Petstore API", value = "")


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public FileRequest version(String version) {
    this.version = version;
    return this;
  }

   /**
   * Get version
   * @return version
  **/
  @ApiModelProperty(value = "")


  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public FileRequest swagger(Object swagger) {
    this.swagger = swagger;
    return this;
  }

   /**
   * Swagger API Specification
   * @return swagger
  **/
  @ApiModelProperty(required = true, value = "Swagger API Specification")
  @NotNull


  public Object getSwagger() {
    return swagger;
  }

  public void setSwagger(Object swagger) {
    this.swagger = swagger;
  }

  public FileRequest settingsId(UUID settingsId) {
    this.settingsId = settingsId;
    return this;
  }

   /**
   * UUID of a setting
   * @return settingsId
  **/
  @ApiModelProperty(example = "3efc7632-e833-40cd-b002-abdfaf426e6c", value = "UUID of a setting")

  @Valid

  public UUID getSettingsId() {
    return settingsId;
  }

  public void setSettingsId(UUID settingsId) {
    this.settingsId = settingsId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileRequest fileRequest = (FileRequest) o;
    return Objects.equals(this.title, fileRequest.title) &&
        Objects.equals(this.version, fileRequest.version) &&
        Objects.equals(this.swagger, fileRequest.swagger) &&
        Objects.equals(this.settingsId, fileRequest.settingsId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, version, swagger, settingsId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FileRequest {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    swagger: ").append(toIndentedString(swagger)).append("\n");
    sb.append("    settingsId: ").append(toIndentedString(settingsId)).append("\n");
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

