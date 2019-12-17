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

    private void clear() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        String system = System.getProperty("os.name");
        if (system.contains("Windows")) {
            processBuilder.command("cmd", "/c", "cls");
        }
        else {
            processBuilder.command("clear");
        }
        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

    Game(KeyListener listener, Field field) throws NativeHookException, IOException, InterruptedException {
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
            this.clear();
            //System.out.println(field.loop);
            if (field.loop == false) {
                break;
            }
            this.field.tick();
            this.field.draw();
            Thread.sleep(30);
        }
        field.draw();

    }
}
