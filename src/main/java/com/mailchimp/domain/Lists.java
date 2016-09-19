package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class Lists {

    @JsonProperty("lists")
    private List<com.mailchimp.domain.List> lists;

    @JsonProperty("total_items")
    private Integer totalItems;
}
