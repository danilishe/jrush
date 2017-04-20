package com.javarush.task.task25.task2506;

/**
 * Created by danilishe on 03.04.2017.
 */
public class LoggingStateThread extends Thread {
    private Thread thread;
    private Thread.State state;
    public LoggingStateThread(Thread t) {
        this.thread = t;
        state = t.getState();
    }
    public void run() {
        System.out.println(state.name());
        while (state != State.TERMINATED) {
            if (state != thread.getState())     {

                state = thread.getState();

                System.out.println(state.name());
            }
        }
    }
}
