// ID: 315789461

package collidables;

import basic.Point;

/**
 * CollisionInfo class:
 * This class is to store the collision info - collision point and collision object.
 *
 * @author Yuval Alroy
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;


    /**
     * Constructor.
     *
     * @param collisionPoint  represents the point at which the collision occurs.
     * @param collisionObject represents the collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionObject = collisionObject;
        this.collisionPoint = collisionPoint;
    }


    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }


    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
