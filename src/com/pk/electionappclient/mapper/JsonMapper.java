package com.pk.electionappclient.mapper;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;

public class JsonMapper {

    public static <T> T mapToObject(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        T object = objectMapper.readValue(json, new TypeReference<T>(){});
        return object;
    }

    public static <T> String mapToJson(T object) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
