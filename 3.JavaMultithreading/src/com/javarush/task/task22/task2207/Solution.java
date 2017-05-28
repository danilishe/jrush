package com.javarush.task.task22.task2207;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws Exception{

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String fileName = console.readLine();
        console.close();

        BufferedReader file = new BufferedReader(new FileReader(fileName));
        List<String> words = new ArrayList<>();
        while (file.ready()) {
            words.addAll(
                    Arrays.asList(
                            file.readLine().split(" ")));
        }
        file.close();

        do {
            String word = words.get(0);
            words.remove(0);
            String reversedWord = new StringBuilder(word).reverse().toString();
            if (words.contains(reversedWord)) {
                Pair foundedPair = new Pair();
                foundedPair.first = word;
                foundedPair.second = reversedWord;
                result.add(foundedPair);

                words.remove(words.indexOf(reversedWord));
            }

        } while (words.size() > 0);


        for(Pair pair: result) {
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
