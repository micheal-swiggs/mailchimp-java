package com.mailchimp.domain;

import lombok.Getter;

public class AccountContact {

    @Getter
    private String company;

    @Getter
    private String addr1;

    @Getter
    private String addr2;

    @Getter
    private String city;

    @Getter
    private String state;

    @Getter
    private String zip;

    @Getter
    private String country;
}
