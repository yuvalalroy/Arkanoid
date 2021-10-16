// ID: 315789461

package levels;

import biuoop.DrawSurface;
import game.GameLevel;
import sprites.Sprite;


/**
 * LevelIndicator class.
 * in charge of displaying the current level.
 *
 * @author Yuval Alroy.
 */
public class LevelIndicator implements Sprite {
    private LevelInformation level;
    private static final int X_LOCATION = 600;
    private static final int SIZE = 15;


    /**
     * Constructor.
     *
     * @param level represents the level of the game.
     */
    public LevelIndicator(LevelInformation level) {
        this.level = level;
    }


    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(X_LOCATION, SIZE, String.format("Level Name: %s", level.levelName()), SIZE);
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
