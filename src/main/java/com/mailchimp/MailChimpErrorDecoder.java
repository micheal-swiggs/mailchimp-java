/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mailchimp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mailchimp.domain.MailChimpError;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.NonNull;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import static feign.Util.RETRY_AFTER;
import static java.util.Locale.US;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
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
        } else if (response.status() == 503) { // 503: Service (temporary) unavailable
            Date retryAfter = new RetryAfterDecoder().apply(firstOrNull(response.headers(), RETRY_AFTER));
            return new RetryableException(response.reason(), retryAfter);
        } else {
            return new ErrorDecoder.Default().decode(methodKey, response);
        }
    }

    private <T> T firstOrNull(Map<String, Collection<T>> map, String key) {
        if (map.containsKey(key) && !map.get(key).isEmpty()) {
            return map.get(key).iterator().next();
        }
        return null;
    }

    static class RetryAfterDecoder {

        static final DateFormat RFC822_FORMAT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", US);
        private final DateFormat rfc822Format;

        RetryAfterDecoder() {
            this(RFC822_FORMAT);
        }

        RetryAfterDecoder(@NonNull DateFormat rfc822Format) {
            this.rfc822Format = rfc822Format;
        }

        protected long currentTimeMillis() {
            return System.currentTimeMillis();
        }

        /**
         * returns a date that corresponds to the first time a request can be retried.
         *
         * @param retryAfter String in <a href="https://tools.ietf.org/html/rfc2616#section-14.37">Retry-After format</a>
         */
        java.util.Date apply(String retryAfter) {
            if (retryAfter == null) {
                return null;
            }
            if (retryAfter.matches("^[0-9]+$")) {
                long deltaMillis = SECONDS.toMillis(Long.parseLong(retryAfter));
                return new java.util.Date(currentTimeMillis() + deltaMillis);
            }
            synchronized (rfc822Format) {
                try {
                    return rfc822Format.parse(retryAfter);
                } catch (ParseException ignored) {
                    return null;
                }
            }
        }
    }

}
