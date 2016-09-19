/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 *
 * @author stevensnoeijen
 */
@Data
public class Batches {

    @JsonProperty("batches")
    private List<Batch> batches;

    @JsonProperty("total_items")
    private Integer totalItems;

}
