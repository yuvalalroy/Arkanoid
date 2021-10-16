// ID: 315789461

package animation;

import biuoop.DrawSurface;


/**
 * Animation interface.
 *
 * @author Yuval Alroy.
 */
public interface Animation {


    /**
     * this method is in charge of the logic of the animation.
     *
     * @param d represents the draw surface of the animation.
     */
    void doOneFrame(DrawSurface d);


    /**
     * this method is in charge of stopping condition of the animation.
     *
     * @return true if the animation should stop, false otherwise.
     */
    boolean shouldStop();
}
