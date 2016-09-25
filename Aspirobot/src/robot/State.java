package ca.uqac.IA.Devoir1.robot;

import ca.uqac.IA.Devoir1.environment.Map;
import ca.uqac.IA.Devoir1.environment.Tile;
import ca.uqac.IA.Devoir1.util.Position;

/**
 * Created by dhawo on 23/09/2016.
 */
public class State {
    private Map map;
    private int electricityUsed;
    private Tile currentTile;

    public State() {
        this.map = new Map();
        this.electricityUsed = 0;
        this.currentTile = map.getTile(0,0);
    }

    public State(Map map) {
        this.map = map;
        this.electricityUsed = 0;
        this.currentTile = map.getTile(0,0);
    }

    public State(Map map, int electricityUsed, Tile currentTile) {
        this.map = map;
        this.electricityUsed = electricityUsed;
        this.currentTile = currentTile;
    }

    public State(State other) {
        this.map = other.getMap();
        this.electricityUsed = other.getElectricityUsed();
        this.currentTile = other.getCurrentTile();
    }

    public Map getMap() {
        return map;
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

    public Position getCurrentPosition(){return new Position(currentTile.getX(),currentTile.getY());}

    public void moveRobot(Position pos){
        this.currentTile = this.getMap().getTile(pos);
    }
}
