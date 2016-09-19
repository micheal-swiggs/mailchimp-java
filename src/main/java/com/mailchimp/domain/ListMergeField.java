package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Micheal Swiggs
 */
@Data
public class ListMergeField {

    private ListMergeField() {
    }

    @JsonProperty
    private String name;

    @JsonProperty
    private MergeType type;

    @JsonProperty
    private String tag;

    @JsonProperty
    private Boolean required;
}
