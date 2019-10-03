/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mailchimp;

import com.mailchimp.domain.MailChimpError;
import feign.FeignException;
import lombok.Getter;

/**
 *
 * @author stevensnoeijen
 */
public class MailChimpErrorException extends FeignException {

    @Getter
    private MailChimpError error;

    /**
     * Creates a new instance of <code>MailChimpErrorException</code> without detail message.
     */
    public MailChimpErrorException(int status, String message, MailChimpError error) {
        super(status, message);
        this.error = error;
    }

    @Override
    public String toString() {
        return error.toString();
    }
}
