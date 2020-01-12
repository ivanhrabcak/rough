package com.classes;

import com.classes.Fields.EmptySmallField;
import com.classes.Fields.PlayerSmallField;
import com.classes.Fields.TreasureSmallField;
import com.classes.Fields.WallSmallField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class FieldTest {
    Size fieldSize = new Size(4, 4);

    private void resetField(Field field) {
        for (int x = 0; x < field.fieldSize.sizex; x++) {
            for (int y = 0; y < field.fieldSize.sizey; y++) {
                field.field[x][y] = new EmptySmallField();
            }
        }
    }


    @Test
    void drawCaseA() {
        Position playerPosition = new Position(2, 2);
        Field field = new Field(fieldSize, playerPosition);
        resetField(field);
        //field.playerPosition = new Position(3, 2);
        field.field[1][1] = new WallSmallField();
        field.field[1][2] = new TreasureSmallField();
        field.field[2][2] = new WallSmallField();
        field.field[2][1] = new PlayerSmallField();

        String expected = ". . . . \n" +
                          ". @ . . \n" +
                          ". ! @ . \n" +
                          ". . . . \n";
        assertEquals(field.draw(), expected);
    }

    @Test
    void drawCaseB() {
        Position playerPosition = new Position(2, 2);
        Field field = new Field(fieldSize, playerPosition);
        resetField(field);
        field.playerPosition = new Position(3, 2);
        field.field[1][0] = new WallSmallField();
        field.field[2][0] = new WallSmallField();

        field.field[1][1] = new TreasureSmallField();
        field.field[2][2] = new TreasureSmallField();
        field.field[3][1] = new TreasureSmallField();

        field.field[2][1] = new PlayerSmallField();

        String expected = ". . . . \n" +
                          "@ T . . \n" +
                          "@ ! T . \n" +
                          ". T . . \n";
        assertEquals(field.draw(), expected);
    }

    @Test
    void drawCaseC() {
        Position playerPosition = new Position(2, 2);
        Field field = new Field(fieldSize, playerPosition);
        resetField(field);
        //field.playerPosition = new Position(3, 2);
        field.field[1][1] = new WallSmallField();

        field.field[0][1] = new TreasureSmallField();

        field.field[2][1] = new PlayerSmallField();

        String expected = ". T . . \n" +
                          ". @ . . \n" +
                          ". ! . . \n" +
                          ". . . . \n";
        assertEquals(field.draw(), expected);
    }

    @Test
    void drawCaseD() {
        Position playerPosition = new Position(2, 2);
        Field field = new Field(fieldSize, playerPosition);
        resetField(field);
        field.playerPosition = new Position(3, 2);
        field.field[1][2] = new WallSmallField();
        field.field[1][1] = new WallSmallField();
        field.field[1][3] = new WallSmallField();
        field.field[2][1] = new WallSmallField();
        field.field[2][3] = new WallSmallField();
        field.field[3][2] = new WallSmallField();
        field.field[3][1] = new WallSmallField();
        field.field[3][3] = new WallSmallField();

        field.field[2][2] = new PlayerSmallField();

        String expected = ". . . . \n" +
                          ". . @ . \n" +
                          ". @ ! @ \n" +
                          ". . @ . \n";
        assertEquals(field.draw(), expected);
    }

    @Test
    void drawCaseE() {
        Position playerPosition = new Position(2, 2);
        Field field = new Field(fieldSize, playerPosition);
        resetField(field);
        field.playerPosition = new Position(3, 2);
        field.field[2][1] = new WallSmallField();
        field.field[1][2] = new TreasureSmallField();

        field.field[3][0] = new PlayerSmallField();

        String expected = ". . . . \n" +
                ". . . . \n" +
                ". @ . . \n" +
                "! . . . \n";
        assertEquals(field.draw(), expected);
    }

    @Test
    void drawCaseF() {
        Position playerPosition = new Position(2, 2);
        Field field = new Field(fieldSize, playerPosition);
        resetField(field);
        field.playerPosition = new Position(3, 2);
        field.field[2][2] = new WallSmallField();
        field.field[3][1] = new WallSmallField();

        field.field[3][2] = new TreasureSmallField();
        field.field[3][3] = new TreasureSmallField();

        field.field[2][1] = new PlayerSmallField();

        String expected = ". . . . \n" +
                          ". . . . \n" +
                          ". ! @ . \n" +
                          ". @ . . \n";
        assertEquals(field.draw(), expected);
    }

    @Test
    void drawCaseG() {
        Position playerPosition = new Position(2, 2);
        Field field = new Field(fieldSize, playerPosition);
        resetField(field);
        field.playerPosition = new Position(3, 2);
        field.field[1][0] = new TreasureSmallField();
        field.field[2][0] = new TreasureSmallField();
        field.field[0][1] = new TreasureSmallField();
        field.field[1][2] = new TreasureSmallField();
        field.field[2][3] = new TreasureSmallField();

        field.field[2][2] = new WallSmallField();

        field.field[2][1] = new PlayerSmallField();

        String expected = ". T . . \n" +
                          "T . T . \n" +
                          "T ! @ . \n" +
                          ". . . . \n";
        assertEquals(field.draw(), expected);
    }

    @Test
    void drawCaseH() {
        Position playerPosition = new Position(2, 2);
        Field field = new Field(fieldSize, playerPosition);
        resetField(field);
        field.playerPosition = new Position(3, 2);
        field.field[2][2] = new WallSmallField();
        field.field[3][3] = new TreasureSmallField();

        field.field[2][1] = new PlayerSmallField();

        String expected = ". . . . \n" +
                          ". . . . \n" +
                          ". ! @ . \n" +
                          ". . . T \n";
        assertEquals(field.draw(), expected);
    }

    @Test
    void drawCaseI() {
        Position playerPosition = new Position(2, 2);
        Field field = new Field(fieldSize, playerPosition);
        resetField(field);
        field.playerPosition = new Position(3, 2);
        field.field[2][2] = new WallSmallField();
        field.field[3][1] = new WallSmallField();

        field.field[3][2] = new TreasureSmallField();

        field.field[2][1] = new PlayerSmallField();

        String expected = ". . . . \n" +
                          ". . . . \n" +
                          ". ! @ . \n" +
                          ". @ . . \n";
        assertEquals(field.draw(), expected);
    }

    @Test
    void drawCaseJ() {
        Position playerPosition = new Position(2, 2);
        Field field = new Field(fieldSize, playerPosition);
        resetField(field);
        field.playerPosition = new Position(3, 2);
        field.field[0][2] = new WallSmallField();
        field.field[1][3] = new WallSmallField();

        field.field[0][3] = new TreasureSmallField();

        field.field[1][0] = new PlayerSmallField();

        String expected = ". . @ . \n" +
                          "! . . @ \n" +
                          ". . . . \n" +
                          ". . . . \n";
        assertEquals(field.draw(), expected);
    }

}