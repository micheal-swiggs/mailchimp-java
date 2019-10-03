package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;

@Getter
public class ClickReportsMembers {

    @JsonProperty("members")
    private List<ClickDetailMember> members;

    @JsonProperty("campaign_id")
    private String campaignId;

    @JsonProperty("total_items")
    private Integer totalItems;

    @Getter
    public static class ClickDetailMember {

        @JsonProperty("email_id")
        private String emailId;

        @JsonProperty("email_address")
        private String emailAddress;

        @JsonProperty("merge_fields")
        private MergeField mergeFields;

        @JsonProperty("list_id")
        private String listId;

    }

    @Getter
    public static class MergeField {

        @JsonProperty("FNAME")
        private String fName;

        @JsonProperty("LNAME")
        private String lName;

        @JsonProperty("ADDRESS")
        private String address;

        @JsonProperty("PHONE")
        private String phone;

        @JsonProperty("BIRTHDAY")
        private String birthday;

        @JsonProperty("MMERGE6")
        private String mMerge6;

        @JsonProperty("MMERGE7")
        private String mMerge7;

        @JsonProperty("MMERGE8")
        private String mMerge8;

        @JsonProperty("MMERGE9")
        private String mMerge9;

        @JsonProperty("MMERGE10")
        private String mMerge10;

        @JsonProperty("MMERGE11")
        private String mMerge11;

        @JsonProperty("MMERGE12")
        private String mMerge12;

        @JsonProperty("MMERGE13")
        private String mMerge13;

        @JsonProperty("MMERGE14")
        private String mMerge14;

        @JsonProperty("MMERGE15")
        private String mMerge15;

        @JsonProperty("MMERGE16")
        private String mMerge16;

        @JsonProperty("MMERGE17")
        private String mMerge17;

        @JsonProperty("MMERGE18")
        private String mMerge18;
    }
}
