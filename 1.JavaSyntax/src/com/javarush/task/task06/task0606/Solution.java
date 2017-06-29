package com.javarush.task.task06.task0606;

import java.io.*;
import java.util.Scanner;

/*
Чётные и нечётные циферки
*/

public class Solution {

    public static int even;
    public static int odd;

    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        for (int i = 0; i < input.length(); i++) {
            int dig = Integer.parseInt(input.substring(i, i + 1));
            if (dig % 2 > 0) odd++;
            else even++;
        }
        System.out.println("Even: "+ even + " Odd: " + odd);
    }
}
