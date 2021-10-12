// ID: 20909000

package game.listener;

import game.objectsGame.Ball;
import game.objectsGame.Block;

/**
 * @author Or Drukman
 * interface HitListener: A HitListener is an object that can get massages from the notifiers.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the block.
     * @param hitter the ball that has been hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}