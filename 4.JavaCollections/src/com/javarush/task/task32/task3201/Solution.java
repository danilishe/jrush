package com.javarush.task.task32.task3201;

import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) throws Exception {
        //args = new String[] {"c:/tmp/1.txt", "32423", "very long text for adding to file"};
        String fileName = args[0];
        long number = Long.parseLong(args[1]);
        String text = args[2];
        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        if (raf.length() < number) number = raf.length();

        raf.seek(number);
        raf.write(text.getBytes());
        raf.close();
    }
}
