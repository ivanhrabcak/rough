package com.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*
 * -1 - empty space (.)
 *  0 - the player (!)
 *  1 - a wall (@)
 *  2 - treasure (T)
 */

public class Field {
    public short[] fieldSize;
    public int[] playerPosition;
    private int[] treasurePositions;
    public int treasures;
    private short[][] field;

    public Field(short[] fieldSize, int[] playerPosition) {
        this.fieldSize = fieldSize;
        this.playerPosition = playerPosition;
        this.field = this.generateField();
        this.treasures = this.calcTreasures();
        this.treasurePositions = new int[this.treasures];
        System.out.println(this.calcTreasures());
        this.printField();
    }

    private int calcTreasures() {
        int treasures = 0;
        for (short lines = 0; lines < this.fieldSize[0]; lines++) {
            for (short columns = 0; columns < this.fieldSize[1]; columns++) {
                short field = this.field[lines][columns];
                if (field == 2) {
                    treasures++;
                }
            }
        }
        return treasures;
    }
    
    private short[][] generateField() {                        // probably broken
        short[][] field = new short[this.fieldSize[0]][this.fieldSize[1]];
        short[] array;
        for (short lines = 0; lines < this.fieldSize[0]; lines++) {
            array = new short[this.fieldSize[1]];              // :((((
            for (short column = 0; column < this.fieldSize[1]; column++) {
                array[column] = 0;
            }
            field[lines] = array;
        }
        field = this.generateObjects(field);
        return field;
    }

    private void draw() {
        this.printField();
        for (int lines = 0; lines < this.fieldSize[0]; lines++) {
            for (int columns = 0; columns < this.fieldSize[1]; columns++) {
                short f = this.field[lines][columns];
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

    public short[][] generateObjects(short[][] field) { // probably not broken
        Random random = new Random(1);
        short[][] newField = new short[this.fieldSize[0]][this.fieldSize[1]];
        for (int lines = 0; lines < this.fieldSize[0]; lines++) {

            for (int columns = 0; columns < this.fieldSize[1]; columns++) {
                int n = random.nextInt(3);
                if (n == 0) {
                    newField[lines][columns] = -1;
                }
                else {
                    newField[lines][columns] = (short) n;
                }
            }
        }
        for (int lines = 0; lines < this.fieldSize[0]; lines++){
            for (int columns = 0; columns < this.fieldSize[1]; columns++) {
            }
        }
        System.out.println(Arrays.deepToString(newField));
        return newField;
    }


    public void printField() {
        System.out.println(Arrays.deepToString(this.field));
    }

    public boolean move(short[] position) {
        System.out.println(":(((");
        return true;
    }

}
