package com.javarush.task.task29.task2909.car;

/**
 * Created by Данил on 28.05.2017.
 */
public class Sedan extends Car {
    public Sedan(int numberOfPassengers){
        super(SEDAN, numberOfPassengers);
    }

    @Override
    public int getMaxSpeed() {
        return MAX_SEDAN_SPEED;
    }
}
