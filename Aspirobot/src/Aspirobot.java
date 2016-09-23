package ca.uqac.IA.Devoir1;

import ca.uqac.IA.Devoir1.controllers.RunController;
import ca.uqac.IA.Devoir1.environment.Environment;
import ca.uqac.IA.Devoir1.environment.Tile;
import ca.uqac.IA.Devoir1.view.MainFrame;
import ca.uqac.IA.Devoir1.view.TilePanel;

import java.util.ArrayList;
import java.util.Timer;

public class Aspirobot {

    private RunController controller;
    private Environment environment;

    public static void main(String[] args) {
        Aspirobot main = new Aspirobot();

        main.environment = new Environment();
        main.initControllers();
        main.initView();

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(main.environment, 0, 1*1000);
}

    public void initView() {
        MainFrame mainFrame = new MainFrame();
        mainFrame.getControlButton().addMouseListener(controller);

        for (ArrayList<Tile> row : environment.getMap()) {
            for (Tile tile : row) {
                TilePanel tilePanel = mainFrame.getTileMap().stream().filter(t -> t.getXPos() == tile.getX() && t.getYPos() == tile.getY()).findFirst().get();
                tile.addObserver(tilePanel);
            }
        }
    }

    public void initControllers() {
        controller = new RunController(environment);
    }
}
