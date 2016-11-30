package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * @author Micheal Swiggs
 */
@Data
public class ListMergeFields {

    @JsonProperty("total_items")
    private Integer totalItems;

    @JsonProperty("merge_fields")
    private List<ListMergeField> mergeFields;
}
