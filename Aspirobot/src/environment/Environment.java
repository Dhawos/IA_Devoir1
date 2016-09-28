package ca.uqac.IA.Devoir1.environment;

import java.util.Random;
import java.util.TimerTask;

public class Environment extends TimerTask {
    public static final int DEFAULT_TICKRATE = 20;//Number of evaluation per minute
    private static final int JEWEL_INV_PROBABILITY = 5;
    private static final int DIRT_INV_PROBABILITY = 3;
    private Map map;
    private int electricityUsed;
    private boolean isGameRunning;
    private Random rng;
    private int jewelsSwept;
    private int jewelsPickedUp;
    private int dirtSwept;

    private void generateDirt(){
        int x = rng.nextInt(map.getNbLines());
        int y = rng.nextInt(map.getNbTilesInLine(x));
        map.getTile(x,y).setHasDirt(true);
    }
    private void generateJewel(){
        int x = rng.nextInt(map.getNbLines());
        int y = rng.nextInt(map.getNbTilesInLine(x));
        map.getTile(x,y).setHasJewel(true);
    }
    private boolean shouldThereBeANewDirtySpace(){
        int result = rng.nextInt(DIRT_INV_PROBABILITY);
        return result == 0;
    }
    private boolean shouldThereBeANewLostJewel(){
        int result = rng.nextInt(JEWEL_INV_PROBABILITY);
        return result == 0;
    }

    public Environment() {
        map = new Map();
        electricityUsed = 0;
        jewelsSwept = 0;
        jewelsPickedUp = 0;
        dirtSwept = 0;
        isGameRunning = false;
        rng = new Random();
    }

    private void computeNextState(){
        if(shouldThereBeANewDirtySpace()){
            generateDirt();
        }
        if(shouldThereBeANewLostJewel()){
            generateJewel();
        }
    }

    @Override
    public void run(){
        computeNextState();
    }

    public Map getMap() {
        return map;
    }

    public boolean isGameRunning() {
        return isGameRunning;
    }

    public void incrementNbJewelsSwept(){
        this.jewelsSwept++;
    }

    public void incrementNbJewelsPickedUp(){
        this.jewelsPickedUp++;
    }

    public void incrementDirtSwept(){
        this.dirtSwept++;
    }
}
