package ca.uqac.IA.Devoir1.util;

/**
 * Created by dhawo on 23/09/2016.
 */
public class Position {
    int x;
    int y;

    public Position() {
        this.x = 0;
        this.y = 0;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position other){
        this.x = other.getX();
        this.y = other.getY();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
