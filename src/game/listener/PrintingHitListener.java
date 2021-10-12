// ID: 20909000

package game.listener;

import game.objectsGame.Ball;
import game.objectsGame.Block;

/**
 * @author Or Drukman
 * class PrintingHitListener: A PrintingHitListener is an object that can notify massages when hit is happened.
 */
public class PrintingHitListener implements HitListener {

    /**
     * notify massages when hit is happened.
     * @param beingHit the block.
     * @param hitter the ball that has been hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}