package com.javarush.task.task07.task0706;

import java.util.Scanner;

/* 
Улицы и дома
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        int[] houses = new int[15];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 15; i++) {
            houses[i] = scanner.nextInt();
        }
        scanner.close();
        int even = 0;
        int odd = 0;
        for (int i = 0; i < 15; i++) {
            if (i % 2 > 0) odd += houses[i];
            else even += houses[i];
        }
        if (odd > even) System.out.println("В домах с нечетными номерами проживает больше жителей.");
        else if (even > odd) System.out.println("В домах с четными номерами проживает больше жителей.");
    }
}
