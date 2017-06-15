package com.javarush.task.task31.task3106;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {

/*        args = new String[]{
                "c:/tmp/some.mp3",
                "c:/tmp/test.003",
                "c:/tmp/test.002",
                "c:/tmp/test.001",
                "c:/tmp/test.004",
                "c:/tmp/test.006",
                "c:/tmp/test.005"
        };*/

        String resultFileName = args[0];

        Arrays.sort(args = Arrays.copyOfRange(args, 1, args.length));
        List zipParts = new ArrayList<>();
        for (String partName : args) {
            zipParts.add(new FileInputStream(partName));
        }

        byte[] buffer = new byte[1024];
        int readByteCnt;

        try (
             ZipInputStream zipIn = new ZipInputStream(new SequenceInputStream(Collections.enumeration(zipParts)));
             FileOutputStream fileOut = new FileOutputStream(new File(resultFileName))) {
            zipIn.getNextEntry();

            while ((readByteCnt = zipIn.read(buffer)) > 0) {
                fileOut.write(buffer, 0, readByteCnt);
            }
        }
    }
}