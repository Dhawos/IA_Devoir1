package ca.uqac.IA.Devoir1.robot.actions;

import ca.uqac.IA.Devoir1.environment.InterfaceEnvironment;
import ca.uqac.IA.Devoir1.robot.Robot;
import ca.uqac.IA.Devoir1.robot.State;

/**
 * Created by dhawo on 23/09/2016.
 */
public class SweepAction extends Action {
    public SweepAction(State state) {
        this.afterState = state;
        this.afterState.getCurrentTile().setHasDirt(false);
        this.afterState.getCurrentTile().setHasJewel(false);
    }

    @Override
    public void doAction(InterfaceEnvironment env, Robot robot) {
        env.sweep();
        System.out.println("Aspirbot has swept dirt");
    }
}
