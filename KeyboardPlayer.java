package assignment2017;

import java.util.*;

import assignment2017.codeprovided.ColumnFullException;
import assignment2017.codeprovided.Connect4GameState;
import assignment2017.codeprovided.Connect4Player;
import assignment2017.codeprovided.IllegalColumnException;

public class KeyboardPlayer extends Connect4Player {
    // Instance variables
    private Scanner keyboard;

    public KeyboardPlayer() {
        keyboard = new Scanner(System.in);
    }

    public void makeMove(Connect4GameState gameState) {
        int selection = -1;

        do {
            System.out.println("Please enter a column number, 0 to 6 followed by return.");
            if (keyboard.hasNextInt())
                selection = keyboard.nextInt();
            else
                keyboard.nextLine();
        } while (selection < 0 || selection > Connect4GameState.NUM_COLS - 1
                || gameState.isColumnFull(selection) == true);

        try {
            gameState.move(selection);
        } catch (IllegalColumnException c) {
            System.out.println("Invalid column selection.");
        } catch (ColumnFullException f) {
            System.out.println("This column is full.");
        }
        System.out.println("Player dropped counter in column " + selection);
    }

}