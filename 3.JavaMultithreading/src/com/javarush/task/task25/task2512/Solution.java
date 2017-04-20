package com.javarush.task.task25.task2512;

/* 
Живем своим умом
*/
public class Solution extends Thread implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        getDeepCause(e);
        e.printStackTrace();
    }

    void getDeepCause (Throwable e) {
        if (e.getCause() != null) this.getDeepCause(e.getCause());
        System.out.println(e.getClass().toString().substring(6) + ": " + e.getMessage());
    }

    public void run() {
       try {
           throw new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")));
       } catch (Exception e) {
            getDeepCause(e);
       }
    }

    public static void main(String[] args) {
        new Solution().start();
    }
}
