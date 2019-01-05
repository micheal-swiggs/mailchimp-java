package com.mailchimp.query;

import com.mailchimp.domain.SubscribeStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
public class ListMembersQuery {

    @Getter
    private Integer offset;

    @Getter
    private Integer count;

    @Getter
    private SubscribeStatus status;

    public static ListMembersQuery all(){
        return new ListMembersQuery(null, null, null);
    }
}
