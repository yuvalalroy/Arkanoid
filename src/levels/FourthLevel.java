// ID: 315789461

package levels;

import backgrounds.FourthBackground;
import basic.Point;
import basic.Velocity;
import collidables.Block;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * FourthLevel class.
 * includes all the information needed for the fourth level.
 *
 * @author Yuval Alroy.
 */
public class FourthLevel implements LevelInformation {
    private static final double SPEED = 5.5;
    private static final int BLOCK_WIDTH = 48;
    private static final int BLOCK_HEIGHT = 27;
    private static final int NUM_OF_LINES = 7;


    @Override
    public int numberOfBalls() {
        return 3;
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
        int angel = 300;
        for (int i = 0; i < numberOfBalls(); i++) {
            l.add(Velocity.fromAngleAndSpeed(angel, SPEED));
            angel += 60;
        }
        return l;
    }


    @Override
    public int paddleSpeed() {
        return 8;
    }


    @Override
    public int paddleWidth() {
        return 100;
    }


    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new FourthBackground();
    }


    /**
     * create an array of five colors.
     *
     * @return array of colors.
     */
    private java.awt.Color[] getColors() {
        return new java.awt.Color[]{new java.awt.Color(147, 28, 220),
                new java.awt.Color(59, 219, 164),
                new java.awt.Color(241, 27, 69),
                new java.awt.Color(229, 226, 9),
                new java.awt.Color(39, 231, 19),
                new java.awt.Color(246, 246, 246),
                new java.awt.Color(226, 91, 16)};
    }


    @Override
    public List<Block> blocks() {
        List<Block> innerBlocks = new ArrayList<>();

        // create an array of colors (for the blocks in each line)
        java.awt.Color[] colors = this.getColors();
        double x = 780 - BLOCK_WIDTH;
        double y = BLOCK_WIDTH + BLOCK_HEIGHT;

        // this is the number of blocks in the first line. decreases by one in each line.
        int numOfBlocks = 16;
        for (int i = 0; i < NUM_OF_LINES; i++) {

            // create a line of blocks
            for (int j = 0; j < numOfBlocks; j++) {
                innerBlocks.add(new Block(new basic.Rectangle(new Point(x, y), BLOCK_WIDTH, BLOCK_HEIGHT),
                        colors[i]));
                x -= BLOCK_WIDTH;
            }
            x = 780 - BLOCK_WIDTH;
            y += BLOCK_HEIGHT;
        }
        return innerBlocks;
    }


    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size() / 4;
    }
}
