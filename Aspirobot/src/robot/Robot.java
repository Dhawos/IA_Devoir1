package ca.uqac.IA.Devoir1.robot;

import ca.uqac.IA.Devoir1.environment.InterfaceEnvironment;
import ca.uqac.IA.Devoir1.environment.Tile;
import ca.uqac.IA.Devoir1.robot.actions.*;
import ca.uqac.IA.Devoir1.robot.sensors.DirtSensor;
import ca.uqac.IA.Devoir1.robot.sensors.JewelSensor;
import ca.uqac.IA.Devoir1.util.Position;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Random;

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
    private Random rng;
    private int goalsNumber = 3;

    public Robot() {
        this.isAlive = true;
        this.state = new State();
        this.tileQueue = new LinkedList<Tile>();
        this.goals = new State[this.goalsNumber];
        //Defining first goal - Incrementing nb of Jewels picked up
        this.goals[0] = new State();
        this.goals[0].setNbJeweledPickedUp(this.goals[0].getNbJeweledPickedUp()+1);
        this.goals[0].setNbDirtSwept(-1);
        this.goals[0].moveRobot(null);
        //Defining second goal - Incrementing nb of dirt swept
        this.goals[1] = new State();
        this.goals[1].setNbJeweledPickedUp(-1);
        this.goals[1].setNbDirtSwept(this.goals[1].getNbDirtSwept()+1);
        this.goals[1].moveRobot(null);
        //Defining third goal - Visiting least visited tile
        //We must first initialize the visited Tiles queues
        this.tileQueue = new LinkedList<>();
        for(int i = 0; i < this.getState().getMap().getNbLines() ; i++){
            for(int j = 0; j < this.getState().getMap().getNbTilesInLine(i); j++){
                tileQueue.add(this.getState().getMap().getTile(i,j));
            }
        }
        this.goals[2] = new State();
        this.goals[2].setNbJeweledPickedUp(-1);
        this.goals[2].setNbDirtSwept(-1);
        this.tileQueue.add(tileQueue.removeFirst());
        this.goals[2].moveRobot(tileQueue.removeFirst().getPosition());

        long seed = System.nanoTime();
        rng = new Random(seed);
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
        Action bestAction = possibleActions.peekFirst();
        int maxUtility = 0;
        int currentScore;
        for(State goal : this.goals){
            for(Action action : possibleActions){
                currentScore = action.getAfterState().rate(goal);
                if(currentScore > maxUtility){
                    bestAction = action;
                    maxUtility = currentScore;
                }
            }
        }
        return bestAction;
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
            if(selectedAction instanceof SweepAction){
                this.goals[1].setNbDirtSwept(this.goals[1].getNbDirtSwept()+1);
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
        PickUpAction pickUpAction = new PickUpAction(this.getState());
        if(pickUpAction.isLegal()){
            list.add(pickUpAction);
        }
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
        if(sweepAction.isLegal()){
            list.add(sweepAction);
        }
        //Collections.shuffle(list,this.rng);
        return list;
    }

    public void move(Position position){
        state.moveRobot(position);
        this.tileQueue.remove(state.getCurrentTile());
        this.tileQueue.add(state.getCurrentTile());
        if(state.getCurrentPosition().equals(this.goals[2].getCurrentPosition())){
            this.goals[2].moveRobot(tileQueue.removeFirst().getPosition());
        }
        state.setElectricityUsed(state.getElectricityUsed() + 1);
        setChanged();
        notifyObservers(position);
    }

    public InterfaceEnvironment getEnv() {
        return env;
    }
}
