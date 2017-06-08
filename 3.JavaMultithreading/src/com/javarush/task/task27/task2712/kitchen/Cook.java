package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by danilishe on 30.05.2017.
 */
public class Cook extends Observable implements Observer {
    String name;

    @Override
    public String toString() {
        return name;
    }

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable tablet, Object order) {
        ConsoleHelper.writeMessage("Start cooking - " + order);
        setChanged();
        notifyObservers(order);
    }
}
