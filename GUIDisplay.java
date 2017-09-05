package assignment2017;

import assignment2017.codeprovided.Connect4Displayable;
import assignment2017.codeprovided.Connect4GameState;
import assignment2017.codeprovided.Connect4Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class GUIDisplay extends JFrame implements Connect4Displayable {

    // Instance variables
    private Container contentPane = getContentPane();
    private JPanel playerPanel = new JPanel();
    private JPanel playerPanel1 = new JPanel();
    private JPanel playerPanel2 = new JPanel();
    private JPanel game = new JPanel();
    private JPanel buttonPanel = new JPanel();

    private MyGameState gs;
    private static int colNo = -1;
    private String choice1;
    private String choice2;

    /**
     * Constructor for the GUI display
     * 
     * @param gameState
     *            Current state of game
     * @param p1
     *            Which user is player 1
     * @param p2
     *            Which user is player 2
     */
    public GUIDisplay(Connect4GameState gameState, String p1, String p2) {
        gs = (MyGameState) gameState;

        // Setting up JFrame
        setTitle("Connect 4");
        contentPane.setLayout(new BorderLayout());

        Toolkit toolkit = Toolkit.getDefaultToolkit();

        Dimension screenDimension = toolkit.getScreenSize();
        setSize(700, 510);
        setLocation(new Point(((int) screenDimension.getWidth() / 2) - 350,
                ((int) screenDimension.getHeight() / 2) - 255));

        // Calling functions to display
        displayBoard();
        playerSelect(p1, p2);

        // Setting layout manager for entire game board
        game.setLayout(new BorderLayout());

        // Drawing what the gamestate looks like
        DrawingBoard board = new DrawingBoard(gs);
        board.setAlignmentY(CENTER_ALIGNMENT);
        game.add(board, BorderLayout.CENTER);

        // Adding game JPanel
        contentPane.add(game);

        // Adding column selection buttons with event listener
        buttonPanel.setLayout(new GridLayout(1, 7));
        for (int i = 0; i < 7; i++) {
            JButton c = new JButton(String.valueOf(i + 1));
            c.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    colNo = Integer.parseInt(c.getText()) - 1;
                    System.out.println(colNo);
                }
            });
            buttonPanel.add(c);
        }
        buttonPanel.setAlignmentY(CENTER_ALIGNMENT);
        game.add(buttonPanel, BorderLayout.SOUTH);

        // Final JFrame setup options
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    /**
     * Accessor for column picked
     * 
     * @return colNo picked
     */
    public static int getColNo() {
        return colNo;
    }

    /**
     * Sets colNo to neg number so column value 0 isn't always picked
     */
    public static void setColNo() {
        colNo = -1;
    }

    /**
     * Sets up new thread for new game function
     */
    private Runnable newGameRunner = new Runnable() {
        @Override
        public void run() {
            Connect4GameState gameState = new MyGameState();

            Connect4Player red = selectPlayer(choice1);
            Connect4Player yellow = selectPlayer(choice2);

            Connect4Displayable console = new GUIDisplay(gameState, choice1, choice2);

            Connect4 game = new Connect4(gameState, red, yellow, console);

            game.play();
        }
    };

    /**
     * Looks at combobox option to determine which players are used
     * 
     * @param s
     *            Current combobox option
     * @return Player to be put in new game
     */
    private Connect4Player selectPlayer(String s) {
        switch (s) {
        case "Human":
            return new GUIPlayer();
        case "Random Computer":
            return new RandomPlayer();
        case "Intelligent Computer":
            return new IntelligentPlayer();
        default:
            return new GUIPlayer();
        }
    }

    /**
     * JPanel with edit game options
     * 
     * @param p1
     *            Player1
     * @param p2
     *            Player2
     */
    public void playerSelect(String p1, String p2) {
        // Set a column layout
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

        // Combobox for player 1 option
        JComboBox<String> player1 = new JComboBox<String>();
        player1.addItem("Human");
        player1.addItem("Random Computer");
        player1.addItem("Intelligent Computer");
        // Displays current selection as default
        player1.setSelectedItem(p1);
        // Stops people putting in a different option
        player1.setEditable(false);

        // Combobox for player 2 option
        JComboBox<String> player2 = new JComboBox<String>();
        player2.addItem("Human");
        player2.addItem("Random Computer");
        player2.addItem("Intelligent Computer");
        // Displays current selection as default
        player2.setSelectedItem(p2);
        // Stops people putting in a different option
        player2.setEditable(false);

        // Frame around player 1 option
        TitledBorder title1;
        title1 = BorderFactory.createTitledBorder("Player 1");
        playerPanel1.setBorder(title1);
        // Adding combobox for player 1
        playerPanel1.add(player1);

        // Frame around player 2 option
        TitledBorder title2;
        title2 = BorderFactory.createTitledBorder("Player 2");
        playerPanel2.setBorder(title2);
        // Adding combobox for player 2
        playerPanel2.add(player2);

        // Start new game button
        JButton newGame = new JButton("Start New Game");
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Getting combobox selection
                choice1 = (String) player1.getSelectedItem();
                choice2 = (String) player2.getSelectedItem();
                // New thread
                Thread s = new Thread(newGameRunner);
                s.start();
                // Close original window
                dispose();
            }
        });
        newGame.setAlignmentX(CENTER_ALIGNMENT);

        // Setting layout of playerPanel
        contentPane.add(playerPanel, BorderLayout.EAST);
        playerPanel.add(Box.createVerticalGlue());
        playerPanel.add(playerPanel1);
        playerPanel.add(playerPanel2);
        playerPanel.add(newGame);
        playerPanel.add(Box.createVerticalGlue());
    }

    /**
     * Redraws the board after each turn
     */
    public void displayBoard() {
        repaint();
    }

}
