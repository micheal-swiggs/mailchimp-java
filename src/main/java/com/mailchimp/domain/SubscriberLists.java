package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SubscriberLists {

    @JsonProperty("lists")
    private List<SubscriberList> lists;

    @JsonProperty("total_items")
    private Integer totalItems;

    public List<SubscriberList> getLists() {
        return lists;
    }

    public void setLists(List<SubscriberList> lists) {
        this.lists = lists;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }
}
