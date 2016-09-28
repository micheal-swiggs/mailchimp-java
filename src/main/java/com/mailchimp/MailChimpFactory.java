package com.mailchimp;

import com.mailchimp.auth.OAuthRequestInterceptor;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public class MailChimpFactory {

    static String mailChimpApi(String i) {
        return "https://" + i + ".api.mailchimp.com";
    }

    public static MailChimpClient createWithBasicAuth(String apiKey, String apiBase) {
        MailChimpClient mailChimp = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .errorDecoder(new MailChimpErrorDecoder())
                .requestInterceptor(new BasicAuthRequestInterceptor("anyString", apiKey))
                .target(MailChimpClient.class, mailChimpApi(apiBase));
        return mailChimp;
    }

    public static MailChimpClient createWithOAuth(String accessToken, String apiBase) {
        MailChimpClient mailChimp = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .errorDecoder(new MailChimpErrorDecoder())
                .requestInterceptor(new OAuthRequestInterceptor(accessToken))
                .target(MailChimpClient.class, mailChimpApi(apiBase));
        return mailChimp;
    }
}
