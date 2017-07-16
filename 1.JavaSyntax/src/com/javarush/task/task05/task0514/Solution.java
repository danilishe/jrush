package com.javarush.task.task05.task0514;

/* 
Программист создает человека
*/

public class Solution {

    public static void main(String[] args) {
        //напишите тут ваш код
        Person person = new Person();
        person.initialize("dsfg", 34);
    }

    static class Person {
        String name;
        int age;

        //напишите тут ваш код
        public void initialize(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
