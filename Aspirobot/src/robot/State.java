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
        this.map = new Map(other.getMap());
        this.electricityUsed = other.getElectricityUsed();
        this.currentTile = this.map.getTile(other.getCurrentPosition());
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

    public void moveRobot(Position pos) throws IndexOutOfBoundsException{
        if(pos.getX() > 0 || pos.getX() < this.getMap().getNbLines()){
            if(pos.getY() > 0 || pos.getY() < this.getMap().getNbTilesInLine(pos.getX())){
                this.currentTile = this.getMap().getTile(pos);
            }
        }else{
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean compare(State other){
        boolean same = true;
        for(int i = 0;i < this.map.getNbLines(); i++){
            for(int j = 0; j < this.map.getNbTilesInLine(i); j++){
                if(!(this.map.getTile(i,j).isHasJewel() == other.map.getTile(i,j).isHasJewel() && this.map.getTile(i,j).isHasDirt() == other.map.getTile(i,j).isHasDirt())){
                    same = false;
                }
            }
        }
        return same;
    }
}
