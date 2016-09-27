package ca.uqac.IA.Devoir1;

import ca.uqac.IA.Devoir1.controllers.RunController;
import ca.uqac.IA.Devoir1.environment.Environment;
import ca.uqac.IA.Devoir1.environment.InterfaceEnvironment;
import ca.uqac.IA.Devoir1.environment.Tile;
import ca.uqac.IA.Devoir1.robot.Robot;
import ca.uqac.IA.Devoir1.robot.sensors.DirtSensor;
import ca.uqac.IA.Devoir1.robot.sensors.JewelSensor;
import ca.uqac.IA.Devoir1.view.MainFrame;
import ca.uqac.IA.Devoir1.view.TilePanel;

import java.awt.*;
import java.util.Timer;

public class Aspirobot {

    private RunController controller;
    private Environment environment;
    private Robot robot;

    public static void main(String[] args) {
        Aspirobot main = new Aspirobot();

        main.environment = new Environment();
        main.robot = new Robot();
        main.robot.addSensor(new DirtSensor(main.environment,main.robot));
        main.robot.addSensor(new JewelSensor(main.environment,main.robot));
        main.robot.setEnv(new InterfaceEnvironment(main.environment,main.robot));
        main.initControllers();
        main.initView();

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(main.environment, 0, (int)(60.0/Environment.DEFAULT_TICKRATE*1000));
        Thread robotThread = new Thread(main.robot);
        robotThread.start();
}

    public void initView() {
        MainFrame mainFrame = new MainFrame();
        mainFrame.getControlButton().addMouseListener(controller);

        for (int i=0;i<environment.getMap().getNbLines();i++) {
            for (int j=0;j<environment.getMap().getNbTilesInLine(i);j++) {
                Tile realTile = environment.getMap().getTile(i,j);
                TilePanel realTilePanel = mainFrame.getRealTileMap().stream().filter(t -> t.getXPos() == realTile.getX() && t.getYPos() == realTile.getY()).findFirst().get();
                realTile.addObserver(realTilePanel);
                robot.addObserver(realTilePanel);

                Tile robotTile = robot.getState().getMap().getTile(i,j);
                TilePanel robotTilePanel = mainFrame.getRobotTileMap().stream().filter(t -> t.getXPos() == robotTile.getX() && t.getYPos() == robotTile.getY()).findFirst().get();
                robotTile.addObserver(robotTilePanel);
                robot.addObserver(robotTilePanel);

                if(i == 0 && j == 0){
                    realTilePanel.setBackground(Color.BLUE);
                    robotTilePanel.setBackground(Color.BLUE);
                }
            }
        }
    }

    public void initControllers() {
        controller = new RunController(environment);
    }
}
