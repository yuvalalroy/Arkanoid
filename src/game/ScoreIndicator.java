// ID: 315789461

package game;

import basic.Counter;
import sprites.Sprite;
import biuoop.DrawSurface;

/**
 * class ScoreIndicator.
 * in charge of displaying the current score.
 *
 * @author Yuval Alroy.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private static final int X_LOCATION = 360;
    private static final int SIZE = 15;


    /**
     * Constructor.
     *
     * @param score represents the score tracking listener.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }


    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(X_LOCATION, SIZE, String.format("Score: %d", score.getValue()), SIZE);
    }


    @Override
    public void timePassed() {
        // remains empty for this assignment.
    }


    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
