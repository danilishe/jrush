package com.javarush.task.task33.task3304;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.util.Arrays;

/* 
Конвертация из одного класса в другой используя JSON
*/
public class Solution {
    public static void main(String[] args) throws IOException {

        Second s = (Second) convertOneToAnother(new First(), Second.class);
        First f = (First) convertOneToAnother(new Second(), First.class);
    }

    public static Object convertOneToAnother(Object one, Class resultClassObject) throws IOException {

        ObjectMapper om = new ObjectMapper();
        StringWriter stringWriter = new StringWriter();
        om.writeValue(stringWriter, one);

        String oneClassName = one.getClass().getSimpleName().toLowerCase();
        String resultClassName = resultClassObject.getSimpleName().toLowerCase();
        String jsonStr = stringWriter.toString().replaceFirst(oneClassName, resultClassName);

        StringReader stringReader = new StringReader(jsonStr);
        Object result = om.readValue(jsonStr, resultClassObject);
        return result;
        /*
//        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(one);



        String oneClassAnnotation = one.getClass().getAnnotations()[1].toString();
        String oneClassJsonName = oneClassAnnotation.substring(oneClassAnnotation.indexOf("name=") + 5, oneClassAnnotation.indexOf(","));
//        System.out.println(oneClassJsonName);

        String toClassAnnotation = resultClassObject.getAnnotations()[1].toString();
        String toClassJsonName = toClassAnnotation.substring(toClassAnnotation.indexOf("name=") + 5, toClassAnnotation.indexOf(","));
//        System.out.println(toClassJsonName);

        String newJson = json.replaceAll(oneClassJsonName, toClassJsonName);
        Object result = mapper.readValue(newJson, resultClassObject);

        return result;*/
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME,  property="className")
    @JsonSubTypes(@JsonSubTypes.Type(value=First.class,  name="first"))
    public static class First {
        public int i;
        public String name;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME,  property="className")
    @JsonSubTypes(@JsonSubTypes.Type(value=Second.class, name="second"))
    public static class Second {
        public int i;
        public String name;
    }
}
