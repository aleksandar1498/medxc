package com.dxc.medxc.rest.utils;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

/**
 * Jackson's ObjectMapper configuration.
 */
@Provider
public final class MedxcObjectMapper implements ContextResolver<ObjectMapper> {

    private final ObjectMapper mapper;

    /**
     * Create and configure Jackson's ObjectMapper instance.
     */
    public MedxcObjectMapper() {
        mapper = createObjectMapper();
    }

    @Override
    public ObjectMapper getContext(final Class<?> type) {
        return mapper;
    }

    //@formatter:off
    private static ObjectMapper createObjectMapper() {
        return new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // should work without mixins .addMixIn(DoctorDTO.class, DoctorMixin.class);
    }
    //@formatter:on
}
