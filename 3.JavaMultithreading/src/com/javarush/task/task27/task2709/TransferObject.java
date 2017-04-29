package com.javarush.task.task27.task2709;

public class TransferObject {
    private int value;
    protected volatile boolean isValuePresent = false; //use this variable

    public synchronized int get() {
        synchronized (this) {
            while (!isValuePresent){
                try {
                    this.wait();
                } catch (InterruptedException ignore) {}
            }
            System.out.println("Got: " + value);
            isValuePresent = false;

            this.notify();

            return value;
        }
    }

    public synchronized void put(int value) {
        synchronized (this) {
            while (isValuePresent) {
                try {
                    this.wait();
                } catch (InterruptedException ignore) {}
            }
            this.value = value;
            System.out.println("Put: " + value);
            isValuePresent = true;
            this.notify();
        }
    }
}
