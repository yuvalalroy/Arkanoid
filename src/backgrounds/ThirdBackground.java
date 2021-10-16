// ID: 315789461

package backgrounds;

import biuoop.DrawSurface;
import game.GameLevel;
import sprites.Sprite;

import java.awt.Color;


/**
 * ThirdBackground class.
 *
 * @author Yuval Alroy.
 */
public class ThirdBackground implements Sprite {


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(12, 132, 3));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.white);
        d.fillRectangle(60, 430, 110, 170);
        d.setColor(new Color(24, 24, 24));
        int temp = 50;
        for (int i = 0; i < 6; i++) {
            d.fillRectangle(temp, 420, 10, 180);
            temp += 23;
        }
        temp = 420;
        for (int i = 0; i < 5; i++) {
            d.fillRectangle(50, temp, 125, 10);
            temp += 38;
        }

        d.setColor(new Color(52, 51, 51));
        d.fillRectangle(93, 360, 40, 60);
        d.setColor(new Color(78, 76, 76));
        d.fillRectangle(106, 200, 13, 160);

        Color[] colors = new Color[]{new java.awt.Color(222, 183, 87),
                new java.awt.Color(231, 65, 95),
                new java.awt.Color(234, 234, 234)};

        temp = 15;
        for (int i = 0; i < 3; i++) {
            d.setColor(colors[i]);
            d.fillCircle(112, 190, temp);
            temp -= 5;
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
