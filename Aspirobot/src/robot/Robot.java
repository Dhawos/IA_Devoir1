package ca.uqac.IA.Devoir1.robot;

import ca.uqac.IA.Devoir1.environment.Tile;

import java.util.ArrayList;

/**
 * Created by dhawo on 23/09/2016.
 */
public class Robot implements Runnable {
    private boolean isAlive;
    private ArrayList<ArrayList<Tile>> map;

    @Override
    public void run() {
        while (isAlive()){
            //ObserveEnvironmentWithAllMySensors();
            //UpdateMyState();
            //ChooseAnAction();
            //justDoIt();
        }
    }

    public boolean isAlive() {
        return isAlive;
    }
}
