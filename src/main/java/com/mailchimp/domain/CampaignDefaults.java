package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <a href="http://kb.mailchimp.com/campaigns/design/set-up-email-subject-from-name-and-from-email-address-on-a-campaign?utm_source=mc-api&amp;utm_medium=docs&amp;utm_campaign=apidocs">Default values for campaigns</a> created for this list.
 *
 * @see <a href="http://developer.mailchimp.com/documentation/mailchimp/reference/lists/">http://developer.mailchimp.com/documentation/mailchimp/reference/lists/#create-post_lists</a>
 * @author stevensnoeijen
 */
public class CampaignDefaults {

    /**
     * The default from name for campaigns sent to this list.
     */
    @JsonProperty("from_name")
    @Getter
    @Setter
    private String fromName;

    /**
     * The default from email for campaigns sent to this list.
     */
    @JsonProperty("from_email")
    @Getter
    @Setter
    private String fromEmail;

    /**
     * The default subject line for campaigns sent to this list.
     */
    @Getter
    @Setter
    private String subject;

    /**
     * The default language for this lists’s forms.
     */
    @Getter
    @Setter
    private String language;

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
