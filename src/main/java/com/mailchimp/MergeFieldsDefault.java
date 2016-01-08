package com.mailchimp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.util.List;

/**
 * @author Micheal Swiggs
 */
@Data
public class MergeFieldsDefault
{
    @JsonProperty(value = JsonConstants.TOTAL_ITEMS)
    private Integer totalItems;

    @JsonProperty(value = JsonConstants.MERGE_FIELDS)
    List<MergeField> mergeFields;

}
