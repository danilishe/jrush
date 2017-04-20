package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by danilishe on 19.04.2017.
 *
 *  ConcurrentHashMap ключ и значение, где ключ — счетчик начиная с 1, значение — фраза:
 *  «Some text for i» , пример «Some text for 1«.
 2. при возникновении исключения выводить в консоль:
 «[TREAD_NAME] thread was terminated«, пример «[thread-1] thread was terminated«.
 *
 */
public class Producer implements Runnable {
    private ConcurrentHashMap<String, String>  map;
    private int i = 0;

    public Producer(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }
    @Override
    public void run() {
        while (true) {
            try {
                map.put("" + ++i, "Some text for " + i);
                Thread.sleep(500);

            } catch (Exception e) {
                System.out.println("[" + Thread.currentThread().getName() + "] thread was terminated");
                break;
            }
        }

    }
}
