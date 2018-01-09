package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;

/**
 * Violation Count
 */
@ApiModel(description = "Violation Count")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-07T11:37:20.557Z")

public class ViolationsCount   {
  @JsonProperty("must")
  private Integer must = null;

  @JsonProperty("should")
  private Integer should = null;

  @JsonProperty("may")
  private Integer may = null;

  @JsonProperty("could")
  private Integer could = null;

  @JsonProperty("hint")
  private Integer hint = null;

  public ViolationsCount must(Integer must) {
    this.must = must;
    return this;
  }

   /**
   * Get must
   * @return must
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getMust() {
    return must;
  }

  public void setMust(Integer must) {
    this.must = must;
  }

  public ViolationsCount should(Integer should) {
    this.should = should;
    return this;
  }

   /**
   * Get should
   * @return should
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getShould() {
    return should;
  }

  public void setShould(Integer should) {
    this.should = should;
  }

  public ViolationsCount may(Integer may) {
    this.may = may;
    return this;
  }

   /**
   * Get may
   * @return may
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getMay() {
    return may;
  }

  public void setMay(Integer may) {
    this.may = may;
  }

  public ViolationsCount could(Integer could) {
    this.could = could;
    return this;
  }

   /**
   * Get could
   * @return could
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getCould() {
    return could;
  }

  public void setCould(Integer could) {
    this.could = could;
  }

  public ViolationsCount hint(Integer hint) {
    this.hint = hint;
    return this;
  }

   /**
   * Get hint
   * @return hint
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getHint() {
    return hint;
  }

  public void setHint(Integer hint) {
    this.hint = hint;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ViolationsCount violationsCount = (ViolationsCount) o;
    return Objects.equals(this.must, violationsCount.must) &&
        Objects.equals(this.should, violationsCount.should) &&
        Objects.equals(this.may, violationsCount.may) &&
        Objects.equals(this.could, violationsCount.could) &&
        Objects.equals(this.hint, violationsCount.hint);
  }

  @Override
  public int hashCode() {
    return Objects.hash(must, should, may, could, hint);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ViolationsCount {\n");
    
    sb.append("    must: ").append(toIndentedString(must)).append("\n");
    sb.append("    should: ").append(toIndentedString(should)).append("\n");
    sb.append("    may: ").append(toIndentedString(may)).append("\n");
    sb.append("    could: ").append(toIndentedString(could)).append("\n");
    sb.append("    hint: ").append(toIndentedString(hint)).append("\n");
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

