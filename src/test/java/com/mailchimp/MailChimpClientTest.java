/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mailchimp;

import com.mailchimp.domain.Member;
import com.mailchimp.domain.Members;
import com.mailchimp.domain.Root;
import com.mailchimp.domain.SearchMembers;
import com.mailchimp.domain.Segment;
import com.mailchimp.domain.SegmentCreate;
import com.mailchimp.domain.SegmentModified;
import com.mailchimp.domain.SegmentModify;
import com.mailchimp.domain.Segments;
import com.mailchimp.domain.SubscribeStatus;
import com.mailchimp.domain.SubscriberList;
import com.mailchimp.domain.SubscriberLists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Requires "test.properties" file in projects root to run the tests. <br>
 * The test.properties file must contain the following values:
 * <ul>
 * <li><b>apiKey</b>. Request on the mailchimp website under account > extras > API keys</li>
 * <li><b>apiBase</b>. Base to send the request to, this is contained in the apiKey after the dash (like "us12")</li>
 * <li><b>email</b>. Use as subscriber. Use a real email-address (preferable your own) because this use may receive some mails.</li>
 * </ul>
 * Do know that if you do not have a paid account that you maybe reach your request limit.
 *
 * @author stevensnoeijen, eamoralesl
 */
@RunWith(InSequenceRunner.class)
public class MailChimpClientTest {

    private static String listID;
    private static Integer segmentID;
    private final MailChimpClient mailChimpClient;
    private final String email;

    public MailChimpClientTest() throws IOException {
        //load properties
        Properties properties = new Properties();
        properties.load(new FileInputStream("test.properties"));

        String apiKey = properties.getProperty("apiKey");
        String apiBase = properties.getProperty("apiBase");
        email = properties.getProperty("email");

        mailChimpClient = MailChimpFactory.createWithBasicAuth(apiKey, apiBase);
    }

    public void throwMailChimpErrorException() throws MailChimpErrorException {
        SubscriberList list = mailChimpClient.getList("123");
        Assert.assertNull(list);
    }

    @Test
    public void decode404() {
        SubscriberList list = mailChimpClient.getList("123");
        assertNull(list);
    }

    @Test
    @InSequence(1)
    public void getRoot() {
        Root account = mailChimpClient.getRoot();
    }

    /*
     * @Test
     * public void createAuthorizedApp() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getAuthorizedApps() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getAuthorizedApp() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getAutomations() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getAutomation() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void pauseAutomationMails() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void startAutomationMails() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getAutomationEmails() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getAutomationEmail() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void pauseAutomationEmail() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void startAutomationEmail() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void addAutomationEmailQueue() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getAutomationEmailQueue() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getAutomationEmailQueueSubscriber() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void removeAutomationSubscriber() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getAutomationSubscribers() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void startBatch() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getBatches() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getBatch() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void removeBatch() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void createCampaignFolder() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getCampaignFolders() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getCampaignFolder() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void updateCampaignFolder() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void removeCampaignFolder() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void createCampaign() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getCampaigns() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getCampaign() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void updateCampaign() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void removeCampaign() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void cancelCampaign() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void pauseCampaign() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void replicateCampaign() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void resumeCampaign() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void scheduleCampaign() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void sendCampaign() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void testCampaign() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void unscheduleCampaign() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getCampaignContent() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void setCampaignContent() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void addCampaignFeedback() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getCampaignFeedbacks() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getCampaignFeedback() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void updateCampaignFeedback() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void removeCampaignFeedback() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getCampaignSendChecklist() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getConversations() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getConversation() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void createConversationMessage() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getConversationMessages() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getConversationMessage() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void createECommerceStore() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getECommerceStores() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getECommerceStore() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void updateECommerceStore() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void removeECommerceStore() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void createECommerceStoreCart() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void createECommerceStoreCarts() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void updateECommerceStoreCart() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void removeECommerceStoreCart() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void createECommerceStoreCartLine() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getECommerceStoreCartLines() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getECommerceStoreCartLine() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void updateECommerceStoreCartLine() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void removeECommerceStoreCartLine() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void createECommerceStoreCartCustomer() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getECommerceStoreCartCustomer() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getECommerceStoreCartCustomers() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void updateECommerceStoreCartCustomer() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void removeECommerceStoreCartCustomer() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void createECommerceStoreCartOrder() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getECommerceStoreCartOrders() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getECommerceStoreCartOrder() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void updateECommerceStoreCartOrder() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void removeECommerceStoreCartOrder() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void createECommerceStoreCartOrderLine() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getECommerceStoreCartOrderLines() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getECommerceStoreCartOrderLine() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void updateECommerceStoreCartOrderLine() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void removeECommerceStoreCartOrderLine() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void createECommerceStoreCartOrderProduct() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getECommerceStoreCartOrderProducts() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getECommerceStoreCartOrderProduct() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void removeECommerceStoreCartOrderProduct() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void createECommerceStoreCartOrderProductVariant() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getECommerceStoreCartOrderProductVariants() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getECommerceStoreCartOrderProductVariant() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void updateECommerceStoreCartOrderProductVariant() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void removeECommerceStoreCartOrderProductVariant() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void uploadFile() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getFilesInfo() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getFile() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void updateFile() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void removeFile() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void createFolder() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getFoldersInfo() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getFolderInfo() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void updateFolder() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void removeFolder() {
     * fail("Not yet implemented");
     * } */
    @Test
    @InSequence(2)
    public void createList() {
        SubscriberList list = new SubscriberList();
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
        SubscriberList list = mailChimpClient.getList(listID);
        assertNotNull(list);
    }

