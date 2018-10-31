package com.mailchimp.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kimvsparrow
 */
@Data
public class Tags {
    private List<Tag> tags = new ArrayList<>();
}
