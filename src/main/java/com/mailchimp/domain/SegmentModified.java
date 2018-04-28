package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author eamoralesl
 */

@Data
public class SegmentModified {
    @JsonProperty("detail")
    private String detail;
    @JsonProperty("error_count")
    private Integer errorCount;
    @JsonProperty("errors")
    private List<SegmentError> errors;
    @JsonProperty("instance")
    private String instance;
    @JsonProperty("members_added")
    private List<Member> membersAdded;
    @JsonProperty("members_removed")
    private List<Member> membersRemoved;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("title")
    private String title;
    @JsonProperty("total_added")
    private Integer totalAdded;
    @JsonProperty("total_removed")
    private Integer totalRemoved;
    @JsonProperty("type")
    private String type;

}
