package assignment2017;

import assignment2017.codeprovided.ColumnFullException;
import assignment2017.codeprovided.Connect4GameState;
import assignment2017.codeprovided.Connect4Player;
import assignment2017.codeprovided.IllegalColumnException;

public class RandomPlayer extends Connect4Player {

    public void makeMove(Connect4GameState gameState) {
        // Get random integer between 0 and 6
        int selection = (int) Math.floor(Math.random() * Connect4GameState.NUM_COLS);
        // Loop if invalid selection
        while (gameState.isColumnFull(selection) == true) {
            selection = (int) Math.floor(Math.random() * Connect4GameState.NUM_COLS);
        }

        try {
            gameState.move(selection);
        } catch (IllegalColumnException c) {
            System.out.println("Invalid column selection.");
        } catch (ColumnFullException f) {
            System.out.println("This column is full.");
        }
        System.out.println("Computer dropped counter in column " + selection);
    }

}
