package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Account {

    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("account_name")
    private String accountName;

    @JsonProperty("contact")
    private Contact contact;
}
