package ca.uqac.IA.Devoir1.robot.sensors;

import ca.uqac.IA.Devoir1.environment.Environment;
import ca.uqac.IA.Devoir1.robot.Robot;

/**
 * Created by dhawo on 23/09/2016.
 */
public class JewelSensor extends Sensor<Boolean> {

    public JewelSensor(Environment env, Robot robot) {
        super(env,robot);
    }

    @Override
    public Boolean getInfo() {
        return this.env.getMap().getTile(robot.getState().getCurrentPosition()).isHasJewel();
    }
}
