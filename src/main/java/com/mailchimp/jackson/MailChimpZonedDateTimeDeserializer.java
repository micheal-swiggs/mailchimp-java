package com.mailchimp.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.mailchimp.MailChimpClient;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author stevensnoeijen
 */
public final class MailChimpZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {

    private final DateTimeFormatter formatter;

    public MailChimpZonedDateTimeDeserializer() {
        formatter = DateTimeFormatter.ofPattern(MailChimpClient.DATETIME_FORMAT);
    }

    @Override
    public ZonedDateTime deserialize(final JsonParser parser, final DeserializationContext context) throws IOException, JsonProcessingException {
        final String stringDate = parser.getText();
        ZonedDateTime date = ZonedDateTime.parse(stringDate, formatter);
        return date;
    }

}
