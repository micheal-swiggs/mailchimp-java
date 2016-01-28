package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MemberCreate
{
    @JsonProperty(value = JsonConstants.MERGE_FIELDS)
    protected MemberMergeFields memberMergeFields;

    @JsonProperty(value = JsonConstants.EMAIL, required = true)
    protected String email;

    @JsonProperty(value = JsonConstants.STATUS)
    protected SubscribeStatus subscribeStatus;
}
