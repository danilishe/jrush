package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
    }


    public void createExpression(int number) {

        String s = number + " =";
        int s1 = 1;
        while (number > 0) {
            int q = number / 3;
            int r = number % 3;

            if (r == 1) {
                s += " + " + s1;
            }

            if (r == 2) {
                s += " - " + s1;
                q++;
            }
            number = q;
            s1 *= 3;
        }
        System.out.println(s);
        // 1234 = + 1 - 9 + 27 - 243 - 729 + 2187


    }
}