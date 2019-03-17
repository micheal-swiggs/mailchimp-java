package com.mailchimp;

import com.mailchimp.domain.*;
import com.mailchimp.domain.SubscriberList;
import com.mailchimp.query.BatchesQuery;
import com.mailchimp.query.ListMembersQuery;
import com.mailchimp.query.ListsQuery;
import feign.Response;
import feign.mock.HttpMethod;
import feign.mock.MockClient;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@link MailChimpClient}, uses feign-mock to mock responses.
 * The responses are saved in the resources/responses folder.
 * These responses were copied from the mailchimp's documentation site, from the associated method's page his "Example response" block.
 */
public class MailChimpClientTest {

    private MockClient mockClient;
    private MailChimpClient mailChimpClient;

    private static InputStream getResponseResourceAsStream(String name) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("responses/" + name);
    }

    private static Response.Builder generateMockResponseByResource(String resourceName)
            throws IOException {
        InputStream is = getResponseResourceAsStream(resourceName);
        String responseString = IOUtils.toString(is);

        //parse responseString to status, headers and body
        //status
        String statusString = responseString.split("\n", 0)[0];
        String statusCodeString = statusString.substring(statusString.indexOf(" ") + 1, statusString.indexOf(" ") + 4);
        int statusCode = Integer.parseInt(statusCodeString);

        Map<String, Collection<String>> headers;
        String bodyString = null;

        //headers
        int split = responseString.indexOf("\n\n");
        if(split == -1){
            //has no body
            String headersString = responseString.substring(responseString.indexOf("\n") + 1);
            String[] headersStrings = headersString.split("\n");
            headers = new HashMap<>();
            for (String headerString : headersStrings) {
                String headerName = headerString.split(": ")[0];
                String headerValue = headerString.split(": ")[1];
                String[] headerValues = headerValue.split("; ");
                headers.put(headerName, Arrays.asList(headerValues));
            }
        }else{
            //has body
            String headersString = responseString.substring(responseString.indexOf("\n") + 1, split);
            String[] headersStrings = headersString.split("\n");
            headers = new HashMap<>();
            for (String headerString : headersStrings) {
                String headerName = headerString.split(": ")[0];
                String headerValue = headerString.split(": ")[1];
                String[] headerValues = headerValue.split("; ");
                headers.put(headerName, Arrays.asList(headerValues));
            }

            //body
            bodyString = responseString.substring(split + 3);
        }

        //create response
        Response.Builder responseBuilder = Response.builder()
                .status(statusCode)
                .headers(headers);
        if(bodyString != null) {
            responseBuilder.body(bodyString, Charset.defaultCharset());
        }

        return responseBuilder;
    }

    @Before
    public void setup() throws IOException {
        mockClient = new MockClient()
                //root
                .add(HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/", generateMockResponseByResource("3.0/root.txt"))
                //list
                .add(HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/lists/57afe96172", generateMockResponseByResource("3.0/lists/57afe96172/get.txt"))
                .add(HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/lists/nonExistingList", generateMockResponseByResource("3.0/404.txt"))
                .add(HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/lists?offset=0&count=1", generateMockResponseByResource("3.0/lists/get?offset=0&count=1.txt"))
                .add(HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/lists?offset=1&count=1", generateMockResponseByResource("3.0/lists/get?offset=1&count=1.txt"))
                .add(HttpMethod.POST, "https://usX.api.mailchimp.com/3.0/lists", generateMockResponseByResource("3.0/lists/get.txt"))
                .add(HttpMethod.DELETE, "https://usX.api.mailchimp.com/3.0/lists/4ca5becb8d", generateMockResponseByResource("3.0/204.txt"))
                .add(HttpMethod.DELETE, "https://usX.api.mailchimp.com/3.0/lists/nonExistingId", generateMockResponseByResource("3.0/404.txt"))
                //list member
                .add(HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/lists/57afe96172/members/852aaa9532cb36adfb5e9fef7a4206a9", generateMockResponseByResource("3.0/lists/57afe96172/members/852aaa9532cb36adfb5e9fef7a4206a9/get.txt"))
                .add(HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/lists/nonExistingListId/members/852aaa9532cb36adfb5e9fef7a4206a9", generateMockResponseByResource("3.0/404.txt"))
                .add(HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/lists/57afe96172/members/nonExistingSubscriberHash", generateMockResponseByResource("3.0/404.txt"))
                .add(HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/lists/57afe96172/members", generateMockResponseByResource("3.0/lists/57afe96172/members/get.txt"))
                .add(HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/lists/nonExistingId/members", generateMockResponseByResource("3.0/404.txt"))
                .add(HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/lists/57afe96172/members?status=subscribed", generateMockResponseByResource("3.0/lists/57afe96172/members/get.txt"))
                .add(HttpMethod.POST, "https://usX.api.mailchimp.com/3.0/lists/57afe96172/members", generateMockResponseByResource("3.0/lists/57afe96172/members/post.txt"))
                .add(HttpMethod.POST, "https://usX.api.mailchimp.com/3.0/lists/nonExistingId/members", generateMockResponseByResource("3.0/404.txt"))
                .add(HttpMethod.PUT, "https://usX.api.mailchimp.com/3.0/lists/57afe96172/members/852aaa9532cb36adfb5e9fef7a4206a9", generateMockResponseByResource("3.0/lists/57afe96172/members/852aaa9532cb36adfb5e9fef7a4206a9/put.txt"))
                .add(HttpMethod.DELETE, "https://usX.api.mailchimp.com/3.0/lists/57afe96172/members/852aaa9532cb36adfb5e9fef7a4206a9", generateMockResponseByResource("3.0/lists/57afe96172/members/852aaa9532cb36adfb5e9fef7a4206a9/delete.txt"))
                //list merge-field
                .add(HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/lists/57afe96172/merge-fields", generateMockResponseByResource("3.0/lists/57afe96172/merge-fields/get.txt"))
                .add(HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/lists/nonExistingListId/merge-fields", generateMockResponseByResource("3.0/404.txt"))
                .add(HttpMethod.POST, "https://usX.api.mailchimp.com/3.0/lists/57afe96172/merge-fields", generateMockResponseByResource("3.0/lists/57afe96172/merge-fields/post.txt"))
                .add(HttpMethod.DELETE, "https://usX.api.mailchimp.com/3.0/lists/57afe96172/merge-fields/3", generateMockResponseByResource("3.0/lists/57afe96172/merge-fields/3/delete.txt"))
                //list segment
                .add(HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/lists/57afe96172/segments", generateMockResponseByResource("3.0/lists/57afe96172/segments/get.txt"))
                .add(HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/lists/nonExistingListId/segments", generateMockResponseByResource("3.0/404.txt"))
                .add(HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/lists/57afe96172/segments/49381", generateMockResponseByResource("3.0/lists/57afe96172/segments/49381/get.txt"))
                .add(HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/lists/nonExistingListId/segments/49381", generateMockResponseByResource("3.0/404.txt"))
                .add(HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/lists/57afe96172/segments/0", generateMockResponseByResource("3.0/404.txt"))
                .add(HttpMethod.POST, "https://usX.api.mailchimp.com/3.0/lists/57afe96172/segments", generateMockResponseByResource("3.0/lists/57afe96172/segments/post.txt"))
                .add(HttpMethod.POST, "https://usX.api.mailchimp.com/3.0/lists/3da7c56ccb/segments/11281", generateMockResponseByResource("3.0/lists/3da7c56ccb/segments/11281/post.txt"))
                .add(HttpMethod.DELETE, "https://usX.api.mailchimp.com/3.0/lists/205d96e6b4/segments/445", generateMockResponseByResource("3.0/lists/205d96e6b4/segments/445/delete.txt"))
                //batch
                .add(HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/batches/8b2428d747", generateMockResponseByResource("3.0/batches/8b2428d747/get.txt"))
                .add(HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/batches/nonExistingBatchId", generateMockResponseByResource("3.0/404.txt"))
                .add(HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/batches?offset=0&count=1", generateMockResponseByResource("3.0/batches/get?offset=0&count=1.txt"))
                .add(HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/batches?offset=1&count=1", generateMockResponseByResource("3.0/batches/get?offset=1&count=1.txt"))
                .add(HttpMethod.POST, "https://usX.api.mailchimp.com/3.0/batches", generateMockResponseByResource("3.0/batches/post.txt"))
                .add(HttpMethod.DELETE, "https://usX.api.mailchimp.com/3.0/batches/1", generateMockResponseByResource("3.0/batches/1/delete.txt"))
                //searcg-members
                .add(HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/search-members", generateMockResponseByResource("3.0/400.txt"))
                .add(HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/search-members?query=freddie@", generateMockResponseByResource("3.0/search-members/get?query=freddie@.txt"))
                .add(HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/search-members?query=freddie@&list_id=1", generateMockResponseByResource("3.0/search-members/get?query=freddie@&list_id=1.txt"))
                .add(HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/search-members?query=freddie@", generateMockResponseByResource("3.0/search-members/get?query=freddie@.txt"))
                .add(HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/search-members?query=freddie@&list_id=57afe96172", generateMockResponseByResource("3.0/search-members/get?query=freddie@.txt"));

        mailChimpClient = MailChimpClient.builder()
                .withClient(mockClient)
                .withApiBase("usX")
                .withBasicAuthentication("apikey")
                .build();
    }

    @After
    public void tearDown() {
        mockClient.verifyStatus();
    }

    @Test
    public void builder_default_returnsBuilder() {
        MailChimpClientBuilder builder = MailChimpClient.builder();
        assertNotNull(builder);
    }

    @Test
    public void getRoot_default_responseWithRootObject() {
        Root root = mailChimpClient.getRoot();
        assertEquals("8d3a3db4d97663a9074efcc16", root.getAccountId());
    }

    @Test(expected = MailChimpErrorException.class)
    public void getList_nonExistingListId_throwException() {
        mailChimpClient.getList("nonExistingList");
    }

    @Test
    public void getList_existingListId_oneList() {
        SubscriberList subscriberList = mailChimpClient.getList("57afe96172");
        assertEquals("57afe96172", subscriberList.getId());
    }

    @Test
    public void getLists_offset0AndCount1_filledLists() {
        ListsQuery query = ListsQuery.builder()
                .offset(0)
                .count(1)
                .build();
        SubscriberLists subscriberLists = mailChimpClient.getLists(query);
        assertEquals(1, subscriberLists.getTotalItems().intValue());
        assertEquals("57afe96172", subscriberLists.getLists().get(0).getId());
    }

    @Test
    public void getList_offset1AndCount1_emptyList() {
        ListsQuery query = ListsQuery.builder()
                .offset(1)
                .count(1)
                .build();
        SubscriberLists subscriberLists = mailChimpClient.getLists(query);
        assertEquals(0, subscriberLists.getLists().size());
    }

    @Test
    public void createList_valid_createdList(){
        SubscriberList subscriberList = new SubscriberList();
        subscriberList.setName("Freddie's Favorite Hats");
        SubscriberList.Contact contact = SubscriberList.Contact.builder()
                .company("Mailchimp")
                .address1("675 Ponce De Leon Ave NE")
                .address2("Suite 5000")
                .city("Atlanta")
                .state("GA")
                .zip("30308")
                .country("US")
                .phone("")
                .build();
        subscriberList.setContact(contact);
        subscriberList.setPermissionReminder("You're receiving this email because you signed up for updates about Freddie's newest hats.");
        CampaignDefaults campaignDefaults = CampaignDefaults.builder()
                .fromName("Freddie")
                .fromEmail("freddie@freddiehats.com")
                .subject("")
                .language("en")
                .build();
        subscriberList.setCampaignDefaults(campaignDefaults);
        subscriberList.setEmailTypeOption(true);

        //create
        subscriberList = mailChimpClient.createList(subscriberList);

        //check
        assertNotNull(subscriberList.getId());
        assertEquals("Freddie's Favorite Hats", subscriberList.getName());
        assertEquals("Atlanta", subscriberList.getContact().getCity());
        assertEquals("Freddie", subscriberList.getCampaignDefaults().getFromName());
        assertNotNull(subscriberList.getDateCreated());
        assertEquals(0, subscriberList.getListRating().intValue());
        assertNotNull(subscriberList.getSubscribeUrlShort());
        assertNotNull(subscriberList.getSubscribeUrlLong());
        assertNotNull(subscriberList.getBeamerAddress());
        assertEquals(SubscriberList.Visibility.pub, subscriberList.getVisibility());
        assertEquals(0, subscriberList.getStats().getMemberCount().intValue());
    }

    @Test
    public void removeList_existingId_removed(){
        mailChimpClient.removeList("4ca5becb8d");//204
    }

    @Test(expected = MailChimpErrorException.class)
    public void removeList_nonExistingId_throwException(){
        mailChimpClient.removeList("nonExistingId");
    }

    @Test
    public void getListMember_existingListIdAndExistingSubscruberHash_listMember(){
        Member member = mailChimpClient.getListMember("57afe96172", "852aaa9532cb36adfb5e9fef7a4206a9");
        assertEquals("57afe96172", member.getListId());
        assertEquals("852aaa9532cb36adfb5e9fef7a4206a9", member.getSubscriberHash());
    }

    @Test(expected = MailChimpErrorException.class)
    public void getListMember_nonExistingListId_throwException(){
        mailChimpClient.getListMember("nonExistingListId", "852aaa9532cb36adfb5e9fef7a4206a9");
    }

    @Test(expected = MailChimpErrorException.class)
    public void getListMember_existingListIdAndNonExistingSubscruberHash_throwException(){
        mailChimpClient.getListMember("57afe96172", "nonExistingSubscriberHash");
    }

    @Test
    public void createListMember_validListIdAndMember_createdListMember(){
        Member member = Member.builder()
                .emailAddress("urist.mcvankab+3@freddiesjokes.com")
                .status(SubscribeStatus.subscribed)
                //.tags(Arrays.asList(new String[] { "a tag", "another tag" }))
                .build();

        Member createdMember = mailChimpClient.createListMember("57afe96172", member);

        assertNotNull(createdMember.getId());
        assertEquals("urist.mcvankab+3@freddiesjokes.com", createdMember.getEmailAddress());
        assertEquals(SubscribeStatus.subscribed, createdMember.getStatus());
        //assertEquals(2, createdMember.getTagsCount());
        assertEquals("198.2.191.34", createdMember.getIpOpt());
        assertNotNull(createdMember.getTimestampOpt());
        assertNotNull(createdMember.getLastChanged());
    }

    @Test(expected = MailChimpErrorException.class)
    public void createListMember_nonExistingId_throwException(){
        Member member = Member.builder()
                .emailAddress("urist.mcvankab+3@freddiesjokes.com")
                .status(SubscribeStatus.subscribed)
                //.tags(Arrays.asList(new String[] { "a tag", "another tag" }))
                .build();

        mailChimpClient.createListMember("nonExistingId", member);
    }

    @Test
    public void updateListMember_validListIdAndMember_updatedListMember(){
        Member member = mailChimpClient.getListMember("57afe96172", "852aaa9532cb36adfb5e9fef7a4206a9");
        member.setStatus(SubscribeStatus.unsubscribed);
        Member updatedMember = mailChimpClient.updateListMember(member.getListId(), member.getSubscriberHash(), member);
        assertEquals(SubscribeStatus.unsubscribed, updatedMember.getStatus());
    }

    @Test
    public void removeListMember_validListIdAndMember_removedListMember(){
        mailChimpClient.removeListMember("57afe96172", "852aaa9532cb36adfb5e9fef7a4206a9");
    }

    @Test(expected = MailChimpErrorException.class)
    public void getListMembers_nonExistingListId_throwException(){
        Members members = mailChimpClient.getListMembers("nonExistingId", ListMembersQuery.all());
    }

    @Test
    public void getListMembers_firstPage_filledLists(){
        Members members = mailChimpClient.getListMembers("57afe96172", ListMembersQuery.all());
        assertEquals("57afe96172", members.getListId());
        assertEquals(3, members.getMembers().size());
    }

    @Test
    public void getListMembers_withStatusEqSubscribed_listWithUnsubscribed(){
        ListMembersQuery query = ListMembersQuery.builder()
                .status(SubscribeStatus.subscribed)
                .build();
        Members members = mailChimpClient.getListMembers("57afe96172", query);
        assertEquals("57afe96172", members.getListId());
        assertEquals(SubscribeStatus.subscribed, members.getMembers().get(0).getStatus());
    }

    @Test(expected = MailChimpErrorException.class)
    public void getListMergeFields_nonExistingListId_throwException(){
        mailChimpClient.getListMergeFields("nonExistingListId");
    }

    @Test
    public void getListMergeFields_existingListId_listMergeFields(){
        ListMergeFields listMergeFields = mailChimpClient.getListMergeFields("57afe96172");
        assertEquals("57afe96172", listMergeFields.getListId());
        assertEquals(2, listMergeFields.getMergeFields().size());
    }

    @Test
    public void createMergeField_valid_createMergeField(){
        ListMergeField listMergeField = new ListMergeField();
        listMergeField.setName("FAVORITEJOKE");
        listMergeField.setType(MergeType.Text);
        ListMergeField createdListMergeField = mailChimpClient.createMergeField("57afe96172", listMergeField);

        assertEquals("57afe96172", createdListMergeField.getListId());
        assertEquals("FAVORITEJOKE", createdListMergeField.getName());
        assertEquals(MergeType.Text, createdListMergeField.getType());
    }

    @Test
    public void removeListMergeField_existingMergeId_removed(){
        mailChimpClient.removeListMergeField("57afe96172", "3");
    }

    @Test(expected = MailChimpErrorException.class)
    public void getSegments_nonExistingListId_throwException(){
        mailChimpClient.getSegments("nonExistingListId");
    }

    @Test
    public void getSegments_existingListId_segments(){
        Segments segments = mailChimpClient.getSegments("57afe96172");
        assertEquals("57afe96172", segments.getListId());
        assertEquals(1, segments.getTotalItems().intValue());
    }

    @Test(expected = MailChimpErrorException.class)
    public void getSegment_nonExistingListId_throwException(){
        mailChimpClient.getSegment("nonExistingListId", 49381);
    }

    @Test(expected = MailChimpErrorException.class)
    public void getSegment_existingListIdAndNonExistingSegmentId_throwException(){
        mailChimpClient.getSegment("57afe96172", 0);
    }

    @Test
    public void getSegment_existingListIdAndExistingSegmentId_segment(){
        Segment segment = mailChimpClient.getSegment("57afe96172", 49381);
        assertEquals("57afe96172", segment.getListId());
        assertEquals(49381, segment.getId().intValue());
    }

    @Test
    public void createSegment_validListIdAndSegment_createdSegment(){
        SegmentCreate segment = new SegmentCreate();
        segment.setName("The jokes that didn't quite make it.");

        Segment createdSegment = mailChimpClient.createSegment("57afe96172", segment);

        assertEquals("57afe96172", createdSegment.getListId());
        assertEquals("The jokes that didn't quite make it.", createdSegment.getName());
        assertNotNull(createdSegment.getCreatedAt());
        assertNotNull(createdSegment.getUpdatedAt());
    }

    @Test
    public void modifySegment_existingSegmentId_updatedSegment(){
        SegmentModify segment = new SegmentModify();
        segment.setMembersToAdd(Arrays.asList("freddie@mailchimp.com", "freddie@freddiesjokes.com"));
        segment.setMembersToRemove(Arrays.asList("freddie@gmail.com", "freddie@yahoo.com", "freddie@hotmail.com"));

        SegmentModified segmentModified = mailChimpClient.modifySegment("3da7c56ccb", 11281, segment);

        assertEquals(2, segmentModified.getTotalAdded().intValue());
        assertEquals(3, segmentModified.getTotalRemoved().intValue());
    }

    @Test
    public void removeSegment_existingSegmentId_removedSegment(){
        mailChimpClient.removeSegment("205d96e6b4", 445);
    }

    @Test(expected = MailChimpErrorException.class)
    public void getBatch_nonExistingBatchId_throwException(){
        mailChimpClient.getBatch("nonExistingBatchId");
    }

    @Test
    public void getBatch_existingBatchId_batch(){
        Batch batch = mailChimpClient.getBatch("8b2428d747");
        assertEquals("8b2428d747", batch.getId());
    }

    @Test
    public void getBatches_Offset0_filledBatchList(){
        BatchesQuery query = BatchesQuery.builder()
                .offset(0)
                .count(1)
                .build();
        Batches batches = mailChimpClient.getBatches(query);
        assertEquals(1, batches.getBatches().size());
    }

    @Test
    public void getBatches_exceedsOffset_emptyList(){
        BatchesQuery query = BatchesQuery.builder()
                .offset(1)
                .count(1)
                .build();
        Batches batches = mailChimpClient.getBatches(query);
        assertEquals(0, batches.getBatches().size());
    }

    @Test
    public void createBatch_validBatch_createdBatch(){
        CreateBatch<Member> batch = new CreateBatch<>();
        java.util.List<Operation<Member>> operations = new ArrayList<>();
        operations.add(new Operation<>("POST","lists/624ea08019/members", Member.builder()
                .emailAddress("freddie@mailchimp.com")
                .status(SubscribeStatus.subscribed)
                .build()));
        operations.add(new Operation<>("POST","lists/624ea08019/members", Member.builder()
                .emailAddress("freddy@mailchimp.com")
                .status(SubscribeStatus.subscribed)
                .build()));
        operations.add(new Operation<>("POST","lists/624ea08019/members", Member.builder()
                .emailAddress("fred@mailchimp.com")
                .status(SubscribeStatus.subscribed)
                .build()));
        operations.add(new Operation<>("POST","lists/624ea08019/members", Member.builder()
                .emailAddress("frederick@mailchimp.com")
                .status(SubscribeStatus.subscribed)
                .build()));
        batch.setOperations(operations);

        Batch createdBatch = mailChimpClient.createBatch(batch);

        assertEquals("pending", createdBatch.getStatus());
        assertEquals(0, createdBatch.getFinishedOperations().intValue());
    }

    @Test
    public void removeBatch_existingBatchId_batchStopped(){
        mailChimpClient.removeBatch("1");
    }

    @Test(expected = MailChimpErrorException.class)
    public void searchMembers_emptyQuery_throwException(){
        mailChimpClient.searchMembers("");
    }

    @Test
    public void searchMembers_validQuery_results(){
        SearchMembers searchMembers = mailChimpClient.searchMembers("freddie@");
        assertEquals(6, searchMembers.getFullSearch().getTotalItems().intValue());
        assertEquals("urist.mcvankab+6@freddiesjokes.com", searchMembers.getFullSearch().getMembers().get(0).getEmailAddress());
    }

    @Test(expected = MailChimpErrorException.class)
    public void searchMembers_invalidListId_throwException(){
        mailChimpClient.searchMembers("freddie@", "1");
    }

    @Test
    public void searchMembers_validQueryAndValidListId_results(){
        SearchMembers searchMembers = mailChimpClient.searchMembers("freddie@", "57afe96172");
        assertEquals(6, searchMembers.getFullSearch().getTotalItems().intValue());
        assertEquals("urist.mcvankab+6@freddiesjokes.com", searchMembers.getFullSearch().getMembers().get(0).getEmailAddress());
    }

    //TODO: check error response: https://github.com/OpenFeign/feign/blob/master/core/src/test/java/feign/FeignTest.java
    //TODO: implement more tests for error situations
    //TODO: implement more query parameters
    //TODO: implement paged results
    //TODO: add method to get next paged response
}
