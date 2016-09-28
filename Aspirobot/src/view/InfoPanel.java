package ca.uqac.IA.Devoir1.view;

import ca.uqac.IA.Devoir1.robot.Robot;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class InfoPanel extends JPanel implements Observer {

    private JLabel jewelsPickedUp;
    private JLabel jewelsSwept;
    private JLabel dirtSwept;
    private JLabel eletricitySpent;

    private int nbJewelsPickedUp = 0;
    private int nbJewelsSwept = 0;
    private int nbDirtSwept = 0;
    private int nbElectricitySpent = 0;

    public InfoPanel(LayoutManager lm) {
        super(lm);
        add(new JLabel("Blue color is for Aspirobot"));
        add(new JLabel("D is for Dust"));
        add(new JLabel("J is for Jewel"));
        jewelsPickedUp =  new JLabel("Jewels picked up: " + String.valueOf(nbJewelsPickedUp));
        add(jewelsPickedUp);
        jewelsSwept = new JLabel("Jewels swept: " + String.valueOf(nbJewelsSwept));
        add(jewelsSwept);
        dirtSwept = new JLabel("Dirt swept: " + String.valueOf(nbDirtSwept));
        add(dirtSwept);
        eletricitySpent = new JLabel("Electricity spent: " + String.valueOf(nbElectricitySpent));
        add(eletricitySpent);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof String){
            switch ((String)arg){
                case "SweptJewel":
                    nbJewelsSwept++;
                    nbElectricitySpent++;
                    jewelsSwept.setText("Jewels swept: " + String.valueOf(nbJewelsPickedUp));
                    eletricitySpent.setText("Electricity spent: " + String.valueOf(nbElectricitySpent));
                    break;
                case "SweptDirt":
                    nbDirtSwept++;
                    dirtSwept.setText("Dirt swept: " + String.valueOf(nbDirtSwept));
                    nbElectricitySpent++;
                    eletricitySpent.setText("Electricity spent: " + String.valueOf(nbElectricitySpent));
                    break;
                case "PickedUp":
                    nbJewelsPickedUp++;
                    jewelsPickedUp.setText("Jewels picked up: " + String.valueOf(nbJewelsPickedUp));
                    nbElectricitySpent++;
                    eletricitySpent.setText("Electricity spent: " + String.valueOf(nbElectricitySpent));
                    break;
                default:
                    break;
            }
        }

        if(o instanceof Robot){
            nbElectricitySpent++;
            eletricitySpent.setText("Electricity spent: " + String.valueOf(nbElectricitySpent));
        }

    }
}
