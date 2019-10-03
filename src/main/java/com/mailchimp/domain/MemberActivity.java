package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mailchimp.jackson.MailChimpZonedDateTimeDeserializer;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class MemberActivity {

    @JsonProperty("activity")
    private List<Activity> activities;

    @Getter
    public static class Activity {

        @JsonProperty("action")
        private Action action;

        @JsonProperty("timestamp")
        @JsonDeserialize(using = MailChimpZonedDateTimeDeserializer.class)
        private ZonedDateTime timestamp;

        @JsonProperty("url")
        private String url;

        @JsonProperty("type")
        private String type;

        @JsonProperty("campaign_id")
        private String campaignId;

        @JsonProperty("title")
        private String title;

        @JsonProperty("parent_campaign")
        private String parentCampaign;

        public static enum Action {
            sent,
            open,
            click,
            unsub;
        }
    }
}


