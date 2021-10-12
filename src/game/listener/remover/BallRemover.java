// ID: 20909000

package game.listener.remover;

import game.GameLevel;
import game.listener.Counter;
import game.listener.HitListener;
import game.objectsGame.Ball;
import game.objectsGame.Block;

/**
 * @author Or Drukman
 * class BallRemover: a BallRemover is in charge of removing balls from the gameLevel, as well as keeping count
 * of the number of balls that remain.
 */
public class BallRemover implements HitListener {
    // fields:
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * create new removeBlock object.
     * @param gameLevel - the gameLevel.
     * @param remainingBalls - the counter of the remaining balls.
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * balls that fall are remove from the gameLevel.
     * @param beingHit the block that involved in the hit.
     * @param hitter the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.remainingBalls.decrease(1);
    }
}