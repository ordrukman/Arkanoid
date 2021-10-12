// ID: 20909000

package game.objectsGame.collections;
import biuoop.DrawSurface;
import game.objectsGame.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Or Drukman
 * class SpriteCollection: A SpriteCollection has all the sprites objects.
 */
public class SpriteCollection {
    // fields:
    private ArrayList<Sprite> allSpritesObjects;

    /**
     * Create a new list of Sprites objects in the Sprite Collection.
     */
    public SpriteCollection() {
        this.allSpritesObjects = new ArrayList<Sprite>();
    }

    /**
     * add the given Sprite to the SpriteCollection.
     * @param s the new Sprite object.
     */
    public void addSprite(Sprite s) {
        this.allSpritesObjects.add(s);
    }

    /**
     * remove the given Sprite of the SpriteCollection.
     * @param s the new Sprite object.
     */
    public void removeSprite(Sprite s) {
        this.allSpritesObjects.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     * activate the function timePassed for all the Sprite objects in the SpriteCollection.
     */
    public void notifyAllTimePassed() {
        List<Sprite> allSprite = new ArrayList<Sprite>(this.allSpritesObjects);
        for (Sprite s: allSprite) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * activate the function drawOn for all the Sprite objects in the SpriteCollection.
     * @param d the surface to drawn on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s: allSpritesObjects) {
            s.drawOn(d);
        }
    }
}
