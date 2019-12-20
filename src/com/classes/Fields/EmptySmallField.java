package com.classes.Fields;

public class EmptySmallField extends SmallField {
    @Override
    public SmallFieldType getType() {
        return SmallFieldType.EMPTY;
    }

    @Override
    public String getString() {
        return ".";
    }
}
