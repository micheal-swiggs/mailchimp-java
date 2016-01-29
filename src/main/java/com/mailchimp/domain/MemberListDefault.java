package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.util.List;

/**
 * @author Micheal Swiggs
 */
@Data
public class MemberListDefault
{
    @JsonProperty(value = JsonConstants.MEMBERS)
    private List<MemberDefault> members;

    @JsonProperty(value = JsonConstants.LIST_ID)
    private String listId;

    @JsonProperty(value = JsonConstants.TOTAL_ITEMS)
    private Integer totalItems;
}
