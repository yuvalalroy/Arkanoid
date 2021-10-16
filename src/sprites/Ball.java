// ID: 315789461

package sprites;

import basic.Line;
import basic.Point;
import basic.Velocity;
import collidables.Block;
import biuoop.DrawSurface;
import collidables.CollisionInfo;
import game.GameLevel;
import game.GameEnvironment;
import observer.HitListener;

import java.util.List;

/**
 * Ball class:
 * Balls have size (radius), color, location (a Point - the center), and velocity.
 * In addition, The ball knows the game environment,
 * and uses it to check for collisions and direct its movement.
 * Balls also know how to draw themselves on a DrawSurface.
 *
 * @author Yuval Alroy
 */
public class Ball implements Sprite, HitListener {
    private Point center;
    private int size;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment environment;


    /**
     * first constructor (set Ball fields)
     * set velocity field with the default values of dx = 1, dy = 1.
     *
     * @param center represents the canter of the ball (it's location).
     * @param r      represents the radius of the ball (it's size).
     * @param color  represents the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.size = r;
        this.color = color;
        this.velocity = new Velocity(1, 1);
        this.environment = new GameEnvironment();
    }


    /**
     * second constructor.
     *
     * @param x     represents the x value of the center point.
     * @param y     represents the y value of the center point.
     * @param r     represents the radius of the ball (it's size).
     * @param color represents the color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this(new Point(x, y), r, color);
    }


    /**
     * set the game environment of this ball.
     *
     * @param newEnvironment represents the game environment.
     */
    public void setEnvironment(GameEnvironment newEnvironment) {
        this.environment = newEnvironment;
    }


    /**
     * @return the x value of the location (the center) of the ball.
     */
    public int getX() {
        return (int) this.center.getX();
    }


    /**
     * @return the y value of the location (the center) of the ball.
     */
    public int getY() {
        return (int) this.center.getY();
    }


    /**
     * @return the size (radius) of the ball.
     */
    public int getSize() {
        return this.size;
    }


    /**
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }


    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(new java.awt.Color(0, 0, 0));
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.size);
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.size);
    }


    @Override
    public void timePassed() {
        this.moveOneStep();
    }


    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }


    /**
     * change this ball's velocity field to the given velocity (v).
     *
     * @param v represents a velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }


    /**
     * change this ball's velocity field by the given dx and dy.
     *
     * @param dx represents the dx of the velocity (the movement in the x-axis)
     * @param dy represents the dy of the velocity (the movement in the y-axis)
     */
    public void setVelocity(double dx, double dy) {
        this.velocity.setDx(dx);
        this.velocity.setDy(dy);
    }


    /**
     * @return this ball's velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }


    /**
     * calculates the trajectory of the ball.
     *
     * @return the trajectory of the ball according to it's velocity.
     */
    public Line getTrajectory() {
        return new Line(this.center, this.velocity.applyToPoint(this.center));
    }


    /**
     * this method keeps the center of the ball in the game frame.
     */
    public void keepBallInFrame() {
        if (this.center.getX() < this.environment.getDEEP()) {
            this.center = new Point(this.environment.getDEEP() + this.size, this.center.getY());
        }
        if (this.center.getX() > this.environment.getWIDTH() - this.environment.getDEEP()) {
            this.center = new Point(this.environment.getWIDTH() - this.environment.getDEEP() - this.size,
                    this.center.getY());
        }
        if (this.center.getY() < this.environment.getDEEP()) {
            this.center = new Point(this.center.getX(), this.environment.getDEEP() + this.size);
        }
        if (this.center.getY() > this.environment.getWIDTH() - this.environment.getDEEP()) {
            this.center = new Point(this.center.getX(),
                    this.environment.getWIDTH() - this.environment.getDEEP() - this.size);
        }
    }


    /**
     * this method moves the center of the ball according to the velocity.
     * when the ball hits a block to the left or to the right it changes it's horizontal direction,
     * and when it hits a block on the top or the bottom, it changes it's vertical direction.
     * in addition, check if the ball is inside the paddle.
     */
    public void moveOneStep() {
        List<CollisionInfo> collisions = this.environment.getClosestCollisions(this.getTrajectory());
        if (collisions.size() > 1) {
            this.setVelocity(-this.velocity.getDx(), -this.velocity.getDy());
        } else {
            CollisionInfo collision = this.environment.getClosestCollision(this.getTrajectory());
            if (collision != null) {
                Velocity newVelocity = collision.collisionObject().hit(this, collision.collisionPoint(),
                        this.getVelocity());
                this.setVelocity(newVelocity);
            }
        }
        CollisionInfo paddleCollision = this.environment.paddleCollision(this.center);

        // if there was no collision with any block, ensure that the ball doesn't go inside the paddle.
        if (paddleCollision != null) {
            Point collisionPoint = paddleCollision.collisionPoint();
            this.center = new Point(collisionPoint.getX() - this.getVelocity().getDx(),
                    collisionPoint.getY() - this.size);
            this.setVelocity(paddleCollision.collisionObject().hit(this, collisionPoint, this.getVelocity()));
        }
        this.center = this.getVelocity().applyToPoint(this.center);
        keepBallInFrame();
    }


    /**
     * @return the center of the ball.
     */
    public Point getCenter() {
        return this.center;
    }


    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(hitter);
    }


    /**
     * Removes the ball from the game.
     *
     * @param gameLevel represents a game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}
