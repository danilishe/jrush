package com.javarush.task.task22.task2210;


import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {
        for (String s :
                getTokens("Some.texted.text . dgg", ".")) {
            System.out.println(s);

        }


    }
    public static String [] getTokens(String query, String delimiter) {
        StringTokenizer stringTokenizer = new StringTokenizer(query, delimiter);
        String[] list = new String[stringTokenizer.countTokens()];
        int count = 0;
        while (stringTokenizer.hasMoreTokens())
            list[count++] = stringTokenizer.nextToken();

        return list;
    }
}
