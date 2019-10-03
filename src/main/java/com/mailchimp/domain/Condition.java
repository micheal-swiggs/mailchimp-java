package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Condition {
    @JsonProperty("field")
    private String field;
    @JsonProperty("op")
    private String op;
    @JsonProperty("value")
    private String value;
}