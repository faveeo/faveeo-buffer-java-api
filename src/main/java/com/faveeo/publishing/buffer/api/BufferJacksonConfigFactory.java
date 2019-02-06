package com.faveeo.publishing.buffer.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;

public class BufferJacksonConfigFactory {

    private static ObjectMapper objectMapper;

    /**
     * Returns or creates an object Mapper.
     * @return the jackson object mapper.
     */
    public static ObjectMapper getObjectMapper() {
        if (objectMapper != null) {
            return objectMapper;
        }
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JodaModule());
        objectMapper.registerModule(new Jdk8Module());
        final SimpleModule simpleModule = new SimpleModule();
        //simpleModule.addDeserializer(BufferMediaItemRepresentation[].class, new BufferMediaDeserializer());
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }

}
