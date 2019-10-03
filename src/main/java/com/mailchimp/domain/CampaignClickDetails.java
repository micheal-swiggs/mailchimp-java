package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mailchimp.domain.ClickReportsMembers.MergeField;
import com.mailchimp.domain.Link.Rel;
import com.mailchimp.jackson.MailChimpZonedDateTimeDeserializer;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.Getter;

/**
 * @author YukiFujisawa
 * @see <a href="https://us20.api.mailchimp.com/schema/3.0/Definitions/Reports/Response.json">/schema/3.0/Definitions/Reports/Response.jso</a>
 */
@Getter
public class CampaignClickDetails {

    @JsonProperty("urls_clicked")
    private List<UrlsClicked> urlsClickeds;

    @JsonProperty("merge_fields")
    private MergeField mergeField;

    @JsonProperty("vip")
    private Boolean vip;

    @JsonProperty("clicks")
    private Integer clicks;

    @JsonProperty("campaign_id")
    private String campaignId;

    @JsonProperty("url_id")
    private String urlId;

    @JsonProperty("list_id")
    private String listId;

    @JsonProperty("list_is_active")
    private Boolean listIsActive;

    @JsonProperty("contact_status")
    private String contactStatus;

    @JsonProperty("_links")
    private List<Link> links;

    @JsonIgnore
    public Link getCampaign() {
        return Link.getLink(links, Rel.CAMPAIGN);
    }

    @Getter
    public static class UrlsClicked {

        @JsonProperty("id")
        private String id;

        @JsonProperty("url")
        private String url;

        @JsonProperty("total_clicks")
        private Integer totalClicks;

        @JsonProperty("click_percentage")
        private Double click_percentage;

        @JsonProperty("unique_clicks")
        private Integer uniqueClicks;

        @JsonProperty("unique_click_percentage")
        private Double uniqueClickPercentage;

        @JsonProperty("last_click")
        @JsonDeserialize(using = MailChimpZonedDateTimeDeserializer.class)
        private ZonedDateTime lastClick;

        @JsonProperty("campaign_id")
        private String campaignId;

        @JsonProperty("_links")
        private List<Link> links;

        @JsonIgnore
        public Link getMembersLink() {
            return Link.getLink(links, Rel.MEMBERS);
        }

        @JsonIgnore
        public Link getCampaignLink() {
            return Link.getLink(links, Rel.CAMPAIGN);
        }
    }




}
