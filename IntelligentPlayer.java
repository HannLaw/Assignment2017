package assignment2017;

import assignment2017.codeprovided.ColumnFullException;
import assignment2017.codeprovided.Connect4GameState;
import assignment2017.codeprovided.Connect4Player;
import assignment2017.codeprovided.IllegalColumnException;

public class IntelligentPlayer extends Connect4Player {
    // Instance variables
    public int[][] board;
    public int turn;
    private int selection;

    public void makeMove(Connect4GameState gameState) {
        /*
         * Desired Implementation
         * 
         * First search for 3 of same colour in a row(vertical horizontal or diagonal)
         * Then make move to complete 4
         * 
         * If that fails then do the same looking for 2 in a row
         * Then make move to complete 3
         * 
         * If that fails then looking for 1
         * Then make move to complete 2
         * 
         * Else make random move
         * 
         * If there is more than one option then do first checked option
         */
        
        
        // Get random integer between 0 and 6
        selection = (int) Math.floor(Math.random() * Connect4GameState.NUM_COLS);
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
        
/*        int bestCol = (int) Math.floor(Math.random() * Connect4GameState.NUM_COLS);
        int bestWin = -100000000;
        
        int me = gameState.whoseTurn();

        for (int i = 0; i < Connect4GameState.NUM_COLS; i++) {
            MyGameState copyState = (MyGameState) gameState.copy();
            
            int score = 0;
            try {
                copyState.move(i);
                
                if (copyState.getWinner() == me) {
                    score++;
                }
            } catch (IllegalColumnException c) {
                System.out.println("Invalid column selection.");
            } catch (ColumnFullException f) {
                System.out.println("This column is full.");
            }
            
            if (score > bestWin) {
                bestCol = i;
                bestWin = score;
            }
        }
        
        try {
            gameState.move(bestCol);
        } catch (Exception e) {
            selection = (int) Math.floor(Math.random() * Connect4GameState.NUM_COLS);
            gameState.move(selection);
            while (gameState.isColumnFull(selection) == true) {
                selection = (int) Math.floor(Math.random() * Connect4GameState.NUM_COLS);
            }
        }*/
        
    }

}
