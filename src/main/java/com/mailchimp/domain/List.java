package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Micheal Swiggs
 */
class ListStats {

    @JsonProperty("member_count")
    @Getter
    private Integer memberCount;
}

/**
 * @author Micheal Swiggs
 */
public class List {

    @JsonProperty("total_items")
    @Getter
    private Integer totalItems;

    @JsonProperty("stats")
    @Getter
    private ListStats stats;

    @JsonProperty("id")
    @Getter
    private String id;

    @JsonProperty("name")
    @Getter
    @Setter
    private String name;

    @JsonProperty("contact")
    @Getter
    private Contact contact;

    @JsonProperty("permission_reminder")

    private String permissionReminder;

    @JsonProperty("campaign_defaults")
    private CampaignDefaults campaignDefaults;

    @JsonProperty("email_type_option")
    private Boolean emailTypeOption;
}
