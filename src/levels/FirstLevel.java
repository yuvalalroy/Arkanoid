// ID: 315789461

package levels;

import backgrounds.FirstBackground;
import basic.Point;
import basic.Rectangle;
import basic.Velocity;
import collidables.Block;
import sprites.Sprite;

import java.util.ArrayList;
import java.util.List;


/**
 * FirstLevel class.
 * includes all the information needed for the first level.
 *
 * @author Yuval Alroy.
 */
public class FirstLevel implements LevelInformation {
    private static final double SPEED = 5.5;


    @Override
    public int numberOfBalls() {
        return 1;
    }


    @Override
    public int ballsRadius() {
        return 6;
    }


    @Override
    public java.awt.Color ballsColor() {
        return new java.awt.Color(255, 255, 255);
    }


    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> l = new ArrayList<>();
        l.add(Velocity.fromAngleAndSpeed(180, SPEED));
        return l;
    }


    @Override
    public int paddleSpeed() {
        return 5;
    }


    @Override
    public int paddleWidth() {
        return 120;
    }


    @Override
    public String levelName() {
        return "Direct Hit";
    }


    @Override
    public Sprite getBackground() {
        return new FirstBackground();
    }


    @Override
    public List<Block> blocks() {
        List<Block> innerBlocks = new ArrayList<>();
        innerBlocks.add(new Block(new Rectangle(new Point(378, 150), 40, 40),
                new java.awt.Color(191, 8, 23)));
        return innerBlocks;
    }


    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
