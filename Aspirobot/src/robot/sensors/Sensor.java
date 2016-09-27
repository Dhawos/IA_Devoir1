package ca.uqac.IA.Devoir1.robot.sensors;

import ca.uqac.IA.Devoir1.environment.Environment;
import ca.uqac.IA.Devoir1.robot.Robot;

/**
 * Created by dhawo on 23/09/2016.
 */
public abstract class Sensor<T> {
    protected Environment env;
    protected Robot robot;

    public Sensor(Environment env,Robot robot) {
        this.env = env;
        this.robot = robot;
    }

    abstract public T getInfo();
}
