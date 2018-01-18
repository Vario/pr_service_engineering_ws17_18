package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.ChangeItem;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Change
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-18T11:43:31.507Z")

public class Change   {
  @JsonProperty("endpoint")
  private String endpoint = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("parameter")
  private List<ChangeItem> parameter = null;

  @JsonProperty("returntype")
  private List<ChangeItem> returntype = null;

  public Change endpoint(String endpoint) {
    this.endpoint = endpoint;
    return this;
  }

   /**
   * Get endpoint
   * @return endpoint
  **/
  @ApiModelProperty(example = "GET /pet", value = "")


  public String getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }

  public Change description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(example = "Find pets", value = "")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Change parameter(List<ChangeItem> parameter) {
    this.parameter = parameter;
    return this;
  }

  public Change addParameterItem(ChangeItem parameterItem) {
    if (this.parameter == null) {
      this.parameter = new ArrayList<ChangeItem>();
    }
    this.parameter.add(parameterItem);
    return this;
  }

   /**
   * Get parameter
   * @return parameter
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<ChangeItem> getParameter() {
    return parameter;
  }

  public void setParameter(List<ChangeItem> parameter) {
    this.parameter = parameter;
  }

  public Change returntype(List<ChangeItem> returntype) {
    this.returntype = returntype;
    return this;
  }

  public Change addReturntypeItem(ChangeItem returntypeItem) {
    if (this.returntype == null) {
      this.returntype = new ArrayList<ChangeItem>();
    }
    this.returntype.add(returntypeItem);
    return this;
  }

   /**
   * Get returntype
   * @return returntype
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<ChangeItem> getReturntype() {
    return returntype;
  }

  public void setReturntype(List<ChangeItem> returntype) {
    this.returntype = returntype;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Change change = (Change) o;
    return Objects.equals(this.endpoint, change.endpoint) &&
        Objects.equals(this.description, change.description) &&
        Objects.equals(this.parameter, change.parameter) &&
        Objects.equals(this.returntype, change.returntype);
  }

  @Override
  public int hashCode() {
    return Objects.hash(endpoint, description, parameter, returntype);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Change {\n");
    
    sb.append("    endpoint: ").append(toIndentedString(endpoint)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    parameter: ").append(toIndentedString(parameter)).append("\n");
    sb.append("    returntype: ").append(toIndentedString(returntype)).append("\n");
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

