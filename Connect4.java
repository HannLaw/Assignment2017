package assignment2017;

import assignment2017.codeprovided.Connect4Displayable;
import assignment2017.codeprovided.Connect4GameState;
import assignment2017.codeprovided.Connect4Player;

public class Connect4 {

    private Connect4GameState gameState;
    private Connect4Player player1;
    private Connect4Player player2;
    private Connect4Displayable display;

    public Connect4(Connect4GameState g, Connect4Player p1, Connect4Player p2,
            Connect4Displayable o) {
        gameState = g;
        player1 = p1;
        player2 = p2;
        display = o;
    }

    public void play() {
        Connect4Player player = player1;

        gameState.startGame();

        do {
            display.displayBoard();
            System.out.println(player);
            player.makeMove(gameState);

            if (player == player1)
                player = player2;
            else
                player = player1;
        } while (gameState.gameOver() == false);

        display.displayBoard();

        if (gameState.getWinner() == Connect4GameState.RED)
            System.out.println("Red wins!");
        if (gameState.getWinner() == Connect4GameState.YELLOW)
            System.out.println("Yellow wins!");
    }
}
