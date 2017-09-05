package assignment2017;

import assignment2017.codeprovided.Connect4Displayable;
import assignment2017.codeprovided.Connect4GameState;
import assignment2017.codeprovided.Connect4Player;

public class PlayConnect4 {

    public static void main(String[] args) {

        if ("-gui".equals(args[0])) {
            Connect4GameState gameState = new MyGameState();
            Connect4Player red = new GUIPlayer();
            Connect4Player yellow = new GUIPlayer();

            Connect4Displayable console = new GUIDisplay(gameState, "Human", "Human");
            Connect4 game = new Connect4(gameState, red, yellow, console);

            game.play();
        } else {
            Connect4GameState gameState = new MyGameState();
            Connect4Player red = new RandomPlayer();
            Connect4Player yellow = new KeyboardPlayer();

            ConsoleDisplay console = new ConsoleDisplay(gameState);

            Connect4 game = new Connect4(gameState, red, yellow, console);

            game.play();
        }

    }

}
