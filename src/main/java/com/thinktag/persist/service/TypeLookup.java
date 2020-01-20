package com.thinktag.persist.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinktag.persist.model.Address;
import com.thinktag.persist.model.Customer;

import java.util.Collection;
import java.util.List;

public final class TypeLookup {

    public static <T> long getId(T t, String type) {
        switch (type) {
            case "Address":
                return ((Address) t).getId();
            case "Customer":
                return ((Customer) t).getId();
        }
        throw new IllegalArgumentException("Invalid type");
    }

    public static Class<?> findClass(String type) {
        switch (type) {
            case "Address":
                return Address.class;
            case "Customer":
                return Customer.class;
        }
        throw new IllegalArgumentException("Invalid type");
    }

    public static <T> T getObject(String json, Class<?> cl) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.readValue(json, (Class<T>) cl);
    }

    public static String writeJson(Object ob) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(ob);
    }

    public static String writeJsonList(Collection<Object> ob) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.writerFor(new TypeReference<List<Object>>(){}).writeValueAsString(ob);
    }
}
