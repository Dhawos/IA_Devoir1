package ca.uqac.IA.Devoir1.controllers;

import ca.uqac.IA.Devoir1.environment.Environment;
import ca.uqac.IA.Devoir1.robot.actions.*;
import ca.uqac.IA.Devoir1.robot.actions.Action;
import javafx.scene.media.MediaPlayer;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

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
                    File yourFile = new File(getClass().getClassLoader().getResource("gottagofast.wav").getPath());
                    AudioInputStream stream;
                    AudioFormat format;
                    DataLine.Info info;

                    stream = AudioSystem.getAudioInputStream(yourFile);
                    format = stream.getFormat();
                    info = new DataLine.Info(Clip.class, format);
                    clip = (Clip) AudioSystem.getLine(info);
                    clip.open(stream);
                    clip.start();
                    Action.RequiredTime = 200;
                    button.setText("STOP GO FAST");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else if (button.getText().equals("STOP GO FAST")) {
                clip.stop();
                Action.RequiredTime = 2000;
                button.setText("GOTTA GO FAST");
            }
        }
    }
}
