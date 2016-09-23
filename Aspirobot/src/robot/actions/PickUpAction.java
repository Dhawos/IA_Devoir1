package ca.uqac.IA.Devoir1.robot.actions;

import ca.uqac.IA.Devoir1.environment.Environment;
import ca.uqac.IA.Devoir1.robot.Robot;
import ca.uqac.IA.Devoir1.robot.State;

/**
 * Created by dhawo on 23/09/2016.
 */
public class PickUpAction extends Action {
    public PickUpAction(State state) {
        this.afterState = state;
        this.afterState.getCurrentTile().setHasDirt(false);
        this.afterState.getCurrentTile().setHasJewel(false);
    }

    @Override
    public void doAction(Environment env, Robot robot) {

    }
}
