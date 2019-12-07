package com.classes.field;

import java.util.ArrayList;
import java.util.List;

public class Field {
    public short[] fieldSize;
    public short treasures;
    public int[] playerPosition;
    private List<Integer> treasurePositions; // TODO: Make it an array
    private List<List<Integer>> field; // TODO: Make it an array

    public Field(short[] fieldSize, short treasures, int[] playerPosition) {
        this.fieldSize = fieldSize;
        this.treasures = treasures;
        this.playerPosition = playerPosition;
        this.treasurePositions = new ArrayList<>();
        this.field = this.generateField();
    }

    private List<List<Integer>> generateField() {
        short sizeX = this.fieldSize[0];
        short sizeY = this.fieldSize[1];
        List<List<Integer>> l = new ArrayList<>();
        for (short a = 0; a <= sizeX; a++) {
            List<Integer> b = new ArrayList<>();
            for (short c = 0; c <= sizeY; c++) {
                b.add(0);
            }
            l.add(b);

        }
        // print field
        return l;

    }
    public boolean move(short[] position) {
        System.out.println(":(((");
        return true;
    }

}
