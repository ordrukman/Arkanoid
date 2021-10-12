// ID: 20909000

package game.listener.score;

import game.listener.Counter;
import game.listener.HitListener;
import game.objectsGame.Ball;
import game.objectsGame.Block;

/**
 * @author Or Drukman
 * class ScoreTrackingListener: a ScoreTrackingListener is in charge of counting the score of the player.
 */
public class ScoreTrackingListener implements HitListener {
    //field:
    private Counter currentScore;

    /**
     * contractor of the object.
     * @param scoreCounter the Counter score that received.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * once hitEvent has been call the score update.
     * @param beingHit the block that involved in the hit.
     * @param hitter the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}