package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;

/**
 * @author YukiFujisawa
 * @see <a href="https://us20.api.mailchimp.com/schema/3.0/Definitions/Reports/Response.json">/schema/3.0/Definitions/Reports/Response.json</a>
 */
@Getter
public class Reports {

    @JsonProperty("reports")
    private List<Report> reports;

}
