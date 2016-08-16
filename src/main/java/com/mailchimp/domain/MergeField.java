package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Micheal Swiggs
 */
@Data
public class MergeField
{
    private MergeField()
    {
    }

    public MergeField(String name, MergeType type, String tag, Boolean required)
    {
        this.name = name;
        this.type = type;
        this.tag = tag;
        this.required = required;
    }

    @JsonProperty(value = JsonConstants.NAME)
    private String name;

    @JsonProperty(value = JsonConstants.TYPE)
    private MergeType type;

    @JsonProperty(value = JsonConstants.TAG)
    private String tag;

    @JsonProperty(value = JsonConstants.REQUIRED)
    private Boolean required;
}
