package com.mailchimp;

import com.mailchimp.domain.Root;
import com.mailchimp.domain.SubscriberList;
import feign.Request;
import feign.Response;
import feign.mock.HttpMethod;
import feign.mock.MockClient;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import feign.mock.RequestHeaders;
import feign.mock.RequestKey;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
public class MailChimpClientTest {

    private String AUTHORIZATION_HEADER_VALUE = "Basic " + Base64.encodeBase64String("anystring:apikey".getBytes());

    private MockClient mockClient;
    private MailChimpClient mailChimpClient;

    private static InputStream getResponseResourceAsStream(String name) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("responses/" + name);
    }

    private static Response generateMockRequestByResource(String resourceName)
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

        //create a empty request
        Request request = Request.create(Request.HttpMethod.GET, "https://usX.api.mailchimp.com/3.0/", Collections.emptyMap(), Request.Body.empty());

        //create response
        Response response = Response.builder()
                .request(request)
                .status(statusCode)
                .headers(headers)
                .body(bodyString, Charset.defaultCharset())
                .build();
        return response;
    }

    @Before
    public void setup() throws IOException {
        mockClient = new MockClient()
                //root
                .add(HttpMethod.GET,"https://usX.api.mailchimp.com/3.0/", generateMockRequestByResource("root.txt"))
                .add(HttpMethod.GET,"https://usX.api.mailchimp.com/3.0/lists/nonExistingList", 404);

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
    public void getList_nonExistingList_isNull() {
        SubscriberList list = mailChimpClient.getList("nonExistingList");
        assertNull(list);
    }

    @Test
    public void getRoot_default_responseWithRootObject() {
        Root root = mailChimpClient.getRoot();
        assertEquals("8d3a3db4d97663a9074efcc16", root.getAccountId());
    }

    //TODO tests:
    //getListMember
    //updateListMember
    //removeListMember
    //createList (SubscriberList)
    //removeList (SubscriberList)
    //getList (SubscriberList)
    //getLists (SubscriberList)
    //getListMembers
    //getListMembersByStatus
    //getListMergeFields
    //createMergeField
    //removeListMergeField
    //createSegment
    //modifySegment
    //getSegments
    //getSegment
    //removeSegment
    //createBatch
    //getBatch
    //getBatches
    //removeBatch
    //removeBatch
    //searchMembers
}
