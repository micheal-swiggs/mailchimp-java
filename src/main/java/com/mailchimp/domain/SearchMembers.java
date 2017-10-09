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

    public ExactMatches getExactMatches() {
        return exactMatches;
    }

    public void setExactMatches(ExactMatches exactMatches) {
        this.exactMatches = exactMatches;
    }

    public FullSearch getFullSearch() {
        return fullSearch;
    }

    public void setFullSearch(FullSearch fullSearch) {
        this.fullSearch = fullSearch;
    }
}
