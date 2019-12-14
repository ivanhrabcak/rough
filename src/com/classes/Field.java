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
    public boolean loop;

    public Field(short[] fieldSize, int[] playerPosition) {
        this.fieldSize = fieldSize;
        this.playerPosition = playerPosition;
        this.field = this.generateField();
        this.treasures = this.calcTreasures();
        this.treasurePositions = new int[this.treasures];
        this.field[playerPosition[0]][playerPosition[1]] = 0;
        this.loop = true;
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
    
    private short[][] generateField() {
        short[][] field = new short[this.fieldSize[0]][this.fieldSize[1]];
        for (short lines = 0; lines < this.fieldSize[0]; lines++) {
            for (short column = 0; column < this.fieldSize[1]; column++) {
                field[lines][column] = 0;
            }
        }
        field = this.generateObjects(field);
        return field;
    }

    public void draw() {
        for (int lines = 0; lines < this.fieldSize[0]; lines++) {
            for (int columns = 0; columns < this.fieldSize[1]; columns++) {
                short f = this.field[lines][columns];
                if (f == -1) {
                    System.out.print(". ");
                } else if (f == 1) {

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
        return newField;
    }


    public void printField() {
        System.out.println(Arrays.deepToString(this.field));
    }

    public boolean tick() {
        for (short a = 0; a < this.fieldSize[0]; a++) {
            for (short b = 0; b < this.fieldSize[1]; b++) {
                if (this.field[a][b] == 0) {
                    this.field[a][b] = -1;
                    break;
                }
            }
        }
        this.field[this.playerPosition[0]][this.playerPosition[1]] = 0;
        return true;
    }

    private boolean isWall(int[] position) {
        try {
            if (this.field[position[0]][position[1]] == 9999999) {
                ;
            }

        }
        catch (Exception e) {
            System.out.println("wall");
            return false;
        }
        return true;
        }

    public boolean move(String direction) {
        if (direction == "Up" && this.isWall(new int[]{this.playerPosition[0] - 1, this.playerPosition[1]}) == true) {
            this.playerPosition[0]--;
        }
        else if (direction == "Down" && this.isWall(new int[]{this.playerPosition[0] + 1, this.playerPosition[1]}) == true) {
            this.playerPosition[0]++;
        }
        else if (direction == "Right" && this.isWall(new int[]{this.playerPosition[0], this.playerPosition[1] + 1}) == true) {
            this.playerPosition[1]++;
        }
        else if (direction == "Left" && this.isWall(new int[]{this.playerPosition[0], this.playerPosition[1] - 1}) == true) {
            this.playerPosition[1]--;
        }

        return true;
    }

    public void br() {
        this.loop = false;
    }

}
