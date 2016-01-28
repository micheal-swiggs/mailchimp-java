package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CampaignDefaults
{
    @JsonProperty(value = JsonConstants.FROM_NAME)
    private String fromName;

    public String getFromName()
    {
        return fromName;
    }

    public void setFromName(String fromName)
    {
        this.fromName = fromName;
    }

    @JsonProperty(value = JsonConstants.FROM_EMAIL)
    private String fromEmail;

    public String getFromEmail()
    {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail)
    {
        this.fromEmail = fromEmail;
    }

    @JsonProperty(value = JsonConstants.SUBJECT)
    private String subject;

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    @JsonProperty(value = JsonConstants.LANGUAGE)
    private String language;

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }
}
