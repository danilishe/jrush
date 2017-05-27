package com.javarush.task.task23.task2312;

public class SnakeSection {
    private int x;
    private int y;


    public SnakeSection(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SnakeSection)) return false;

        SnakeSection that = (SnakeSection) o;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
