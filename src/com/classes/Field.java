package com.classes;

import com.classes.Fields.*;

import java.util.Arrays;
import java.util.Random;

/*
 * -1 - empty space (.)
 *  0 - the player (!)
 *  1 - a wall (@)
 *  2 - treasure (T)
 */

public class Field {
    public Size fieldSize;
    public Position playerPosition;
    //private Position treasurePositions;
    public int treasures;
    private SmallField[][] field;
    public boolean loop;
    public int treasuresCollected;
    public int totalSteps;
    public int stepsRemaining;
    public boolean magicHax;

    public Field(Size fieldSize, Position playerPosition) {
        this.magicHax = false;
        this.totalSteps = 60;
        this.stepsRemaining = 60;
        this.treasuresCollected = 0;
        this.fieldSize = fieldSize;
        this.playerPosition = playerPosition;
        this.field = this.generateField();
        this.treasures = this.calcTreasures();
        //this.treasurePositions = new int[this.treasures];
        setField(playerPosition, new PlayerSmallField());
        this.loop = true;
    }

    private SmallField getField(Position position) {
        return this.field[position.x][position.y];
    }

    private void setField(Position position, SmallField field) {
        this.field[position.x][position.y] = field;
    }

    private int calcTreasures() { // TODO: Remove
        int treasures = 0;
        for (short lines = 0; lines < fieldSize.sizex; lines++) {
            for (short columns = 0; columns < this.fieldSize.sizey; columns++) {
                SmallField field = getField(new Position(lines, columns));
                if (field.getType() == SmallFieldType.TREASURE) {
                    treasures++;
                }
            }
        }
        return treasures;
    }
    
    private SmallField[][] generateField() {
        SmallField[][] field = new SmallField[this.fieldSize.sizex][this.fieldSize.sizey];
        for (short lines = 0; lines < this.fieldSize.sizex; lines++) {
            for (short column = 0; column < this.fieldSize.sizey; column++) {
                field[lines][column] = new PlayerSmallField();
            }
        }
        field = this.generateObjects(field);
        return field;
    }

    public void draw() {
        /*
       for (int lines = 0; lines < this.fieldSize.sizex; lines++) {
            for (int columns = 0; columns < this.fieldSize.sizey; columns++) {
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

        Size fieldOfView = new Size(3, 3); // fov on each size shape - <>
        Position[] drawPositions = new Position[(fieldOfView.sizex * 2) + (fieldOfView.sizey * 2)];
        int indexX = this.playerPosition.x;
        int indexY = this.playerPosition.y;
        int drawPositionsIndex = 0;
        boolean canDraw = false;
        Position currentPosition;

        for (int x = indexX - fieldOfView.sizex; x < indexX + fieldOfView.sizex; x++) {
            for (int y = indexY - fieldOfView.sizey; y < indexY + fieldOfView.sizey; y++) {
                currentPosition = new Position(x, y);
                if (this.isInBounds(currentPosition)) {
                    drawPositions[drawPositionsIndex] = new Position(x, y);
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

        for (int lines = 0; lines < this.fieldSize.sizex; lines++) {
            for (int columns = 0; columns < this.fieldSize.sizey; columns++) {
                for (Position drawPosition : drawPositions) {
                    currentPosition = new Position(lines, columns);
                        // NullPointerException
                    if (currentPosition != null || currentPosition.x == drawPosition.x && currentPosition.y == drawPosition.y) {
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


                SmallField f = getField(new Position(lines, columns));
                System.out.print(f.getString() + " ");

            }
            System.out.println("");
        }
        this.gameBar();

    }


    public SmallField[][] generateObjects(SmallField[][] field) { // probably not broken
        Random random = new Random();
        SmallField[][] newField = new SmallField[this.fieldSize.sizex][this.fieldSize.sizey];
        for (int lines = 0; lines < this.fieldSize.sizex; lines++) {

            for (int columns = 0; columns < this.fieldSize.sizey; columns++) {
                int n = random.nextInt(3);
                if (n == 0) {
                    newField[lines][columns] = new EmptySmallField();
                }
                else if (n == 1){
                    newField[lines][columns] = new WallSmallField();
                }
                else if (n == 2) {
                    newField[lines][columns] = new TreasureSmallField();
                }
            }
        }
        for (int lines = 0; lines < this.fieldSize.sizex; lines++){
            for (int columns = 0; columns < this.fieldSize.sizey; columns++) {
            }
        }
        return newField;
    }


    public void printField() {
        System.out.println(Arrays.deepToString(this.field));
    }

    private void clearField(Position position){
        this.field[position.x][position.y] = new EmptySmallField();
    }

    public boolean tick() {
        for (short a = 0; a < this.fieldSize.sizex; a++) {
            for (short b = 0; b < this.fieldSize.sizey; b++) {
                boolean isPlayer = getField(new Position(a, b)).getType() == SmallFieldType.PLAYER;
                if (getField(new Position(a, b)).getType() == SmallFieldType.WALL) {

                }
                if (isPlayer) {
                    //this.field[a][b] = -1;
                    clearField(new Position(a, b));
                    break;
                }
            }
        }
        this.field[this.playerPosition.x][this.playerPosition.y] = new PlayerSmallField();
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
        boolean isXInNotBounds = position.x > this.fieldSize.sizex || position.x < 0;
        boolean isYInNotBounds = position.y > this.fieldSize.sizey || position.y < 0;

        return !(isXInNotBounds || isYInNotBounds);
    }

    private boolean canMove(String direction) {
        if (this.magicHax) {
            return true;
        }
        Position position;
        if (direction == "Up" && this.isInBounds(this.playerPosition)) {
            //f = this.field[this.playerPosition.x - 1][this.playerPosition.y];
            position = new Position(this.playerPosition.x - 1, this.playerPosition.y);
        }
        else if (direction == "Down" && this.isInBounds(new Position(this.playerPosition.x + 1, this.playerPosition.y)) == true) {
            //f = this.field[this.playerPosition.x + 1][this.playerPosition.y];
            position = new Position(this.playerPosition.x + 1, this.playerPosition.y);
        }
        else if (direction == "Right" && this.isInBounds(new Position(this.playerPosition.x, this.playerPosition.y + 1)) == true) {
            //f = this.field[this.playerPosition.x][this.playerPosition.y + 1];
            position = new Position(this.playerPosition.x, this.playerPosition.y + 1);
        }
        else if (direction == "Left" && this.isInBounds(new Position(this.playerPosition.x, this.playerPosition.y - 1)) == true) {
            //f = this.field[this.playerPosition.x][this.playerPosition.y - 1];
            position = new Position(this.playerPosition.x, this.playerPosition.y -1);
        }
        else {
            return false;
        }
        SmallField f = getField(position);
        if (f.getType() == SmallFieldType.WALL) {               /* There is a wall in the direction the player is moving */
            return false;
        }
        else if (f.getType() == SmallFieldType.TREASURE) {          /* There is a treasure in the direction the player is moving */
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
