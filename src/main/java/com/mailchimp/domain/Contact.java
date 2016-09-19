package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Contact {

    @JsonProperty
    private String company;

    @JsonProperty
    private String address1;

    @JsonProperty
    private String address2;

    @JsonProperty
    private String city;

    @JsonProperty
    private String state;

    @JsonProperty
    private String zip;

    @JsonProperty
    private String country;

    @JsonProperty
    private String phone;
}
