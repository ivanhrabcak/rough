package com.classes;

public class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class DiagnoalPositon {
    private int x;
    private int y;

    private int age;
    private int yearOfBirth;

    public DiagnoalPositon(int position) {
        this.x = position;
        this.y = position;
    }

    public void setCoordinate(int position){
        int lokalnaPremenna = 0;
        x = position;
        y = lokalnaPremenna;
    }
}
