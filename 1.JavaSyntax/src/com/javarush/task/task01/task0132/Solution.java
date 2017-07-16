package com.javarush.task.task01.task0132;

/* 
Сумма цифр трехзначного числа
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(sumDigitsInNumber(546));
    }

    public static int sumDigitsInNumber(int number) {
        //напишите тут ваш код
        int result = 0;
        String num = String.valueOf(number);
        for (int i = 0; i < num.length(); i++) {
            result += Integer.parseInt(num.substring(i, i + 1));
        }
        return result;
    }
}