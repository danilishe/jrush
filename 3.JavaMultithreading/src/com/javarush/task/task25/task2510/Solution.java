package com.javarush.task.task25.task2510;

/* 
Поживем - увидим
*/
public class Solution extends Thread{


    public Solution() {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
    }

    public static void main(String[] args) {
    }
}
