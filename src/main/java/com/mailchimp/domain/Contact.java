package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Contact
{
    @JsonProperty(value = JsonConstants.COMPANY)
    private String company;

    @JsonProperty(value = JsonConstants.ADDRESS1)
    private String address1;

    @JsonProperty(value = JsonConstants.ADDRESS2)
    private String address2;

    @JsonProperty(value = JsonConstants.CITY)
    private String city;

    @JsonProperty(value = JsonConstants.STATE)
    private String state;

    @JsonProperty(value = JsonConstants.ZIP)
    private String zip;

    @JsonProperty(value = JsonConstants.COUNTRY)
    private String country;

    @JsonProperty(value = JsonConstants.PHONE)
    private String phone;
}
