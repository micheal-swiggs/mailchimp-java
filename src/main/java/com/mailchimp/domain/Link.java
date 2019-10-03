package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;

@Getter
public class Link {

    @JsonProperty("rel")
    private String rel;

    @JsonProperty("href")
    private String href;

    @JsonProperty("method")
    private String method;

    @JsonProperty("targetSchema")
    private String targetSchema;

    @JsonProperty("schema")
    private String schema;

    public enum Rel {
        PARENT("parent"),
        SELF("self"),
        MEMBERS("members"),
        CLICK_DETAILS("click-details"),
        CAMPAIGN("campaign");

        private String title;

        Rel(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }
    }

    public static Link getLink(List<Link> links, Rel targetLink) {
        if (links == null || targetLink == null) {
            return null;
        }
        for (Link link : links) {
            if (link.getRel().equals(targetLink.getTitle())) {
                return link;
            }
        }
        return null;
    }
}
