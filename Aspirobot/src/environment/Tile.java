package ca.uqac.IA.Devoir1.environment;

import ca.uqac.IA.Devoir1.util.Position;

import java.util.Observable;

public class Tile extends Observable{
    private Position position;
    private boolean hasDirt=false;
    private boolean hasJewel=false;

    public Tile(int x, int y) {
        this.position = new Position(x,y);
    }

    public Tile(Position position) {
        this.position = position;
    }

    public Tile(Tile other){
        this.position = new Position(other.getPosition());
        this.hasDirt = other.isHasDirt();
        this.hasJewel = other.isHasJewel();
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public Position getPosition(){
        return this.position;
    }

    public boolean isHasDirt() {
        return hasDirt;
    }

    public void setHasDirt(boolean hasDirt) {
        this.hasDirt = hasDirt;
        setChanged();
        notifyObservers();
        //System.out.println("Dirt has been set on tile (" + position.getX() + "," + position.getY()+ ").");
    }

    public boolean isHasJewel() {
        return hasJewel;
    }

    public void setHasJewel(boolean hasJewel) {
        this.hasJewel = hasJewel;
        setChanged();
        notifyObservers();
        //System.out.println("Jewel has been set on tile (" + position.getX() + "," + position.getY() + ").");

    }
}



