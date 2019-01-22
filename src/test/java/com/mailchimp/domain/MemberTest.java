package com.mailchimp.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class MemberTest {

    @Test
    public void getSubscriberHash_validEmail_validHash() {
        String subscriberHash = Member.getSubscriberHash("urist.mcvankab+3@freddiesjokes.com");
        assertEquals("852aaa9532cb36adfb5e9fef7a4206a9", subscriberHash);
    }

    @Test
    public void getSubscriberHash_empty_validHash() {
        String subscriberHash = Member.getSubscriberHash("");
        assertEquals("d41d8cd98f00b204e9800998ecf8427e", subscriberHash);
    }

}