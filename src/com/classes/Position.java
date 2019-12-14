package com.classes;

public class Position {
    public int[] position;
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.position = new int[]{x, y};

    }

    public Position(int[] position) {
        this.x = position[0];
        this.y = position[1];
        this.position = position;
    }

}
