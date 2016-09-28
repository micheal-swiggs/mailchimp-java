package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mailchimp.jackson.MailChimpZonedDateTimeDeserializer;
import java.time.ZonedDateTime;
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
 * @author stevensnoeijen
 */
public class List {

    @Getter
    private String id;

    @JsonProperty("total_items")
    @Getter
    private Integer totalItems;

    @Getter
    private ListStats stats;

    @Getter
    @Setter
    private String name;

    @JsonProperty("contact")
    @Getter
    private ListContact contact = new ListContact();

    @JsonProperty("permission_reminder")
    @Getter
    @Setter
    private String permissionReminder;

    @JsonProperty("use_archive_bar")
    @Getter
    @Setter
    private Boolean useArchiveBar;

    @JsonProperty("campaign_defaults")
    @Getter
    private CampaignDefaults campaignDefaults = new CampaignDefaults();

    /**
     * The email address to send <a href="http://kb.mailchimp.com/lists/managing-subscribers/change-subscribe-and-unsubscribe-notifications?utm_source=mc-api&amp;utm_medium=docs&amp;utm_campaign=apidocs">subscribe notifications</a> to.
     */
    @JsonProperty("notify_on_subscribe")
    @Getter
    @Setter
    private String notifyOnSubscribe;

    /**
     * The email address to send <a href="http://kb.mailchimp.com/lists/managing-subscribers/change-subscribe-and-unsubscribe-notifications?utm_source=mc-api&amp;utm_medium=docs&amp;utm_campaign=apidocs">unsubscribe notifications</a> to.
     */
    @JsonProperty("notify_on_unsubscribe")
    @Getter
    @Setter
    private String notifyOnUnsubscribe;

    @JsonProperty("date_created")
    @Getter
    @JsonDeserialize(using = MailChimpZonedDateTimeDeserializer.class)
    private ZonedDateTime dateCreated;

    @JsonProperty("email_type_option")
    @Getter
    private Boolean emailTypeOption;
}
