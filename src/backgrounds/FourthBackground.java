// ID: 315789461

package backgrounds;

import biuoop.DrawSurface;
import game.GameLevel;
import sprites.Sprite;

import java.awt.Color;


/**
 * FourthBackground class.
 *
 * @author Yuval Alroy.
 */
public class FourthBackground implements Sprite {


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(76, 129, 220));
        d.fillRectangle(0, 0, 800, 600);
        Color[] colors = new Color[]{new java.awt.Color(205, 200, 199),
                new java.awt.Color(179, 173, 174),
                new java.awt.Color(167, 167, 167),
                new java.awt.Color(175, 165, 165),
                new java.awt.Color(160, 152, 152)};

        d.setColor(colors[1]);
        int startX = 110;
        int endX = 130;
        for (int rain = 0; rain < 10; rain++) {
            d.drawLine(startX, 370, endX, 600);
            startX += 12;
            endX += 12;
        }

        startX = 585;
        endX = 550;
        for (int rain = 0; rain < 10; rain++) {
            d.drawLine(startX, 470, endX, 600);
            startX += 12;
            endX += 12;
        }

        int x = 112;
        int y = 370;
        int r = 40;
        for (int cloud = 0; cloud < 2; cloud++) {
            if (cloud == 1) {
                x += 350;
                y += 100;
                r -= 3;
            }
            for (int i = 0; i < 5; i++) {
                d.setColor(colors[i]);
                d.fillCircle(x, y, r);
                r = (i % 2 == 0) ? (r - 5) : (r + 5);
                x += 25;
                y = (i % 2 == 0) ? (y - 25) : (y + 25);
            }
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
