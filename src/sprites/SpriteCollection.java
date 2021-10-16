// ID: 315789461

package sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * SpriteCollection class:
 * This class holds a collection of sprites.
 * This class supports the addition of new sprites, and also can notify all the sprites that time has passed.
 *
 * @author Yuval Alroy
 */
public class SpriteCollection {
    private List<Sprite> sprites;


    /**
     * Constructor.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }


    /**
     * adds a sprite to the sprite collection.
     *
     * @param s represents a Sprites.Sprite.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }


    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {

        // Make a copy of the sprites before iterating over them.
        List<Sprite> spritesCopy = new ArrayList<>(this.sprites);
        for (Sprite s : spritesCopy) {
            s.timePassed();
        }
    }


    /**
     * Removes a sprite from the sprites list.
     *
     * @param s represents a sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }


    /**
     * call drawOn(d) on all sprites.
     *
     * @param d represents the draw surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.sprites) {
            s.drawOn(d);
        }
    }
}
