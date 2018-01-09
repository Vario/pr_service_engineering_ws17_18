package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;

/**
 * Violation Object
 */
@ApiModel(description = "Violation Object")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-07T11:37:20.557Z")

public class Violation   {
  @JsonProperty("title")
  private String title = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("violation_type")
  private String violationType = null;

  @JsonProperty("rule_link")
  private String ruleLink = null;

  @JsonProperty("paths")
  private List<String> paths = null;

  public Violation title(String title) {
    this.title = title;
    return this;
  }

   /**
   * Get title
   * @return title
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Violation description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Violation violationType(String violationType) {
    this.violationType = violationType;
    return this;
  }

   /**
   * Get violationType
   * @return violationType
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getViolationType() {
    return violationType;
  }

  public void setViolationType(String violationType) {
    this.violationType = violationType;
  }

  public Violation ruleLink(String ruleLink) {
    this.ruleLink = ruleLink;
    return this;
  }

   /**
   * Get ruleLink
   * @return ruleLink
  **/
  @ApiModelProperty(value = "")


  public String getRuleLink() {
    return ruleLink;
  }

  public void setRuleLink(String ruleLink) {
    this.ruleLink = ruleLink;
  }

  public Violation paths(List<String> paths) {
    this.paths = paths;
    return this;
  }

  public Violation addPathsItem(String pathsItem) {
    if (this.paths == null) {
      this.paths = new ArrayList<String>();
    }
    this.paths.add(pathsItem);
    return this;
  }

   /**
   * Get paths
   * @return paths
  **/
  @ApiModelProperty(value = "")


  public List<String> getPaths() {
    return paths;
  }

  public void setPaths(List<String> paths) {
    this.paths = paths;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Violation violation = (Violation) o;
    return Objects.equals(this.title, violation.title) &&
        Objects.equals(this.description, violation.description) &&
        Objects.equals(this.violationType, violation.violationType) &&
        Objects.equals(this.ruleLink, violation.ruleLink) &&
        Objects.equals(this.paths, violation.paths);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, description, violationType, ruleLink, paths);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Violation {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    violationType: ").append(toIndentedString(violationType)).append("\n");
    sb.append("    ruleLink: ").append(toIndentedString(ruleLink)).append("\n");
    sb.append("    paths: ").append(toIndentedString(paths)).append("\n");
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

