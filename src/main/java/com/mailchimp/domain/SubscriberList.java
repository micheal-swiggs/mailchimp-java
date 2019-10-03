package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mailchimp.jackson.MailChimpZonedDateTimeDeserializer;
import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A MailChimp list is a powerful and flexible tool that helps you manage your contacts.
 *
 * @see <a href="http://developer.mailchimp.com/documentation/mailchimp/reference/lists/">http://developer.mailchimp.com/documentation/mailchimp/reference/lists/</a>
 * @author Micheal Swiggs
 * @author stevensnoeijen
 */
public class SubscriberList {

    public enum Visibility {
        pub, prv
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Contact {

        /**
         * The company name for the list.
         */
        @Getter
        @Setter
        private String company;

        /**
         * The street address for the list contact.
         */
        @Getter
        @Setter
        private String address1;

        /**
         * The street address for the list contact.
         */
        @Getter
        @Setter
        private String address2;

        /**
         * The city for the list contact.
         */
        @Getter
        @Setter
        private String city;

        /**
         * The state for the list contact.
         */
        @Getter
        @Setter
        private String state;

        /**
         * The postal or zip code for the list contact.
         */
        @Getter
        @Setter
        private String zip;

        /**
         * A two-character ISO3166 country code. Defaults to US if invalid.
         */
        @Getter
        @Setter
        private String country;

        /**
         * The phone number for the list contact.
         */
        @Getter
        @Setter
        private String phone;
    }

    /**
     * A string that uniquely identifies this list.
     */
    @Getter
    @Setter
    private String id;

    @JsonProperty("total_items")
    @Getter
    @Setter
    private Integer totalItems;

    /**
     * The name of the list.
     */
    @Getter
    @Setter
    private String name;

    /**
     * <a href="http://kb.mailchimp.com/lists/growth/about-the-required-email-footer-content?utm_source=mc-api&amp;utm_medium=docs&amp;utm_campaign=apidocs">Contact information displayed in campaign footers</a> to comply with international spam laws.
     */
    @JsonProperty("contact")
    @Getter
    @Setter
    private Contact contact = new Contact();

    /**
     * The <a href="http://kb.mailchimp.com/accounts/compliance-tips/edit-the-permission-reminder?utm_source=mc-api&amp;utm_medium=docs&amp;utm_campaign=apidocs">permission reminder</a> for the list.
     */
    @JsonProperty("permission_reminder")
    @Getter
    @Setter
    private String permissionReminder;

    /**
     * Whether campaigns for this list use the <a href="http://kb.mailchimp.com/campaigns/archives/about-the-archive-bar?utm_source=mc-api&amp;utm_medium=docs&amp;utm_campaign=apidocs">Archive Bar</a> in archives by default.
     */
    @JsonProperty("use_archive_bar")
    @Getter
    @Setter
    private Boolean useArchiveBar;

    /**
     * <a href="http://kb.mailchimp.com/campaigns/design/set-up-email-subject-from-name-and-from-email-address-on-a-campaign?utm_source=mc-api&amp;utm_medium=docs&amp;utm_campaign=apidocs">Default values for campaigns</a> created for this list.
     */
    @JsonProperty("campaign_defaults")
    @Getter
    @Setter
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

    /**
     * The date and time that this list was created.
     */
    @JsonProperty("date_created")
    @Getter
    @Setter
    @JsonDeserialize(using = MailChimpZonedDateTimeDeserializer.class)
    private ZonedDateTime dateCreated;

    /**
     * An auto-generated activity score for the list (0-5).
     */
    @JsonProperty("list_rating")
    @Getter
    @Setter
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
    @Setter
    private String subscribeUrlShort;

    /**
     * The full version of this list’s subscribe form (host will vary).
     */
    @JsonProperty("subscribe_url_long")
    @Getter
    @Setter
    private String subscribeUrlLong;

    /**
     * The list’s <a href="http://kb.mailchimp.com/campaigns/ways-to-build/use-email-beamer-to-create-a-campaign?utm_source=mc-api&amp;utm_medium=docs&amp;utm_campaign=apidocs">Email Beamer</a> address.
     */
    @JsonProperty("beamer_address")
    @Getter
    @Setter
    private String beamerAddress;

    /**
     * Whether this list is <a href="http://kb.mailchimp.com/lists/growth/about-publicity-settings?utm_source=mc-api&amp;utm_medium=docs&amp;utm_campaign=apidocs&amp;_ga=1.130765839.1491637616.1466152007">public or private</a>
     */
    @Getter
    @Setter
    private Visibility visibility;

    /**
     * Any list-specific modules installed for this list.
     */
    @Getter
    @Setter
    private java.util.List<String> modules;

    /**
     * Stats for the list. Many of these are cached for at least five minutes.
     */
    @Getter
    @Setter
    private ListStats stats;
}
