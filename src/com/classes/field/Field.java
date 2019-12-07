package com.classes.field;

import java.util.ArrayList;
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
        this.treasurePositions = new int[treasurePositions.length];
        this.field = this.generateField();
    }

    private short[][] generateField() {
        short[][] l = new short[this.fieldSize[0]][this.fieldSize[1]]
        return l;

    }
    public boolean move(short[] position) {
        System.out.println(":(((");
        return true;
    }

}
