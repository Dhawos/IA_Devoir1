package ca.uqac.IA.Devoir1.view;


import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MainFrame extends JFrame implements Observer {
    static final String TITLE = "ASPIROBOT T-0.1";
    static final String COLS_LETTERS = "ABCDE";
    private JButton controlButton = new JButton("Run Aspirobot");
    private JPanel[][] gamePanelSquares = new JPanel[3][5];
    private ArrayList<TilePanel> tileMap = new ArrayList(11);


    public MainFrame() {
        super(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout(0, 0));
        JPanel legendPanel = new JPanel(new GridLayout(3, 1, 5, 0));
        legendPanel.setBorder(new CompoundBorder(new EmptyBorder(100, 20, 300, 40), BorderFactory.createTitledBorder("Legend")));
        legendPanel.add(new JLabel("A is for Aspirobot"));
        legendPanel.add(new JLabel("D is for Dust"));
        legendPanel.add(new JLabel("J is for Jewel"));

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 20));
        controlPanel.add(controlButton);


        JPanel gamePanel = new JPanel(new GridLayout(0, 6));
        gamePanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        for (int i = 0; i < gamePanelSquares.length; i++) {
            for (int j = 0; j < gamePanelSquares[i].length; j++) {
                JPanel panel;
                if (j < 3 || i == 1) {
                    panel = new TilePanel(i, j);
                    tileMap.add((TilePanel) panel);
                } else {
                    panel = new JPanel();
                }
                gamePanelSquares[i][j] = panel;
            }
        }

        gamePanel.add(new JLabel(""));
        for (int i = 0; i < 5; i++) {
            gamePanel.add(new JLabel("" + (i + 1),
                    SwingConstants.CENTER));
        }
        for (int i = 0; i < gamePanelSquares.length; i++) {
            for (int j = 0; j < gamePanelSquares[i].length; j++) {
                switch (j) {
                    case 0:
                        gamePanel.add(new JLabel("" + (i + 1),
                                SwingConstants.CENTER));
                    default:
                        gamePanel.add(gamePanelSquares[i][j]);
                }
            }
        }

        add(legendPanel, BorderLayout.WEST);
        add(gamePanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        setResizable(false);
        setVisible(true);

    }

    @Override
    public void update(Observable o, Object arg) {
        /*if(Aspirobot.isRunning())
            controlButton.setText("Stop Aspirobot");
        else
            controlButton.setText("Run Aspirobot");
        */
    }

    public JButton getControlButton() {
        return controlButton;
    }

    public ArrayList<TilePanel> getTileMap() {
        return tileMap;
    }
}
