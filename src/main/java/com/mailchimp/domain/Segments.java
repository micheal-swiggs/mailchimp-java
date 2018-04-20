package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
/**
 * @author eamoralesl
 */

public class Segments {
    @JsonProperty("list_id")
    private String listId;
    @JsonProperty("segments")
    private List<Segment> segments = null;
    @JsonProperty("total_items")
    private Integer totalItems;

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }
}
