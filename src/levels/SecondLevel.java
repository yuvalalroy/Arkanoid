// ID: 315789461

package levels;

import backgrounds.SecondBackground;
import basic.Point;
import basic.Rectangle;
import basic.Velocity;
import collidables.Block;
import sprites.Sprite;

import java.util.ArrayList;
import java.util.List;


/**
 * SecondLevel class.
 * includes all the information needed for the second level.
 *
 * @author Yuval Alroy.
 */
public class SecondLevel implements LevelInformation {
    private static final double SPEED = 5.5;
    private static final int NUM_OF_BLOCKS = 14;


    @Override
    public int numberOfBalls() {
        return 6;
    }


    @Override
    public int ballsRadius() {
        return 5;
    }


    @Override
    public java.awt.Color ballsColor() {
        return new java.awt.Color(255, 255, 255);
    }


    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> l = new ArrayList<>();
        int angel = 285;
        for (int i = 0; i < numberOfBalls(); i++) {
            l.add(Velocity.fromAngleAndSpeed(angel, SPEED));
            angel += 30;
        }
        return l;
    }


    @Override
    public int paddleSpeed() {
        return 4;
    }


    @Override
    public int paddleWidth() {
        return 550;
    }


    @Override
    public String levelName() {
        return "Wide Easy";
    }


    @Override
    public Sprite getBackground() {
        return new SecondBackground();
    }


    /**
     * create an array of five colors.
     *
     * @return array of colors.
     */
    private java.awt.Color[] getColors() {
        return new java.awt.Color[]{new java.awt.Color(190, 114, 234),
                new java.awt.Color(60, 190, 200),
                new java.awt.Color(243, 87, 123),
                new java.awt.Color(232, 230, 50),
                new java.awt.Color(68, 231, 50),
                new java.awt.Color(246, 34, 163),
                new java.awt.Color(239, 128, 68)};
    }


    @Override
    public List<Block> blocks() {
        List<Block> innerBlocks = new ArrayList<>();
        int blockWidth = 55;
        int blockHeight = 25;

        // create an array of colors.
        java.awt.Color[] colors = this.getColors();
        double x = 785 - blockWidth;
        int colorIndex = 0;

        // create a line of blocks
        for (int i = 0; i < NUM_OF_BLOCKS; i++) {
            innerBlocks.add(new Block(new Rectangle(new Point(x, 300), blockWidth, blockHeight),
                    colors[colorIndex]));
            x -= blockWidth;
            if (i % 2 != 0) {
                colorIndex++;
            }
        }
        return innerBlocks;
    }


    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
    }
}
