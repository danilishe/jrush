package com.javarush.task.task27.task2712.ad;

import java.util.Comparator;

/**
 * Created by Данил on 08.06.2017.
 */
public class Advertisement implements Comparable {
    private Object content;
    private String name;
    private long initialAmount;
    private int hits;
    private int duration; // длина в секундах
    private long moneyPerSecond;
    private long amountPerOneDisplaying;

    public String getName() {
        return name;
    }

    public int getHits() {
        return hits;
    }

    public int getDuration() {
        return duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        amountPerOneDisplaying = initialAmount / hits;
        moneyPerSecond = amountPerOneDisplaying * 1000 / duration;
    }

    public void revalidate() throws UnsupportedOperationException {
        if (hits <= 0) throw new UnsupportedOperationException();
        else hits--;
    }

    @Override
    public int compareTo(Object o) {
        Advertisement other = (Advertisement) o;
        if (other.amountPerOneDisplaying == this.amountPerOneDisplaying) {
            return (int) (other.moneyPerSecond - this.moneyPerSecond);
        }
        return (int) (other.amountPerOneDisplaying - this.amountPerOneDisplaying);
    }

    @Override
    public String toString() {
        return name + " is displaying... " + amountPerOneDisplaying + ", " + moneyPerSecond + " in min:" + (int) duration/60;
    }
}
