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

    public State() {
        this.map = new Map();
        this.electricityUsed = 0;
        this.currentTile = map.getTile(0,0);
        this.nbJeweledPickedUp = 0;
    }

    public State(Map map) {
        this.map = map;
        this.electricityUsed = 0;
        this.currentTile = map.getTile(0,0);
        this.nbJeweledPickedUp = 0;
    }

    public State(State other) {
        this.map = new Map(other.getMap());
        this.electricityUsed = other.getElectricityUsed();
        this.currentTile = this.map.getTile(other.getCurrentPosition());
        this.nbJeweledPickedUp = other.getNbJeweledPickedUp();
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
            return 2;
        }
        int minX = getCurrentPosition().getX()-1;
        int maxX = getCurrentPosition().getX()+1;
        int minY = getCurrentPosition().getY()-1;
        int maxY = getCurrentPosition().getY()+1;
        boolean hasLeftDirtBehind = false;
        for(int i = minX; i <= maxX ; i++){ //The bot should not leave dirt in its surroundings
            for(int j = minY; j<=maxY ; j++){
                try{
                    if(this.getMap().getTile(i,j).isHasDirt() != other.getMap().getTile(i,j).isHasDirt()){
                        hasLeftDirtBehind = true;
                    }
                }catch(IndexOutOfBoundsException ex){

                }
            }
        }
        if(hasLeftDirtBehind){
            utility = 0;
        }else{
            utility = 1;
        }

        return utility;
    }


}
