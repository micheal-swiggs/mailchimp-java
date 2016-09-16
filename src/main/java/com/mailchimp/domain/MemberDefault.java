package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mailchimp.jackson.MailChimpZonedDateTimeDeserializer;
import com.mailchimp.jackson.MailChimpZonedDateTimeSerializer;
import java.security.MessageDigest;
import java.time.ZonedDateTime;
import java.util.Map;
import lombok.Data;
import org.apache.commons.codec.binary.Hex;

/**
 * @author Ed Bras
 */
@Data
public class MemberDefault extends MemberCreate {

    @JsonProperty(value = JsonConstants.ID)
    private String id;

    @JsonProperty(value = JsonConstants.MEMBER_RATING)
    private Integer memberRating;

    @JsonProperty(value = JsonConstants.SIGNUP_DATE)
    @JsonDeserialize(using = MailChimpZonedDateTimeDeserializer.class)
    @JsonSerialize(using = MailChimpZonedDateTimeSerializer.class)
    private ZonedDateTime signupDate;

    @JsonProperty(value = JsonConstants.STATUS_IF_NEW)
    private SubscribeStatus statusIfNew;

    @JsonProperty(value = JsonConstants.LIST_ID)
    private String listId;

    @JsonProperty(value = JsonConstants.LAST_CHANGED)
    @JsonDeserialize(using = MailChimpZonedDateTimeDeserializer.class)
    @JsonSerialize(using = MailChimpZonedDateTimeSerializer.class)
    private ZonedDateTime lastChanged;

    @JsonProperty(JsonConstants.MERGE_VARS)
    private Map<String, Object> mergeVars;

    @JsonIgnore
    public String getSubscriberHash() {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return Hex.encodeHexString(md.digest(email.toLowerCase().getBytes("UTF-8")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
