// ID: 315789461

package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;


/**
 * KeyPressStoppableAnimation class.
 * decorator-class that wraps an existing animation and adds a "waiting-for-key" behavior to it.
 *
 * @author Yuval Alroy.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;


    /**
     * Constructor.
     *
     * @param sensor    represents the keyboard sensor.
     * @param key       represents the key on the keyboard.
     * @param animation represents the animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.sensor.isPressed(key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            } else {
                this.isAlreadyPressed = false;
            }
        }
    }


    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
