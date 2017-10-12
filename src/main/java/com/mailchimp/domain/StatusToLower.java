package com.mailchimp.domain;

import feign.Param;


/**
 * Used for the searchByStatus: <a href="https://github.com/OpenFeign/feign#custom-param-expansion">Custom @Param Expansion</a>
 *
 * @author eamoralesl
 */

public class StatusToLower implements Param.Expander {
    @Override
    public String expand(Object value) {
        SubscribeStatus status = (SubscribeStatus) value;
        return status.name().toLowerCase();
    }
}
