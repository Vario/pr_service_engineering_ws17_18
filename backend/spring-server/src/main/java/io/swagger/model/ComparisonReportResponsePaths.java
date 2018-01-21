package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Change;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ComparisonReportResponsePaths
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-18T11:43:31.507Z")

public class ComparisonReportResponsePaths   {
  @JsonProperty("new")
  private List<Change> _new = null;

  @JsonProperty("removed")
  private List<Change> removed = null;

  @JsonProperty("changed")
  private List<Change> changed = null;

  public ComparisonReportResponsePaths _new(List<Change> _new) {
    this._new = _new;
    return this;
  }

  public ComparisonReportResponsePaths addNewItem(Change _newItem) {
    if (this._new == null) {
      this._new = new ArrayList<Change>();
    }
    this._new.add(_newItem);
    return this;
  }

   /**
   * Get _new
   * @return _new
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Change> getNew() {
    return _new;
  }

  public void setNew(List<Change> _new) {
    this._new = _new;
  }

  public ComparisonReportResponsePaths removed(List<Change> removed) {
    this.removed = removed;
    return this;
  }

  public ComparisonReportResponsePaths addRemovedItem(Change removedItem) {
    if (this.removed == null) {
      this.removed = new ArrayList<Change>();
    }
    this.removed.add(removedItem);
    return this;
  }

   /**
   * Get removed
   * @return removed
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Change> getRemoved() {
    return removed;
  }

  public void setRemoved(List<Change> removed) {
    this.removed = removed;
  }

  public ComparisonReportResponsePaths changed(List<Change> changed) {
    this.changed = changed;
    return this;
  }

  public ComparisonReportResponsePaths addChangedItem(Change changedItem) {
    if (this.changed == null) {
      this.changed = new ArrayList<Change>();
    }
    this.changed.add(changedItem);
    return this;
  }

   /**
   * Get changed
   * @return changed
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Change> getChanged() {
    return changed;
  }

  public void setChanged(List<Change> changed) {
    this.changed = changed;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ComparisonReportResponsePaths comparisonReportResponsePaths = (ComparisonReportResponsePaths) o;
    return Objects.equals(this._new, comparisonReportResponsePaths._new) &&
        Objects.equals(this.removed, comparisonReportResponsePaths.removed) &&
        Objects.equals(this.changed, comparisonReportResponsePaths.changed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(_new, removed, changed);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ComparisonReportResponsePaths {\n");
    
    sb.append("    _new: ").append(toIndentedString(_new)).append("\n");
    sb.append("    removed: ").append(toIndentedString(removed)).append("\n");
    sb.append("    changed: ").append(toIndentedString(changed)).append("\n");
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
    Document d = new Document();

    /* new */
    List<Document> dNew = new ArrayList<>();
    for (Change c : this._new){
      dNew.add(c.toBsonDocument());
    }
    d.append("new", dNew);

    /* Changed */
    List<Document> dChanged = new ArrayList<>();
    for (Change c : this.changed){
      dChanged.add(c.toBsonDocument());
    }
    d.append("changed", dChanged);

    /* Removed */
    List<Document> dRemoved = new ArrayList<>();
    for (Change c : this.removed){
      dRemoved.add(c.toBsonDocument());
    }
    d.append("removed", dRemoved);

    return d;
  }

}

