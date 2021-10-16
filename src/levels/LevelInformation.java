// ID: 315789461

package levels;

import basic.Velocity;
import collidables.Block;
import sprites.Sprite;

import java.util.List;


/**
 * LevelInformation interface.
 * specifies the information required to fully describe a level.
 *
 * @author Yuval Alroy.
 */
public interface LevelInformation {


    /**
     * @return the number of the balls in the level.
     */
    int numberOfBalls();


    /**
     * @return the radius of the balls in the level.
     */
    int ballsRadius();


    /**
     * @return the color of the balls in the level.
     */
    java.awt.Color ballsColor();


    /**
     * @return The initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();


    /**
     * @return the speed of the paddle.
     */
    int paddleSpeed();


    /**
     * @return the width of the paddle.
     */
    int paddleWidth();


    /**
     * the level name is displayed at the top of the screen.
     *
     * @return the level name.
     */
    String levelName();


    /**
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();


    /**
     * @return the Blocks that make up this level, each block contains its size, color and location.
     */
    List<Block> blocks();


    /**
     * @return the number of blocks that should be removed before the level is considered to be "cleared".
     */
    int numberOfBlocksToRemove();
}