package com.mailchimp;

import com.mailchimp.domain.*;
import feign.Param;
import feign.RequestLine;

/**
 * @throws MailChimpErrorException when an error occors with the call (runtimeexception)
 * @author stevensnoeijen
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

    @RequestLine("GET /3.0/lists/{list-id}/members")
    Members getListMembers(@Param("list-id") String listId);

    @RequestLine("GET /3.0/lists/{list-id}/members?offset={offset}&count={count}")
    Members getListMembers(@Param("list-id") String listId, @Param("offset") Integer offset, @Param("count") Integer count);

    @RequestLine("GET /3.0/lists/{list-id}/merge-fields")
    ListMergeFields getListMergeFields(@Param("list-id") String listId);

    @RequestLine("POST /3.0/lists/{list-id}/merge-fields")
    ListMergeField createMergeField(@Param("list-id") String listId, ListMergeField mergeField);

    @RequestLine("DELETE /lists/{list-id}/merge-fields/{merge-id}")
    void removeListMergeField(@Param("list-id") String listId, @Param("merge-id") String mergeId);

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
