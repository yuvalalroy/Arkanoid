// ID: 315789461

package collidables;

import basic.Line;
import basic.Point;
import basic.Rectangle;
import basic.Velocity;
import game.GameLevel;
import levels.LevelInformation;
import sprites.Ball;
import sprites.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * Paddle class:
 * The Paddle is the player in the game.
 * It is a rectangle that is controlled by the arrow keys, and moves according to the player key presses.
 * Paddle has width and depth, it is represented by a block and connected to the keyboard.
 *
 * @author Yuval Alroy
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Block paddle;
    private java.awt.Color color;
    private int width;
    private int deep;
    private int paddleSpeed;

    // height and width of the frame of the game.
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    // the number of equally-spaced regions of the paddle.
    private static final int REGIONS = 5;

    // the speed of the ball after it hits the paddle.
    private static final double SPEED = 5.5;


    /**
     * Constructor.
     *
     * @param keyboard represents a keyboard sensor.
     * @param level    represents the level in the game.
     */
    public Paddle(KeyboardSensor keyboard, LevelInformation level) {
        this.keyboard = keyboard;
        this.color = new java.awt.Color(215, 174, 29);
        this.width = level.paddleWidth();
        this.paddleSpeed = level.paddleSpeed();
        this.deep = 20;
        Point upLeft = new Point((double) (WIDTH / 2) - (double) this.width / 2, HEIGHT - 2 * this.deep);
        this.paddle = new Block(new Rectangle(upLeft, this.width, this.deep), this.color);
    }


    /**
     * moves the paddle to the left if the left key in the keyboard is pressed.
     * (by changing the upper left point of the block)
     */
    public void moveLeft() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            double newX = this.paddle.getCollisionRectangle().getUpperLeft().getX() - paddleSpeed;
            if (newX >= this.deep) {
                double y = this.paddle.getCollisionRectangle().getUpperLeft().getY();
                this.paddle = new Block(new Rectangle(new Point(newX, y), this.width, this.deep), this.color);
            }
        }
    }


    /**
     * moves the paddle to the right if the right key in the keyboard is pressed.
     * (by changing the upper left point of the block)
     */
    public void moveRight() {
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            double newX = this.paddle.getCollisionRectangle().getUpperLeft().getX() + paddleSpeed;
            if (newX <= WIDTH - this.deep - this.width) {
                double y = this.paddle.getCollisionRectangle().getUpperLeft().getY();
                this.paddle = new Block(new Rectangle(new Point(newX, y), this.width, this.deep), this.color);
            }
        }
    }


    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle.getCollisionRectangle();
    }


    /**
     * check if the collision point is in a region, meaning between x and x+diff.
     *
     * @param collisionPoint represents the point of the hit.
     * @param x              represents the starting x value of the region
     * @param diff           represents the distance between a region to it's neighbor
     * @return true if the point is in the region, false otherwise.
     */
    private boolean inRegion(Point collisionPoint, double x, double diff) {
        return (collisionPoint.getX() >= x) && (collisionPoint.getX() <= x + diff);
    }


    /**
     * check if the object hit the paddle in one of it's side ribs, and make sure that it doesn't in it.
     *
     * @param collisionPoint  represents the point of the hit.
     * @param currentVelocity represents the current velocity of the object that hit the paddle.
     * @return the new velocity of the object if it hit the paddle, otherwise its current velocity.
     */
    private Velocity checkPaddleSides(Point collisionPoint, Velocity currentVelocity) {
        List<Line> ribs = this.paddle.getCollisionRectangle().getRibs();
        if (collisionPoint.isOnLine(ribs.get(1)) || collisionPoint.isOnLine(ribs.get(3))) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        return currentVelocity;
    }


    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (checkPaddleSides(collisionPoint, currentVelocity).getDx() != currentVelocity.getDx()) {
            return checkPaddleSides(collisionPoint, currentVelocity);
        }
        double x = this.paddle.getCollisionRectangle().getUpperLeft().getX();

        // define the distance between a region to it's neighbor.
        double diff = this.width / (double) REGIONS;

        // define the starting angle, the difference of the angles between the regions, and the speed.
        int angle = 300;
        int diffAngle = 30;
        for (int i = 1; i <= REGIONS; i++) {

            // if the collision point is in the i region
            if (inRegion(collisionPoint, x, diff)) {
                break;
            }
            x += diff;
            angle += diffAngle;
        }
        return Velocity.fromAngleAndSpeed(angle, SPEED);
    }


    @Override
    public void drawOn(DrawSurface d) {
        this.paddle.drawOn(d);
    }


    @Override
    public void timePassed() {
        this.moveLeft();
        this.moveRight();
    }


    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
    }
}
