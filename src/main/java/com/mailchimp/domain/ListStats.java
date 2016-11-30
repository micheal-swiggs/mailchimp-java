/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mailchimp.jackson.MailChimpZonedDateTimeDeserializer;
import java.time.ZonedDateTime;
import lombok.Getter;

/**
 * Stats for the list. Many of these are cached for at least five minutes.
 *
 * @author Micheal Swiggs
 * @author stevensnoeijen
 */
public class ListStats {

    /**
     * The number of active members in the list.
     */
    @JsonProperty("member_count")
    @Getter
    private Integer memberCount;

    /**
     * The number of members who have unsubscribed from the list.
     */
    @JsonProperty("unsubscribe_count")
    @Getter
    private Integer unsubscribeCount;

    /**
     * The number of members cleaned from the list.
     */
    @JsonProperty("cleaned_count")
    @Getter
    private Integer cleanedCount;

    /**
     * The number of active members in the list since the last campaign was sent.
     */
    @JsonProperty("member_count_since_send")
    @Getter
    private Integer memberCountSinceSend;

    /**
     * The number of members who have unsubscribed since the last campaign was sent.
     */
    @JsonProperty("unsubscribe_count_since_send")
    @Getter
    private Integer unsubscribeCountSinceSend;

    /**
     * The number of members cleaned from the list since the last campaign was sent.
     */
    @JsonProperty("cleaned_count_since_send")
    @Getter
    private Integer cleanedCountSinceSend;

    /**
     * The number of campaigns in any status that use this list.
     */
    @JsonProperty("campaign_count")
    @Getter
    private Integer campaignCount;

    /**
     * The date and time the last campaign was sent to this list.
     */
    @JsonProperty("campaign_last_sent")
    @JsonDeserialize(using = MailChimpZonedDateTimeDeserializer.class)
    @Getter
    private ZonedDateTime campaignLastSent;

    /**
     * The number of merge vars for this list (not EMAIL, which is required).
     */
    @JsonProperty("merge_field_count")
    @Getter
    private Integer mergeFieldCount;

    /**
     * The average number of subscriptions per month for the list (not returned if we haven’t calculated it yet).
     */
    @JsonProperty("avg_sub_rate")
    @Getter
    private Float avgSubRate;

    /**
     * The average number of unsubscriptions per month for the list (not returned if we haven’t calculated it yet).
     */
    @JsonProperty("avg_unsub_rate")
    @Getter
    private Float avgUnsubRate;

    /**
     * The target number of subscriptions per month for the list to keep it growing (not returned if we haven’t calculated it yet).
     */
    @JsonProperty("target_sub_rate")
    @Getter
    private Float targetSubRate;

    /**
     * The average open rate (a percentage represented as a number between 0 and 100)
     * per campaign for the list (not returned if we haven’t calculated it yet).
     */
    @JsonProperty("open_rate")
    @Getter
    private Float openRate;

    /**
     * The average click rate (a percentage represented as a number between 0 and 100)
     * per campaign for the list (not returned if we haven’t calculated it yet).
     */
    @JsonProperty("click_rate")
    @Getter
    private Float clickRate;

    /**
     * The date and time of the last time someone subscribed to this list.
     */
    @JsonProperty("last_sub_date")
    @JsonDeserialize(using = MailChimpZonedDateTimeDeserializer.class)
    @Getter
    private ZonedDateTime lastSubDate;

    /**
     * The date and time of the last time someone unsubscribed from this list.
     */
    @JsonProperty("last_unsub_date")
    @JsonDeserialize(using = MailChimpZonedDateTimeDeserializer.class)
    @Getter
    private ZonedDateTime lastUnsubDate;
}
