package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * FileRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-06T16:52:57.027Z")

public class FileRequest   {
  @JsonProperty("api-id")
  private String apiId = null;

  @JsonProperty("version")
  private String version = null;

  @JsonProperty("swagger")
  private Object swagger = null;

  public FileRequest apiId(String apiId) {
    this.apiId = apiId;
    return this;
  }

   /**
   * Get apiId
   * @return apiId
  **/
  @ApiModelProperty(value = "")


  public String getApiId() {
    return apiId;
  }

  public void setApiId(String apiId) {
    this.apiId = apiId;
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileRequest fileRequest = (FileRequest) o;
    return Objects.equals(this.apiId, fileRequest.apiId) &&
        Objects.equals(this.version, fileRequest.version) &&
        Objects.equals(this.swagger, fileRequest.swagger);
  }

  @Override
  public int hashCode() {
    return Objects.hash(apiId, version, swagger);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FileRequest {\n");
    
    sb.append("    apiId: ").append(toIndentedString(apiId)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    swagger: ").append(toIndentedString(swagger)).append("\n");
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

