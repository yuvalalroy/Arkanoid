// ID: 315789461

package game;

import basic.Counter;
import collidables.Block;
import observer.HitListener;
import sprites.Ball;

/**
 * class BallRemover.
 * in charge of removing balls, and updating an available-balls counter.
 *
 * @author Yuval Alroy.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;


    /**
     * Constructor.
     *
     * @param gameLevel           represents the game.
     * @param remainingBalls represents the remaining balls in the game.
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }


    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(hitter);
        this.remainingBalls.decrease(1);
    }
}
