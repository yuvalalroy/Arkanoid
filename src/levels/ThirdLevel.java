// ID: 315789461

package levels;

import backgrounds.ThirdBackground;
import basic.Point;
import basic.Rectangle;
import basic.Velocity;
import collidables.Block;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * ThirdLevel class.
 * includes all the information needed for the third level.
 *
 * @author Yuval Alroy.
 */
public class ThirdLevel implements LevelInformation {
    private static final int BLOCK_WIDTH = 55;
    private static final int BLOCK_HEIGHT = 25;
    private static final int NUM_OF_LINES = 5;
    private static final double SPEED = 5.5;


    @Override
    public int numberOfBalls() {
        return 2;
    }


    @Override
    public int ballsRadius() {
        return 5;
    }


    @Override
    public Color ballsColor() {
        return Color.white;
    }


    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> l = new ArrayList<>();
        l.add(Velocity.fromAngleAndSpeed(300, SPEED));
        l.add(Velocity.fromAngleAndSpeed(420, SPEED));
        return l;
    }


    @Override
    public int paddleSpeed() {
        return 12;
    }


    @Override
    public int paddleWidth() {
        return 120;
    }


    @Override
    public String levelName() {
        return "Green 3";
    }


    @Override
    public Sprite getBackground() {
        return new ThirdBackground();
    }


    /**
     * create an array of five colors.
     *
     * @return array of colors.
     */
    private java.awt.Color[] getColors() {
        return new java.awt.Color[]{new java.awt.Color(172, 51, 232),
                new java.awt.Color(25, 158, 170),
                new java.awt.Color(248, 36, 86),
                new java.awt.Color(184, 183, 10),
                new java.awt.Color(230, 234, 239)};
    }


    @Override
    public List<Block> blocks() {
        List<Block> innerBlocks = new ArrayList<>();

        // create an array of colors (for the blocks in each line)
        java.awt.Color[] colors = this.getColors();
        int x = 780 - BLOCK_WIDTH;
        int y = BLOCK_WIDTH + 2 * BLOCK_HEIGHT;

        // this is the number of blocks in the first line. decreases by one in each line.
        int numOfBlocks = 10;
        for (int i = 0; i < NUM_OF_LINES; i++) {

            // create a line of blocks
            for (int j = 0; j < numOfBlocks; j++) {
                innerBlocks.add(new Block(new Rectangle(new Point(x, y), BLOCK_WIDTH, BLOCK_HEIGHT), colors[i]));
                x -= BLOCK_WIDTH;
            }
            x = 780 - BLOCK_WIDTH;
            y += BLOCK_HEIGHT;
            numOfBlocks--;
        }
        return innerBlocks;
    }


    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size() / 2;
    }
}
