package com.javarush.task.task33.task3303;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/* 
Десериализация JSON объекта
*/
public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new FileReader(new File(fileName)), clazz);

    }

    public static void main(String[] args) {
 //       System.out.println(convertFromJsonToNormal("c:/tmp/cat.txt", Cat.class));
    }

/*    @JsonAutoDetect
    public static class Cat {
        @JsonProperty("wildAnimal")
        public String name;

        @JsonIgnore
        public int age;

        @JsonProperty("over")
        public int weight;

        Cat() {
        }

        @Override
        public String toString() {
            return "Cat{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", weight=" + weight +
                    '}';
        }
    }*/
}
