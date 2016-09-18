package environment;

import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

public class Environment extends TimerTask {
    public static final int DEFAULT_TICKRATE = 60;
    private static final int JEWEL_INV_PROBABILITY = 5;
    private static final int DIRT_INV_PROBABILITY = 3;
    private ArrayList<ArrayList<Tile>> map;
    private int electricityUsed;
    private boolean isGameRunning;
    private Random rng;

    private void generateDirt(){
        int x = rng.nextInt(map.size()-1);
        int y = rng.nextInt(map.get(x).size()-1);
        map.get(x).get(y).setHasDirt(true);
    }
    private void generateJewel(){
        int x = rng.nextInt(map.size()-1);
        int y = rng.nextInt(map.get(x).size()-1);
        map.get(x).get(y).setHasDirt(false);
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
        map = new ArrayList<>(3);
        int i = 0;
        for (ArrayList<Tile> row : map) {
            if(i != 1){
                row = new ArrayList<>(3);
            }else{
                row = new ArrayList<>(5);
            }
            i++;
        }
        electricityUsed = 0;
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
}
