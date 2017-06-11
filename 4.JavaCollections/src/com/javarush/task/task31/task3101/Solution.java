package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

/*
Проход по дереву файлов
*/
public class Solution {
    static ArrayList<File> fileList = new ArrayList<>();

    public static void main(String[] args) {

        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        File outputFile = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath, outputFile);
        try (BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(outputFile))) {
            getFileList(path);

            fileList.sort(new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

//        System.out.println(fileList); // Test

            for (File f : fileList) {
                try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(f))) {
                    byte[] content = new byte[input.available()];
                    input.read(content);
                    output.write(content,0, content.length);
                    output.write('\n');
                } catch (IOException ignore) {
                }
            }
            output.close();
        } catch (IOException ignore) {
        }

    }

    public static void getFileList(File file) {
        if (file.isDirectory()) {
            for (File f :
                    file.listFiles()) {
                if (f.isDirectory()) getFileList(f);
                else {
                    if (f.length() > 50) FileUtils.deleteFile(f);
                    else fileList.add(f);
                }
            }
        }

    }

    public static void deleteFile(File file) {
        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
    }
}
