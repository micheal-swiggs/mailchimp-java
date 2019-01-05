package com.mailchimp;

import feign.Param;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Page {
    @Getter
    private Integer offset;

    @Getter
    private Integer count;

    public static Page first(){
        return new Page(null, null);
    }
}
