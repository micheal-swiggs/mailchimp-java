package com.mailchimp;

import org.junit.Assert;
import org.junit.Test;

public class MailChimpClientBuilderTest {
    @Test(expected = NullPointerException.class)
    public void build_withoutApiKeyAndApiBase_throwsNullPointerException(){
        new MailChimpClientBuilder().build();
    }

    @Test(expected = NullPointerException.class)
    public void build_withoutApiKey_throwsNullPointerException(){
        new MailChimpClientBuilder()
                .withApiBase("us1")
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void build_withoutApiBase_throwsNullPointerException(){
        new MailChimpClientBuilder()
                .withBasicAuthentication("abc")
                .build();
    }

    @Test
    public void build_withApiBaseAndBasicAuthentication_throwsNullPointerException(){
        MailChimpClient mailChimpClient = new MailChimpClientBuilder()
                .withApiBase("us1")
                .withBasicAuthentication("abc")
                .build();
        Assert.assertNotNull(mailChimpClient);
    }
}
