package de.zalando.zally.api.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import de.zalando.zally.api.models.Rule;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Supported Rules
 */
@ApiModel(description = "Supported Rules")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-05T20:37:30.578Z")

public class SupportedRulesResponse   {
    @JsonProperty("rules")
    private List<Rule> rules = new ArrayList<Rule>();

    public SupportedRulesResponse rules(List<Rule> rules) {
        this.rules = rules;
        return this;
    }

    public SupportedRulesResponse addSupportedRulesItem(Rule rulesItem) {
        this.rules.add(rulesItem);
        return this;
    }

    /**
     * Get rules
     * @return rules
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    @Valid

    public List<Rule> getSupportedRules() {
        return rules;
    }

    public void setSupportedRules(List<Rule> rules) {
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
        SupportedRulesResponse rulesResponse = (SupportedRulesResponse) o;
        return Objects.equals(this.rules, rulesResponse.rules);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rules);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SupportedRulesResponse {\n");

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


