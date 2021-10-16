// ID: 315789461

package game;

import basic.Line;
import basic.Point;
import basic.Rectangle;
import collidables.Block;
import collidables.Collidable;
import collidables.CollisionInfo;
import collidables.Paddle;

import java.util.ArrayList;
import java.util.List;

/**
 * GameEnvironment class:
 * During the game, there are many objects a Ball can collide with.
 * This class is a collection of such things.
 * In this class we also store the height and width of the frame (both of the game and of the frame blocks).
 *
 * @author Yuval Alroy
 */
public class GameEnvironment {
    private List<Collidable> collidables;
    private Paddle paddle;

    private static final java.awt.Color FRAME_COLOR = new java.awt.Color(110, 110, 110);
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    // represents the height / width of the frame blocks.
    private static final int DEEP = 20;


    /**
     * Constructor.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }


    /**
     * @return the height / width of the frame blocks.
     */
    public int getDEEP() {
        return DEEP;
    }


    /**
     * @return the height of the frame of the game.
     */
    public int getHEIGHT() {
        return HEIGHT;
    }


    /**
     * @return the width of the frame of the game.
     */
    public int getWIDTH() {
        return WIDTH;
    }


    /**
     * finds the collision of the ball with the paddle.
     *
     * @param ballCenter represents the center of the ball in the game.
     * @return the closest collision point of the ball (represented by it's center) with the paddle.
     */
    public CollisionInfo paddleCollision(Point ballCenter) {
        if (ballCenter.pointInRectangle(this.paddle.getCollisionRectangle())) {
            if (ballCenter.distance(this.paddle.getCollisionRectangle().getUpperLeft())
                    < ballCenter.distance(this.paddle.getCollisionRectangle().getUpperRight())) {
                return new CollisionInfo(this.paddle.getCollisionRectangle().getUpperLeft(),
                        this.paddle);
            }
            return new CollisionInfo(this.paddle.getCollisionRectangle().getUpperRight(),
                    this.paddle);
        }
        return null;
    }


    /**
     * add the paddle to the game environment.
     *
     * @param pad represents a paddle.
     */
    public void addPaddleToGame(Paddle pad) {
        this.paddle = pad;
    }


    /**
     * sets the blocks which represents the borders of the draw surface.
     *
     * @return a list of the border blocks.
     */
    public List<Block> setupFrameBlocks() {

        // create the list of the border blocks
        List<Block> frameBlocks = new ArrayList<>();
        frameBlocks.add(new Block(new Rectangle(new Point(0, DEEP), WIDTH, DEEP), FRAME_COLOR));
        frameBlocks.add(new Block(new Rectangle(new Point(0, DEEP * 2), DEEP, HEIGHT - 2 * DEEP),
                FRAME_COLOR));
        frameBlocks.add(new Block(new Rectangle(new Point(WIDTH - DEEP, DEEP), DEEP,
                HEIGHT - DEEP), FRAME_COLOR));

        for (Block block : frameBlocks) {
            addCollidable(block);
        }
        return frameBlocks;
    }


    /**
     * Creates a death-region block at the bottom of the screen, and adds it as a collidable.
     *
     * @return the death-region block.
     */
    public Block createDeathBlock() {
        Block death = new Block(new Rectangle(new Point(0, HEIGHT), WIDTH, 1), FRAME_COLOR);
        addCollidable(death);
        return death;
    }


    /**
     * creates the upper block for the score (only draws it).
     *
     * @param g represents the game.
     */
    public void createScoreBlock(GameLevel g) {
        g.addSprite(new Block(new Rectangle(new Point(0, 0), WIDTH, DEEP),
                new java.awt.Color(189, 137, 125)));
    }


    /**
     * @return this list of collidables.
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }


    /**
     * adds the given collidable to the environment.
     *
     * @param c represents the collidable.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }


    /**
     * Removes a collidable from the collidables list.
     *
     * @param c represents the collidable.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }


    /**
     * assuming an object moving from line.start() to line.end().
     * if this object will not collide with any of the collidables in this collection, return null.
     * else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory represents the object's trajectory (the line that the object is moving on).
     * @return the closest collision that is going to occur, null otherwise.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo closest = null;
        double minDistance = Integer.MAX_VALUE;

        // Make a copy of the collidables before iterating over them.
        List<Collidable> cols = new ArrayList<>(this.collidables);
        for (Collidable collidable : cols) {
            Point p = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (p != null) {
                if (p.distance(trajectory.start()) < minDistance) {
                    minDistance = p.distance(trajectory.start());
                    closest = new CollisionInfo(p, collidable);
                }
            }
        }
        return closest;
    }


    /**
     * using getClosestCollision method.
     * adding to a list all of the collisions that occur on the same point.
     *
     * @param trajectory represents the object's trajectory (the line that the object is moving on).
     * @return a list of the closest collisions that is going to occur, null list otherwise.
     */
    public List<CollisionInfo> getClosestCollisions(Line trajectory) {
        List<CollisionInfo> collisions = new ArrayList<>();
        CollisionInfo closest = this.getClosestCollision(trajectory);
        collisions.add(closest);
        for (Collidable collidable : this.collidables) {
            Point p = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (p != null) {

                // check if this the first collision we found (same point and same object).
                if (p.distance(trajectory.start()) == closest.collisionPoint().distance(trajectory.start())) {
                    if (collidable.getCollisionRectangle().getUpperLeft().equals(
                            closest.collisionObject().getCollisionRectangle().getUpperLeft())) {
                        continue;
                    }

                    // if not - add it to the list.
                    collisions.add(new CollisionInfo(p, collidable));
                }
            }
        }
        return collisions;
    }
}

