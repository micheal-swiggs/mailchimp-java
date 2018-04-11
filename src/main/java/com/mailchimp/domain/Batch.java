package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mailchimp.jackson.MailChimpZonedDateTimeDeserializer;
import java.time.ZonedDateTime;
import lombok.Data;

@Data
public class Batch {

    @JsonProperty("id")
    private String id;

    @JsonProperty("status")
    private String status;

    @JsonProperty("total_operations")
    private Integer totalOperations;

    @JsonProperty("finished_operations")
    private Integer finishedOperations;

    @JsonProperty("errored_operations")
    private Integer erroredOperations;

    @JsonProperty("submitted_at")
    @JsonDeserialize(using = MailChimpZonedDateTimeDeserializer.class)
    private ZonedDateTime submittedAt;

    @JsonProperty("completed_at")
    @JsonDeserialize(using = MailChimpZonedDateTimeDeserializer.class)
    private ZonedDateTime completedAt;

    @JsonProperty("response_body_url")
    private String responseBodyUrl;
}
