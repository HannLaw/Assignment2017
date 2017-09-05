package assignment2017;

import assignment2017.codeprovided.Connect4Displayable;
import assignment2017.codeprovided.Connect4GameState;

public class ConsoleDisplay implements Connect4Displayable {

    private MyGameState g;

    public ConsoleDisplay(Connect4GameState gameState) {
        g = (MyGameState) gameState;
    }

    public void displayBoard() {
        for (int i = Connect4GameState.NUM_ROWS - 1; i >= 0; i--) {
            System.out.print("|");
            for (int j = 0; j < Connect4GameState.NUM_COLS; j++) {
                int colour = g.getCounterAt(j, i);

                if (colour == Connect4GameState.EMPTY)
                    System.out.print("   ");
                if (colour == Connect4GameState.RED)
                    System.out.print(" R ");
                if (colour == Connect4GameState.YELLOW)
                    System.out.print(" Y ");

            }
            System.out.println("|");
        }

        System.out.print(" ");
        for (int j = 0; j < Connect4GameState.NUM_COLS; j++) {
            System.out.print("---");
        }
        System.out.println();

        System.out.print(" ");
        for (int j = 0; j < Connect4GameState.NUM_COLS; j++) {
            System.out.print(" " + j + " ");
        }
        System.out.println();
    }

}
