package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Explanation and usage of the possible values: <a href="http://kb.mailchimp.com/api/article/how-to-manage-subscribers">Mail Chimp status usage</a>
 *
 * @author Ed Bras
 */
public enum SubscribeStatus {

    SUBSCRIBED,
    /**
     * Used to archive a subscriber.
     */
    UNSUBSCRIBED,
    /**
     * Used for double opt-in. The subscriber will receive an email to confirm his subscription.<br>
     * Subscribing a subscriber with the same email address, will result in an http 400 error indicating that the user already exists. As such, he will
     * not receive a confirmation email again.
     */
    PENDING,
    /**
     * Used to archive a subscriber.
     */
    CLEANED;

    @JsonValue
    public String toJson() {
        return name().toLowerCase();
    }
}
