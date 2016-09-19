package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * @author Micheal Swiggs
 */
@Data
public class MergeFieldsDefault {

    @JsonProperty(value = "total_items")
    private Integer totalItems;

    @JsonProperty(value = "merge_fields")
    private List<MergeField> mergeFields;
}
