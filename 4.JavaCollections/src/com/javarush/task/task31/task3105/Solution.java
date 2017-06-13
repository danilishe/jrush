package com.javarush.task.task31.task3105;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
//        String fileName = "c:/tmp/base.dat";
//        String zipName = "c:/tmp/test2.zip";
        String fileName = args[0];
        String zipName = args[1];
        ZipEntry newFile = new ZipEntry("new" + fileName.substring(fileName.lastIndexOf('/')));
        Path tempFile = Files.createTempFile(null, null);
        ZipInputStream inputStream = new ZipInputStream(new FileInputStream(zipName));
        ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(tempFile.toString()));
        ZipEntry zipEntry;
        while ((zipEntry = inputStream.getNextEntry()) != null) {
            if (zipEntry.getName().equals(newFile.getName().toString())) continue;
            outputStream.putNextEntry(zipEntry);
            if (!zipEntry.isDirectory()) {
                while (inputStream.available() > 0) {
                    byte[] entry = new byte[1024];
                    int len = inputStream.read(entry);
                    if (len > 0)
                        outputStream.write(entry, 0, len);
                }
                outputStream.closeEntry();
                inputStream.closeEntry();
            }
        }
        inputStream.close();

        //ZipEntry newDir = new ZipEntry("new");

        outputStream.putNextEntry(newFile);

        Path inputFile = Paths.get(fileName);
        byte[] content = Files.readAllBytes(inputFile);
        outputStream.write(content);
        outputStream.closeEntry();
        outputStream.close();

        Files.move(tempFile, Paths.get(zipName), StandardCopyOption.REPLACE_EXISTING);
    }
}
