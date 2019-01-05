package com.mailchimp.query;

import lombok.Builder;
import lombok.Getter;

@Builder
public class BatchesQuery {
    @Getter
    private Integer offset;

    @Getter
    private Integer count;
}
