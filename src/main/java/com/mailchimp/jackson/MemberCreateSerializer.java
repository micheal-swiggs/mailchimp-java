package com.mailchimp.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.mailchimp.domain.Member;
import java.io.IOException;

public class MemberCreateSerializer extends JsonSerializer<Member> {

    @Override
    public void serialize(Member value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(value);
        jgen.writeString(json);
    }

}
