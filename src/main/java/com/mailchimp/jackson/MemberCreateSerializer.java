package com.mailchimp.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.mailchimp.domain.MemberCreate;

import java.io.IOException;

public class MemberCreateSerializer extends JsonSerializer<MemberCreate>
{
    @Override
    public void serialize(MemberCreate value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException
    {

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(value);
        jgen.writeString(json);
    }

}
