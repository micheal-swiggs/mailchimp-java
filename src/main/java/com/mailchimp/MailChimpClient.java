package com.mailchimp;

import com.mailchimp.domain.*;
import feign.Param;
import feign.RequestLine;

public interface MailChimpClient {
    //@RequestLine("GET /3.0/lists/{list-id}")
    //public Object callList(@Param("list-id") String listId);

    @RequestLine("GET /3.0/")
    AccountDefault getAccount();

    @RequestLine("GET /3.0/lists/{list-id}/members/{subscriber-hash}")
    Member getListMember(@Param("list-id") String listId, @Param("subscriber-hash") String subscriberHash);

    @RequestLine("POST /3.0/lists/{list-id}/members")
    Member createMember(@Param("list-id") String listId, Member member);

    @RequestLine("PUT /3.0/lists/{list-id}/members/{subscriber-hash}")
    Member updateMember(@Param("list-id") String listId, @Param("subscriber-hash") String subscriberHash, Member memberDefault);

    @RequestLine("DELETE /3.0/lists/{list-id}/members/{subscriber-hash}")
    void removeMember(@Param("list-id") String listId, @Param("subscriber-hash") String subscriberHash);

    @RequestLine("POST /3.0/lists")
    ListDefault createList(ListDefault listDefault);

    @RequestLine("DELETE /3.0/lists/{list-id}")
    void removeList(@Param("list-id") String listId);

    @RequestLine("GET /3.0/lists")
    ListsDefault getLists();

    @RequestLine("GET /3.0/lists/{list-id}")
    ListDefault getMembersList(@Param("list-id") String listId);

    @RequestLine("GET /3.0/lists/{list-id}/members?offset={offset}&count={count}")
    MemberListDefault getPagedListMembers(@Param("list-id") String listId, @Param("offset") Integer offset, @Param("count") Integer count);

    @RequestLine("GET /3.0/lists/{list-id}/merge-fields?count={count}")
    MergeFieldsDefault getListMergeFields(@Param("list-id") String listId, @Param("count") Integer count);

    @RequestLine("POST /3.0/lists/{list-id}/merge-fields")
    MergeField createMergeField(@Param("list-id") String listId, MergeField mergeField);

    @RequestLine("POST /3.0/batches")
    Batch createMembersBatch(MemberCreateBatch batch);

    @RequestLine("GET /3.0/batches/{batch-id}")
    Batch getBatch(@Param("batch-id") String batchId);
}
