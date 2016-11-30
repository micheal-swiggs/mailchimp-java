package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mailchimp.jackson.MailChimpZonedDateTimeDeserializer;
import com.mailchimp.jackson.MailChimpZonedDateTimeSerializer;
import java.security.MessageDigest;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.binary.Hex;

/**
 * @author Ed Bras
 * @author stevensnoeijen
 */
@JsonIgnoreProperties(ignoreUnknown = true)//TODO: remove this when all properties are add
public class Member {

    public static enum EmailType {
        html, text
    }

    public static class SubscriberStats {

        @JsonProperty("avg_open_rate")
        @Getter
        private int avgOpenRate;

        @JsonProperty("avg_click_rate")
        @Getter
        private int avgClickRate;

    }

    /**
     * Default name for mergefield first name
     */
    public final static String MERGEFIELD_FNAME = "FNAME";
    /*
     * Default name for mergefield first name
     */
    public final static String MERGEFIELD_LNAME = "LNAME";

    @JsonProperty
    @Getter
    private String id;

    /**
     * You can not edit an existing member's e-mail-address.
     */
    @JsonProperty(value = "email_address", required = true)
    @Getter
    @Setter
    protected String emailAddress;

    @JsonProperty("unique_email_id")
    @Getter
    private String uniqueEmailId;

    @JsonProperty("email_type")
    @Getter
    @Setter
    private EmailType emailType;

    @JsonProperty("status")
    @Getter
    @Setter
    private SubscribeStatus status;

    @JsonProperty("merge_fields")
    @Getter
    @Setter
    private Map<String, String> mergeFields = new HashMap<>();

//    @JsonProperty
//    @Getter
//    @Setter
//    private Map<String, Boolean> interests = new HashMap<>();
    //@JsonProperty
    //@Getter
    //@JsonIgnore
    //private SubscriberStats stats = new SubscriberStats();
//    @JsonProperty("ip_signup")
//    @Getter
//    private String ipSignup;
    @JsonProperty("timestamp_signup")
    @JsonDeserialize(using = MailChimpZonedDateTimeDeserializer.class)
    @JsonSerialize(using = MailChimpZonedDateTimeSerializer.class)
    @Getter
    @Setter
    private ZonedDateTime timestampSignup;

//    @JsonProperty("ip_opt")
//    @Getter
//    private String ipOpt;
    @JsonProperty("timestamp_opt")
    @JsonDeserialize(using = MailChimpZonedDateTimeDeserializer.class)
    @JsonSerialize(using = MailChimpZonedDateTimeSerializer.class)//TODO: dont post/put this
    @Getter
    private ZonedDateTime timestampOpt;

    @JsonProperty("member_rating")
    @Getter
    private Integer memberRating;

    @JsonProperty("last_changed")
    @JsonDeserialize(using = MailChimpZonedDateTimeDeserializer.class)
    @JsonSerialize(using = MailChimpZonedDateTimeSerializer.class)
    @Getter
    @Setter
    private ZonedDateTime lastChanged;

    @JsonProperty
    @Getter
    @Setter
    private String language;

    @JsonProperty
    @Getter
    @Setter
    private boolean vip;

    @JsonProperty("email_client")
    @Getter
    @Setter
    private String emailClient;

//    @JsonProperty
//    @Getter
//    @Setter
//    private Location location;
    /**
     * When creating a subscriber that already exists this status will be used.
     */
    @JsonProperty("status_if_new")
    @Getter
    @Setter
    private SubscribeStatus statusIfNew;

    @JsonProperty("list_id")
    @Getter
    private String listId;

    public Member() {
    }

    public Member(String emailAddress) {
        this.emailAddress = emailAddress;
    }

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
        return getSubscriberHash(emailAddress);
    }

    @JsonIgnore
    public static String getSubscriberHash(String email) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return Hex.encodeHexString(md.digest(email.toLowerCase().getBytes("UTF-8")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
