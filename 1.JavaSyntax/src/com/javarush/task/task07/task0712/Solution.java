package com.javarush.task.task07.task0712;


import java.util.ArrayList;
import java.util.*;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        ArrayList<String> list = new ArrayList<>(10);
        //напишите тут ваш код
        int longestLength = 0, longestPos = 0;
        int shortestLength = Integer.MAX_VALUE, shortestPos = 0;

        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            String s = scanner.nextLine();
            if (s.length() > longestLength) {
                longestLength = s.length();
                longestPos = i;
            }
            if (s.length() < shortestLength) {
                shortestLength = s.length();
                shortestPos = i;
            }
            list.add(s);
        }

        if (shortestPos < longestPos) System.out.println(list.get(shortestPos));
        else System.out.println(list.get(longestPos));
    }
}
