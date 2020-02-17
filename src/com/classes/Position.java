package com.classes;

public class Position {
    public int x;
    public int y;

    public String toString() {
        return x + " " + y;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}