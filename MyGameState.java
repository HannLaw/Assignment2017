package assignment2017;

import assignment2017.codeprovided.ColumnFullException;
import assignment2017.codeprovided.Connect4GameState;
import assignment2017.codeprovided.IllegalColumnException;
import assignment2017.codeprovided.IllegalRowException;

public class MyGameState extends Connect4GameState {
    // Instance variables
    public int[][] board;
    public int turn;

    // Method declarations
    public void startGame() {
        board = new int[NUM_ROWS][NUM_COLS];
        // Iterate through array to initialise
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                board[i][j] = EMPTY;
            }
        }
        // Set initial turn
        turn = RED;
    }

    public void move(int col) throws ColumnFullException, IllegalColumnException {
        // Check for exceptions
        if (isColumnFull(col) == true)
            throw new ColumnFullException(col);
        if (col > NUM_COLS - 1)
            throw new IllegalColumnException(col);
        // Put counter in first available space in column
        for (int i = 0; i < NUM_ROWS; i++) {
            if (getCounterAt(col, i) == EMPTY) {
                int colour = whoseTurn();
                board[i][col] = colour;
                if (turn == RED)
                    turn = YELLOW;
                else
                    turn = RED;
                break;
            }
        }
    }

    public int whoseTurn() {
        if (turn == RED)
            return RED;
        else
            return YELLOW;
    }

    public int getCounterAt(int col, int row) throws IllegalColumnException, IllegalRowException {
        // Checking for exceptions
        if (col < 0 && col < NUM_COLS - 1)
            throw new IllegalColumnException(col);
        if (row < 0 && row < NUM_ROWS - 1)
            throw new IllegalRowException(row);
        // Find out counter
        int colour = board[row][col];

        return colour;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                if (board[i][j] == EMPTY)
                    return false;
            }
        }
        return true;
    }

    public boolean isColumnFull(int col) throws IllegalColumnException {
        if (col < 0 || col > NUM_COLS - 1)
            throw new IllegalColumnException(col);
        else {
            for (int i = 0; i < NUM_ROWS; i++) {
                if (board[i][col] == EMPTY)
                    return false;
            }
        }
        return true;
    }

    public int getWinner() {
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                if (board[i][j] != EMPTY) {
                    int colour = getCounterAt(j, i);
                    if (i < 3) {
                        // Check vertical
                        if (board[i + 1][j] == colour && board[i + 2][j] == colour
                                && board[i + 3][j] == colour)
                            return colour;
                    }
                    if (j < 4) {
                        // Check horizontal right
                        if (board[i][j + 1] == colour && board[i][j + 2] == colour
                                && board[i][j + 3] == colour)
                            return colour;
                        if (i < 3) {
                            // Check diagonal right
                            if (board[i + 1][j + 1] == colour && board[i + 2][j + 2] == colour
                                    && board[i + 3][j + 3] == colour)
                                return colour;
                        }
                    }
                    if (j > 3 && i < 3) {
                        // Check diagonal left
                        if (board[i + 1][j - 1] == colour && board[i + 2][j - 2] == colour
                                && board[i + 3][j - 3] == colour)
                            return colour;
                    }
                }
            }
        }
        return EMPTY;
    }

    public boolean gameOver() {
        if (getWinner() != EMPTY || isBoardFull() == true)
            return true;
        else
            return false;
    }

    public void setBoard(int[][] b) {
        board = b;
    }

    public void setTurn(int t) {
        turn = t;
    }

    public Connect4GameState copy() {
        MyGameState newGameState = new MyGameState();
        int[][] newBoard = new int[NUM_ROWS][NUM_COLS];

        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                newBoard[i][j] = getCounterAt(j, i);
            }
        }

        newGameState.setBoard(newBoard);
        newGameState.setTurn(turn);

        return newGameState;
    }
}
