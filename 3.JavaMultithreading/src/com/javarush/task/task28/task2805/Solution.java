package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

/*
Приоритеты в Threads
*/
public class Solution {
    static volatile AtomicInteger threadCount = new AtomicInteger(0);
    public static void main(String[] args) {
        for (int i = 0; i < 12; i++) {
            System.out.print(new MyThread().getPriority() + " ");
        }
        //output
        //1 2 3 4 5 6 7 8 9 10 1 2

        System.out.println();
        ThreadGroup group = new ThreadGroup("someName");
        group.setMaxPriority(7);
        for (int i = 0; i < 12; i++) {
            System.out.print(new MyThread(group, "name" + i).getPriority() + " ");
        }
        //output
        //3 4 5 6 7 7 7 7 1 2 3 4
    }

}
