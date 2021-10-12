// ID: 209090000

package game.screens;

import biuoop.DrawSurface;
import game.Animation;
import game.listener.Counter;

/**
 * @author Or Drukman
 * class PauseScreen: A class that responsable for the PauseScreen.
 */
public class YouWin implements Animation {
    // fields:
    private Counter score;

    // constructor:
    /**
     * create new runner.
     * @param score the score.
     */
    public YouWin(Counter score) {
        this.score = score;
    }

    /**
     * This function is in charge of the game logic and make the running of the game.
     * @param d the DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2 + 100, "You Win! Your score is " + this.score.getValue(), 32);
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @return  true or false if the game should stop.
     */
    public boolean shouldStop() {
        return false;
    }
}