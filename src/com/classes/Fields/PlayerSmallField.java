package com.classes.Fields;

public class PlayerSmallField extends SmallField {
    @Override
    public SmallFieldType getType() {
        return SmallFieldType.PLAYER;
    }

    @Override
    public String getString() {
        return "!";
    }
}
