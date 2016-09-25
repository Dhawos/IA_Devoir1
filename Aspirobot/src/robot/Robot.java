package ca.uqac.IA.Devoir1.robot;

import ca.uqac.IA.Devoir1.robot.actions.*;
import ca.uqac.IA.Devoir1.robot.sensors.DirtSensor;
import ca.uqac.IA.Devoir1.robot.sensors.JewelSensor;
import ca.uqac.IA.Devoir1.util.Position;

import java.util.LinkedList;
import java.util.Observable;

/**
 * Created by dhawo on 23/09/2016.
 */
public class Robot extends Observable implements Runnable {
    private boolean isAlive;
    private State state;
    private JewelSensor jewelSensor;
    private DirtSensor dirtSensor;

    public Robot(JewelSensor jewelSensor, DirtSensor dirtSensor) {
        this.jewelSensor = jewelSensor;
        this.dirtSensor = dirtSensor;
        this.isAlive = true;
        this.state = new State();
    }

    private void observeEnvironmentAndUpdateState(){
        this.state.getCurrentTile().setHasDirt(this.dirtSensor.getInfo(this.state.getCurrentPosition()));
        this.state.getCurrentTile().setHasJewel(this.jewelSensor.getInfo(this.state.getCurrentPosition()));
    }

    public Action chooseAnAction(){
        LinkedList<Action> possibleActions = getLegalActions();
        /*for(Action action : possibleActions){
            //Evaluate if action is good and if it is, select it

        }*/
        return possibleActions.peek();
    }

    @Override
    public void run() {
        while (isAlive()){
            observeEnvironmentAndUpdateState();
            Action selectedAction  = chooseAnAction();
            //selectedAction.doAction(); Check how we can pass the environment to the bot without he having full knowledge of the environment
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

    public void move(Position position){
        state.moveRobot(position);
        state.setElectricityUsed(state.getElectricityUsed() + 1);
        notifyObservers(position);
    }

}
