package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CampaignDefaults
{
    @JsonProperty(value = JsonConstants.FROM_NAME)
    private String fromName;

    @JsonProperty(value = JsonConstants.FROM_EMAIL)
    private String fromEmail;

    @JsonProperty(value = JsonConstants.SUBJECT)
    private String subject;

    @JsonProperty(value = JsonConstants.LANGUAGE)
    private String language;

}
