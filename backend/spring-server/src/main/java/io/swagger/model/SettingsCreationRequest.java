package io.swagger.model;

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
 * SettingsCreationRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-06T16:52:57.027Z")

public class SettingsCreationRequest   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("rules")
  private List<String> rules = null;

  public SettingsCreationRequest name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "JKU Students API Guidelines", value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public SettingsCreationRequest rules(List<String> rules) {
    this.rules = rules;
    return this;
  }

  public SettingsCreationRequest addRulesItem(String rulesItem) {
    if (this.rules == null) {
      this.rules = new ArrayList<String>();
    }
    this.rules.add(rulesItem);
    return this;
  }

   /**
   * Get rules
   * @return rules
  **/
  @ApiModelProperty(value = "")


  public List<String> getRules() {
    return rules;
  }

  public void setRules(List<String> rules) {
    this.rules = rules;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SettingsCreationRequest settingsCreationRequest = (SettingsCreationRequest) o;
    return Objects.equals(this.name, settingsCreationRequest.name) &&
        Objects.equals(this.rules, settingsCreationRequest.rules);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, rules);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SettingsCreationRequest {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    rules: ").append(toIndentedString(rules)).append("\n");
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

