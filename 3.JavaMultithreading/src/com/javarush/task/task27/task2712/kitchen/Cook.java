package com.javarush.task.task27.task2712.kitchen;

/**
 * Created by danilishe on 30.05.2017.
 */
public class Cook {
    String name;

    @Override
    public String toString() {
        return name;
    }

    public Cook(String name) {
        this.name = name;
    }
}
