package com.mailchimp;

import com.mailchimp.domain.Batch;
import com.mailchimp.domain.Batches;
import com.mailchimp.domain.ListMergeFields;
import com.mailchimp.domain.Member;
import com.mailchimp.domain.Members;
import com.mailchimp.domain.Root;
import com.mailchimp.domain.Segment;
import com.mailchimp.domain.Segments;
import com.mailchimp.domain.SubscriberList;
import com.mailchimp.domain.SubscriberLists;
import feign.Response;
import feign.mock.HttpMethod;
import feign.mock.MockClient;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Unit tests for {@link MailChimpClient}, uses feign-mock to mock responses.
 * The responses are saved in the resources/responses folder.
 * These responses were copied from the mailchimp's documentation site, from the associated method's page his "Example response" block.
 */
public class MailChimpClientTest {

    private String AUTHORIZATION_HEADER_VALUE = "Basic " + Base64.encodeBase64String("anystring:apikey".getBytes());

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

        //headers
        int split = responseString.indexOf("\n\n");
        String headersString = responseString.substring(responseString.indexOf("\n")+1, split);
        String[] headersStrings = headersString.split("\n");
        Map<String, Collection<String>> headers = new HashMap<>();
        for(String headerString : headersStrings){
            String headerName = headerString.split(": ")[0];
            String headerValue = headerString.split(": ")[1];
            String[] headerValues = headerValue.split("; ");
            headers.put(headerName, Arrays.asList(headerValues));
        }

        //body
        String bodyString = responseString.substring(split+3);

