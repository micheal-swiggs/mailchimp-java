package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Micheal Swiggs
 */
public enum MergeType
{
    Text("text"),
    Number("number"),
    Radio("radio"),
    Birthday("birthday"),
    Date("date"),
    Dropdown("dropdown"),
    Address("address"),
    Zip("zip"),
    Phone("phone"),
    URL("url"),
    ImageURL("imageurl");

    private String value;

    MergeType(final String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }
}
