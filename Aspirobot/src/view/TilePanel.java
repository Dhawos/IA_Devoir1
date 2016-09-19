package ca.uqac.IA.Devoir1.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class TilePanel extends JPanel implements Observer {

    private int xPos;
    private int yPos;
    private JLabel label = new JLabel("empty");

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }


    public TilePanel(int x, int y) {
        xPos = x;
        yPos = y;
        add(label);
        setBackground(Color.WHITE);
        setBorder(new LineBorder(Color.black));
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof String){
            switch ((String)arg){
                case "Dirt":
                    label.setText("D");
                    break;
                case "Jewel":
                    label.setText("J");
                    break;
                case "Aspirobot":
                    label.setText("A");
                    break;
                default:
                    break;

            }
        }
    }
}
