package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OperationDefault
{
    @JsonProperty(value = JsonConstants.METHOD)
    private String method;

    public void setMethod(String method)
    {
        this.method = method;
    }

    @JsonProperty(value = JsonConstants.PATH)
    private String path;

    public void setPath(String path)
    {
        this.path = path;
    }
}
