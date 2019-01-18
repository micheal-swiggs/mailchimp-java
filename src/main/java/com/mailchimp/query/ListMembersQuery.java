package com.mailchimp.query;

import com.mailchimp.domain.SubscribeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListMembersQuery {

    @Getter
    private Integer offset;

    @Getter
    private Integer count;

    @Getter
    private SubscribeStatus status;

    public static ListMembersQuery all(){
        return new ListMembersQuery();
    }

    public static ListMembersQuery firstPage(){
        return new ListMembersQuery();
    }
}
