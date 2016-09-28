package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class CampaignDefaults {

    @JsonProperty("from_name")
    @Getter
    @Setter
    private String fromName;

    @JsonProperty("from_email")
    @Getter
    @Setter
    private String fromEmail;

    @Getter
    @Setter
    private String subject;

    @Getter
    @Setter
    private String language;

}
