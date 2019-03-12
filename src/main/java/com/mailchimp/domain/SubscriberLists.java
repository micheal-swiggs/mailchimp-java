package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SubscriberLists {

    @JsonProperty("lists")
    private List<SubscriberList> subscriberLists;

    @JsonProperty("total_items")
    private Integer totalItems;

    public List<SubscriberList> getSubscriberLists() {
        return subscriberLists;
    }

    public void setSubscriberLists(List<SubscriberList> subscriberLists) {
        this.subscriberLists = subscriberLists;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }
}
