package com.mailchimp.jackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import feign.Response;
import feign.Util;
import feign.codec.Decoder;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Collections;

public class JacksonDecoder implements Decoder {

    private final ObjectMapper mapper;

    public JacksonDecoder() {
        this(Collections.<Module>emptyList());
    }

    public JacksonDecoder(Iterable<Module> modules) {
        this(new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModules(modules));
    }

    public JacksonDecoder(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Object decode(Response response, Type type) throws IOException {
        if (response.status() == 404) {
            return Util.emptyValueOf(type);
        }
        if (response.body() == null) {
            return null;
        }

        InputStream inputStream = response.body().asInputStream();
        try {
            return mapper.readValue(inputStream, mapper.constructType(type));
        } catch (RuntimeJsonMappingException e) {
            if (e.getCause() != null && e.getCause() instanceof IOException) {
                throw IOException.class.cast(e.getCause());
            }
            throw e;
        }
    }
}
