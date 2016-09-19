package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CampaignDefaults {

    @JsonProperty("from_name")
    private String fromName;

    @JsonProperty("from_email")
    private String fromEmail;

    @JsonProperty
    private String subject;

    @JsonProperty
    private String language;

}
