package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mailchimp.jackson.MemberCreateSerializer;
import lombok.Data;

@Data
public class MemberCreateOperation extends OperationDefault
{
    @JsonProperty(value = JsonConstants.BODY)
    @JsonSerialize(using = MemberCreateSerializer.class)
    private MemberCreate body;
}
