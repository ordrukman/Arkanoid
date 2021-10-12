// ID: 20909000

package game.objectsGame;

import biuoop.DrawSurface;

/**
 * @author Or Drukman
 * interface Sprite: A Sprite is an object that can be drawn to the screen.
 */
public interface Sprite {
    /**
     * call drawOn(d) on all sprites.
     * activate the function drawOn for all the Sprite objects in the SpriteCollection.
     * @param d the surface to drawn on.
     */
    void drawOn(DrawSurface d);

    /**
     * call timePassed() on all sprites.
     * activate the function timePassed for all the Sprite objects in the SpriteCollection.
     */
    void timePassed();
}
