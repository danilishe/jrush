package com.javarush.task.task21.task2113;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Данил on 09.04.2017.
 */
public class Hippodrome {
    private List<Horse> horses;
    public static Hippodrome game;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void move() {
        for (Horse horse :
                horses) {
            horse.move();
        }
    }
    public Horse getWinner() {
        Horse winner = horses.get(0);
        for (Horse horse :
                horses) {
            if (horse.getDistance() > winner.getDistance())
                winner = horse;
        }
        return winner;
    }

    public void printWinner() {
        System.out.format("Winner is %s!", getWinner().getName());
    }


    public void run() throws InterruptedException{
        for (int i = 0; i < 100; i++) {
            move();
            print();
            Thread.sleep(200);
        }


    }

    public void print() {
        for (Horse horse :
                horses) {
            horse.print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public static void main(String[] args) throws InterruptedException{
         game = new Hippodrome(Arrays.asList(
                 new Horse ("Васька", 3, 0),
                 new Horse ("Петька", 3, 0),
                 new Horse ("Димка", 3, 0))
         );
         game.run();
         game.printWinner();
    }
}
