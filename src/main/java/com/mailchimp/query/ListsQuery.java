package com.mailchimp.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListsQuery {

    @Getter
    private Integer offset;

    @Getter
    private Integer count;

    public static ListsQuery firstPage(){
        return new ListsQuery();
    }
}
