package com.classes.Fields;

public class WallSmallField extends SmallField {
    @Override
    public SmallFieldType getType() {
        return SmallFieldType.WALL;
    }

    @Override
    public String getString() {
        return "@";
    }
}
