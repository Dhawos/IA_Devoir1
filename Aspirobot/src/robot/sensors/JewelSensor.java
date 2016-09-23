package ca.uqac.IA.Devoir1.robot.sensors;

import ca.uqac.IA.Devoir1.environment.Environment;

/**
 * Created by dhawo on 23/09/2016.
 */
public class JewelSensor extends Sensor<Boolean> {

    public JewelSensor(Environment env) {
        super(env);
    }

    @Override
    public Boolean getInfo(int x, int y) {
        return this.env.getMap().getTile(x,y).isHasJewel();
    }
}