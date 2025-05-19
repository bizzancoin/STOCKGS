package com.xc.utils.redis;


import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;

import org.codehaus.jackson.map.DeserializationConfig;

import org.codehaus.jackson.map.ObjectMapper;

import org.codehaus.jackson.map.SerializationConfig;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import org.codehaus.jackson.type.JavaType;

import org.codehaus.jackson.type.TypeReference;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;


public class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);


    private static ObjectMapper objectMapper = new ObjectMapper();


    static {

        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.ALWAYS);


        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);


        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);


        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));


        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    }


    public static <T> String obj2String(T obj) {

        if (obj == null) {

            return null;

        }

        try {

            return (obj instanceof String) ? (String) obj : objectMapper.writeValueAsString(obj);

        } catch (Exception e) {

            log.warn("XC JsonUtil Parse obj to string error ", e);

            return null;

        }

    }


    public static <T> String obj2StringPretty(T obj) {

        if (obj == null) {

            return null;

        }

        try {

            return (obj instanceof String) ? (String) obj : objectMapper

                    .writerWithDefaultPrettyPrinter().writeValueAsString(obj);

        } catch (Exception e) {

            log.warn("XC JsonUtil Parse obj to string error ", e);

            return null;

        }

    }


    public static <T> T string2Obj(String str, Class<T> clazz) {

        if (StringUtils.isEmpty(str) || clazz == null) {

            return null;

        }

        try {

            return (T) (clazz.equals(String.class) ? str : objectMapper.readValue(str, clazz));

        } catch (Exception e) {

            log.warn("XC 1 JsonUtil Parse string to obj error", e);

            return null;

        }

    }


    public static <T> T string2Obj(String str, TypeReference<T> typeReference) {

        if (StringUtils.isEmpty(str) || typeReference == null) {

            return null;

        }

        try {

            return (T) (typeReference.getType().equals(String.class) ? str : objectMapper

                    .readValue(str, typeReference));

        } catch (Exception e) {

            log.warn("XC 2 JsonUtil Parse string to obj error", e);

            return null;

        }

    }


    public static <T> T string2Obj(String str, Class<?> collectionClass, Class... elementClasses) {

        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);


        try {

            return (T) objectMapper.readValue(str, javaType);

        } catch (Exception e) {

            log.warn("XC 3 JsonUtil Parse string to obj error", e);

            return null;

        }

    }


    public static void main(String[] args) {
    }

}
