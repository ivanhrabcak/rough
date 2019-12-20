package com.classes.Fields;

public class TreasureSmallField extends SmallField {
    @Override
    public SmallFieldType getType() {
        return SmallFieldType.TREASURE;
    }

    @Override
    public String getString() {
        return "T";
    }
}
