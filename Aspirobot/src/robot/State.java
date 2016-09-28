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

    public Tile getCurrentTile() throws NullPointerException {
        return currentTile;
    }

    public Position getCurrentPosition(){
        if(this.currentTile != null){
            return new Position(currentTile.getX(),currentTile.getY());
        }
        else{
            return null;
        }
    }

    public int getNbJeweledPickedUp() {
        return nbJeweledPickedUp;
    }

    public void setNbJeweledPickedUp(int nbJeweledPickedUp) {
        this.nbJeweledPickedUp = nbJeweledPickedUp;
    }

    public void moveRobot(Position pos) throws IndexOutOfBoundsException{
        if(pos == null){
            this.currentTile = null;
        }else{
            if(pos.getX() > 0 || pos.getX() < this.getMap().getNbLines()){
                if(pos.getY() > 0 || pos.getY() < this.getMap().getNbTilesInLine(pos.getX())){
                    this.currentTile = this.getMap().getTile(pos);
                }
            }else{
                throw new IndexOutOfBoundsException();
            }
        }
    }

    public int rate(State other){
        int utility = 10;
        if(this.nbJeweledPickedUp == other.getNbJeweledPickedUp()){
            return 50;
        }
        if(this.nbDirtSwept == other.getNbDirtSwept()){
            return 49;
        }
        if(other.getCurrentTile() != null){
            utility -= (Math.abs(other.getCurrentPosition().getX()-this.getCurrentPosition().getX()) + Math.abs(other.getCurrentPosition().getY()-this.getCurrentPosition().getY()));
        }else{
            utility -= 10;
        }
        return utility;
        }
}
