// ID: 315789461

package collidables;

import basic.Line;
import basic.Point;
import basic.Rectangle;
import basic.Velocity;
import game.GameLevel;
import observer.HitListener;
import observer.HitNotifier;
import sprites.Ball;
import sprites.Sprite;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;


/**
 * Block class:
 * The blocks are represented by rectangles. blocks also have color.
 * In addition, blocks have hit flag so they know if an object hits them.
 * Blocks can also draw themselves on a DrawSurface.
 *
 * @author Yuval Alroy
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle collisionRectangle;
    private java.awt.Color color;
    private final java.awt.Color delimiterColor;
    private List<HitListener> hitListeners;


    /**
     * Constructor.
     *
     * @param rectangle represents the block (which is a rectangle)
     * @param color     represents the color of the block.
     */
    public Block(Rectangle rectangle, java.awt.Color color) {
        this.collisionRectangle = rectangle;
        this.color = color;
        this.delimiterColor = new java.awt.Color(0, 0, 0);
        this.hitListeners = new ArrayList<>();
    }


    @Override
    public Rectangle getCollisionRectangle() {
        return this.collisionRectangle;
    }


    /**
     * calculates the new velocity expected after the hit.
     *
     * @param currentVelocity represents the velocity of the object that we collided with.
     * @param index           represents the index in the ribs list, which represents the location of the hit.
     * @param hitter          represents the ball that hits the object.
     * @return the new velocity expected after the hit.
     */
    private Velocity calculateVelocity(Ball hitter, Velocity currentVelocity, int index) {
        if (index % 2 == 0) {
            currentVelocity.setDy(-currentVelocity.getDy());
        } else {
            currentVelocity.setDx(-currentVelocity.getDx());
        }

        // notify the hit object that a collision occurred.
        this.addHitListener(hitter);
        this.notifyHit(hitter);
        return currentVelocity;
    }


    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        List<Line> ribs = this.collisionRectangle.getRibs();
        for (int i = 0; i < ribs.size(); i++) {
            if (collisionPoint.isOnLine(ribs.get(i))) {
                currentVelocity.setVelocity(calculateVelocity(hitter, currentVelocity, i));
            }
        }
        return currentVelocity;
    }


    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }


    @Override
    public void drawOn(DrawSurface surface) {
        Rectangle rec = this.collisionRectangle;
        surface.setColor(this.color);
        surface.fillRectangle((int) rec.getUpperLeft().getX(), (int) rec.getUpperLeft().getY(),
                (int) rec.getWidth(), (int) rec.getHeight());
        surface.setColor(this.delimiterColor);
        surface.drawRectangle((int) rec.getUpperLeft().getX(), (int) rec.getUpperLeft().getY(),
                (int) rec.getWidth(), (int) rec.getHeight());
    }


    @Override
    public void timePassed() {
        // remains empty for this assignment.
    }


    /**
     * Removes the block from the game.
     *
     * @param gameLevel represents a game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }


    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }


    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }


    /**
     * Notifies all listeners about a hit event.
     *
     * @param hitter represents the ball that's doing the hitting.
     */
    private void notifyHit(Ball hitter) {

        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
