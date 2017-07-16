package com.javarush.task.task22.task2213;

/**
 * Created by Данил on 25.06.2017.
 */
public class Figure {
    private int x;
    private int y;

    public Figure(int x, int y, int[][] matrix) {
        this.x = x;
        this.y = y;
        this.matrix = matrix;
    }

    private int[][] matrix;

    protected void left(){}
    protected void right(){}
    protected void down(){}
    protected void up(){}
    protected void rotate(){}
    protected void downMaximum(){}
    protected boolean isCurrentPositionAvailable(){
        return true;
    }
    protected void landed(){}

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
}
