package com.classes.Fields;

import com.classes.Position;
import com.classes.Size;

import java.util.Arrays;
import java.util.List;

public class Direction {
    private String string;

    public Direction(String direction) {
        setString(direction);
    }

    public void setString(String value) {
        List<String> correctValues = Arrays.asList("Up", "Down", "Left", "Right");
        if (correctValues.contains(value)) {
            string = value;
        }
    }

    public String getString() {
        return string;
    }

    public void reverse() {
        if (string == "Up") {
            string = "Down";
        }
        else if (string == "Down") {
            string = "Up";
        }
        else if (string == "Left") {
            string = "Right";
        }
        else if (string == "Right") {
            string = "Left";
        }
    }

    private boolean isInBounds(Position position, Size fieldSize) {
        boolean isXInNotBounds = position.x > fieldSize.sizex || position.x < 0;
        boolean isYInNotBounds = position.y > fieldSize.sizey || position.y < 0;

        return !(isXInNotBounds || isYInNotBounds);
    }

    public void adjustPosition(Position position, Size fieldSize) { // Adjust position based on the direction string

        if (string == "Up" && isInBounds(new Position(position.x - 1, position.y), fieldSize)) {
            position.x--;
        }
        else if (string == "Down" && isInBounds(new Position(position.x + 1, position.y), fieldSize)) {
            position.x++;
        }
        else if (string == "Left" && isInBounds(new Position(position.x, position.y - 1), fieldSize)) {
            position.y--;
        }
        else if (string == "Right" && isInBounds(new Position(position.x, position.y + 1), fieldSize)) {
            position.y++;
        }
    }
}
