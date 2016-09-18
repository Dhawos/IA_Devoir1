package environment;

public class Tile{
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

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isHasDirt() {
        return hasDirt;
    }

    public void setHasDirt(boolean hasDirt) {
        this.hasDirt = hasDirt;
    }

    public boolean isHasJewel() {
        return hasJewel;
    }

    public void setHasJewel(boolean hasJewel) {
        this.hasJewel = hasJewel;
    }
}



