package com.mailchimp;

import com.mailchimp.domain.*;
import feign.Param;
import feign.RequestLine;

public interface MailChimpClient {

    @RequestLine("GET /3.0/")
    Account getAccount();

    @RequestLine("GET /3.0/lists/{list-id}/members/{subscriber-hash}")
    Member getListMember(@Param("list-id") String listId, @Param("subscriber-hash") String subscriberHash);

    @RequestLine("POST /3.0/lists/{list-id}/members")
    Member createListMember(@Param("list-id") String listId, Member member);

    @RequestLine("PUT /3.0/lists/{list-id}/members/{subscriber-hash}")
    Member updateListMember(@Param("list-id") String listId, @Param("subscriber-hash") String subscriberHash, Member member);

    @RequestLine("DELETE /3.0/lists/{list-id}/members/{subscriber-hash}")
    void removeListMember(@Param("list-id") String listId, @Param("subscriber-hash") String subscriberHash);

    @RequestLine("POST /3.0/lists")
    List createList(List listDefault);

    @RequestLine("DELETE /3.0/lists/{list-id}")
    void removeList(@Param("list-id") String listId);

    @RequestLine("GET /3.0/lists")
    Lists getLists();

    @RequestLine("GET /3.0/lists/{list-id}")
    List getListMembers(@Param("list-id") String listId);

    @RequestLine("GET /3.0/lists/{list-id}/members?offset={offset}&count={count}")
    Members getListMembers(@Param("list-id") String listId, @Param("offset") Integer offset, @Param("count") Integer count);

    @RequestLine("GET /3.0/lists/{list-id}/merge-fields?count={count}")
    ListMergeFields getListMergeFields(@Param("list-id") String listId, @Param("count") Integer count);

    @RequestLine("POST /3.0/lists/{list-id}/merge-fields")
    ListMergeField createMergeField(@Param("list-id") String listId, ListMergeField mergeField);

    @RequestLine("POST /3.0/batches")
    Batch createBatch(CreateBatch batch);

    @RequestLine("GET /3.0/batches/{batch-id}")
    Batch getBatch(@Param("batch-id") String batchId);

    @RequestLine("GET /3.0/batches?offset={offset}&count={count}")
    java.util.List<Batch> getBatches(@Param("offset") Integer offset, @Param("count") Integer count);

    @RequestLine("DELETE /3.0/batches/{batch-id}")
    void removeBatch(@Param("batch-id") String batchId);
}