        //create response
        Response.Builder responseBuilder = Response.builder()
                .status(statusCode)
                .headers(headers)
                .body(bodyString, Charset.defaultCharset());
        return responseBuilder;
    }

    @Before
    public void setup() throws IOException {
        mockClient = new MockClient()
                .add(HttpMethod.GET,"https://usX.api.mailchimp.com/3.0/", generateMockResponseByResource("3.0/root.txt"))
                .add(HttpMethod.GET,"https://usX.api.mailchimp.com/3.0/lists/57afe96172", generateMockResponseByResource("3.0/lists/57afe96172.txt"))
                .add(HttpMethod.GET,"https://usX.api.mailchimp.com/3.0/lists?offset=0&count=1", generateMockResponseByResource("3.0/lists?offset=0&count=1.txt"))
                .add(HttpMethod.GET,"https://usX.api.mailchimp.com/3.0/lists?offset=1&count=1", generateMockResponseByResource("3.0/lists?offset=1&count=1.txt"))
                .add(HttpMethod.GET,"https://usX.api.mailchimp.com/3.0/lists/57afe96172/members/852aaa9532cb36adfb5e9fef7a4206a9", generateMockResponseByResource("3.0/lists/57afe96172/members/852aaa9532cb36adfb5e9fef7a4206a9.txt"))
                .add(HttpMethod.GET,"https://usX.api.mailchimp.com/3.0/lists/57afe96172/members?offset=0&count=3", generateMockResponseByResource("3.0/lists/57afe96172/members.txt"))
                .add(HttpMethod.GET,"https://usX.api.mailchimp.com/3.0/lists/57afe96172/merge-fields", generateMockResponseByResource("3.0/lists/57afe96172/merge-fields.txt"))
                .add(HttpMethod.GET,"https://usX.api.mailchimp.com/3.0/lists/57afe96172/segments", generateMockResponseByResource("3.0/lists/57afe96172/segments.txt"))
                .add(HttpMethod.GET,"https://usX.api.mailchimp.com/3.0/lists/57afe96172/segments/49381", generateMockResponseByResource("3.0/lists/57afe96172/segments/49381.txt"))
                .add(HttpMethod.GET,"https://usX.api.mailchimp.com/3.0/batches/8b2428d747", generateMockResponseByResource("3.0/batches/8b2428d747.txt"))
                .add(HttpMethod.GET,"https://usX.api.mailchimp.com/3.0/batches?offset=0&count=1", generateMockResponseByResource("3.0/batches?offset=0&count=1.txt"))
                .add(HttpMethod.GET,"https://usX.api.mailchimp.com/3.0/batches?offset=1&count=1", generateMockResponseByResource("3.0/batches?offset=1&count=1.txt"));

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
    public void builder_default_returnsBuilder(){
        MailChimpClientBuilder builder = MailChimpClient.builder();
        assertNotNull(builder);
    }

    @Test
    public void getRoot_default_responseWithRootObject() {
        Root root = mailChimpClient.getRoot();
        assertEquals("8d3a3db4d97663a9074efcc16", root.getAccountId());
    }

    @Test
    public void getSubscriberList_nonExistingListId_isNull() {
        SubscriberList list = mailChimpClient.getSubscriberList("nonExistingList");
        assertNull(list);
    }

    @Test
    public void getSubscriberList_existingListId_oneList(){
        SubscriberList list = mailChimpClient.getSubscriberList("57afe96172");
        assertEquals("57afe96172", list.getId());
    }

    @Test
    public void getSubscriberLists_offset0AndCount1_filledLists(){
        SubscriberLists subscriberLists = mailChimpClient.getSubscriberLists(0, 1);
        assertEquals(1, subscriberLists.getTotalItems().intValue());
        assertEquals("57afe96172", subscriberLists.getLists().get(0).getId());
    }

    @Test
    public void getSubscriberList_offset1AndCount1_emptyList(){
        SubscriberLists subscriberLists = mailChimpClient.getSubscriberLists(1, 1);
        assertEquals(0, subscriberLists.getLists().size());
    }

    //TODO: createSubscriberList
    //TODO: removeSubscriberList

    @Test
    public void  getListMember_existingListIdAndExistingSubscruberHash_listMember(){
        Member member = mailChimpClient.getListMember("57afe96172", "852aaa9532cb36adfb5e9fef7a4206a9");
        assertEquals("57afe96172", member.getListId());
        assertEquals("852aaa9532cb36adfb5e9fef7a4206a9", member.getSubscriberHash());
    }

    @Test
    public void  getListMember_nonExistingListId_isNull(){
        Member member = mailChimpClient.getListMember("nonExistingListId", "852aaa9532cb36adfb5e9fef7a4206a9");
        assertNull(member);
    }

    @Test
    public void  getListMember_existingListIdAndNonExistingSubscruberHash_isNull(){
        Member member = mailChimpClient.getListMember("57afe96172", "nonExistingSubscriberHash");
        assertNull(member);
    }

    //TODO: updateListMember
    //TODO: removeListMember

    @Test
    public void getListMembers_nonExistingListId_isNull(){
        Members members = mailChimpClient.getListMembers("nonExistingId");
        assertNull(members);
    }

    @Test
    public void getListMembers_firstPage_filledLists(){
        Members members = mailChimpClient.getListMembers("57afe96172", 0, 3);
        assertEquals("57afe96172", members.getListId());
        assertEquals(3, members.getMembers().size());
    }

    //TODO: getListMembersByStatus, combine this into one method

    @Test
    public void getListMergeFields_nonExistingListId_isNull(){
        ListMergeFields listMergeFields = mailChimpClient.getListMergeFields("nonExistingListId");
        assertNull(listMergeFields);
    }

    @Test
    public void getListMergeFields_existingListId_listMergeFields(){
        ListMergeFields listMergeFields = mailChimpClient.getListMergeFields("57afe96172");
        assertEquals("57afe96172", listMergeFields.getListId());
        assertEquals(2, listMergeFields.getMergeFields().size());
    }

    //TODO: createMergeField
    //TODO: removeListMergeField

    @Test
    public void getSegments_nonExistingListId_isNull(){
        Segments segments = mailChimpClient.getSegments("nonExistingListId");
        assertNull(segments);
    }

    @Test
    public void getSegments_existingListId_segments(){
        Segments segments = mailChimpClient.getSegments("57afe96172");
        assertEquals("57afe96172", segments.getListId());
        assertEquals(1, segments.getTotalItems().intValue());
    }

    @Test
    public void getSegment_nonExistingListId_isNull(){
        Segment segment = mailChimpClient.getSegment("nonExistingListId", 49381);
        assertNull(segment);
    }

    @Test
    public void getSegment_existingListIdAndNonExistingSegmentId_isNull(){
        Segment segment = mailChimpClient.getSegment("57afe96172", 0);
        assertNull(segment);
    }

    @Test
    public void getSegment_existingListIdAndExistingSegmentId_segment(){
        Segment segment = mailChimpClient.getSegment("57afe96172", 49381);
        assertEquals("57afe96172", segment.getListId());
        assertEquals(49381, segment.getId().intValue());
    }

    //TODO: createSegment
    //TODO: modifySegment
    //TODO: removeSegment

    @Test
    public void getBatch_nonExistingBatchId_isNull(){
        Batch batch = mailChimpClient.getBatch("nonExistingBatchId");
        assertNull(batch);
    }

    @Test
    public void getBatch_existingBatchId_batch(){
        Batch batch = mailChimpClient.getBatch("8b2428d747");
        assertEquals("8b2428d747", batch.getId());
    }

    @Test
    public void getBatches_Offset0_filledBatchList(){
        Batches batches = mailChimpClient.getBatches(0, 1);
        assertEquals(1, batches.getBatches().size());
    }

    @Test
    public void getBatches_nonExistingPage_emptyList(){
        Batches batches = mailChimpClient.getBatches(1, 1);
        assertEquals(0, batches.getBatches().size());
    }

    //TODO: createBatch
    //TODO: removeBatch

    //TODO: searchMembers_invalidQuery_noResults
    //TODO: searchMembers_validQuery_results
    //TODO: searchMembers_validQueryAndInvalidListId_noResults
    //TODO: searchMembers_validQueryAndValidListId_results

    //TODO: lists responses as Page<T> response with page info
    //TODO: add method to get next paged response
}
