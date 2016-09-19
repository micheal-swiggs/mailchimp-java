package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class CreateBatch<T> {

    @JsonProperty
    private List<Operation<T>> operations;
}
