package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mailchimp.jackson.FlexDateDeserializer;
import com.mailchimp.jackson.FlexDateSerializer;
import lombok.Data;

import java.util.Date;
import java.util.Map;
import java.security.MessageDigest;
import org.apache.commons.codec.binary.Hex;

/**
 * @author Ed Bras
 */
@Data
public class MemberDefault
{
    @JsonProperty(value = JsonConstants.ID)
    private String id;

    @JsonProperty(value = JsonConstants.MEMBER_RATING)
    private Integer memberRating;

    @JsonProperty(value = JsonConstants.FIRST_NAME)
    private String firstName;

    @JsonProperty(value = JsonConstants.LAST_NAME)
    private String lastName;

    @JsonProperty(value = JsonConstants.EMAIL, required = true)
    private String email;

    @JsonProperty(value = JsonConstants.STATUS)
    private SubscribeStatus subscribeStatus;

    @JsonProperty(value = JsonConstants.STATUS_IF_NEW)
    private SubscribeStatus statusIfNew;

    @JsonProperty(value = JsonConstants.LIST_ID)
    private String listId;

    //	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @JsonProperty(value = JsonConstants.SIGNUP_DATE)
    @JsonDeserialize(using = FlexDateDeserializer.class)
    private Date signupDate;

    @JsonProperty(value = JsonConstants.LAST_CHANGED)
    @JsonDeserialize(using = FlexDateDeserializer.class)
    @JsonSerialize(using = FlexDateSerializer.class)
    private Date lastChanged;

    @JsonProperty(JsonConstants.MERGE_VARS)
    private Map<String, String> mergeVars;

    public String getSubscriberHash()
    {
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            return Hex.encodeHexString(md.digest(email.toLowerCase().getBytes("UTF-8")));
        } catch (Exception e) {
             throw new RuntimeException(e);
        }
    }
}
