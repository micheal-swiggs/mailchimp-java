package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Micheal Swiggs
 */
@Data
public class Location {

    @JsonProperty
    private Double latitude;

    @JsonProperty
    private Double longtitude;

    @JsonProperty
    private Double gmtoff;

    @JsonProperty
    private Double dstoff;

    @JsonProperty("country_code")
    private String countryCode;

    @JsonProperty
    private String timezone;
}
