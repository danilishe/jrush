package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> fileTree = new ArrayList<>();
        Stack<File> stack = new Stack<>();
        stack.push(new File(root));
        while (!stack.empty()) {
            File dir = stack.pop();
            for (File file : dir.listFiles()) {
                if (file.isDirectory()) stack.push(file);
                else fileTree.add(file.getAbsolutePath());
            }
        }
        return fileTree;

    }

    public static void main(String[] args) throws Exception{
        System.out.println(getFileTree("c:/tmp/test/"));
    }
}
