// ID: 315789461

package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.PauseScreen;
import animation.KeyPressStoppableAnimation;
import animation.CountdownAnimation;
import basic.Counter;
import basic.Point;
import basic.Velocity;
import biuoop.KeyboardSensor;
import collidables.Block;
import collidables.Collidable;
import collidables.Paddle;
import levels.LevelIndicator;
import levels.LevelInformation;
import observer.ScoreTrackingListener;
import sprites.Ball;
import sprites.Sprite;
import sprites.SpriteCollection;

import biuoop.DrawSurface;

import java.util.List;

/**
 * GameLevel class:
 * This class holds the sprites and the collidables, and is in charge of the animation in the game.
 * In this class, we initialize the game and run it.
 *
 * @author Yuval Alroy
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation level;
    private int numOfBlocks;

    private static final int STRIKE_SCORE = 100;


    /**
     * Constructor.
     *
     * @param level represents the level in the game.
     * @param runner represents the animation runner of the game.
     * @param keyboard represents the keyboard sensor of the game.
     * @param score represents the score tracker in the game.
     */
    public GameLevel(LevelInformation level, AnimationRunner runner, KeyboardSensor keyboard,
                     ScoreTrackingListener score) {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.score = score;
        this.blockRemover = new BlockRemover(this, remainingBlocks);
        this.ballRemover = new BallRemover(this, remainingBalls);
        this.runner = runner;
        this.keyboard = keyboard;
        this.level = level;
    }


    /**
     * adds a collidable to the game environment collidables list.
     *
     * @param c represents a new collidable we want to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }


    /**
     * adds a sprite to the sprite collection sprites list.
     *
     * @param s represents a new sprite we want to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }


    /**
     * create an array of balls, with random angle and location.
     */
    public void createBalls() {
        Ball[] balls = new Ball[this.level.numberOfBalls()];
        List<Velocity> velocities = this.level.initialBallVelocities();

        for (int i = 0; i < this.level.numberOfBalls(); i++) {
            balls[i] = new Ball(new Point((double) this.environment.getWIDTH() / 2,
                    (double) this.environment.getHEIGHT() - 41), this.level.ballsRadius(),
                    this.level.ballsColor());
            balls[i].setVelocity(velocities.get(i));
        }
        this.remainingBalls.increase(this.level.numberOfBalls());

        for (Ball ball : balls) {
            ball.addToGame(this);
            ball.setEnvironment(this.environment);
        }
    }


    /**
     * Creates the inner blocks of the game.
     */
    public void createInnerBlocks() {
        List<Block> innerBlocks = this.level.blocks();
        for (Block block : innerBlocks) {
            block.addToGame(this);
            block.addHitListener(this.blockRemover);
            block.addHitListener(this.score);
        }
        this.remainingBlocks.increase(innerBlocks.size());
        this.numOfBlocks = innerBlocks.size();
    }


    /**
     * Removes a collidable from the collidables list.
     *
     * @param c represents a collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }


    /**
     * Removes a sprite from the sprites list.
     *
     * @param s represents a sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }


    /**
     * create the paddle.
     */
    private void createPaddle() {
        Paddle paddle = new Paddle(this.keyboard, this.level);
        paddle.addToGame(this);
        this.environment.addPaddleToGame(paddle);
    }


    /**
     * creates the score block and the score indicator.
     * also creates the level indicator.
     */
    private void createScoreAndLevelInfo() {

        // create the score block
        this.environment.createScoreBlock(this);

        // create the score indicator.
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score.getScore());
        scoreIndicator.addToGame(this);

        // create the level indicator.
        LevelIndicator levelIndicator = new LevelIndicator(this.level);
        levelIndicator.addToGame(this);
    }


    /**
     * create the death-region block at the bottom of the screen.
     */
    private void createDeathBlock() {
        Block death = environment.createDeathBlock();
        death.addToGame(this);
        death.addHitListener(this.ballRemover);
    }


    /**
     * initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     * this method is in charge of setting up the game.
     */
    public void initialize() {

        // create the background
        this.addSprite(this.level.getBackground());

        /*
         * create the paddle, the balls, the inner-blocks, the score and level (block and indicator),
         * and the death block in the bottom.
         */
        createPaddle();
        createBalls();
        createInnerBlocks();
        createScoreAndLevelInfo();
        createDeathBlock();

        // create the frame blocks
        List<Block> frame = this.environment.setupFrameBlocks();
        for (Block block : frame) {
            block.addToGame(this);
        }
    }


    /**
     * run the game - start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2000, 3, this.sprites));
        this.running = true;

        // use our runner to run the current animation - which is one turn of the game.
        this.runner.run(this);
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        // if there are no more balls or no more block - end the game.
        if (this.remainingBlocks.getValue() == this.numOfBlocks - this.level.numberOfBlocksToRemove()
                || this.remainingBalls.getValue() == 0) {

            // if all the blocks are removed - add 100 points.
            if (this.remainingBlocks.getValue() == this.numOfBlocks - this.level.numberOfBlocksToRemove()) {
                this.score.raiseCurrentScore(STRIKE_SCORE);
            }
            this.running = false;
        }

        // if the player wants to pause the game
        if (this.keyboard.isPressed("p")) {
            PauseScreen ps = new PauseScreen(this.keyboard);
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", ps));
        }
    }


    @Override
    public boolean shouldStop() {
        return !this.running;
    }


    /**
     * @return the number of remaining balls in the game.
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }


    /**
     * @return the number of remaining blocks in the game.
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }


    /**
     * @return the number of blocks in the game.
     */
    public int getNumOfBlocks() {
        return numOfBlocks;
    }
}
