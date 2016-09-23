package ca.uqac.IA.Devoir1.robot;

import ca.uqac.IA.Devoir1.robot.actions.*;

import java.util.LinkedList;

/**
 * Created by dhawo on 23/09/2016.
 */
public class Robot implements Runnable {
    private boolean isAlive;
    private State state;

    @Override
    public void run() {
        while (isAlive()){
            //ObserveEnvironmentWithAllMySensors();
            //UpdateMyState();
            //ChooseAnAction();
            //justDoIt();
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public State getState() {
        return state;
    }

    public LinkedList<Action> getLegalActions(){
        LinkedList<Action> list = new LinkedList<>();
        MoveUpAction moveUp = new MoveUpAction(this.getState());
        if(moveUp.isLegal()){
            list.add(moveUp);
        }
        MoveDownAction moveDown = new MoveDownAction(this.getState());
        if(moveDown.isLegal()){
            list.add(moveDown);
        }
        MoveLeftAction moveLeft = new MoveLeftAction(this.getState());
        if(moveLeft.isLegal()){
            list.add(moveLeft);
        }
        MoveRightAction moveRight = new MoveRightAction(this.getState());
        if(moveRight.isLegal()){
            list.add(moveRight);
        }
        SweepAction sweepAction = new SweepAction(this.getState());
        list.add(sweepAction);
        PickUpAction pickUpAction = new PickUpAction(this.getState());
        list.add(pickUpAction);
        return list;
    }
}
