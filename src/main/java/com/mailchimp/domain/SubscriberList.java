package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mailchimp.jackson.MailChimpZonedDateTimeDeserializer;
import java.time.ZonedDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * A MailChimp list is a powerful and flexible tool that helps you manage your contacts.
 *
 * @see <a href="http://developer.mailchimp.com/documentation/mailchimp/reference/lists/">http://developer.mailchimp.com/documentation/mailchimp/reference/lists/</a>
 * @author Micheal Swiggs
 * @author stevensnoeijen
 */
public class SubscriberList {

    public static enum Visibility {
        pub, prv
    }

    public class ListContact {

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

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getAddress1() {
            return address1;
        }

        public void setAddress1(String address1) {
            this.address1 = address1;
        }

        public String getAddress2() {
            return address2;
        }

        public void setAddress2(String address2) {
            this.address2 = address2;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

    /**
     * A string that uniquely identifies this list.
     */
    @Getter
    private String id;

    @JsonProperty("total_items")
    @Getter
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
    private ListContact contact = new ListContact();

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

    /**
     * The list’s <a href="http://kb.mailchimp.com/campaigns/ways-to-build/use-email-beamer-to-create-a-campaign?utm_source=mc-api&amp;utm_medium=docs&amp;utm_campaign=apidocs">Email Beamer</a> address.
     */
    @JsonProperty("beamer_address")
    @Getter
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
    private java.util.List<String> modules;

    /**
     * Stats for the list. Many of these are cached for at least five minutes.
     */
    @Getter
    private ListStats stats;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ListContact getContact() {
        return contact;
    }

    public void setContact(ListContact contact) {
        this.contact = contact;
    }

    public String getPermissionReminder() {
        return permissionReminder;
    }

    public void setPermissionReminder(String permissionReminder) {
        this.permissionReminder = permissionReminder;
    }

    public Boolean getUseArchiveBar() {
        return useArchiveBar;
    }

    public void setUseArchiveBar(Boolean useArchiveBar) {
        this.useArchiveBar = useArchiveBar;
    }

    public CampaignDefaults getCampaignDefaults() {
        return campaignDefaults;
    }

    public void setCampaignDefaults(CampaignDefaults campaignDefaults) {
        this.campaignDefaults = campaignDefaults;
    }

    public String getNotifyOnSubscribe() {
        return notifyOnSubscribe;
    }

    public void setNotifyOnSubscribe(String notifyOnSubscribe) {
        this.notifyOnSubscribe = notifyOnSubscribe;
    }

    public String getNotifyOnUnsubscribe() {
        return notifyOnUnsubscribe;
    }

    public void setNotifyOnUnsubscribe(String notifyOnUnsubscribe) {
        this.notifyOnUnsubscribe = notifyOnUnsubscribe;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getListRating() {
        return listRating;
    }

    public void setListRating(Integer listRating) {
        this.listRating = listRating;
    }

    public Boolean getEmailTypeOption() {
        return emailTypeOption;
    }

    public void setEmailTypeOption(Boolean emailTypeOption) {
        this.emailTypeOption = emailTypeOption;
    }

    public String getSubscribeUrlShort() {
        return subscribeUrlShort;
    }

    public void setSubscribeUrlShort(String subscribeUrlShort) {
        this.subscribeUrlShort = subscribeUrlShort;
    }

    public String getSubscribeUrlLong() {
        return subscribeUrlLong;
    }

    public void setSubscribeUrlLong(String subscribeUrlLong) {
        this.subscribeUrlLong = subscribeUrlLong;
    }

    public String getBeamerAddress() {
        return beamerAddress;
    }

    public void setBeamerAddress(String beamerAddress) {
        this.beamerAddress = beamerAddress;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public List<String> getModules() {
        return modules;
    }

    public void setModules(List<String> modules) {
        this.modules = modules;
    }

    public ListStats getStats() {
        return stats;
    }

    public void setStats(ListStats stats) {
        this.stats = stats;
    }
}
