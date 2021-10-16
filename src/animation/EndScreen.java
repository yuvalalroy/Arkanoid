// ID: 315789461

package animation;

import basic.Counter;
import biuoop.DrawSurface;


/**
 * EndScreen class.
 * Once the game is over (either the player died, or he managed to clear all the levels),
 * displays the final score and the winning position.
 *
 * @author Yuval Alroy.
 */
public class EndScreen implements Animation {
    private Counter score;
    private boolean win;


    /**
     * Constructor.
     *
     * @param score represents the current score
     * @param win   represents if the player won or lost.
     */
    public EndScreen(Counter score, boolean win) {
        this.score = score;
        this.win = win;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.win) {
            d.setColor(new java.awt.Color(26, 172, 46));
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(new java.awt.Color(161, 161, 161));
            d.drawText(260, 220, "You Win!", 70);
            d.setColor(new java.awt.Color(97, 97, 97));
            d.drawText(260, 210, "You Win!", 70);
            d.setColor(new java.awt.Color(0, 0, 0));
            d.drawText(260, 200, "You Win!", 70);
        } else {
            d.setColor(new java.awt.Color(182, 28, 51));
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(new java.awt.Color(161, 161, 161));
            d.drawText(220, 220, "Game Over.", 70);
            d.setColor(new java.awt.Color(97, 97, 97));
            d.drawText(220, 210, "Game Over.", 70);
            d.setColor(new java.awt.Color(0, 0, 0));
            d.drawText(220, 200, "Game Over.", 70);
        }
        d.drawText(310, 550, "Your score is: " + this.score.getValue(), 20);
    }


    @Override
    public boolean shouldStop() {
        return false;
    }
}
