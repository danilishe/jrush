package com.javarush.task.task31.task3113;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

/*
Что внутри папки?
*/
public class Solution {
    static int fileTotal = 0;
    static int folderTotal = 0;
    static long totalSize = 0L;

    public static void main(String[] args) throws IOException {
        Scanner console = new Scanner(System.in);
        String fileName = console.nextLine();
        console.close();

        Path file = Paths.get(fileName);
        if (!Files.isDirectory(file))
            System.out.println(file.toString() + " - не папка");
        else {
            Files.walkFileTree(file, new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    folderTotal++;
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    fileTotal++;
                    totalSize += attrs.size();
                    return FileVisitResult.CONTINUE;
                }
            });
            System.out.println("Всего папок - " + (folderTotal - 1));
            System.out.println("Всего файлов - " + fileTotal);
            System.out.println("Общий размер - " + totalSize);
        }
    }
}
