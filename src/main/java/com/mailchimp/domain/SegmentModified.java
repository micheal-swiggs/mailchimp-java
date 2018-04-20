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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public List<SegmentError> getErrors() {
        return errors;
    }

    public void setErrors(List<SegmentError> errors) {
        this.errors = errors;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public List<Member> getMembersAdded() {
        return membersAdded;
    }

    public void setMembersAdded(List<Member> membersAdded) {
        this.membersAdded = membersAdded;
    }

    public List<Member> getMembersRemoved() {
        return membersRemoved;
    }

    public void setMembersRemoved(List<Member> membersRemoved) {
        this.membersRemoved = membersRemoved;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTotalAdded() {
        return totalAdded;
    }

    public void setTotalAdded(Integer totalAdded) {
        this.totalAdded = totalAdded;
    }

    public Integer getTotalRemoved() {
        return totalRemoved;
    }

    public void setTotalRemoved(Integer totalRemoved) {
        this.totalRemoved = totalRemoved;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
