package com.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Field {
    public short[] fieldSize;
    public short treasures;
    public int[] playerPosition;
    private int[] treasurePositions; // TODO: Make it an array
    private short[][] field; // TODO: Make it an array

    public Field(short[] fieldSize, short treasures, int[] playerPosition) {
        this.fieldSize = fieldSize;
        this.treasures = treasures;
        this.playerPosition = playerPosition;
        this.treasurePositions = new int[treasures];
        this.field = this.generateField();
    }

    private short[][] generateField() {
        short[][] l = new short[this.fieldSize[0]][this.fieldSize[1]];
        short[] a = new short[this.fieldSize[0]];
        for (int i = 0; i < this.fieldSize[0]; i++) {
            a[i] = 0;
        }
        for (int i = 0; i < this.fieldSize[1]; i++) {
            l[i] = a;
        }
        return l;
    }

    public void printField() {
        System.out.println(Arrays.deepToString(this.field));
    }
    public boolean move(short[] position) {
        System.out.println(":(((");
        return true;
    }

}
