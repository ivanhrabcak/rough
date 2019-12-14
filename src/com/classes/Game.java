package com.classes;

import com.classes.KeyLisener.KeyListener;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {
    private Field field;
    Game(KeyListener listener, Field field) throws NativeHookException, IOException {
        this.field = field;
        // Disable logging
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);
        logger.setUseParentHandlers(false);
        listener.writeField(this.field);
        // Add key listener
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(listener);
        while (true) {
            System.out.println(field.loop);
            if (field.loop == false) {
                break;
            }
            this.field.tick();
        }
        field.draw();

    }
}
