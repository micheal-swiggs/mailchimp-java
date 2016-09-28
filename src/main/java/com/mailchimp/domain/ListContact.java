/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mailchimp.domain;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author stevensnoeijen
 */
public class ListContact {

    @Getter
    @Setter
    private String company;

    @Getter
    @Setter
    private String address1;

    @Getter
    @Setter
    private String address2;

    @Getter
    @Setter
    private String city;

    @Getter
    @Setter
    private String state;

    @Getter
    @Setter
    private String zip;

    @Getter
    @Setter
    private String country;

    @Getter
    @Setter
    private String phone;
}
