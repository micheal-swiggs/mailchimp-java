package com.mailchimp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.util.List;

/**
 * @author Micheal Swiggs
 */
@Data
class ListStats
{
    @JsonProperty(value = JsonConstants.MEMBER_COUNT)
    private Integer memberCount;
}

/**
 * @author Micheal Swiggs
 */
@Data
public class ListDefault
{
    @JsonProperty(value = JsonConstants.TOTAL_ITEMS)
    private Integer totalItems;

    @JsonProperty(value = JsonConstants.STATS)
    private ListStats stats;
}
