package ca.uqac.IA.Devoir1.environment;

import ca.uqac.IA.Devoir1.robot.Robot;

/**
 * Created by dhawo on 25/09/2016.
 */
public class InterfaceEnvironment {
    private Environment env;
    private Robot robot;

    public InterfaceEnvironment(Environment env, Robot robot) {
        this.env = env;
        this.robot = robot;
    }

    public void sweep(){
    Tile tile = this.env.getMap().getTile(this.robot.getState().getCurrentPosition());
        tile.setHasDirt(false);
        if(tile.isHasJewel()){
        this.env.incrementNbJewelsSwept();
    }
        tile.setHasJewel(false);
}

    public void pickUpJewel(){
        Tile tile = this.env.getMap().getTile(this.robot.getState().getCurrentPosition());
        tile.setHasJewel(false);
    }
}
