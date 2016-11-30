/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mailchimp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mailchimp.domain.MailChimpError;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;

/**
 *
 * @author stevensnoeijen
 */
public class MailChimpErrorDecoder implements ErrorDecoder {

    private final ObjectMapper om;

    public MailChimpErrorDecoder() {
        om = new ObjectMapper();
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 400 || response.status() == 404 || response.status() == 500) {
            MailChimpError error;
            try {
                error = om.readValue(response.body().asInputStream(), MailChimpError.class);
                return new MailChimpErrorException(response.status(), response.reason(), error);
            } catch (IOException ex) {
                return new RuntimeException("json serialization of mailchimp error", ex);
            }
        } else {
            return new ErrorDecoder.Default().decode(methodKey, response);
        }
    }

}
