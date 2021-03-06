package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by danilishe on 29.05.2017.
 */
public class Tablet extends Observable {
    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }

    static Logger logger = Logger.getLogger(Tablet.class.getName());
    final int number;

    public Tablet(int num) {
        number = num;
    }

    public Order createOrder() {

        Order order = null;
        try {

            order = new Order(this);

        } catch (IOException ioe) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        } finally {
            if (!order.isEmpty()) {
                setChanged();
                try {
                    new AdvertisementManager(order.getTotalCookingTime() * 60).processVideos();
                } catch (NoVideoAvailableException nvae) {
                    logger.log(Level.INFO, "No video is available for the order " + order);
                }
                notifyObservers(order);
            }
        }
        return order;
    }

}
