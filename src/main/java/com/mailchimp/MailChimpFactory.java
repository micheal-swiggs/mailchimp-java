package com.mailchimp;

import com.mailchimp.auth.OAuthRequestInterceptor;
import feign.*;
import feign.jackson.*;
import feign.auth.BasicAuthRequestInterceptor;


public class MailChimpFactory
{
    static String mailChimpApi (String i)
    {
        return "https://"+i+".api.mailchimp.com";
    }

    public static MailChimpClient createWithBasicAuth (String apiKey, String apiBase)
    {
        MailChimpClient mailChimp = Feign.builder()
            .decoder(new JacksonDecoder())
            .encoder(new JacksonEncoder())
            .requestInterceptor(new BasicAuthRequestInterceptor("anyString", apiKey))
            .target(MailChimpClient.class, mailChimpApi(apiBase));
        return mailChimp;
    }

    public static MailChimpClient createWithOAuth (String accessToken, String apiBase)
    {
        MailChimpClient mailChimp = Feign.builder()
            .decoder(new JacksonDecoder())
            .encoder(new JacksonEncoder())
            .requestInterceptor(new OAuthRequestInterceptor(accessToken))
            .target(MailChimpClient.class, mailChimpApi(apiBase));
        return mailChimp;
    }
}
