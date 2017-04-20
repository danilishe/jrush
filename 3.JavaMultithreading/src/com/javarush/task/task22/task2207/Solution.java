package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();


    public static void main(String[] args) throws Exception {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String fileName = console.readLine();
        console.close();

        fileName = "c:/tmp/2.txt";

        List<String> allWords = new ArrayList<>();

        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        StringBuilder fileData = new StringBuilder();
        while (fileReader.ready()) {
            fileData.append(fileReader.readLine());
        }
        fileReader.close();

        for (String string : fileData.toString().split(" ")) {
            if (string.trim() != "") allWords.add(string);
        }



//        for (Pair pair :
//                allWords) {
//            System.out.println(pair);
//        }

        for (int i = 0; i  < allWords.size(); i++) {
            for (int j = i + 1 ; j < allWords.size(); j++) {
                StringBuilder thisWord = new StringBuilder(allWords.get(i));
                    if (thisWord.reverse().toString().equals(allWords.get(j))) {
                        Pair pair = new Pair();
                        pair.first = allWords.get(i);
                        pair.second = allWords.get(j);
                        result.add(pair);
                        allWords.remove(j);
                        allWords.remove(i);
                    }
                }
        }

        for (Pair pair :
                result) {
            System.out.println(pair);
        }

    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
