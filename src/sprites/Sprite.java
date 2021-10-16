// ID: 315789461

package sprites;

import biuoop.DrawSurface;
import game.GameLevel;

/**
 * Sprite interface:
 * a Sprite is a game object that can be drawn to the screen (and which is not just a background image).
 * Sprites can be drawn on the screen, and can be notified that time has passed -
 * so that they know to change their position / shape / appearance / etc.
 *
 * @author Yuval Alroy
 */
public interface Sprite {


    /**
     * draw the sprite to the screen.
     *
     * @param d represents the draw surface.
     */
    void drawOn(DrawSurface d);


    /**
     * notify the sprite that time has passed.
     */
    void timePassed();


    /**
     * adding the sprite to the game.
     *
     * @param gameLevel represents a game.
     */
    void addToGame(GameLevel gameLevel);
}
