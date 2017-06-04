package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danilishe on 29.05.2017.
 */
public class ConsoleHelper {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        String result = bufferedReader.readLine();
        return result;
    }

    public static List<Dish> getAllDishesForOrder() throws IOException{
        List<Dish> orderList = new ArrayList<>();

        String consoleInput = "";
        while (true) {
            writeMessage(Dish.allDishesToString());
            consoleInput = readString();

            if ("exit".equals(consoleInput)) break;

            boolean isDish = false;
            for (Dish dish : Dish.values()) {
                if (consoleInput.equals(dish.name())) {
                    orderList.add(Dish.valueOf(consoleInput));
                    isDish = true;
                }
            }
            if (!isDish) writeMessage("Такого блюда нет в нашем ресторане :(");
        }

        return orderList;
    }
}
