package ca.uqac.IA.Devoir1.environment;

import java.util.Observable;

public class Tile extends Observable{
    private int x;
    private int y;
    private boolean hasDirt=false;
    private boolean hasJewel=false;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public boolean isHasDirt() {
        return hasDirt;
    }

    public void setHasDirt(boolean hasDirt) {
        this.hasDirt = hasDirt;
        setChanged();
        notifyObservers("Dirt");
        System.out.println("Dirt has been set on tile (" + x + "," + y + ").");
    }

    public boolean isHasJewel() {
        return hasJewel;
    }

    public void setHasJewel(boolean hasJewel) {
        this.hasJewel = hasJewel;
        setChanged();
        notifyObservers("Jewel");
        System.out.println("Jewel has been set on tile (" + x + "," + y + ").");

    }
}



