package com.mailchimp.domain;

import lombok.Data;

/**
 * @author kimvsparrow
 */
@Data
public class Tag {

    public enum TagStatus {
        active, inactive
    }

    private String name;
    private TagStatus status;
}
