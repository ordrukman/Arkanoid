// ID: 209090000

package game;

import biuoop.DrawSurface;

/**
 * @author Or Drukman
 * interface Animation: separete the animation of the game from the Gui.
 */
public interface Animation {

    /**
     * This function is in charge of the game logic and make the running of the game.
     * @param d the DrawSurface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @return  true or false if the game should stop.
     */
    boolean shouldStop();
}
