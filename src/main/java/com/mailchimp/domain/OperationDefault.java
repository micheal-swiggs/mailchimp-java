package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OperationDefault
{
    @JsonProperty(value = JsonConstants.METHOD)
    private String method;

    @JsonProperty(value = JsonConstants.PATH)
    private String path;
}
