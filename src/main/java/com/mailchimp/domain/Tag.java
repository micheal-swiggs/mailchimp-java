package com.mailchimp.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 * @author kimvsparrow
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Tag {

    public enum TagStatus {
        active, inactive
    }

    private String name;
    private TagStatus status;
}
