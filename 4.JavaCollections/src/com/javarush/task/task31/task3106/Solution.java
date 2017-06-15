package com.javarush.task.task31.task3106;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) {
        byte[] buffer = new byte[8024];
        int len;

/*        args = new String[] {
                "c:/tmp/some.mp3",
                "c:/tmp/test.003",
                "c:/tmp/test.002",
                "c:/tmp/test.001",
                "c:/tmp/test.004",
                "c:/tmp/test.006",
                "c:/tmp/test.005"
        };*/

        String resultFileName = args[0];
        List<String> fileNamePart = new ArrayList<>();
        fileNamePart.addAll(Arrays.asList(Arrays.copyOfRange(args, 1, args.length - 1)));
        Collections.sort(fileNamePart);

        OutputStream outputStream = null;
        try {
            Path temFile = Files.createTempFile(null, null);
            outputStream = Files.newOutputStream(temFile);
            for (String part :
                    fileNamePart) {
                InputStream inputStream = Files.newInputStream(Paths.get(part));

                while((len = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, len);
                }
                inputStream.close();
            }
            outputStream.close();

            ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(temFile));
            OutputStream newFile = Files.newOutputStream(Paths.get(resultFileName));
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            while ( (len = zipInputStream.read(buffer)) > 0) {
                newFile.write(buffer, 0, len);
            }
            zipInputStream.closeEntry();
            zipInputStream.close();
            newFile.close();

        } catch (Exception ignore) {}
    }
}
