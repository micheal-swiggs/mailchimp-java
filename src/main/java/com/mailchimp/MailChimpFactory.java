package com.mailchimp;

import feign.*;
import feign.jackson.*;
import feign.auth.BasicAuthRequestInterceptor;


public class MailChimpFactory
{
    static String mailChimpApi (String i)
    {
        return "https://"+i+".api.mailchimp.com";
    }

    public static MailChimpClient create (String apiKey, String apiBase)
    {
        MailChimpClient mailChimp = Feign.builder()
            .decoder(new JacksonDecoder())
            .encoder(new JacksonEncoder())
            .requestInterceptor(new BasicAuthRequestInterceptor("anyString", apiKey ))
            .target(MailChimpClient.class, mailChimpApi(apiBase));
        return mailChimp;
    }
}
