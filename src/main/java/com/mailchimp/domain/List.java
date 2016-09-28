package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mailchimp.jackson.MailChimpZonedDateTimeDeserializer;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Micheal Swiggs
 * @author stevensnoeijen
 */
public class List {

    public static enum ListVisibility {
        pub, prv
    }

    @Getter
    private String id;

    @JsonProperty("total_items")
    @Getter
    private Integer totalItems;

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

    /**
     * An auto-generated activity score for the list (0-5).
     */
    @JsonProperty("list_rating")
    @Getter
    private Integer listRating;

    /**
     * Whether the list supports <a href="http://kb.mailchimp.com/lists/growth/how-to-change-list-name-and-defaults?utm_source=mc-api&amp;utm_medium=docs&amp;utm_campaign=apidocs#Change-Subscription-Settings">multiple formats for emails</a>.
     * When set to <code>true</code>, subscribers can choose whether they want to receive HTML or plain-text emails.
     * When set to <code>false</code>, subscribers will receive HTML emails, with a plain-text alternative backup.
     */
    @JsonProperty("email_type_option")
    @Getter
    @Setter
    private Boolean emailTypeOption;

    /**
     * Our <a href="http://kb.mailchimp.com/lists/signup-forms/share-your-signup-form?utm_source=mc-api&amp;utm_medium=docs&amp;utm_campaign=apidocs#Signup-Form-URL">EepURL shortened</a> version of this list’s subscribe form.
     */
    @JsonProperty("subscribe_url_short")
    @Getter
    private String subscribeUrlShort;

    /**
     * The full version of this list’s subscribe form (host will vary).
     */
    @JsonProperty("subscribe_url_long")
    @Getter
    private String subscribeUrlLong;

    @JsonProperty("beamer_address")
    @Getter
    private String beamerAddress;

    /**
     * Whether this list is <a href="http://kb.mailchimp.com/lists/growth/about-publicity-settings?utm_source=mc-api&amp;utm_medium=docs&amp;utm_campaign=apidocs&amp;_ga=1.130765839.1491637616.1466152007">public or private</a>
     */
    @Getter
    @Setter
    private ListVisibility visibility;

    /**
     * Any list-specific modules installed for this list.
     */
    @Getter
    private java.util.List<String> modules;

    @Getter
    private ListStats stats;
}
