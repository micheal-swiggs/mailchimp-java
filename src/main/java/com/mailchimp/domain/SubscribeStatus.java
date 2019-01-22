package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Explanation and usage of the possible values: <a href="http://kb.mailchimp.com/api/article/how-to-manage-subscribers">Mail Chimp status usage</a>
 *
 * @author Ed Bras
 */
public enum SubscribeStatus {

    @JsonProperty("subscribed")
    Subscribed,
    /**
     * Used to archive a subscriber.
     */
    @JsonProperty("unsubscribed")
    Unsubscribed,
    /**
     * Used for double opt-in. The subscriber will receive an email to confirm his subscription.<br>
     * Subscribing a subscriber with the same email address, will result in an http 400 error indicating that the user already exists. As such, he will
     * not receive a confirmation email again.
     */
    @JsonProperty("pending")
    Pending,
    /**
     * Used to archive a subscriber.
     */
    @JsonProperty("cleaned")
    Cleaned;

    /**
     * For correct serializing when using the enum in the query.
     * @return lowercased value
     */
    @Override
    public String toString() {
        return name().toLowerCase();
    }

    /**
     * for deserializing older version of jackson < 2.0
     * @return lowercased string of the enum
     */
    @JsonValue
    public String value(){
        return toString();
    }
}
