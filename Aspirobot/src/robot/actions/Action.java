package ca.uqac.IA.Devoir1.robot.actions;

import ca.uqac.IA.Devoir1.environment.InterfaceEnvironment;
import ca.uqac.IA.Devoir1.robot.Robot;
import ca.uqac.IA.Devoir1.robot.State;

/**
 * Created by dhawo on 23/09/2016.
 */
public abstract class Action {
    protected State afterState;
    private boolean legal;
    public abstract void doAction(InterfaceEnvironment env, Robot robot);

    public State getAfterState() {
        return afterState;
    }

    public boolean isLegal() {
        return legal;
    }

    public void setLegal(boolean legal){
        this.legal = false;
    }
}


