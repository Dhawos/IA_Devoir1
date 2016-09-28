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
    private int nbJeweledPickedUp;
    private int nbDirtSwept;

    public State() {
        this.map = new Map();
        this.electricityUsed = 0;
        this.currentTile = map.getTile(0,0);
        this.nbJeweledPickedUp = 0;
        this.nbDirtSwept = 0;
    }

    public State(Map map) {
        this.map = map;
        this.electricityUsed = 0;
        this.currentTile = map.getTile(0,0);
        this.nbJeweledPickedUp = 0;
        this.nbDirtSwept = 0;
    }

    public State(State other) {
        this.map = new Map(other.getMap());
        this.electricityUsed = other.getElectricityUsed();
        this.currentTile = this.map.getTile(other.getCurrentPosition());
        this.nbJeweledPickedUp = other.getNbJeweledPickedUp();
        this.nbDirtSwept = other.getNbDirtSwept();
    }

    public int getNbDirtSwept() {
        return nbDirtSwept;
    }

    public void setNbDirtSwept(int nbDirtSwept) {
        this.nbDirtSwept = nbDirtSwept;
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

    public int getNbJeweledPickedUp() {
        return nbJeweledPickedUp;
    }

    public void setNbJeweledPickedUp(int nbJeweledPickedUp) {
        this.nbJeweledPickedUp = nbJeweledPickedUp;
    }

    public void moveRobot(Position pos) throws IndexOutOfBoundsException{
        if(pos.getX() > 0 || pos.getX() < this.getMap().getNbLines()){
            if(pos.getY() > 0 || pos.getY() < this.getMap().getNbTilesInLine(pos.getX())){
                this.currentTile = this.getMap().getTile(pos);
            }
        }else{
            throw new IndexOutOfBoundsException();
        }
    }

    public int rate(State other){
        int utility = 0;
        if(this.nbJeweledPickedUp == other.getNbJeweledPickedUp()){
            utility = 100;
        }
        if(this.nbDirtSwept == other.getNbDirtSwept()){
            utility = 10;
        }

        return utility;
    }


}
