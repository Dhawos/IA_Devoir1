package ca.uqac.IA.Devoir1.robot.actions;

import ca.uqac.IA.Devoir1.environment.InterfaceEnvironment;
import ca.uqac.IA.Devoir1.robot.Robot;
import ca.uqac.IA.Devoir1.robot.State;

/**
 * Created by dhawo on 23/09/2016.
 */
public class SweepAction extends Action {
    public SweepAction(State state) {
        if(state.getCurrentTile().isHasJewel() || state.getCurrentTile().isHasDirt()){
            this.afterState = new State(state);
            this.afterState.getCurrentTile().setHasDirt(false);
            this.afterState.getCurrentTile().setHasJewel(false);
            this.afterState.setNbDirtSwept(state.getNbDirtSwept()+1);
            this.setLegal(true);
        }else{
            this.setLegal(false);
        }
}

    @Override
    public void doAction(InterfaceEnvironment env, Robot robot) {
        try{
            Thread.sleep(this.RequiredTime);
        }catch (InterruptedException ex){

        }
        env.sweep();
        System.out.println(String.format("Aspirobot has swept dirt on tile (%1$d,%2$d)", robot.getState().getCurrentPosition().getX(), robot.getState().getCurrentPosition().getY()));
    }
}
