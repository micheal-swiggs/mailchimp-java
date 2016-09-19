package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mailchimp.jackson.MailChimpZonedDateTimeDeserializer;
import com.mailchimp.jackson.MailChimpZonedDateTimeSerializer;
import java.security.MessageDigest;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import org.apache.commons.codec.binary.Hex;

/**
 * @author Ed Bras
 */
@Data
public class Member {

    /**
     * Default name for mergefield first name
     */
    public final static String MERGEFIELD_FNAME = "FNAME";
    /*
     * Default name for mergefield first name
     */
    public final static String MERGEFIELD_LNAME = "LNAME";

    @JsonProperty(value = JsonConstants.ID)
    private String id;

    @JsonProperty(value = JsonConstants.EMAIL, required = true)
    protected String email;

    @JsonProperty(value = JsonConstants.STATUS)
    protected SubscribeStatus subscribeStatus;

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

    @JsonProperty("merge_fields")
    private Map<String, String> mergeFields = new HashMap<>();

    public boolean hasMergeField(String name) {
        return mergeFields.containsKey(name);
    }

    public String getMergeField(String name) {
        return mergeFields.get(name);
    }

    public void putMergeField(String name, String value) {
        mergeFields.put(name, value);
    }

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
