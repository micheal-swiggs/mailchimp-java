package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
/**
 * @author eamoralesl
 */
@Data
public class Segments {
    @JsonProperty("list_id")
    private String listId;
    @JsonProperty("segments")
    private List<Segment> segments = null;
    @JsonProperty("total_items")
    private Integer totalItems;

}
