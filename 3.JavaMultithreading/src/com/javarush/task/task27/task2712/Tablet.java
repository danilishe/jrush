package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Order;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by danilishe on 29.05.2017.
 */
public class Tablet {
    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
    static Logger logger = Logger.getLogger(Tablet.class.getName());
    final int number;
    public Tablet(int num){
        number = num;
    }
    public void createOrder(){
        try {
            new Order(this);
        } catch (IOException ioe) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }
}
