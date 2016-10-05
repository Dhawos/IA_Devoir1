package ca.uqac.IA.Devoir1.controllers;

import ca.uqac.IA.Devoir1.environment.Environment;
import ca.uqac.IA.Devoir1.robot.actions.Action;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class RunController extends MouseAdapter {
    private Environment environment;
    private Clip clip;

    public RunController(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("GOTTA GO FAST")) {

                try {
                    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("gottagofast.wav");
                    InputStream bufferedIn = new BufferedInputStream(inputStream);
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                    AudioFormat format = audioStream.getFormat();
                    DataLine.Info info = new DataLine.Info(Clip.class, format);
                    clip = (Clip) AudioSystem.getLine(info);
                    clip.open(audioStream);
                    clip.start();
                    Action.RequiredTime = 200;
                    Environment.DEFAULT_TICKRATE = 60;
                    button.setText("STOP GO FAST");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else if (button.getText().equals("STOP GO FAST")) {
                clip.stop();
                Action.RequiredTime = 2000;
                Environment.DEFAULT_TICKRATE = 20;
                button.setText("GOTTA GO FAST");
            }
        }
    }
}
