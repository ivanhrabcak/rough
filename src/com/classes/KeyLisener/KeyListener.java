package com.classes.KeyLisener;


import com.classes.Fields.Direction;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import com.classes.Field;


public class KeyListener implements NativeKeyListener {
    private Field field;

    public void writeField(Field field) {
        this.field = field;
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        String key = nativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode());
        if (key == "Escape") {
            this.field.br();
        }
        else if (key == "C") {
            field.magicHax = !field.magicHax;
        }
        else {
            Direction direction = new Direction(key);
            this.field.move(direction);
        }

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        ;
    }
}