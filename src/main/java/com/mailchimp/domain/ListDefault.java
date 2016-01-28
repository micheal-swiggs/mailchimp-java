package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author Micheal Swiggs
 */
@Data
class ListStats
{
    @JsonProperty(value = JsonConstants.MEMBER_COUNT)
    private Integer memberCount;
}

/**
 * @author Micheal Swiggs
 */
@Data
public class ListDefault
{
    @JsonProperty(value = JsonConstants.TOTAL_ITEMS)
    private Integer totalItems;

    @JsonProperty(value = JsonConstants.STATS)
    private ListStats stats;

    @JsonProperty(value = JsonConstants.ID)
    private String id;

    @JsonProperty(value = JsonConstants.NAME)
    private String name;

    @JsonProperty(value = JsonConstants.CONTACT)
    private Contact contact;

    @JsonProperty(value = JsonConstants.PERMISSION_REMINDER)
    private String permissionReminder;

    @JsonProperty(value = JsonConstants.CAMPAIGN_DETAILS)
    private CampaignDefaults campaignDefaults;

    @JsonProperty(value = JsonConstants.EMAIL_TYPE_OPTION)
    private Boolean emailTypeOption;
}
