package com.mailchimp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @author Micheal Swiggs
 */
@Data
public class LocationDefault
{
    @JsonProperty(value = JsonConstants.COUNTRY_CODE)
    private String countryCode;

}
