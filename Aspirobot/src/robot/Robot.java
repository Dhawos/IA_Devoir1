package ca.uqac.IA.Devoir1.robot;

import ca.uqac.IA.Devoir1.environment.InterfaceEnvironment;
import ca.uqac.IA.Devoir1.environment.Tile;
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
    private State[] goals;
    private JewelSensor jewelSensor;
    private DirtSensor dirtSensor;
    private LinkedList<Tile> tileQueue;
    private InterfaceEnvironment env;

    public Robot() {
        this.isAlive = true;
        this.state = new State();
        this.tileQueue = new LinkedList<Tile>();
        this.goals = new State[1];
        this.goals[0] = new State();
        this.goals[0].setNbJeweledPickedUp(this.goals[0].getNbJeweledPickedUp()+1);
        //this.goals[1] = new State();
    }

    public void setEnv(InterfaceEnvironment env){
        this.env = env;
    }

    public void addSensor(DirtSensor sensor){
        this.dirtSensor = sensor;
    }

    public void addSensor(JewelSensor sensor){
        this.jewelSensor = sensor;
    }

    private void observeEnvironmentAndUpdateState(){
        this.state.getCurrentTile().setHasDirt(this.dirtSensor.getInfo());
        this.state.getCurrentTile().setHasJewel(this.jewelSensor.getInfo());
    }

    public Action chooseAnAction(){
        LinkedList<Action> possibleActions = getLegalActions();
        for(Action action : possibleActions){
            for(State goal : this.goals)
            if(action.getAfterState().compare(goal)){
                return action;
            }
        }
        return possibleActions.peek();
    }

    @Override
    public void run() {
        while (isAlive()){
            observeEnvironmentAndUpdateState();
            Action selectedAction  = chooseAnAction();
            selectedAction.doAction(this.env,this);
            if(selectedAction instanceof PickUpAction){
                this.goals[0].setNbJeweledPickedUp(this.goals[0].getNbJeweledPickedUp()+1);
            }
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
        if(pickUpAction.isLegal()){
            list.add(pickUpAction);
        }
        return list;
    }

    public void move(Position position){
        state.moveRobot(position);
        state.setElectricityUsed(state.getElectricityUsed() + 1);
        setChanged();
        notifyObservers(position);
    }

}
