package com.javarush.task.task32.task3202;

import java.io.*;
import java.nio.charset.Charset;

/* 
Читаем из потока
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("c:/tmp/1.txt"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        StringWriter stringWriter = new StringWriter();
        if (is != null) {
            InputStreamReader isr = new InputStreamReader(is, Charset.defaultCharset());

            char[] buffer = new char[2048];
            int len;
            while ((len = isr.read(buffer)) > 0) {
                stringWriter.write(buffer, 0, len);
            }
        }
        return stringWriter;
    }
}