package com.mailchimp;

import com.mailchimp.domain.ListDefault;
import com.mailchimp.domain.MemberDefault;
import com.mailchimp.domain.MemberListDefault;
import com.mailchimp.domain.MergeField;
import com.mailchimp.domain.MergeFieldsDefault;

import feign.*;
import feign.jackson.*;

public interface MailChimpClient {

    //@RequestLine("GET /3.0/lists")
    //Object callLists();

    //@RequestLine("GET /3.0/lists/{list-id}")
    //public Object callList(@Param("list-id") String listId);

    @RequestLine("GET /3.0/lists/{list-id}/members/{user-id}")
    public MemberDefault getListMember(@Param("list-id") String listId, @Param("user-id") String userId);

    @RequestLine("POST /3.0/lists/{list-id}/members")
    public MemberDefault createMember (@Param("list-id") String listId, MemberDefault memberDefault);

    @RequestLine("PUT /3.0/lists/{list-id}/members/{subscriber-hash}")
    public MemberDefault updateMember (@Param("list-id") String listId, @Param("subscriber-hash") String subscriberHash, MemberDefault memberDefault);

    @RequestLine("GET /3.0/lists/{list-id}")
    public ListDefault getMembersList(@Param("list-id") String listId);

    @RequestLine("GET /3.0/lists/{list-id}/members")
    public MemberListDefault getAllListMembers(@Param("list-id") String listId);

    @RequestLine("GET /3.0/lists/{list-id}/members?offset={offset}&count={count}")
    public MemberListDefault getPagedListMembers(@Param("list-id") String listId, @Param("offset") Integer offset, @Param("count") Integer count);

    @RequestLine("GET /3.0/lists/{list-id}/merge-fields?count={count}")
    public MergeFieldsDefault getListMergeFields(@Param("list-id") String listId, @Param("count") Integer count);

    @RequestLine("POST /3.0/lists/{list-id}/merge-fields")
    public MergeField createMergeField(@Param("list-id") String listId, MergeField mergeField);
}
