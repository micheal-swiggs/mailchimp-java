package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
/**
 * @author eamoralesl
 */
//TODO Implement dynamic segment creation  http://developer.mailchimp.com/documentation/mailchimp/reference/lists/segments/#%20
@Data
public class SegmentCreate {
    @JsonProperty("name")
    private String name;
    @JsonProperty("static_segment")
    private List<String> staticSegment = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getStaticSegment() {
        return staticSegment;
    }

    public void setStaticSegment(List<String> staticSegment) {
        this.staticSegment = staticSegment;
    }
}
