package at.jku.se.pr.rest.qualityapi.models;


import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SwaggerDiffRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-25T17:13:08.538Z")

public class SwaggerDiffRequest   {
    @JsonProperty("oldSpec")
    private String oldSpec = null;

    @JsonProperty("newSpec")
    private String newSpec = null;

    public SwaggerDiffRequest oldSpec(String oldSpec) {
        this.oldSpec = oldSpec;
        return this;
    }

    /**
     * Get oldSpec
     * @return oldSpec
     **/
    @ApiModelProperty(value = "")


    public String getOldSpec() {
        return oldSpec;
    }

    public void setOldSpec(String oldSpec) {
        this.oldSpec = oldSpec;
    }

    public SwaggerDiffRequest newSpec(String newSpec) {
        this.newSpec = newSpec;
        return this;
    }

    /**
     * Get newSpec
     * @return newSpec
     **/
    @ApiModelProperty(value = "")


    public String getNewSpec() {
        return newSpec;
    }

    public void setNewSpec(String newSpec) {
        this.newSpec = newSpec;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SwaggerDiffRequest swaggerDiffRequest = (SwaggerDiffRequest) o;
        return Objects.equals(this.oldSpec, swaggerDiffRequest.oldSpec) &&
                Objects.equals(this.newSpec, swaggerDiffRequest.newSpec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oldSpec, newSpec);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SwaggerDiffRequest {\n");

        sb.append("    oldSpec: ").append(toIndentedString(oldSpec)).append("\n");
        sb.append("    newSpec: ").append(toIndentedString(newSpec)).append("\n");
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
