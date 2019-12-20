package com.classes;

import java.util.Arrays;
import java.util.Random;

/*
 * -1 - empty space (.)
 *  0 - the player (!)
 *  1 - a wall (@)
 *  2 - treasure (T)
 */

public class Field {
    public short[] fieldSize;
    public Position playerPosition;
    //private Position treasurePositions;
    public int treasures;
    private short[][] field;
    public boolean loop;
    public int treasuresCollected;
    public int totalSteps;
    public int stepsRemaining;
    public boolean magicHax;

    public Field(short[] fieldSize, Position playerPosition) {
        this.magicHax = false;
        this.totalSteps = 60;
        this.stepsRemaining = 60;
        this.treasuresCollected = 0;
        this.fieldSize = fieldSize;
        this.playerPosition = playerPosition;
        this.field = this.generateField();
        this.treasures = this.calcTreasures();
        //this.treasurePositions = new int[this.treasures];
        setField(playerPosition, 0);
        this.loop = true;
    }

    private int getField(Position position) {
        return this.field[position.x][position.y];
    }

    private void setField(Position position, int value) {
        this.field[position.x][position.y] = (short) value;
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
        /*
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
        this.gameBar();*/

        int[] fieldOfView = new int[]{3, 3}; // fov on each size shape - <>
        int[][] drawPositions = new int[(fieldOfView[0] * 2) + (fieldOfView[1] * 2)][2];
        int indexX = this.playerPosition.x;
        int indexY = this.playerPosition.y;
        int drawPositionsIndex = 0;
        boolean canDraw = false;
        int[] currentPosition;

        for (int x = indexX - fieldOfView[0]; x < indexX + fieldOfView[0]; x++) {
            for (int y = indexY - fieldOfView[1]; y < indexY + fieldOfView[1]; y++) {
                currentPosition = new int[]{x, y};
                if (this.isInBounds(currentPosition)) {//&& this.field[x][y] != 1) {
                    drawPositions[drawPositionsIndex] = new int[]{x, y};
                    drawPositionsIndex++;
                }/*
                else if (!this.isWall(currentPosition)) {
                    continue;
                }
                else if (this.field[x][y] == 1 && x >= this.playerPosition.x && y <= this.playerPosition.y) {
                    drawPositions[drawPositionsIndex] = new int[]{x, y};
                    drawPositionsIndex++;
                    break;
                }
                */

            }
            drawPositionsIndex = 0;
        }
        boolean condition = false;

        for (int lines = 0; lines < this.fieldSize[0]; lines++) {
            for (int columns = 0; columns < this.fieldSize[1]; columns++) {
                currentPosition = new int[]{lines, columns};
                for (int i = 0; i < drawPositions.length; i++) {
                    if (currentPosition[0] == drawPositions[i][0] && currentPosition[1] == drawPositions[i][1]) {
                        condition = true;
                        canDraw = true;
                        break;
                    }
                }
                if (!condition) {
                    canDraw = false;
                    condition = false;
                }
                else {
                    condition = false;
                }
                if (!canDraw) {
                    canDraw = false;
                    System.out.print(". ");
                    continue;
                }

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
        this.gameBar();

    }


    public short[][] generateObjects(short[][] field) { // probably not broken
        Random random = new Random();
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

    private void clearField(Position position){
        this.field[position.x][position.y] = -1;
    }

    public boolean tick() {
        for (short a = 0; a < this.fieldSize[0]; a++) {
            for (short b = 0; b < this.fieldSize[1]; b++) {
                boolean isPlayer = this.field[a][b] == 0;
                if (getField(new Position(a, b)).getType() == ) {

                }
                if (isPlayer) {
                    //this.field[a][b] = -1;
                    clearField(new Position(a, b));
                    break;
                }
            }
        }
        this.field[this.playerPosition.x][this.playerPosition.y] = 0;
        if (this.stepsRemaining == 0 && this.treasuresCollected != this.treasures) {
            System.out.println("you lost :(((");
            this.loop = false;
        }
        else if (this.stepsRemaining == 0 && this.treasuresCollected == this.treasures) {
            System.out.println("YOU WON!!!!!!! :)))");
            this.loop = false;
        }
        else if (this.treasuresCollected == this.treasures) {
            System.out.println("YOU WON!!!!!! :))))");
            this.loop = false;
        }
        return true;
    }

    private boolean isInBounds(Position position) {
        boolean isXInNotBounds = position.x > this.fieldSize[0] || position.x < 0;
        boolean isYInNotBounds = position.y > this.fieldSize[1] || position.y < 0;

        return !(isXInNotBounds || isYInNotBounds);
    }

    private boolean canMove(String direction) {
        if (this.magicHax) {
            return true;
        }
        int f;
        f = -5;
        Position position;
        if (direction == "Up" && this.isInBounds(this.playerPosition)) {
            //f = this.field[this.playerPosition.x - 1][this.playerPosition.y];
            position = new Position(this.playerPosition.x - 1, this.playerPosition.y);
        }
        else if (direction == "Down" && this.isInBounds(new int[]{this.playerPosition.x + 1, this.playerPosition.y}) == true) {
            //f = this.field[this.playerPosition.x + 1][this.playerPosition.y];
            position = new Position(this.playerPosition.x + 1, this.playerPosition.y);
        }
        else if (direction == "Right" && this.isInBounds(new int[]{this.playerPosition.x, this.playerPosition.y + 1}) == true) {
            //f = this.field[this.playerPosition.x][this.playerPosition.y + 1];
            position = new Position(this.playerPosition.x, this.playerPosition.y + 1);
        }
        else if (direction == "Left" && this.isInBounds(new int[]{this.playerPosition.x, this.playerPosition.y - 1}) == true) {
            //f = this.field[this.playerPosition.x][this.playerPosition.y - 1];
            position = new Position(this.playerPosition.x, this.playerPosition.y -1);
        }
        else {
            return false;
        }
        f = getField(position);
        if (f == 1) {               /* There is a wall in the direction the player is moving */
            return false;
        }
        else if (f == 2) {          /* There is a treasure in the direction the player is moving */
            this.treasuresCollected++;
        }
        this.stepsRemaining--;
        return true;
    }

    public boolean move(String direction) {
        boolean canMove = this.canMove(direction);
        if (!canMove) {
            return true;
        }
        if (direction == "Up") {
            this.playerPosition.x--;
        }
        else if (direction == "Down") {
            this.playerPosition.x++;
        }
        else if (direction == "Right") {
            this.playerPosition.y++;
        }
        else if (direction == "Left") {
            this.playerPosition.y--;
        }

        return true;
    }

    public void br() {
        this.loop = false;
    }

    private void gameBar() {
        System.out.println("Steps remaining: " + this.stepsRemaining + ", Treasures collected: " + this.treasuresCollected);
    }

}
