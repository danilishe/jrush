package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.*;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        //Path passwords = downloadFile("http://nutz.narod.ru/index.html", Paths.get("c:/tmp"));
        Path passwords = downloadFile("https://www.amigo.com/ship/secretPassword.txt", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        URL url = new URL(urlString);
        Path tempFile = Files.createTempFile("-temp", ".tmp");
        Path resultFile = Paths.get(downloadDirectory.toString() + urlString.substring(urlString.lastIndexOf('/')));

        InputStream urlInputStream = url.openStream();


        Files.copy(urlInputStream, tempFile);

        if (Files.notExists(downloadDirectory))
            Files.createDirectories(downloadDirectory);
        Files.move(tempFile, resultFile);


        return resultFile;
    }
}
