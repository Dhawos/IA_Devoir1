package ca.uqac.IA.Devoir1.environment;

import ca.uqac.IA.Devoir1.util.Position;

import java.util.ArrayList;

/**
 * Created by dhawo on 23/09/2016.
 */
public class Map {
    private ArrayList<ArrayList<Tile>> data;

    public Map() {
        this.data = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            if(i != 1){
                this.data.add(i,new ArrayList<>(3));
                for(int j = 0; j < 3; j++){
                    this.data.get(i).add(j, new Tile(i,j));
                }
            }else{
                this.data.add(i,new ArrayList<>(5));
                for(int j = 0; j < 5; j++){
                    this.data.get(i).add(j, new Tile(i,j));
                }
            }

        }
    }

    public Map(Map other) {
        this.data = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            if(i != 1){
                this.data.add(i,new ArrayList<>(3));
                for(int j = 0; j < 3; j++){
                    this.data.get(i).add(j, new Tile(other.getTile(i,j)));
                }
            }else{
                this.data.add(i,new ArrayList<>(5));
                for(int j = 0; j < 5; j++){
                    this.data.get(i).add(j, new Tile(other.getTile(i,j)));
                }
            }

        }
    }

    public Map(ArrayList<ArrayList<Tile>> data) {
        this.data = data;
    }

    public Tile getTile(int x, int y) throws ArrayIndexOutOfBoundsException{
        if(x < 0 || x > this.data.size()-1){
            throw new ArrayIndexOutOfBoundsException();
        }else if(y < 0 || y > this.data.get(x).size()-1){
            throw new ArrayIndexOutOfBoundsException();
        }else{
            return this.data.get(x).get(y);
        }
    }

    public Tile getTile(Position pos) throws ArrayIndexOutOfBoundsException{
        int x = pos.getX();
        int y = pos.getY();
        return getTile(x,y);
    }

    public int getNbLines(){
        return this.data.size();
    }

    public int getNbTilesInLine(int x){
        if(x < 0 || x > this.data.size()-1){
            throw new ArrayIndexOutOfBoundsException();
        }else{
            return this.data.get(x).size();
        }
    }
}
