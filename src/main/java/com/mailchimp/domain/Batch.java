package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mailchimp.jackson.FlexDateDeserializer;
import lombok.Data;

import java.util.Date;

@Data
public class Batch
{
    @JsonProperty(value = JsonConstants.ID)
    private String id;

    @JsonProperty(value = JsonConstants.STATS)
    private String status;

    @JsonProperty(value = JsonConstants.TOTAL_OPERATIONS)
    private Integer totalOperations;

    @JsonProperty(value = JsonConstants.FINISHED_OPERATIONS)
    private Integer finishedOperations;

    @JsonProperty(value = JsonConstants.ERRORED_OPERATIONS)
    private Integer erroredOperations;

    @JsonProperty(value = JsonConstants.SUBMITTED_AT)
    @JsonDeserialize(using = FlexDateDeserializer.class)
    private Date submittedAt;

    @JsonProperty(value = JsonConstants.COMPLETED_AT)
    @JsonDeserialize(using = FlexDateDeserializer.class)
    private Date completedAt;

    @JsonProperty(value = JsonConstants.RESPONSE_BODY_URL)
    private String responseBodyUrl;
}
