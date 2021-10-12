// ID: 209090000

package game;

import biuoop.DrawSurface;
import game.objectsGame.collections.SpriteCollection;

/**
 * @author Or Drukman
 * class CountdownAnimation: A class that responsable for the CountdownAnimation.
 */
public class CountdownAnimation implements Animation {
    // fields:
    private double numOfSeconds;
    private int countFrom;
    private long startTime;
    private int timeLeft;
    private SpriteCollection gameScreen;
    private boolean running;

    // constructor:
    /**
     * create new runner.
     * @param numOfSeconds number of seconds the screen will display.
     * @param countFrom number of to count from.
     * @param gameScreen the gameScreen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.timeLeft = countFrom;
        this.startTime = System.currentTimeMillis();
        this.gameScreen = gameScreen;
        this.running = true;
    }

    /**
     * This function is in charge of the game logic and make the running of the game.
     * calculate the timing to show the countdown.
     * @param d the DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(java.awt.Color.blue);
        // creating the blue background:
        d.fillRectangle(20, 20, 760, 580);
        this.gameScreen.drawAllOn(d);
        this.timeLeft = countFrom - ((int) ((System.currentTimeMillis() - this.startTime) / 1000));
        d.drawText(380, 450, Integer.valueOf(timeLeft).toString(), 100);
        if (timeLeft <= 0) {
            this.running = false;
        }
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @return  true or false if the game should stop.
     */
    public boolean shouldStop() {
        return !running;
    }
}
