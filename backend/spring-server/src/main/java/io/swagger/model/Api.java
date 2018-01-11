package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.VersionRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Api
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-11T20:20:53.474Z")

public class Api   {
  @JsonProperty("title")
  private String title = null;

  @JsonProperty("api-id")
  private UUID apiId = null;

  @JsonProperty("settings-id")
  private UUID settingsId = null;

  @JsonProperty("versions")
  private List<VersionRequest> versions = null;

  public Api title(String title) {
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

  public Api apiId(UUID apiId) {
    this.apiId = apiId;
    return this;
  }

   /**
   * Get apiId
   * @return apiId
  **/
  @ApiModelProperty(example = "3efc7632-e833-40cd-b002-abdfaf426e6c", value = "")

  @Valid

  public UUID getApiId() {
    return apiId;
  }

  public void setApiId(UUID apiId) {
    this.apiId = apiId;
  }

  public Api settingsId(UUID settingsId) {
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

  public Api versions(List<VersionRequest> versions) {
    this.versions = versions;
    return this;
  }

  public Api addVersionsItem(VersionRequest versionsItem) {
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
    Api api = (Api) o;
    return Objects.equals(this.title, api.title) &&
        Objects.equals(this.apiId, api.apiId) &&
        Objects.equals(this.settingsId, api.settingsId) &&
        Objects.equals(this.versions, api.versions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, apiId, settingsId, versions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Api {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    apiId: ").append(toIndentedString(apiId)).append("\n");
    sb.append("    settingsId: ").append(toIndentedString(settingsId)).append("\n");
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

