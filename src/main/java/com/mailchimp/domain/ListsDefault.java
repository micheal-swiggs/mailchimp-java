package com.mailchimp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class ListsDefault
{
  @JsonProperty(value = JsonConstants.LISTS)
  private List<ListDefault> lists;

  @JsonProperty(value = JsonConstants.TOTAL_ITEMS)
  private Integer totalItems;
}
