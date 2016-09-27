package ca.uqac.IA.Devoir1.view;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class InfoPanel extends JPanel implements Observer {

    private JLabel jewelCount = new JLabel("Jewels picked up: 0");
    private JLabel eletricitySpent = new JLabel("Electricity spent: 0");

    public InfoPanel(LayoutManager lm) {
        super(lm);
        add(new JLabel("Blue color is for Aspirobot"));
        add(new JLabel("D is for Dust"));
        add(new JLabel("J is for Jewel"));
        add(jewelCount);
        add(eletricitySpent);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
