package com.javarush.task.task28.task2802;


import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* 
Пишем свою ThreadFactory
*/
public class Solution {

    public static AtomicInteger factoryNumber = new AtomicInteger(0);

    public static void main(String[] args) {
        class EmulateThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulateThreadFactoryTask());

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulateThreadFactoryTask());

        thread.start();
        thread2.start();
    }


    private static void emulateThreadFactory() {
        AmigoThreadFactory factory = new AmigoThreadFactory();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
    }

    /*1. Реализация интерфейсного метода — создайте и верните трэд, который должен:
1.1. не быть демоном,
1.2. иметь нормальный приоритет,
1.3. имя трэда должно иметь шаблон «GN-pool-A-thread-B«,
где GN — это имя группы,
A — это номер фабрики инкрементируется в пределах класса начиная с 1, используйте AtomicInteger,
B — номер треда инкрементируется в пределах конкретной фабрики начиная с 1, используйте AtomicInteger.
2. Каждая фабрика должна иметь ту группу тредов (ThreadGroup), в которой она была создана.*/

    public static class AmigoThreadFactory implements ThreadFactory {
        private AtomicInteger threadNumber = new AtomicInteger(0);
        private int factoryNum;
        public  AmigoThreadFactory() {
            // добавляем при создании фабрики
            factoryNum = factoryNumber.incrementAndGet();
        }
        @Override
        public Thread newThread(Runnable r) {

            Thread newThread = new Thread(r);
            newThread.setDaemon(false);
            newThread.setPriority(Thread.NORM_PRIORITY);

            String gn = newThread.getThreadGroup().getName();
            int b = threadNumber.incrementAndGet();
            newThread.setName(String.format("%s-pool-%d-thread-%d", gn, factoryNum, b));
            return newThread;
        }
    }

}
