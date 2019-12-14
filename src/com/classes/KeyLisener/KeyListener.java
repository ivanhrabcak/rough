package com.classes.KeyLisener;


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
        else {
            this.field.move(key);
        }

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        ;
    }
}