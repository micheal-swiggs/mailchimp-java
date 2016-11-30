/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author stevensnoeijen
 */
@ToString(of = {"title", "status", "detail"})
public class MailChimpError {

    public static class BodyError {

        @JsonProperty
        @Getter
        private String field;

        @JsonProperty
        @Getter
        private String message;
    }

    @JsonProperty
    @Getter
    private String type;

    @JsonProperty
    @Getter
    private String title;

    @JsonProperty
    @Getter
    private Integer status;

    @JsonProperty
    @Getter
    private String detail;

    @JsonProperty
    @Getter
    private String instance;

    @JsonProperty
    @Getter
    private java.util.List<BodyError> errors;
}
