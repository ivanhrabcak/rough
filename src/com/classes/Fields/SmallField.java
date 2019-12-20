package com.classes.Fields;

public abstract class SmallField {
    public void Wtf(){

    }
    public abstract SmallFieldType getType();

    public abstract String getString();
}

public enum SmallFieldType {
    PLAYER, WALL, EMPTY, TREASURE
}