    @Test
    @InSequence(4)
    public void getLists() {
        SubscriberLists lists = mailChimpClient.getLists();
        assertTrue(lists.getTotalItems() > 0);
    }

    @Test
    @InSequence(5)
    public void createListMemberSubscribed() {
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
    @InSequence(5)
    public void createListMemberUnSubscribed() {
        String emailAddress = email.replace("@", "+10@");
        Member member = new Member(emailAddress);
        member.setEmailType(Member.EmailType.html);
        member.setStatus(SubscribeStatus.UNSUBSCRIBED);
        member.putMergeField("EMAIL", emailAddress);
        member.putMergeField("MESSAGE", "some message");
        member.setLanguage("nl");
        member.setTimestampSignup(ZonedDateTime.now());

        //test create
        member = mailChimpClient.createListMember(listID, member);
        assertNotNull(member.getId());
        assertNotNull(member.getUniqueEmailId());
    }


    @Test
    @InSequence(5)
    public void createListMemberCleaned() {
        String emailAddress = email.replace("@", "+20@");
        Member member = new Member(emailAddress);
        member.setEmailType(Member.EmailType.html);
        member.setStatus(SubscribeStatus.CLEANED);
        member.putMergeField("EMAIL", emailAddress);
        member.putMergeField("MESSAGE", "some message");
        member.setLanguage("nl");
        member.setTimestampSignup(ZonedDateTime.now());

        //test create
        member = mailChimpClient.createListMember(listID, member);
        assertNotNull(member.getId());
        assertNotNull(member.getUniqueEmailId());
    }

    @Test
    @InSequence(5)
    public void createListMemberPending() {
        String emailAddress = email.replace("@", "+30@");
        Member member = new Member(emailAddress);
        member.setEmailType(Member.EmailType.html);
        member.setStatus(SubscribeStatus.PENDING);
        member.putMergeField("EMAIL", emailAddress);
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
    public void getListMember() throws InterruptedException {
        Thread.sleep(1000);//wait a sec
        Member member = mailChimpClient.getListMember(listID, Member.getSubscriberHash(email));
        assertNotNull(member);
    }

    @Test
    @InSequence(7)
    public void getListMembers() throws InterruptedException {
        Thread.sleep(1000);//wait a sec
        Members members = mailChimpClient.getListMembers(listID);
        assertEquals(4, members.getTotalItems().longValue());
    }


    @Test
    @InSequence(7)
    public void getListMembersByStatus() throws InterruptedException {
        Thread.sleep(1000);//wait a sec
        Members subscribed = mailChimpClient.getListMembersByStatus(listID, 0, 1, SubscribeStatus.SUBSCRIBED);
        assertEquals(1, subscribed.getTotalItems().longValue());
        Members unSubscribed = mailChimpClient.getListMembersByStatus(listID, 0, 1, SubscribeStatus.UNSUBSCRIBED);
        assertEquals(1, unSubscribed.getTotalItems().longValue());
        Members cleaned = mailChimpClient.getListMembersByStatus(listID, 0, 1, SubscribeStatus.CLEANED);
        assertEquals(1, cleaned.getTotalItems().longValue());
        Members pending = mailChimpClient.getListMembersByStatus(listID, 0, 1, SubscribeStatus.PENDING);
        assertEquals(1, pending.getTotalItems().longValue());
    }

    @Test
    @InSequence(8)
    public void updateListMember() {
        Member member = mailChimpClient.getListMember(listID, Member.getSubscriberHash(email));
        member.putMergeField("FNAME", "test");
        member = mailChimpClient.updateListMember(listID, member.getId(), member);
        assertEquals("test", member.getMergeField("FNAME"));
    }


    /*
     * @Test
     * public void getListAbuseReports() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getListAbuseReport() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getListActivity() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getListClients() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getListGrowthHistories() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getListGrowthHistory() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void createListInterestCategory() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getListInterestCategories() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getListInterestCategory() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void updateListInterestCategory() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void removeListInterestCategory() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void createListInterestCategoryInterest() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getListInterestCategoriesInterest() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getListInterestCategoryInterest() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void updateListInterestCategoryInterest() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void removeListInterestCategoryInterest() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getListMemberActivity() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getListMemberGoals() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void createListMemberNote() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getListMemberNotes() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getListMemberNote() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void updateListMemberNote() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void removeListMemberNote() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void createListMergeField() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getListMergeFields() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getListMergeField() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void updateListMergeField() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void removeListMergeField() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void createListSegment() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getListSegments() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getListSegment() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void updateListSegment() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void removeListSegment() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void createListSegmentMember() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getListSegmentMembers() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void removeListSegmentMember() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void createListSignupForm() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getListSignupForm() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void createListTwitterLeadGenCard() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getListTwitterLeadGenCards() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getListTwitterLeadGenCard() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void createListWebhook() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getListWebhooks() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getListWebhook() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void removeListWebhooks() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getReports() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getReport() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getReportAbuses() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getReportAbuse() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getReportAdvice() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getReportClickDetails() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getReportClickDetail() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getReportClickDetailMembers() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getReportClickDetailMember() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getReportDomainPerformance() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getReportEepurl() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getReportEmailActivities() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getReportEmailActivity() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getReportLocations() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getReportSentTo() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getReportSubReports() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getReportUnsubscribed() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getReportUnsubscribedByMember() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void searchCampaigns() {
     * fail("Not yet implemented");
     * } */
    @Test
    @InSequence(9)
    public void searchMembers() throws InterruptedException {
        Thread.sleep(1000);//wait a sec
        SearchMembers searchMembers = mailChimpClient.searchMembers(email);
        assertEquals(1l, searchMembers.getExactMatches().getTotalItems().longValue());
    }


    @Test
    @InSequence(10)
    public void searchMembersByListId() throws InterruptedException {
        Thread.sleep(1000);//wait a sec
        SearchMembers searchMembers = mailChimpClient.searchMembers(email, listID);
        //The expected value here is 1 because it only returns subscribed members
        assertEquals(1l, searchMembers.getExactMatches().getTotalItems().longValue());
    }


    @Test
    @InSequence(11)
    public void createListSegment() throws InterruptedException {
        Thread.sleep(1000);//wait a sec
        SegmentCreate request = new SegmentCreate();
        request.setName("Test segment");
        final Segment segment = mailChimpClient.createSegment(listID, request);
        assertNotNull(segment.getId());
        segmentID = segment.getId();
    }

    @Test
    @InSequence(12)
    public void getListSegments() throws InterruptedException {
        Thread.sleep(1000);//wait a sec
        final Segments segments = mailChimpClient.getSegments(listID);
        assertEquals(1, segments.getSegments().size());
    }

    @Test
    @InSequence(13)
    public void getListSegment() throws InterruptedException {
        Thread.sleep(1000);
        final Segment segment = mailChimpClient.getSegment(listID, segmentID);
        assertEquals(segmentID, segment.getId());
    }

    @Test
    @InSequence(14)
    public void updateListSegmentAdd() throws InterruptedException {
        Thread.sleep(1000);
        SegmentModify request = new SegmentModify();
        request.setMembersToAdd(Arrays.asList(email, "test." + email));
        final SegmentModified segment = mailChimpClient.modifySegment(listID, segmentID, request);
        assertNotNull(segment);
        assertEquals(1, (int) segment.getTotalAdded());
    }

    @Test(expected = MailChimpErrorException.class)
    @InSequence(15)
    public void updateListSegmentError() throws InterruptedException {
        Thread.sleep(1000);
        SegmentModify request = new SegmentModify();
        request.setMembersToRemove(Arrays.asList("test." + email, "dummy@server.com"));
        mailChimpClient.modifySegment(listID, segmentID, request);
    }

    @Test
    @InSequence(16)
    public void updateListSegmentRemove() throws InterruptedException {
        Thread.sleep(1000);
        SegmentModify request = new SegmentModify();
        request.setMembersToRemove(Collections.singletonList(email));
        final SegmentModified segment = mailChimpClient.modifySegment(listID, segmentID, request);
        assertNotNull(segment);
        assertEquals(1, (int) segment.getTotalRemoved());
    }

    @Test
    @InSequence(17)
    public void removeListSegment() throws InterruptedException {
        Thread.sleep(1000);
        mailChimpClient.removeSegment(listID, segmentID);

        final Segments segments = mailChimpClient.getSegments(listID);
        assertEquals(0, segments.getSegments().size());
    }

    @Test
    @InSequence(18)
    public void removeListMember() {
        Member member = mailChimpClient.getListMember(listID, Member.getSubscriberHash(email));
        mailChimpClient.removeListMember(listID, member.getId());
    }

    @Test
    @InSequence(19)
    public void removeList() {
        mailChimpClient.removeList(listID);
    }
    /*
     * @Test
     * public void createTemplateFolder() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getTemplateFolders() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getTemplateFolder() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void updateTemplateFolder() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void removeTemplateFolder() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void createTemplate() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getTemplates() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getTemplate() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void updateTemplate() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void deleteTemplate() {
     * fail("Not yet implemented");
     * }
     *
     * @Test
     * public void getTemplateDefaultContent() {
     * fail("Not yet implemented");
     * }
     */
}
