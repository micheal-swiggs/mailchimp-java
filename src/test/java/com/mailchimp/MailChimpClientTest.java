package com.mailchimp;

import com.mailchimp.domain.Root;
import com.mailchimp.domain.SubscriberList;
import com.mailchimp.domain.SubscriberLists;
import feign.Request;
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
                .add(HttpMethod.GET,"https://usX.api.mailchimp.com/3.0/", generateMockResponseByResource("root.txt"))
                .add(HttpMethod.GET,"https://usX.api.mailchimp.com/3.0/lists/nonExistingList", 404)
                .add(HttpMethod.GET,"https://usX.api.mailchimp.com/3.0/lists/57afe96172", generateMockResponseByResource("lists/57afe96172.txt"))
                .add(HttpMethod.GET,"https://usX.api.mailchimp.com/3.0/lists?offset=1&count=1", generateMockResponseByResource("lists_offset-1.txt"))
                .add(HttpMethod.GET,"https://usX.api.mailchimp.com/3.0/lists?offset=2&count=1", generateMockResponseByResource("lists_offset-2.txt"));

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
    public void getSubscriberLists_offset1AndCount1_filledLists(){
        SubscriberLists subscriberLists = mailChimpClient.getSubscriberLists(1, 1);
        assertEquals(1, subscriberLists.getTotalItems().intValue());
        assertEquals("57afe96172", subscriberLists.getLists().get(0).getId());
    }

    @Test
    public void getSubscriberLists_nonExistendPage_emptyList(){
        SubscriberLists subscriberLists = mailChimpClient.getSubscriberLists(2, 1);
        assertEquals(0, subscriberLists.getLists().size());
    }

    //TODO: createSubscriberList
    //TODO: removeSubscriberList

    //TODO: getListMember_nonExistingListId_isNull
    //TODO: getListMember_existingListId_listMember
    //TODO: updateListMember
    //TODO: removeListMember
    //TODO: getListMembers_nonExistingListId_isNull
    //TODO: getListMembers_firstPage_filledLists
    //TODO: getListMembersByStatus, combine this into one method

    //TODO: getListMergeFields_nonExistingListId_isNull
    //TODO: getListMergeFields_existingListId_listMergeFields
    //TODO: createMergeField
    //TODO: removeListMergeField

    //TODO: createSegment
    //TODO: modifySegment
    //TODO: getSegments_nonExistingListId_isNull
    //TODO: getSegments_existingListId_segments
    //TODO: getSegment_nonExistingListId_isNull
    //TODO: getSegment_existingListIdAndNonExistingSegmentId_isNull
    //TODO: getSegment_existingListIdAndExistingSegmentId_segment
    //TODO: removeSegment

    //TODO: createBatch
    //TODO: getBatch_nonExistingBatchId_isNull
    //TODO: getBatch_existingBatchId_batch
    //TODO: getBatches_firstPage_filledBatchList
    //TODO: getBatches_nonExistingPage_emptyList
    //TODO: removeBatch

    //TODO: searchMembers_invalidQuery_noResults
    //TODO: searchMembers_validQuery_results
    //TODO: searchMembers_validQueryAndInvalidListId_noResults
    //TODO: searchMembers_validQueryAndValidListId_results

    //TODO: lists responses as Page<T> response with page info
    //TODO: add method to get next paged response
}
