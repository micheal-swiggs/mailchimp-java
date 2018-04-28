package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author eamoralesl
 */

@Data
public class Segment {
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("list_id")
    private String listId;
    @JsonProperty("member_count")
    private Integer memberCount;
    @JsonProperty("name")
    private String name;
    @JsonProperty("options")
    private Options options;
    @JsonProperty("type")
    private String type;
    @JsonProperty("updated_at")
    private String updatedAt;
}
