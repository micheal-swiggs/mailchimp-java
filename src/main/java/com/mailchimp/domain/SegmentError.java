package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SegmentError {
    @JsonProperty("field")
    private String field;
    @JsonProperty("message")
    private String message;

}
