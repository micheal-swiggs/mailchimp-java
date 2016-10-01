package com.mailchimp.domain;

import lombok.Getter;

/**
 * Information about the account contact.
 *
 * @see <a href="http://developer.mailchimp.com/documentation/mailchimp/reference/root/#read-get_root">http://developer.mailchimp.com/documentation/mailchimp/reference/root/#read-get_root</a>
 * @author stevensnoeijen
 */
public class AccountContact {

    /**
     * The company name for the account.
     */
    @Getter
    private String company;

    /**
     * The street address for the account contact.
     */
    @Getter
    private String addr1;

    /**
     * The street address for the account contact.
     */
    @Getter
    private String addr2;

    /**
     * The city for the account contact.
     */
    @Getter
    private String city;

    /**
     * The state for the account contact.
     */
    @Getter
    private String state;

    /**
     * The zip code for the account contact.
     */
    @Getter
    private String zip;

    /**
     * The country for the account contact.
     */
    @Getter
    private String country;
}
