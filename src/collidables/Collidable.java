// ID: 315789461

package collidables;

import basic.Point;
import basic.Rectangle;
import basic.Velocity;
import game.GameLevel;
import sprites.Ball;

/**
 * Collidable interface:
 * this interface will be used by things that can be collided with (the Blocks and the Paddle).
 * In our world, everything that we can collide into is rectangular.
 * Thus, the location and size of the collidables will be specified using a Rectangle.
 * we also have a hit method in order to know what happens when the collision occurs.
 *
 * @author Yuval Alroy
 */
public interface Collidable {


    /**
     * @return the "collision shape" of the object, meaning the block rectangle.
     */
    Rectangle getCollisionRectangle();


    /**
     * @param collisionPoint  represents the point of the object that we collided with.
     * @param currentVelocity represents the velocity of the object that we collided with.
     * @param hitter          represents the ball that hits the object.
     * @return the new velocity expected after the hit, based on the force the object inflicted on us.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);


    /**
     * adding the collidable to the game.
     *
     * @param gameLevel represents a game.
     */
    void addToGame(GameLevel gameLevel);
}
