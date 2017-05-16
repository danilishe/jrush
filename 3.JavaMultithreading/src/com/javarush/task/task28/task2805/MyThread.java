package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by danilishe on 10.05.2017.
 */
public class MyThread extends Thread {

    private void setPriority() {

        if (Solution.threadCount.incrementAndGet() > 10) Solution.threadCount.set(1);


        if (getThreadGroup().getName() != null) {
            if (Solution.threadCount.get() > getThreadGroup().getMaxPriority())
                setPriority(getThreadGroup().getMaxPriority());
            else setPriority(Solution.threadCount.get());
        } else setPriority(Solution.threadCount.get());


    }

    public MyThread() {
        setPriority();
    }

    public MyThread(Runnable target) {
        super(target);
        setPriority();
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        setPriority();
    }

    public MyThread(String name) {
        super(name);
        setPriority();
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        setPriority();
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        setPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        setPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        setPriority();
    }
}
