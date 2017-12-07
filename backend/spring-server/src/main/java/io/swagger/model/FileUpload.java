package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * FileUpload
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-07T17:54:08.344Z")

public class FileUpload   {
  @JsonProperty("api-id")
  private String apiId = null;

  @JsonProperty("swagger")
  private Object swagger = null;

  public FileUpload apiId(String apiId) {
    this.apiId = apiId;
    return this;
  }

   /**
   * Name of the API (optional)
   * @return apiId
  **/
  @ApiModelProperty(value = "Name of the API (optional)")


  public String getApiId() {
    return apiId;
  }

  public void setApiId(String apiId) {
    this.apiId = apiId;
  }

  public FileUpload swagger(Object swagger) {
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
    FileUpload fileUpload = (FileUpload) o;
    return Objects.equals(this.apiId, fileUpload.apiId) &&
        Objects.equals(this.swagger, fileUpload.swagger);
  }

  @Override
  public int hashCode() {
    return Objects.hash(apiId, swagger);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FileUpload {\n");
    
    sb.append("    apiId: ").append(toIndentedString(apiId)).append("\n");
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

