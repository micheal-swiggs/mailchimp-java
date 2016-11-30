package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * @author Micheal Swiggs
 */
@Data
public class Members {

    @JsonProperty("members")
    private List<Member> members;

    @JsonProperty("list_id")
    private String listId;

    @JsonProperty("total_items")
    private Integer totalItems;
}
