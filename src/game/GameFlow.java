// ID: 315789461

package game;

import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import basic.Counter;
import biuoop.KeyboardSensor;
import animation.EndScreen;
import levels.LevelInformation;
import observer.ScoreTrackingListener;

import java.util.List;


/**
 * GameFlow class.
 * in charge of running the game - moving from one level to the next.
 *
 * @author Yuval Alroy.
 */
public class GameFlow {
    private ScoreTrackingListener score;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;


    /**
     * Constructor.
     *
     * @param ar represents the animation runner.
     * @param ks represents the keyboard sensor.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.score = new ScoreTrackingListener(new Counter());
    }


    /**
     * runs the levels in the game.
     *
     * @param levels represents a list of the levels in the game.
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean win = true;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.keyboardSensor, this.score);
            level.initialize();
            while ((level.getRemainingBalls().getValue() > 0)
                    && (level.getRemainingBlocks().getValue() > level.getNumOfBlocks()
                    - levelInfo.numberOfBlocksToRemove())) {
                level.run();
            }
            if (level.getRemainingBalls().getValue() == 0) {
                win = false;
                break;
            }
        }

        // Show the end screen
        EndScreen es = new EndScreen(this.score.getScore(), win);
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space", es));
        this.animationRunner.getGui().close();
    }
}
