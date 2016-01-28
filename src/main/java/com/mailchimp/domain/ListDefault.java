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

    public Integer getMemberCount()
    {
        return memberCount;
    }
}

/**
 * @author Micheal Swiggs
 */
@Data
public class ListDefault
{

    @JsonProperty(value = JsonConstants.TOTAL_ITEMS)
    private Integer totalItems;

    public Integer getTotalItems()
    {
        return totalItems;
    }

    @JsonProperty(value = JsonConstants.STATS)
    private ListStats stats;

    public ListStats getStats()
    {
        return stats;
    }

    @JsonProperty(value = JsonConstants.ID)
    private String id;

    public String getId()
    {
        return id;
    }

    @JsonProperty(value = JsonConstants.NAME)
    private String name;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @JsonProperty(value = JsonConstants.CONTACT)
    private Contact contact;

    public Contact getContact()
    {
        return contact;
    }

    public void setContact(Contact contact)
    {
        this.contact = contact;
    }

    @JsonProperty(value = JsonConstants.PERMISSION_REMINDER)
    private String permissionReminder;

    public String getPermissionReminder()
    {
        return permissionReminder;
    }

    public void setPermissionReminder(String permissionReminder)
    {
        this.permissionReminder = permissionReminder;
    }

    @JsonProperty(value = JsonConstants.CAMPAIGN_DETAILS)
    private CampaignDefaults campainDetails;

    public CampaignDefaults getCampainDetails()
    {
        return campainDetails;
    }

    public void setCampainDetails(CampaignDefaults campainDetails)
    {
        this.campainDetails = campainDetails;
    }

    @JsonProperty(value = JsonConstants.EMAIL_TYPE_OPTION)
    private Boolean emailTypeOption;

    public Boolean getEmailTypeOption()
    {
        return emailTypeOption;
    }

    public void setEmailTypeOption(Boolean emailTypeOption)
    {
        this.emailTypeOption = emailTypeOption;
    }
}
