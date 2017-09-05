package assignment2017;

import java.awt.*;
import javax.swing.*;

import assignment2017.codeprovided.Connect4GameState;

public class DrawingBoard extends JPanel {
    // Instance variables
    private MyGameState gs;

    /**
     * Constructor for the board
     * 
     * @param mgs
     *            MyGameState (Current gamestate)
     */
    public DrawingBoard(MyGameState mgs) {
        gs = mgs;
    }

    /**
     * Function to draw what board looks like
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        // Iterate through board array adding colour to board
        for (int i = Connect4GameState.NUM_ROWS - 1; i >= 0; i--) {
            for (int j = 0; j < Connect4GameState.NUM_COLS; j++) {
                int colour = gs.getCounterAt(j, i);
                if (colour == Connect4GameState.RED)
                    g.setColor(Color.RED);
                else if (colour == Connect4GameState.YELLOW)
                    g.setColor(Color.YELLOW);
                else
                    g.setColor(Color.WHITE);

                g.fillOval(j * 74, (Connect4GameState.NUM_ROWS - 1 - i) * 74, 73, 73);
            }
        }
    }

}
