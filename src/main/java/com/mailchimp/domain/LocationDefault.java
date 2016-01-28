package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Micheal Swiggs
 */
@Data
public class LocationDefault
{
    @JsonProperty(value = JsonConstants.COUNTRY_CODE)
    private String countryCode;

}
