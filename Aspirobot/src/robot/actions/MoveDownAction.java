package ca.uqac.IA.Devoir1.robot.actions;

import ca.uqac.IA.Devoir1.environment.InterfaceEnvironment;
import ca.uqac.IA.Devoir1.environment.Tile;
import ca.uqac.IA.Devoir1.robot.Robot;
import ca.uqac.IA.Devoir1.robot.State;
import ca.uqac.IA.Devoir1.util.Position;

/**
 * Created by dhawo on 23/09/2016.
 */
public class MoveDownAction extends Action {
    public MoveDownAction(State state) {
        int destX = state.getCurrentTile().getX()+1;
        int y = state.getCurrentTile().getY();
        try{
            this.afterState = new State(state);
            this.afterState.moveRobot(new Position(destX,y));
            this.afterState.setElectricityUsed(this.afterState.getElectricityUsed() + 1);
            this.setLegal(true);
        }catch(IndexOutOfBoundsException ex){
            this.setLegal(false);
        }
    }

    @Override
    public void doAction(InterfaceEnvironment env, Robot robot) {
        try{
            Thread.sleep(this.RequiredTime);
        }catch (InterruptedException ex){

        }
        Tile currentTile = robot.getState().getCurrentTile();
        robot.move(new Position(currentTile.getX() + 1, currentTile.getY()));
        System.out.println(String.format("Aspirobot has moved to tile (%1$d,%2$d)", robot.getState().getCurrentTile().getX(), robot.getState().getCurrentTile().getY()));

    }
}
