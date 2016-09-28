package com.mailchimp.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.mailchimp.MailChimpClient;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author stevensnoeijen
 */
public final class MailChimpZonedDateTimeSerializer extends JsonSerializer<ZonedDateTime> {

    private final DateTimeFormatter formatter;

    public MailChimpZonedDateTimeSerializer() {
        formatter = DateTimeFormatter.ofPattern(MailChimpClient.DATETIME_FORMAT);
    }

    @Override
    public void serialize(final ZonedDateTime value, final JsonGenerator gen, final SerializerProvider arg2) throws IOException, JsonProcessingException {
        String stringValue = formatter.format(value);
        gen.writeString(stringValue);
    }
}
