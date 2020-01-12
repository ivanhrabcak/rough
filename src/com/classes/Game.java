package com.classes;

import com.classes.Fields.PlayerSmallField;
import com.classes.Fields.SmallFieldType;
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

    public void tick() throws IOException {
        // NullPointerException
        System.out.println(field.fieldSize.sizex + field.fieldSize.sizey);
        for (short a = 0; a < field.fieldSize.sizex; a++) {
            for (short b = 0; b < field.fieldSize.sizey; b++) {
                boolean isPlayer = field.getField(new Position(a, b)).getType() == SmallFieldType.PLAYER;
                /*if (field.getField(new Position(a, b)).getType() == SmallFieldType.WALL) {

                }*/
                if (isPlayer) {
                    //field.field[a][b] = -1;
                    field.clearField(new Position(a, b));
                    break;
                }
            }
        }
        field.setField(field.playerPosition, new PlayerSmallField());
        if (field.stepsRemaining == 0 && field.treasuresCollected != field.treasures) {
            clear();
            System.out.println("you lost :(((");
            field.loop = false;
        }
        else if (field.stepsRemaining == 0 && field.treasuresCollected == field.treasures) {
            clear();
            System.out.println("YOU WON!!!!!!! :)))");
            field.loop = false;
        }
        else if (field.treasuresCollected == field.treasures) {
            clear();
            System.out.println("YOU WON!!!!!! :))))");
            field.loop = false;
        }
    }

    Game(KeyListener listener, Field field) throws NativeHookException, IOException, InterruptedException {
        this.field = field;
        this.field.fieldSize = new Size(50, 50);
        System.out.println(this.field.fieldSize);
        // Disable logging
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);
        logger.setUseParentHandlers(false);
        listener.writeField(field);
        // Add key listener
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(listener);
        while (true) {
            clear();
            //System.out.println(field.loop);
            if (this.field.loop == false) {
                break;
            }
            tick();
            System.out.println(this.field.draw());
            Thread.sleep(30);
        }
        //field.draw();

    }
}
