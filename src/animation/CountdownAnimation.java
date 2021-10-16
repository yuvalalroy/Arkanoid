// ID: 315789461

package animation;

import basic.Counter;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprites.SpriteCollection;


/**
 * CountdownAnimation class.
 * feature of countdown from 3 to 1, which will show up at the beginning of each turn.
 *
 * @author Yuval Alroy.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private Counter countFrom;
    private SpriteCollection gameScreen;
    private double count;


    /**
     * Constructor.
     *
     * @param numOfSeconds represents the number of seconds the countdown screen ids displayed.
     * @param countFrom    represents the number we are counting from.
     * @param gameScreen   represents the screen of the game.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = new Counter();
        this.countFrom.increase(countFrom);
        this.count = countFrom;
        this.gameScreen = gameScreen;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(new java.awt.Color(189, 137, 125));
        d.drawText(370, 320, String.valueOf(this.countFrom.getValue()), 120);
        if (this.countFrom.getValue() != this.count) {
            Sleeper sleeper = new Sleeper();
            sleeper.sleepFor((long) numOfSeconds / (long) this.count);
        }
        this.countFrom.decrease(1);
    }


    @Override
    public boolean shouldStop() {
        return this.countFrom.getValue() <= -1;
    }
}
