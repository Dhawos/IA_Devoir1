package ca.uqac.IA.Devoir1.robot.actions;

import ca.uqac.IA.Devoir1.environment.InterfaceEnvironment;
import ca.uqac.IA.Devoir1.robot.Robot;
import ca.uqac.IA.Devoir1.robot.State;

/**
 * Created by dhawo on 23/09/2016.
 */
public class PickUpAction extends Action {
    public PickUpAction(State state) {
        this.afterState = new State(state);
        this.afterState.getCurrentTile().setHasDirt(false);
        this.afterState.getCurrentTile().setHasJewel(false);
    }

    @Override
    public void doAction(InterfaceEnvironment env, Robot robot) {
        env.pickUpJewel();
        System.out.println("Aspirbot has picked up jewels");
    }
}
