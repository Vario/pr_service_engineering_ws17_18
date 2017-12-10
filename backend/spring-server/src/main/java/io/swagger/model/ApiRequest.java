package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.VersionRequest;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ApiRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-08T13:49:07.180+01:00")

public class ApiRequest   {
  @JsonProperty("api-id")
  private String apiId = null;

  @JsonProperty("versions")
  private List<VersionRequest> versions = null;

  public ApiRequest apiId(String apiId) {
    this.apiId = apiId;
    return this;
  }

   /**
   * Title of the API
   * @return apiId
  **/
  @ApiModelProperty(example = "Petstore API", value = "Title of the API")


  public String getApiId() {
    return apiId;
  }

  public void setApiId(String apiId) {
    this.apiId = apiId;
  }

  public ApiRequest versions(List<VersionRequest> versions) {
    this.versions = versions;
    return this;
  }

  public ApiRequest addVersionsItem(VersionRequest versionsItem) {
    if (this.versions == null) {
      this.versions = new ArrayList<VersionRequest>();
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

  public List<VersionRequest> getVersions() {
    return versions;
  }

  public void setVersions(List<VersionRequest> versions) {
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
    ApiRequest apiRequest = (ApiRequest) o;
    return Objects.equals(this.apiId, apiRequest.apiId) &&
        Objects.equals(this.versions, apiRequest.versions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(apiId, versions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiRequest {\n");
    
    sb.append("    apiId: ").append(toIndentedString(apiId)).append("\n");
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

