package com.javarush.task.task30.task3013;

/* 
Набираем код
*/

public class Solution {
    int  number = 2048 + 256;
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Integer.toBinaryString(number));
        number |= number >> 1;
        System.out.println(Integer.toBinaryString(number));
        number |= number >> 2;
        System.out.println(Integer.toBinaryString(number));
        number |= number >> 4;
        System.out.println(Integer.toBinaryString(number));
        number |= number >> 8;
        System.out.println(Integer.toBinaryString(number));
        number |= number >> 8;
        System.out.println(Integer.toBinaryString(number));
        number |= number >> 8;
        System.out.println(Integer.toBinaryString(number));
        number &= ~(number >> 1);
        System.out.println(Integer.toBinaryString(number));

    }


}