package ca.uqac.IA.Devoir1.robot.actions;

import ca.uqac.IA.Devoir1.environment.Environment;
import ca.uqac.IA.Devoir1.robot.Robot;

/**
 * Created by dhawo on 23/09/2016.
 */
public abstract class Action {
    private boolean afterState;
    public abstract void doAction(Environment env, Robot robot);
    //public static Action[] getLegalActions(Environment env,Robot robot)
}
