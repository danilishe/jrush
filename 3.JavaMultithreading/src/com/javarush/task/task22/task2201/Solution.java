package com.javarush.task.task22.task2201;

/* 
Строки нитей или строковые нити? Вот в чем вопрос
*/
public class Solution {
    public static void main(String[] args) {
        new Solution();
    }

    public static final String FIRST_THREAD_NAME = "1#";
    public static final String SECOND_THREAD_NAME = "2#";

    private Thread thread1;
    private Thread thread2;
    private Thread thread3;

    public Solution() {
        initThreads();
    }

    protected void initThreads() {
        this.thread1 = new Thread(new Task(this, "A\tB\tC\tD\tE\tF\tG\tH\tI"), FIRST_THREAD_NAME);
        this.thread2 = new Thread(new Task(this, "J\tK\tL\tM\tN\tO\tP\tQ\tR\tS\tT\tU\tV\tW\tX\tY\tZ"), SECOND_THREAD_NAME);
        this.thread3 = new Thread(new Task(this, "\t\t"), "3#");

        Thread.setDefaultUncaughtExceptionHandler(new ThisUncaughtExceptionHandler());

        this.thread1.start();
        this.thread2.start();
        this.thread3.start();
    }

    public synchronized String getPartOfString(String string, String threadName) {

/*
        1. Метод getPartOfString должен возвращать подстроку между первой и
        последней табуляцией строки string переданной ему в качестве первого параметра.
        2. В случае некорректных данных метод getPartOfString должен бросить исключение
        TooShortStringFirstThreadException, если имя трэда(threadName) Solution.FIRST_THREAD_NAME.
        3. В случае некорректных данных метод getPartOfString должен бросить исключение
        TooShortStringSecondThreadException, если имя трэда(threadName) Solution.SECOND_THREAD_NAME.
        4. В случае некорректных данных метод getPartOfString должен бросить исключение
        RuntimeException, если имя трэда(threadName) не Solution.FIRST_THREAD_NAME или Solution.SECOND_THREAD_NAME.
*/
        String result = "";
        try {
            result = string.substring(string.indexOf("\t") + 1, string.lastIndexOf("\t"));
        } catch (Exception e) {
            switch (threadName) {
                case FIRST_THREAD_NAME:
                    TooShortStringFirstThreadException e1 = new TooShortStringFirstThreadException();
                    e1.initCause(e);
                    throw e1;
                case SECOND_THREAD_NAME:
                    TooShortStringSecondThreadException e2 = new TooShortStringSecondThreadException();
                    e2.initCause(e);
                    throw e2;
                default:
                    RuntimeException e3 = new RuntimeException();
                    e3.initCause(e);
                    throw e3;
            }
        }
        return result;
    }
}
