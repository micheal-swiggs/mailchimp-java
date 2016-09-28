/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mailchimp;

import com.mailchimp.domain.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Properties;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 *
 * @author stevensnoeijen
 */
@RunWith(InSequenceRunner.class)
public class MailChimpClientTest {

    private final MailChimpClient mailChimpClient;
    private final String email;
    private String listID;

    public MailChimpClientTest() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("test.properties"));

        String apiKey = properties.getProperty("apiKey");
        String apiBase = properties.getProperty("apiBase");
        email = properties.getProperty("email");

        mailChimpClient = MailChimpFactory.createWithBasicAuth(apiKey, apiBase);
    }

    @Test(expected = MailChimpErrorException.class)
    public void throwMailChimpErrorException() throws MailChimpErrorException {
        mailChimpClient.getList("123");
    }

    @Test
    @InSequence(1)
    public void getAccount() {
        Account account = mailChimpClient.getAccount();
    }

    @Test
    @InSequence(2)
    public void createList() {
        List list = new List();
        list.setName("mailchimp-test");
        list.getContact().setCompany("MailChimp test");
        list.getContact().setAddress1("1386 Andy Street");
        list.getContact().setCity("Springfield");
        list.getContact().setState("South Dakota");
        list.getContact().setZip("57062");
        list.getContact().setCountry("US");
        list.setPermissionReminder("MailChimp test");
        list.getCampaignDefaults().setFromName("MailChimp test");
        list.getCampaignDefaults().setFromEmail(email);
        list.getCampaignDefaults().setSubject("");
        list.getCampaignDefaults().setLanguage("en");
        list.setEmailTypeOption(false);

        list = mailChimpClient.createList(list);
        assertNotNull(list);
        listID = list.getId();
    }

    @Test
    @InSequence(3)
    public void getList() {
        List list = mailChimpClient.getList(listID);
        assertNotNull(list);
    }

    @Test
    @InSequence(4)
    public void getLists() {
        Lists lists = mailChimpClient.getLists();
        assertTrue(lists.getTotalItems() > 0);
    }

    @Test
    @InSequence(5)
    public void createListMember() {
        Member member = new Member(email);
        member.setEmailType(Member.EmailType.html);
        member.setStatus(SubscribeStatus.SUBSCRIBED);
        member.putMergeField("EMAIL", email);
        member.putMergeField("MESSAGE", "some message");
        member.setLanguage("nl");
        member.setTimestampSignup(ZonedDateTime.now());

        //test create
        member = mailChimpClient.createListMember(listID, member);
        assertNotNull(member.getId());
        assertNotNull(member.getUniqueEmailId());
    }

    @Test
    @InSequence(6)
    public void getListMember() {
        Member member = mailChimpClient.getListMember(listID, "e26cacce30fb2566ba0c8dddc7260948");
        assertNotNull(member);
    }

    @Test
    @InSequence(7)
    public void getListMembers() {
        Members members = mailChimpClient.getListMembers(listID);
        assertEquals(1l, members.getTotalItems().longValue());
    }

    @Test
    @InSequence(8)
    public void updateListMember() {
        Member member = mailChimpClient.getListMember(listID, "e26cacce30fb2566ba0c8dddc7260948");
        member.putMergeField("NAME", "test");
        member = mailChimpClient.updateListMember(listID, member.getId(), member);
        assertEquals("test", member.getMergeField("NAME"));
    }

    @Test
    @InSequence(9)
    public void removeListMember() {
        Member member = mailChimpClient.getListMember(listID, "e26cacce30fb2566ba0c8dddc7260948");
        mailChimpClient.removeListMember(listID, member.getId());
    }

    @Test
    @InSequence(12)
    public void removeList() {
        mailChimpClient.removeList(listID);
    }
}
