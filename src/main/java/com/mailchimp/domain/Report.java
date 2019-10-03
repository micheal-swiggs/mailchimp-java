package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mailchimp.domain.Link.Rel;
import com.mailchimp.jackson.MailChimpZonedDateTimeDeserializer;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.Getter;

/**
 * @author YukiFujisawa
 * @see <a href="https://us20.api.mailchimp.com/schema/3.0/Definitions/Reports/Response.json">/schema/3.0/Definitions/Reports/Response.json</a>
 */
@Getter
@JsonIgnoreProperties(ignoreUnknown = true) //TODO: need implementation.
public class Report {

    @JsonProperty("id")
    private String id;

    @JsonProperty("campaign_title")
    private String campaignTitle;

    @JsonProperty("type")
    private String type;

    @JsonProperty("list_id")
    private String listId;

    @JsonProperty("list_is_active")
    private boolean listIsActive;

    @JsonProperty("list_name")
    private String listName;

    @JsonProperty("subject_line")
    private String subjectLine;

    @JsonProperty("preview_text")
    private String previewText;

    @JsonProperty("emails_sent")
    private Integer emailsSent;

    @JsonProperty("abuse_reports")
    private Integer abuseReports;

    @JsonProperty("unsubscribed")
    private Integer unsubscribed;

    @JsonProperty("send_time")
    private String sendTime;

    @JsonDeserialize(using = MailChimpZonedDateTimeDeserializer.class)
    @JsonProperty("rss_last_send")
    private ZonedDateTime rssLastSend;

    @JsonProperty("bounces")
    private Bounces bounces;

    @JsonProperty("forwards")
    private Forward forward;

    @JsonProperty("opens")
    private Open open;

    @JsonProperty("clicks")
    private Click click;

    @JsonProperty("facebook_likes")
    private FacebookLike facebookLike;

    @JsonProperty("industry_stats")
    private IndustryStats industryStats;

    @JsonProperty("list_stats")
    private ListStats listStats;

    @JsonProperty("_links")
    private List<Link> links;

    @JsonIgnore
    public Link getClickDetailsLink() {
        return Link.getLink(links, Rel.CLICK_DETAILS);
    }

    @JsonIgnore
    public Link getCampaign() {
        return Link.getLink(links, Rel.CAMPAIGN);
    }

    /**
     * @author YukiFujisawa
     */
    @Getter
    public static class Bounces {

        @JsonProperty("hard_bounces")
        private Integer hardBounces;

        @JsonProperty("soft_bounces")
        private Integer softBounces;

        @JsonProperty("syntax_errors")
        private Integer syntaxErrors;
    }

    @Getter
    public static class Forward {

        @JsonProperty("forwards_count")
        private Integer forwardsCount;

        @JsonProperty("forwards_opens")
        private Integer forwardsOpens;
    }

    @Getter
    public static class FacebookLike {

        @JsonProperty("recipient_likes")
        private Integer recipientLikes;

        @JsonProperty("unique_likes")
        private Integer uniqueLikes;

        @JsonProperty("facebook_likes")
        private Integer facebookLikes;
    }

    @Getter
    public static class Open {

        @JsonProperty("opens_total")
        private Integer opensTotal;

        @JsonProperty("unique_opens")
        private Integer uniqueOpens;

        @JsonProperty("open_rate")
        private Double openRate;

        @JsonDeserialize(using = MailChimpZonedDateTimeDeserializer.class)
        @JsonProperty("last_open")
        private ZonedDateTime lastOpen;
    }

    @Getter
    public static class IndustryStats {

        @JsonProperty("type")
        private String type;

        @JsonProperty("open_rate")
        private Double openRate;

        @JsonProperty("click_rate")
        private Double clickRate;

        @JsonProperty("bounce_rate")
        private Double bounceRate;

        @JsonProperty("unopen_rate")
        private Double unopenRate;

        @JsonProperty("unsub_rate")
        private Double unsubRate;

        @JsonProperty("abuse_rate")
        private Double abuseRate;
    }

    @Getter
    public static class ListStats {

        @JsonProperty("sub_rate")
        private Double subRate;

        @JsonProperty("unsub_rate")
        private Double unsubRate;

        @JsonProperty("open_rate")
        private Double openRate;

        @JsonProperty("click_rate")
        private Double clickRate;
    }

    @Getter
    public static class Click {

        @JsonProperty("clicks_total")
        private Integer clicksTotal;

        @JsonProperty("unique_clicks")
        private Integer uniqueClicks;

        @JsonProperty("unique_subscriber_clicks")
        private Integer uniqueSubscriberClicks;

        @JsonProperty("click_rate")
        private Double clickRate;

        @JsonProperty("last_click")
        private String lastClick;
    }
}
