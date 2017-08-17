package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Keisuke Kato
 */
@Data
public class SearchMembers {

    @JsonProperty("exact_matches")
    private ExactMatches exactMatches;

    @JsonProperty("full_search")
    private FullSearch fullSearch;
}
