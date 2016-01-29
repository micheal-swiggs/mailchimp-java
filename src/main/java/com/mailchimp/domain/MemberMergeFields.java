package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MemberMergeFields
{
    @JsonProperty(value = JsonConstants.FIRST_NAME)
    private String firstName;

    @JsonProperty(value = JsonConstants.LAST_NAME)
    private String lastName;
}
