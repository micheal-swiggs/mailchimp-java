package com.mailchimp.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.mailchimp.MailChimpClient;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author stevensnoeijen
 */
public final class MailChimpZonedDateTimeSerializer extends JsonSerializer<ZonedDateTime> {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    @Override
    public void serialize(final ZonedDateTime value, final JsonGenerator gen, final SerializerProvider arg2) throws IOException, JsonProcessingException {
        if (value == null) {
            gen.writeString("");
        } else {
            String stringValue = dateTimeFormatter.format(value);
            gen.writeString(stringValue);
        }
    }
}
