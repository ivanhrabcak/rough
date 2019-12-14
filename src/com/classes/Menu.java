package com.classes;

import com.classes.KeyLisener.KeyListener;
import org.jnativehook.NativeHookException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Menu {
    public String menu;
    private Size fieldSize;
    private short treasures;
    private Position playerPosition;

    public Menu() throws IOException, NativeHookException, InterruptedException {
        if (this.menu == null) {
            this.menu = "1. New Game\n2. Options\n3. Scores\n0. Exit\n? ";
        }
        this.fieldSize = new Size( (short) 10, (short) 10); // Java is retarded
        this.treasures = 40;
        this.playerPosition = new Position(0, 0);

        while (true) {
            System.out.print(menu);
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String option = consoleReader.readLine();
            switch (option) {
                case "0":
                    System.exit(0);
                case "1":
                    this.newGame();
                    break;
                case "2":
                    this.options();
                    break;
                case "3":
                    this.scores();
                    break;

            }

        }
    }

    private void newGame() throws NativeHookException, IOException, InterruptedException {
        Field field = new Field(this.fieldSize.size, this.playerPosition.position);
        KeyListener listener = new KeyListener();
        Game game = new Game(listener, field);
    }

    private boolean options() throws IOException {
        while (true) {
            System.out.print("1. Change amount of treasures\n2. Change starting player position\n3. Change field size\n0. Go to the main menu\n? ");
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String option = consoleReader.readLine();
            String inp;
            switch (option) {
                case "0":
                    return true;
                case "1":
                    System.out.print("New value? ");
                    String value = consoleReader.readLine();
                    this.treasures = Short.parseShort(value);
                    break;
                case "2":
                    System.out.print("\nFormat: x-y\nNew position? ");
                    inp = consoleReader.readLine();
                    String[] position = inp.split("-", 0);
                    this.playerPosition = new Position((Integer.parseInt(position[0])), Integer.parseInt(position[1]));
                    break;
                case "3":
                    System.out.print("\nFormat: x-y\nNew position? ");
                    inp = consoleReader.readLine();
                    String[] size = inp.split("-");
                    this.fieldSize = new Size(Short.parseShort(size[0]), Short.parseShort(size[1]));
                    break;
                default:
                    System.out.println("Unknown option.");
                    break;
            }

        }

    }

    private void scores() {
        System.out.println(":((");
    }
}
