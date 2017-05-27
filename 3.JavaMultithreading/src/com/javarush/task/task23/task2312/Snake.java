package com.javarush.task.task23.task2312;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Данил on 25.02.2017.
 */
public class Snake {
    private List<SnakeSection> sections;
    private boolean isAlive;
    private SnakeDirection direction;

    public Snake(int x, int y) {
        sections = new ArrayList<>();  // создаём туловище
        sections.add(new SnakeSection(x, y));  // добавляем первый кусочек
        this.isAlive = true; // змея жива
    }
    public void checkBorders(SnakeSection head){
        int x = head.getX();
        int y = head.getY();
        int width = Room.game.getWidth();
        int height = Room.game.getHeight();
        if (x >= width || x < 0 || y >= height || y < 0)
            isAlive = false;
    }

    public void checkBody(SnakeSection head){
        if (sections.contains(head)) isAlive = false;
    }
    /*
    *
    Задание:
    а) реализуй методы equals и hashCode в классе SnakeSection.
    б) реализуй метод checkBorders(SnakeSection head): если голова змеи за границами комнаты — змея умирает (isAlive = false)
    в) реализуй метод checkBody(SnakeSection head): если голова змеи пересекается с ее телом — змея умирает (isAlive = false)


    * */
    public void move() {
        if (isAlive) {
            if (direction == SnakeDirection.UP) move(0, -1);
            if (direction == SnakeDirection.RIGHT) move(1, 0);
            if (direction == SnakeDirection.DOWN) move(0, 1);
            if (direction == SnakeDirection.LEFT) move(-1, 0);
        }
    }

    public void move(int x, int y) {

    }

    public SnakeDirection getDirection() {
        return direction;
    }

    public void setDirection(SnakeDirection direction) {
        this.direction = direction;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public List<SnakeSection> getSections() {
        return sections;
    }

    public int getX() {
        return sections.get(0).getX();
    }

    public int getY() {
        return sections.get(0).getY();
    }
}
