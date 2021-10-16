// ID: 315789461

package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;


/**
 * PauseScreen class.
 * gives the player an option to pause the game when pressing the p key.
 * display a screen with the message paused -- press space to continue until a key is pressed.
 *
 * @author Yuval Alroy.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;


    /**
     * Constructor.
     *
     * @param k represents a key-board-sensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(134, 129, 129));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.black);
        d.drawCircle(380, 180, 150);
        d.setColor(new Color(69, 114, 198));
        d.fillCircle(380, 180, 150);
        d.setColor(Color.black);
        d.drawCircle(380, 180, 130);
        d.setColor(new Color(113, 149, 239));
        d.fillCircle(380, 180, 130);
        d.setColor(Color.black);
        d.fillRectangle(340, 120, 20, 120);
        d.fillRectangle(400, 120, 20, 120);
        d.drawText(140, 380, "paused -- press space to continue", 32);
    }


    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}