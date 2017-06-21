package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] digits = "0123456789".getBytes();
        byte[] smallCaps = "abcdefghijklmnopqrstuvwxyz".getBytes();
        byte[] bigCaps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes();
        byte[] caps;
        for (int i = 0; i < 8; i++) {
            byte rnd = (byte) (i % 3); //(byte) (Math.random() * 3);
            if (rnd == 0) caps = digits;
            else if (rnd == 1) caps = smallCaps;
            else caps = bigCaps;
            result.write(caps[(int) (Math.random() * caps.length)]);
        }
        return result;
    }
}