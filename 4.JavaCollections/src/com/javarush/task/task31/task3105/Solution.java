package com.javarush.task.task31.task3105;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
/*    public static void main(String[] args) throws IOException {
        Map<ZipEntry, ByteArrayOutputStream> contentOfZipFile = new HashMap<>();

        String fullFileName = "c:/tmp/base.dat";
        String zipName = "c:/tmp/test2.zip";
//        String fullFileName = args[0];
//        String zipName = args[1];

        String fileName = fullFileName.substring(fullFileName.lastIndexOf('/'));

        ZipInputStream inputStream = new ZipInputStream(new FileInputStream(zipName));

        boolean containsName = false;

        ZipEntry zipEntry;
        while ((zipEntry = inputStream.getNextEntry()) != null) {
            if (!zipEntry.isDirectory()) {
                if (zipEntry.getName().contains(fileName)) {
                    containsName = true;
                    byte[] newFile = Files.readAllBytes(Paths.get(fullFileName));
                    contentOfZipFile.put(zipEntry, newFile);
                } else {
                    int len = 0;
                    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                    byte[] entry = new byte[2048];
                    while ((len = inputStream.read(entry)) > 0) {
                        buffer.write(entry, 0, len);
                        contentOfZipFile.put(zipEntry, buffer);
                    }
                }
            }
            inputStream.closeEntry();
        }
        inputStream.close();

        ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(zipName));

        if (!containsName) { // Добавляем файл, если он НЕ содержался в зип архиве
            ZipEntry newFile = new ZipEntry("new" + fileName);
            byte[] newFileContent = Files.readAllBytes(Paths.get(fullFileName));
            contentOfZipFile.put(newFile, newFileContent);
            outputStream.closeEntry();
        }

        for (Map.Entry<ZipEntry, byte[]> entry: contentOfZipFile.entrySet()) { // Добавляем все файлы из зип архива, в т.ч. с подменой
            outputStream.putNextEntry(entry.getKey());
            outputStream.write(entry.getValue());
            outputStream.closeEntry();
        }

        outputStream.close();
    }*/
}
