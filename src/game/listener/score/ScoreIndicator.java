// ID: 20909000

package game.listener.score;
import biuoop.DrawSurface;
import game.GameLevel;
import game.listener.Counter;
import game.objectsGame.Sprite;

/**
 * @author Or Drukman
 * class ScoreIndicator: a ScoreIndicator which will be in charge of displaying the current score.
 * The ScoreIndicator will hold a reference to the scores counter,
 * and will be added to the game as a sprite positioned at the top of the screen.
 */
public class ScoreIndicator implements Sprite {
    //field:
    private Counter score;
    private String levelName;

    /**
     * contractor of the object.
     * @param score the Counter score that received.
     * @param levelName the level name.
     */
    public ScoreIndicator(Counter score, String levelName) {
        this.score = score;
        this.levelName = levelName;
    }

    /**
     * draw the sprite- the score in the top of the screen.
     * @param d - the cDrawSurface of the screen.
     */
    public void drawOn(DrawSurface d) {
        d.drawText(300, 17, "score: " + this.score.getValue(), 20);
        d.drawText(500, 17, "Level Name: " + this.levelName, 20);
    }

    /**
     * call timePassed() on all sprites.
     * activate the function timePassed for all the Sprite objects in the SpriteCollection.
     */
    public void timePassed() {
    }

    /**
     * adding the object to the list of Sprites.
     * @param gameLevel the gameLevel object.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
