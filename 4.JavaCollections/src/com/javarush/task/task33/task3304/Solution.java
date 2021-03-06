package com.javarush.task.task33.task3304;

import com.fasterxml.jackson.annotation.JsonProperty;
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
        StringWriter writer = new StringWriter();
        om.writeValue(writer, one);

        String oneClassName = one.getClass().getSimpleName().toLowerCase();
        String resultClassName = resultClassObject.getSimpleName().toLowerCase();
        String jsonStr = writer.toString().replaceFirst(oneClassName, resultClassName);

 //       StringReader stringReader = new StringReader(jsonStr);
        Object result = om.readValue(jsonStr, resultClassObject);
 //       return result;

//        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, one);
        System.out.println(writer.toString());
        return mapper.readValue(writer.toString().replace(one.getClass().getName().toLowerCase(), resultClassObject.getName().toLowerCase()), resultClassObject);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME,  property="className")
    @JsonSubTypes(@JsonSubTypes.Type(value=First.class,  name="first"))
    public static class First {
        @JsonProperty
        public int i;
        @JsonProperty
        public String name;

        @Override
        public String toString() {
            return "First{" +
                    "i=" + i +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME,  property="className")
    @JsonSubTypes(@JsonSubTypes.Type(value=Second.class, name="second"))
    public static class Second {
        @JsonProperty
        public int i;
        @JsonProperty
        public String name;

        @Override
        public String toString() {
            return "Second{" +
                    "i=" + i +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
