package com.javarush.task.task27.task2712.kitchen;

/**
 * Created by danilishe on 29.05.2017.
 */
public enum Dish {
    Fish, Steak, Soup, Juice, Water;

    public static String allDishesToString() {
        StringBuffer result = new StringBuffer();
        for (Dish dish :
                Dish.values()) {
            result.append(dish.toString() + ", ");
        }

        return result.substring(0, result.length() - 2);
    }
}
