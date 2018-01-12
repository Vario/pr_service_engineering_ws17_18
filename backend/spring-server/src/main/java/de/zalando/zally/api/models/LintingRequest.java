package de.zalando.zally.api.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Request containing a swagger definition file
 */
@ApiModel(description = "Request containing a swagger definition file")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-07T11:37:20.557Z")

public class LintingRequest   {
  @JsonProperty("api_definition")
  private Object apiDefinition = null;

  @JsonProperty("api_definition_url")
  private String apiDefinitionUrl = null;

  @JsonProperty("ignore_rules")
  private List<String> ignoreRules = null;

  public LintingRequest apiDefinition(Object apiDefinition) {
    this.apiDefinition = apiDefinition;
    return this;
  }

   /**
   * Get apiDefinition
   * @return apiDefinition
  **/
  @ApiModelProperty(value = "")


  public Object getApiDefinition() {
    return apiDefinition;
  }

  public void setApiDefinition(Object apiDefinition) {
    this.apiDefinition = apiDefinition;
  }

  public LintingRequest apiDefinitionUrl(String apiDefinitionUrl) {
    this.apiDefinitionUrl = apiDefinitionUrl;
    return this;
  }

   /**
   * Get apiDefinitionUrl
   * @return apiDefinitionUrl
  **/
  @ApiModelProperty(value = "")


  public String getApiDefinitionUrl() {
    return apiDefinitionUrl;
  }

  public void setApiDefinitionUrl(String apiDefinitionUrl) {
    this.apiDefinitionUrl = apiDefinitionUrl;
  }

  public LintingRequest ignoreRules(List<String> ignoreRules) {
    this.ignoreRules = ignoreRules;
    return this;
  }

  public LintingRequest addIgnoreRulesItem(String ignoreRulesItem) {
    if (this.ignoreRules == null) {
      this.ignoreRules = new ArrayList<String>();
    }
    this.ignoreRules.add(ignoreRulesItem);
    return this;
  }

   /**
   * Get ignoreRules
   * @return ignoreRules
  **/
  @ApiModelProperty(value = "")


  public List<String> getIgnoreRules() {
    return ignoreRules;
  }

  public void setIgnoreRules(List<String> ignoreRules) {
    this.ignoreRules = ignoreRules;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LintingRequest lintingRequest = (LintingRequest) o;
    return Objects.equals(this.apiDefinition, lintingRequest.apiDefinition) &&
        Objects.equals(this.apiDefinitionUrl, lintingRequest.apiDefinitionUrl) &&
        Objects.equals(this.ignoreRules, lintingRequest.ignoreRules);
  }

  @Override
  public int hashCode() {
    return Objects.hash(apiDefinition, apiDefinitionUrl, ignoreRules);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LintingRequest {\n");
    
    sb.append("    apiDefinition: ").append(toIndentedString(apiDefinition)).append("\n");
    sb.append("    apiDefinitionUrl: ").append(toIndentedString(apiDefinitionUrl)).append("\n");
    sb.append("    ignoreRules: ").append(toIndentedString(ignoreRules)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

