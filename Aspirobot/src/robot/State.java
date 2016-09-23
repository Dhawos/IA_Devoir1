package ca.uqac.IA.Devoir1.robot;

import ca.uqac.IA.Devoir1.environment.Tile;

import java.util.ArrayList;

/**
 * Created by dhawo on 23/09/2016.
 */
public class State {
    private ArrayList<ArrayList<Tile>> map;
    private int electricityUsed;
    private Tile currentTile;

    public State(ArrayList<ArrayList<Tile>> map) {
        this.map = map;
        this.electricityUsed = 0;
        this.currentTile = map.get(0).get(0);
    }

    public State(ArrayList<ArrayList<Tile>> map, int electricityUsed, Tile currentTile) {
        this.map = map;
        this.electricityUsed = electricityUsed;
        this.currentTile = currentTile;
    }

    public ArrayList<ArrayList<Tile>> getMap() {
        return map;
    }

    public void setMap(ArrayList<ArrayList<Tile>> map) {
        this.map = map;
    }

    public int getElectricityUsed() {
        return electricityUsed;
    }

    public void setElectricityUsed(int electricityUsed) {
        this.electricityUsed = electricityUsed;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }
}
