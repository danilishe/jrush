package com.javarush.task.task08.task0812;

import java.io.*;
import java.util.*;

/* 
Cамая длинная последовательность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        ArrayList<Integer> list = new ArrayList<>();

        Scanner console = new Scanner(System.in);

        for (int i = 0; i < 10; i++) {
            list.add(console.nextInt());
        }
        console.close();

        int longitude = 1;
        int max = 0;
        for (int i = 1; i < 10; i++) {
            boolean numberSame = list.get(i).equals(list.get(i - 1));
            if (numberSame) longitude++;
                else longitude = 1;
            if (max < longitude) max = longitude;
        }

        System.out.println(max);
    }
}