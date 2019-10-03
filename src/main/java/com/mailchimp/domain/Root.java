package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mailchimp.domain.Report.IndustryStats;
import com.mailchimp.jackson.MailChimpZonedDateTimeDeserializer;
import java.time.ZonedDateTime;
import lombok.Getter;

/**
 * The API root resource links to all other resources available in the API.
 * Calling the root directory also returns details about the MailChimp user account.
 *
 * @see <a href="http://developer.mailchimp.com/documentation/mailchimp/reference/root/">http://developer.mailchimp.com/documentation/mailchimp/reference/root/</a>
 * @author stevensnoeijen
 */
public class Root {

    /**
     * The MailChimp account id, used for features like list <a href="http://kb.mailchimp.com/lists/signup-forms/create-signup-forms-and-response-emails?utm_source=mc-api&amp;utm_medium=docs&amp;utm_campaign=apidocs">subscribe forms</a>.
     */
    @JsonProperty("account_id")
    @Getter
    private String accountId;

    /**
     * The login name for the MailChimp account.
     */
    @JsonProperty("account_name")
    @Getter
    private String accountName;

    /**
     * The account email address.
     */
    @Getter
    private String email;

    /**
     * The username tied to the account.
     */
    @Getter
    private String username;

    /**
     * The <a href="http://kb.mailchimp.com/accounts/multi-user/manage-user-levels-in-your-account?utm_source=mc-api&amp;utm_medium=docs&amp;utm_campaign=apidocs">user role</a> for the account.
     */
    @Getter
    private String role;

    /**
     * Information about the account contact.
     */
    @Getter
    private AccountContact contact;

    /**
     * Whether the account includes <a href="http://mailchimp.com/pro?utm_source=mc-api&amp;utm_medium=docs&amp;utm_campaign=apidocs">MailChimp Pro</a>.
     */
    @JsonProperty("pro_enabled")
    @Getter
    private boolean proEnabled;

    /**
     * The date and time of the last login for this account.
     */
    @JsonProperty("last_login")
    @Getter
    @JsonDeserialize(using = MailChimpZonedDateTimeDeserializer.class)
    private ZonedDateTime lastLogin;

    /**
     * The total number of subscribers across all lists in the account.
     */
    @JsonProperty("total_subscribers")
    @Getter
    private int totalSubscribers;

    /**
     * The <a href="http://mailchimp.com/resources/research/email-marketing-benchmarks/?utm_source=mc-api&amp;utm_medium=docs&amp;utm_campaign=apidocs">average campaign statistics</a> for all campaigns in the account’s specified industry.
     */
    @JsonProperty("industry_stats")
    @Getter
    private IndustryStats industryStats;

}
