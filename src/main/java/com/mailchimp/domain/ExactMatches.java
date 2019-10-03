package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Keisuke Kato
 */
@Data
public class ExactMatches {

    @JsonProperty("members")
    private List<Member> members;

    @JsonProperty("total_items")
    private Integer totalItems;

}
