package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Micheal Swiggs
 */
@Data
public class ListMergeField {

    @JsonProperty
    private String tag;

    @JsonProperty
    private String name;

    @JsonProperty
    private Boolean required;

    @JsonProperty
    private MergeType type;
}
