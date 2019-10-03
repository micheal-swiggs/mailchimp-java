package com.mailchimp.query;

import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @see <a href="https://mailchimp.com/developer/reference/reports/#get_/reports">https://mailchimp.com/developer/reference/reports/#get_/reports</a>
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ReportsQuery {

    @Getter
    private Integer count;

    @Getter
    private Integer offset;

    @Getter
    private ZonedDateTime since_send_time;
}
