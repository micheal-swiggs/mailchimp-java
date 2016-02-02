package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MemberCreate<F extends MemberMergeFields>
{
    @JsonProperty(value = JsonConstants.MERGE_FIELDS)
    protected F memberMergeFields;

    @JsonProperty(value = JsonConstants.EMAIL, required = true)
    protected String email;

    @JsonProperty(value = JsonConstants.STATUS)
    protected SubscribeStatus subscribeStatus;
}
