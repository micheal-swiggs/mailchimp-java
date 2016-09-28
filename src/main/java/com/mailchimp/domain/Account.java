package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mailchimp.jackson.MailChimpZonedDateTimeDeserializer;
import java.time.ZonedDateTime;
import lombok.Getter;

public class Account {

    public static class IndustryStats {

        @JsonProperty("open_rate")
        @Getter
        private double openRate;

        @JsonProperty("bounce_rate")
        @Getter
        private double bounceRate;

        @JsonProperty("click_rate")
        @Getter
        private double clickRate;
    }

    @JsonProperty("account_id")
    @Getter
    private String accountId;

    @JsonProperty("account_name")
    @Getter
    private String accountName;

    @Getter
    private String email;

    @Getter
    private String username;

    @Getter
    private String role;

    @Getter
    private AccountContact contact;

    @JsonProperty("pro_enabled")
    @Getter
    private boolean proEnabled;

    @JsonProperty("last_login")
    @Getter
    @JsonDeserialize(using = MailChimpZonedDateTimeDeserializer.class)
    private ZonedDateTime lastLogin;

    @JsonProperty("total_subscribers")
    @Getter
    private int totalSubscribers;

    @JsonProperty("industry_stats")
    @Getter
    private IndustryStats industryStats;

}
