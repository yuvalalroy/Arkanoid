// ID: 315789461

package game;

import basic.Counter;
import collidables.Block;
import observer.HitListener;
import sprites.Ball;

/**
 * class BlockRemover.
 * in charge of removing blocks from the game, as well as keeping count of the number of blocks that remain.
 *
 * @author Yuval Alroy.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;


    /**
     * Constructor.
     *
     * @param gameLevel          represents the game.
     * @param removedBlocks represents the remaining blocks in the game.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }


    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(hitter);
        this.remainingBlocks.decrease(1);
    }
}