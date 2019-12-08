package com.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*
 *
 */
public class Field {
    public short[] fieldSize;
    public short treasures;
    public int[] playerPosition;
    private int[] treasurePositions;
    private short[][] field;

    public Field(short[] fieldSize, short treasures, int[] playerPosition) {
        this.fieldSize = fieldSize;
        this.treasures = treasures;
        this.playerPosition = playerPosition;
        this.treasurePositions = new int[treasures];
        this.field = this.generateField();
    }

    private short[][] generateField() {
        short[][] l = new short[this.fieldSize[0]][this.fieldSize[1]];
        short[] b = new short[this.fieldSize[1]];
        for (short x = 0; x < this.fieldSize[0]; x++) {
            //b = new short[this.fieldSize[1]];
            for (short y = 0; y < this.fieldSize[1]; y++) {
                b[y] = 0;
            }
            l[x] = b;
        }
        l = this.generateWalls(l);
        //this.generateTreasures(l);

        return l;
    }

    private void draw() {
        this.printField();
        for (int a = 0; a < this.fieldSize[0]; a++) {
            for (int b = 0; b < this.fieldSize[1]; b++) {
                short f = this.field[a][b];
                if (f == -1) {
                    System.out.print(". ");
                } else if (f == 1) {
                    System.out.print("@ ");
                } else if (f == 0) {
                    System.out.print("! ");
                } else {
                    System.out.print("T ");
                }
            }
            System.out.println("");
        }
    }

    public short[][] generateWalls(short[][] field) {
        Random random = new Random(1);
        short[][] newField = new short[this.fieldSize[0]][this.fieldSize[1]];
        for (int a = 0; a < this.fieldSize[0]; a++) {

            for (int b = 0; b < this.fieldSize[1]; b++) {
                int n = random.nextInt(3);
                if (n == 0) {
                    field[a][b] = -1;
                }
                else {
                    field[a][b] = (short) n;
                }
            }
        }
        for (int a = 0; a < this.fieldSize[0]; a++){
            for (int b = 0; b < this.fieldSize[1]; b++) {
            }
        }
        System.out.println(Arrays.deepToString(field));
        return field;
    }


    public void printField() {
        System.out.println(Arrays.deepToString(this.field));
    }

    public boolean move(short[] position) {
        System.out.println(":(((");
        return true;
    }

}
