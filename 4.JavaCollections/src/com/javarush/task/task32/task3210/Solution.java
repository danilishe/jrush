package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws Exception{

//        args = new String[] {"c:/tmp/1.txt", "10", "text"};

        String fileName = args[0];
        long number = Long.parseLong(args[1]);
        String text = args[2];
        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        raf.seek(number);
        byte[] readed = new byte[text.length()];
        raf.read(readed, 0, text.length());
        raf.seek(raf.length());

//        System.out.println(text);
//        System.out.println(new String(readed));

        raf.write((text.equals(new String(readed))? "true" : "false").getBytes());
    }
}
