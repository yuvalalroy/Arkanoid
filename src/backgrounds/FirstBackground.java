// ID: 315789461

package backgrounds;

import basic.Point;
import biuoop.DrawSurface;
import game.GameLevel;
import sprites.Sprite;

import java.awt.Color;


/**
 * FirstBackground class.
 *
 * @author Yuval Alroy.
 */
public class FirstBackground implements Sprite {


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        Point center = new Point(398, 170);
        d.setColor(Color.BLUE);
        int r = 60;
        for (int i = 0; i < 3; i++) {
            d.drawCircle((int) center.getX(), (int) center.getY(), r);
            r += 30;
        }
        d.drawLine(420, 170, 570, 170);
        d.drawLine(225, 170, 375, 170);
        d.drawLine(398, 150, 398, 0);
        d.drawLine(398, 185, 398, 335);
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
