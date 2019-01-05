package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mailchimp.jackson.JsonStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Operation<T> {

    @JsonProperty
    private String method;

    @JsonProperty
    private String path;

    @JsonProperty
    @JsonSerialize(using = JsonStringSerializer.class)
    private T body;
}
