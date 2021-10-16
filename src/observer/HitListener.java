// ID: 315789461

package observer;

import collidables.Block;
import sprites.Ball;

/**
 * interface HitListener.
 * implemented by objects that want to be notified of hit events.
 *
 * @author Yuval Alroy.
 */
public interface HitListener {


    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit represents the object that is being hit.
     * @param hitter   represents the ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
