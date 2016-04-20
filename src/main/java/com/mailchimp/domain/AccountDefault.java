package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccountDefault {

    @JsonProperty(value = JsonConstants.ACCOUNT_ID)
    private String accountId;

    @JsonProperty(value = JsonConstants.ACCOUNT_NAME)
    private String accountName;

    @JsonProperty(value = JsonConstants.EMAIL)
    private String email;

    @JsonProperty(value = JsonConstants.CONTACT)
    private Contact contact;
}
