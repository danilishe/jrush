package com.javarush.task.task05.task0507;

/* 
Среднее арифметическое
*/

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        int n = 0;
        double result = 0;
        int c = 0;
        Scanner scanner = new Scanner(System.in);
        while (n  != -1) {
            n = scanner.nextInt();
            if (n == -1) break;
            c++;
            result += n;
        }
        result /= c;
        System.out.println(result);
    }
}

