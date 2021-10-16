// ID: 315789461

package backgrounds;

import basic.Point;
import biuoop.DrawSurface;
import game.GameLevel;
import sprites.Sprite;

import java.awt.Color;


/**
 * SecondBackground class.
 *
 * @author Yuval Alroy.
 */
public class SecondBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 600);
        Point center = new Point(140, 165);
        Color[] colors = new Color[]{new java.awt.Color(255, 249, 0),
                new java.awt.Color(234, 219, 75),
                new java.awt.Color(246, 246, 140)};

        d.setColor(colors[2]);
        int endX = 10;
        for (int i = 0; i < 100; i++) {
            d.drawLine((int) center.getX(), (int) center.getY(), endX, 300);
            endX += 7;
        }

        int r = 65;
        for (int i = 2; i >= 0; i--) {
            d.setColor(colors[i]);
            d.fillCircle((int) center.getX(), (int) center.getY(), r);
            r -= 10;
        }
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
