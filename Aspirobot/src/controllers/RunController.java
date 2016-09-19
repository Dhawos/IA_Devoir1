package ca.uqac.IA.Devoir1.controllers;

import ca.uqac.IA.Devoir1.environment.Environment;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RunController extends MouseAdapter {
    private Environment environment;

    public RunController(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("Run Aspirobot")) {
                //Do stuff
            } else {
                //Do stuff
            }
        }
    }
}
