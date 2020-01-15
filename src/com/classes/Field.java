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
    public SmallField[][] field;
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
        //this.treasurePositions = new int[this.treasures];
        setField(playerPosition, new PlayerSmallField());
        this.loop = true;
    }

    public SmallField getField(Position position) {
        return this.field[position.x][position.y];
    }

    public void setField(Position position, SmallField field) {
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

    private boolean isInBounds(Position position) {
        boolean isXInNotBounds = position.x > fieldSize.sizex || position.x < 0;
        boolean isYInNotBounds = position.y > fieldSize.sizey || position.y < 0;

        return !(isXInNotBounds || isYInNotBounds);
    }

    public String draw() {
        String output = "";

        Size fieldOfView = new Size(3, 3); // fov on each size shape - <>
        Position[] drawPositions = new Position[(fieldOfView.sizex * 2) * 2 + ((fieldOfView.sizey * 35) * 2)];
        int indexX = this.playerPosition.x;
        int indexY = this.playerPosition.y;
        int drawPositionsIndex = 0;
        Position currentPosition;

        for (int x = indexX - fieldOfView.sizex; x < indexX + fieldOfView.sizex; x++) {
            if (drawPositionsIndex == drawPositions.length - 1) {
                drawPositionsIndex = 0;
            }
            for (int y = indexY - fieldOfView.sizey; y < indexY + fieldOfView.sizey; y++) {
                currentPosition = new Position(x, y);
                if (this.isInBounds(currentPosition)) {
                    drawPositions[drawPositionsIndex] = new Position(x, y);
                    drawPositionsIndex++;
                }
            }
        }
        boolean condition = false;
        boolean canDraw = false;
        boolean isWallBehind = false;

        for (int lines = 0; lines < this.fieldSize.sizex; lines++) {
            isWallBehind = false;
            for (int columns = 0; columns < this.fieldSize.sizey; columns++) {

                    for (Position drawPosition : drawPositions) {
                        currentPosition = new Position(lines, columns);
                        // NullPointerException
                        if (currentPosition == null || drawPosition == null) {
                            break;
                        }
                        if (currentPosition != null && currentPosition.x == drawPosition.x && currentPosition.y == drawPosition.y) {
                            canDraw = true;
                            break;
                        }
                    }
                    if (isWallBehind) {
                        canDraw = false;
                    }

                    if (canDraw) {
                        SmallField f = getField(new Position(lines, columns));
                        if (f.getType() == SmallFieldType.WALL) {
                            isWallBehind = true;
                        }
                        output = output + f.getString() + " ";
                        canDraw = false;
                    }
                    else {
                        output = output + ". ";
                    }




                }
                output = output + "\n";
            }
        this.gameBar();
        return output;


/*
        Size fieldOfViewSize = new Size(3, 3);
        fieldOfView fieldOfView = new fieldOfView(playerPosition, fieldOfViewSize);
        String output = "";
        Position currentPosition = new Position(playerPosition.x, playerPosition.y);

        for (int x = fieldOfView.getStart().x; x < fieldOfViewEnd.x; x++) {
            for (int y = fieldOfViewStart.y; y < fieldOfViewEnd.y; y++) {
                currentPosition = new Position(x, y);
                if (!isInBounds(currentPosition)) {
                    continue;
                }
                SmallField currentField = getField(currentPosition);
                if (currentField.getType() != SmallFieldType.WALL) {
                    fieldOfView[fieldOfViewIndexX][fieldOfViewIndexY] = currentField;
                    fieldOfViewIndexY++;
                }
                else {
                    if (currentPosition.x > playerPosition.x) {
                        eraseSmallField(field[x], y, field.length - 1);
                    }
                    else {
                        fieldOfView[fieldOfViewIndexX][fieldOfViewIndexY] = currentField;
                    }
                    fieldOfViewIndexY++;
                }

            }
            fieldOfViewIndexX++;
            fieldOfViewIndexY = 0;
        }


        for (int x = 0; x < fieldSize.sizex; x++) {
            for (int y = 0; y < fieldSize.sizey; y++) {
                if (x >= fieldOfViewStart.x && y >= fieldOfViewStart.y) {
                    if (x <= fieldOfViewEnd.x && y <= fieldOfViewEnd.y) {
                        output = output + fieldOfView[x][y].getString() + " ";
                    }
                }
                else {
                    output = output + ". ";
                }
            }
        }


        return output;
 */
    }

    private void eraseField(SmallField[][] field) {
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field[0].length; y++) {
                field[x][y] = new EmptySmallField();
                }
            }
        }

    private void eraseSmallField(SmallField[] field, int startY, int endY) {
        for (int y = startY; y < endY; y++) {
            field[y] = new EmptySmallField();
        }
    }

    public SmallField[][] generateObjects(SmallField[][] field) {
        treasures = 0;
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
                    treasures++;
                }
            }
        }
        for (int lines = 0; lines < this.fieldSize.sizex; lines++){
            for (int columns = 0; columns < this.fieldSize.sizey; columns++) {
            }
        }
        return newField;
    }


    public void printField(SmallField[][] field) {
        System.out.println(Arrays.deepToString(field));
    }

    public void clearField(Position position){
        this.field[position.x][position.y] = new EmptySmallField();
    }

    public boolean move(Direction direction) {
        if (this.magicHax) {
            direction.adjustPosition(playerPosition, fieldSize);
            return true;
        }
        direction.adjustPosition(playerPosition, fieldSize);


        SmallField f = getField(playerPosition);
        if (f.getType() == SmallFieldType.WALL) {               /* There is a wall in the direction the player is moving */
            direction.reverse();
            direction.adjustPosition(playerPosition, fieldSize);
        }
        else if (f.getType() == SmallFieldType.TREASURE) {          /* There is a treasure in the direction the player is moving */
            this.treasuresCollected++;
            this.stepsRemaining--;
        }
        else if (f.getType() == SmallFieldType.EMPTY) {
            this.stepsRemaining--;
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
