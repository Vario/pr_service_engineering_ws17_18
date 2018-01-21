package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.bson.Document;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ChangeItem
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-18T11:43:31.507Z")

public class ChangeItem   {
  @JsonProperty("change")
  private String change = null;

  @JsonProperty("description")
  private String description = null;

  public ChangeItem change(String change) {
    this.change = change;
    return this;
  }

   /**
   * Get change
   * @return change
  **/
  @ApiModelProperty(example = "ADDED body.newpet", required = true, value = "")
  @NotNull


  public String getChange() {
    return change;
  }

  public void setChange(String change) {
    this.change = change;
  }

  public ChangeItem description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(example = "A field for new pets", value = "")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChangeItem changeItem = (ChangeItem) o;
    return Objects.equals(this.change, changeItem.change) &&
        Objects.equals(this.description, changeItem.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(change, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChangeItem {\n");
    
    sb.append("    change: ").append(toIndentedString(change)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

  public Document toBsonDocument(){
    return new Document()
            .append("change", this.change)
            .append("description", this.description);
  }
}

