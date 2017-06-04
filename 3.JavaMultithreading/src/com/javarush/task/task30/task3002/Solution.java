package com.javarush.task.task30.task3002;

/* 
Осваиваем методы класса Integer
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        int radx = 10;
        if (s.startsWith("0")) radx = 8;
        if (s.startsWith("0x")) {
            radx = 16;
            s = s.substring(2);
        }
        if (s.startsWith("0b")) {
            radx = 2;
            s = s.substring(2);
        }

        Integer result = Integer.parseInt(s, radx); //напишите тут ваш код
        return result.toString();
    }
}
