package assignment2017;

import java.util.*;

import assignment2017.codeprovided.ColumnFullException;
import assignment2017.codeprovided.Connect4GameState;
import assignment2017.codeprovided.Connect4Player;
import assignment2017.codeprovided.IllegalColumnException;

public class GUIPlayer extends Connect4Player {
    // Instance variables

    public GUIPlayer() {

    }

    public void makeMove(Connect4GameState gameState) {
        int selection;
        GUIDisplay.setColNo();

        do {
            selection = GUIDisplay.getColNo();
            try {
                Thread.sleep(1);
            } catch (Exception e) {

            }

        } while (selection < 0 || selection > Connect4GameState.NUM_COLS - 1
                || gameState.isColumnFull(selection) == true);

        try {
            gameState.move(selection);
        } catch (IllegalColumnException c) {
            System.out.println("Invalid column selection.");
        } catch (ColumnFullException f) {
            System.out.println("This column is full.");
        }

    }

}