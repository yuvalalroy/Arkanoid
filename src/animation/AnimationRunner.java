// ID: 315789461

package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;


/**
 * AnimationRunner class.
 * runs the animation.
 *
 * @author Yuval Alroy.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;


    /**
     * Constructor.
     */
    public AnimationRunner() {

        /*
         * Create a window with the title "Game"
         * which is 800 pixels wide and 600 pixels high.
         */
        this.gui = new GUI("Game", 800, 600);
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }


    /**
     * @return the gui of the animation.
     */
    public GUI getGui() {
        return gui;
    }


    /**
     * @param animation represents an animation in the game.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}