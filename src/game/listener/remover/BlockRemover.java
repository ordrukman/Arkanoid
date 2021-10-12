// ID: 20909000

package game.listener.remover;

import game.GameLevel;
import game.listener.Counter;
import game.listener.HitListener;
import game.objectsGame.Ball;
import game.objectsGame.Block;

/**
 * @author Or Drukman
 * class BlockRemover: a BlockRemover is in charge of removing blocks from the gameLevel, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    // fields:
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * create new removeBlock object.
     * @param gameLevel - the gameLevel.
     * @param remainingBlocks - the counter of the remaining blocks.
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Blocks that are hit should be removed from the gameLevel.
     * that is being removed from the gameLevel.
     * @param beingHit the block that involved in the hit.
     * @param hitter the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}