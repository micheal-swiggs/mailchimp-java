package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
/**
 * @author eamoralesl
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Options {
    @JsonProperty("conditions")
    private List<Condition> conditions = null;

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }
}