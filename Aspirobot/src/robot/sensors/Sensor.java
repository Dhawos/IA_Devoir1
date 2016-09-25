package ca.uqac.IA.Devoir1.robot.sensors;

import ca.uqac.IA.Devoir1.environment.Environment;
import ca.uqac.IA.Devoir1.util.Position;

/**
 * Created by dhawo on 23/09/2016.
 */
public abstract class Sensor<T> {
    protected Environment env;

    public Sensor(Environment env) {
        this.env = env;
    }

    abstract public T getInfo(int x, int y);
    abstract public T getInfo(Position pos);
}
