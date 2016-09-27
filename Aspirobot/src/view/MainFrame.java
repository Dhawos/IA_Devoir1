package ca.uqac.IA.Devoir1.view;


import ca.uqac.IA.Devoir1.util.Position;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MainFrame extends JFrame implements Observer {
    static final String TITLE = "ASPIROBOT T-0.1";
    private JButton controlButton = new JButton("Run Aspirobot");
    private JPanel[][] gamePanelSquares = new JPanel[3][5];
    private ArrayList<TilePanel> realTileMap;
    private ArrayList<TilePanel> robotTileMap;


    public MainFrame() {
        super(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 600);
        setLayout(new BorderLayout(0, 0));
        JPanel legendPanel = new InfoPanel(new GridLayout(6, 1, 5, 0));
        legendPanel.setBorder(new CompoundBorder(new EmptyBorder(100, 20, 150, 40), BorderFactory.createTitledBorder("Information")));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(controlButton, BorderLayout.EAST);
        bottomPanel.setBorder(new EmptyBorder(10, 20, 20, 20));

        JPanel centerPanel = new JPanel(new GridLayout(1, 0));

        realTileMap = createBoard(centerPanel, "Real Board");
        robotTileMap = createBoard(centerPanel, "Aspirobot's Board");

        initLogger(bottomPanel);

        add(centerPanel, BorderLayout.CENTER);
        add(legendPanel, BorderLayout.WEST);
        add(bottomPanel, BorderLayout.SOUTH);
        setResizable(false);
        setVisible(true);

    }

    private ArrayList<TilePanel> createBoard(JPanel parentPanel, String boardName) {
        JPanel gamePanel = new JPanel(new BorderLayout());
        JPanel boardPanel = new JPanel(new GridLayout(0, 6));
        boardPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        ArrayList<TilePanel> tileMap = new ArrayList<>(11);

        for (int i = 0; i < gamePanelSquares.length; i++) {
            for (int j = 0; j < gamePanelSquares[i].length; j++) {
                JPanel panel;
                if (j < 3 || i == 1) {
                    panel = new TilePanel(j, i); //inverted to stick with model
                    tileMap.add((TilePanel) panel);
                } else {
                    panel = new JPanel();
                }
                gamePanelSquares[i][j] = panel;
            }
        }

        boardPanel.add(new JLabel(""));
        for (int i = 0; i < 5; i++) {
            boardPanel.add(new JLabel("" + i,
                    SwingConstants.CENTER));
        }
        for (int i = 0; i < gamePanelSquares.length; i++) {
            for (int j = 0; j < gamePanelSquares[i].length; j++) {
                switch (j) {
                    case 0:
                        boardPanel.add(new JLabel("" + i,
                                SwingConstants.CENTER));
                    default:
                        boardPanel.add(gamePanelSquares[i][j]);
                }
            }
        }

        gamePanel.add(boardPanel, BorderLayout.CENTER);

        JLabel label = new JLabel(boardName);
        label.setHorizontalAlignment(JLabel.CENTER);
        gamePanel.add(label, BorderLayout.SOUTH);

        parentPanel.add(gamePanel);

        return tileMap;

    }

    private void initLogger(JPanel bottomPanel) {
        EventQueue.invokeLater(() -> {
            LogPanel logPanel = new LogPanel();
            bottomPanel.add(logPanel, BorderLayout.CENTER);

            PrintStream ps = System.out;
            System.setOut(new PrintStream(new StreamLogger("STDOUT", logPanel, ps)));
        });
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public JButton getControlButton() {
        return controlButton;
    }

    public ArrayList<TilePanel> getRealTileMap() {
        return realTileMap;
    }

    public ArrayList<TilePanel> getRobotTileMap() {
        return robotTileMap;
    }
}
