package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

/**
 * Created by danilishe on 29.05.2017.
 */
public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;
    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        dishes = ConsoleHelper.getAllDishesForOrder();
    }
    public String toString() {
        if (dishes.size() > 0) {
            StringBuilder orderList = new StringBuilder();
            if (dishes.size() > 0) {
                for (Dish dish : dishes) {
                    orderList.append(dish.name() + ", ");
                }
                return String.format("Your order: [%s] of %s",
                orderList.subSequence(0, orderList.length()-1),
                        tablet.toString());
            }
        }
        return "";
    }
}
