package com.mailchimp;

import com.mailchimp.domain.Batch;
import com.mailchimp.domain.CreateBatch;
import com.mailchimp.domain.ListMergeField;
import com.mailchimp.domain.ListMergeFields;
import com.mailchimp.domain.Member;
import com.mailchimp.domain.Members;
import com.mailchimp.domain.Root;
import com.mailchimp.domain.SearchMembers;
import com.mailchimp.domain.Segment;
import com.mailchimp.domain.SegmentCreate;
import com.mailchimp.domain.SegmentModified;
import com.mailchimp.domain.SegmentModify;
import com.mailchimp.domain.Segments;
import com.mailchimp.domain.StatusToLower;
import com.mailchimp.domain.SubscribeStatus;
import com.mailchimp.domain.SubscriberList;
import com.mailchimp.domain.SubscriberLists;
import feign.Param;
import feign.RequestLine;

/**
 * @author stevensnoeijen, eamoralesl
 */
public interface MailChimpClient {

    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    /**
     * API Root
     *
     * @return
     */
    @RequestLine("GET /3.0/")
    Root getRoot();

    @RequestLine("GET /3.0/lists/{list-id}/members/{subscriber-hash}")
    Member getListMember(@Param("list-id") String listId, @Param("subscriber-hash") String subscriberHash);

    @RequestLine("POST /3.0/lists/{list-id}/members")
    Member createListMember(@Param("list-id") String listId, Member member);

    @RequestLine("PUT /3.0/lists/{list-id}/members/{subscriber-hash}")
    Member updateListMember(@Param("list-id") String listId, @Param("subscriber-hash") String subscriberHash, Member member);

    @RequestLine("DELETE /3.0/lists/{list-id}/members/{subscriber-hash}")
    void removeListMember(@Param("list-id") String listId, @Param("subscriber-hash") String subscriberHash);

    /**
     * Create a new list in your MailChimp account.
     *
     * @param list
     * @return created list
     */
    @RequestLine("POST /3.0/lists")
    SubscriberList createList(SubscriberList list);

    @RequestLine("DELETE /3.0/lists/{list-id}")
    void removeList(@Param("list-id") String listId);

    @RequestLine("GET /3.0/lists/{list-id}")
    SubscriberList getList(@Param("list-id") String listId);

    @RequestLine("GET /3.0/lists")
    SubscriberLists getLists();

    @RequestLine("GET /3.0/lists?offset={offset}&count={count}")
    SubscriberLists getLists(@Param("offset") Integer offset, @Param("count") Integer count);

    @RequestLine("GET /3.0/lists/{list-id}/members")
    Members getListMembers(@Param("list-id") String listId);

    @RequestLine("GET /3.0/lists/{list-id}/members?offset={offset}&count={count}")
    Members getListMembers(@Param("list-id") String listId, @Param("offset") Integer offset, @Param("count") Integer count);

    @RequestLine("GET /3.0/lists/{list-id}/members?offset={offset}&count={count}&status={status}")
    Members getListMembersByStatus(@Param("list-id") String listId, @Param("offset") Integer offset, @Param("count") Integer count, @Param(value = "status", expander = StatusToLower.class) SubscribeStatus status);

    @RequestLine("GET /3.0/lists/{list-id}/merge-fields")
    ListMergeFields getListMergeFields(@Param("list-id") String listId);

    @RequestLine("POST /3.0/lists/{list-id}/merge-fields")
    ListMergeField createMergeField(@Param("list-id") String listId, ListMergeField mergeField);

    @RequestLine("DELETE /lists/{list-id}/merge-fields/{merge-id}")
    void removeListMergeField(@Param("list-id") String listId, @Param("merge-id") String mergeId);

    @RequestLine("POST /3.0/lists/{list-id}/segments")
    Segment createSegment(@Param("list-id") String listId, SegmentCreate request);

    @RequestLine("POST /3.0/lists/{list-id}/segments/{segment-id}")
    SegmentModified modifySegment(@Param("list-id") String listId, @Param("segment-id") Integer segmentId, SegmentModify request);

    @RequestLine("GET /3.0/lists/{list-id}/segments")
    Segments getSegments(@Param("list-id") String listId);

    @RequestLine("GET /3.0/lists/{list-id}/segments/{segment-id}")
    Segment getSegment(@Param("list-id") String listId, @Param("segment-id") Integer segmentId);

    @RequestLine("DELETE /3.0/lists/{list-id}/segments/{segment-id}")
    void removeSegment(@Param("list-id") String listId, @Param("segment-id") Integer segmentId);

    @RequestLine("POST /3.0/batches")
    Batch createBatch(CreateBatch batch);

    @RequestLine("GET /3.0/batches/{batch-id}")
    Batch getBatch(@Param("batch-id") String batchId);

    @RequestLine("GET /3.0/batches?offset={offset}&count={count}")
    java.util.List<Batch> getBatches(@Param("offset") Integer offset, @Param("count") Integer count);

    @RequestLine("DELETE /3.0/batches/{batch-id}")
    void removeBatch(@Param("batch-id") String batchId);

    @RequestLine("GET /3.0/search-members?query={query}")
    SearchMembers searchMembers(@Param("query") String query);

    @RequestLine("GET /3.0/search-members?query={query}&list_id={listId}")
    SearchMembers searchMembers(@Param("query") String query, @Param("listId") String listId);
}
