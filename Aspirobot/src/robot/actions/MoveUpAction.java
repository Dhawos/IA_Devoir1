package ca.uqac.IA.Devoir1.robot.actions;

import ca.uqac.IA.Devoir1.environment.Environment;
import ca.uqac.IA.Devoir1.robot.Robot;
import ca.uqac.IA.Devoir1.robot.State;
import ca.uqac.IA.Devoir1.util.Position;

/**
 * Created by dhawo on 23/09/2016.
 */
public class MoveUpAction extends Action{
    public MoveUpAction(State state) {
        int x = state.getCurrentTile().getX();
        int destY = state.getCurrentTile().getY()-1;
        try{
            this.afterState = state;
            this.afterState.moveRobot(new Position(x,destY));
        }catch(IndexOutOfBoundsException ex){
            this.setLegal(false);
        }
    }

    @Override
    public void doAction(Environment env, Robot robot) {

    }
}
