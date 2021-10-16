// ID: 315789461

package observer;

import basic.Counter;
import collidables.Block;
import sprites.Ball;

/**
 * class ScoreTrackingListener.
 * updates the counter of the game when blocks are being hit and removed.
 *
 * @author Yuval Alroy.
 */
public class ScoreTrackingListener implements HitListener {
    private static final int BLOCK_SCORE = 5;
    private Counter currentScore;


    /**
     * Constructor.
     *
     * @param scoreCounter represents the score counter (the current score).
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }


    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(BLOCK_SCORE);
    }


    /**
     * Raises the score of the player.
     *
     * @param points represents the number of points we want to add to the score.
     */
    public void raiseCurrentScore(int points) {
        this.currentScore.increase(points);
    }


    /**
     * @return the current score of the player.
     */
    public Counter getScore() {
        return this.currentScore;
    }
}
