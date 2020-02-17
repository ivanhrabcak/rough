package com.classes;

import com.classes.Fields.EmptySmallField;
import com.classes.Fields.SmallField;
import com.classes.Fields.SmallFieldType;
import com.classes.Fields.TreasureSmallField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class fieldOfViewTest {

    @Test
    void resetFieldOfView() throws Exception {
        Position testPlayerPosition = new Position(0, 0);
        Size testFieldOfViewSize = new Size(5, 5);
        FieldOfView fieldOfView = new FieldOfView(testPlayerPosition, testFieldOfViewSize);
        fieldOfView.writeField(new TreasureSmallField(), new Position(0, 0));
        fieldOfView.resetFieldOfView();

        SmallField expected = new EmptySmallField();
        for (SmallField[] line : fieldOfView.toArray()) {
            for (SmallField f : line) {
                if (f == null) {
                    continue;
                }
                else if (f.getType() != SmallFieldType.EMPTY) {
                    assertEquals(expected, f);
                }
            }
        }
    }

    @Test
    void writeField_writeField() {
        SmallField expected = new TreasureSmallField();

        Position testPlayerPosition = new Position(0, 0);
        Size testFieldOfViewSize = new Size(5, 5);
        FieldOfView testFieldOfView = new FieldOfView(testPlayerPosition, testFieldOfViewSize);
        testFieldOfView.writeField(expected, new Position(0, 0));
        SmallField actual = testFieldOfView.readField(new Position(0,0));
        assertEquals(actual, expected);
    }

    @Test
    void getStart() {
        Position testPlayerPosition = new Position(7, 7);
        Size testFieldOfViewSize = new Size(5, 5);
        FieldOfView testFieldOfView = new FieldOfView(testPlayerPosition, testFieldOfViewSize);
        Position expected = new Position(testPlayerPosition.x - testFieldOfViewSize.sizex, testPlayerPosition.y - testFieldOfViewSize.sizey);
        Position actual = testFieldOfView.getStart();
        assertEquals(expected.x, actual.x);
        assertEquals(expected.y, actual.y);
    }
}