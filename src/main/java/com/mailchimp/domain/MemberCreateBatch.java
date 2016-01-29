package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MemberCreateBatch
{
    @JsonProperty(value = JsonConstants.OPERATIONS)
    private List<MemberCreateOperation> operations;
}
