package ca.uqac.IA.Devoir1.robot.actions;

import ca.uqac.IA.Devoir1.environment.Environment;
import ca.uqac.IA.Devoir1.robot.Robot;
import ca.uqac.IA.Devoir1.robot.State;

/**
 * Created by dhawo on 23/09/2016.
 */
public class MoveDownAction extends Action {
    public MoveDownAction(State state) {
        int x = state.getCurrentTile().getX();
        int destY = state.getCurrentTile().getY()+1;
        try{
            state.getMap().getTile(x,destY);
        }catch(IndexOutOfBoundsException ex){
            this.setLegal(false);
        }
    }

    @Override
    public void doAction(Environment env, Robot robot) {

    }
}
