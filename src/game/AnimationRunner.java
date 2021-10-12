// ID: 209090000

package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Or Drukman
 * class AnimationRunner: A class that responsable for kipping the game running f is needed.
 */
public class AnimationRunner {
    // fields:
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper = new Sleeper();

    // constructor:
    /**
     * create new runner.
     * @param g the gui of the game.
     */
    public AnimationRunner(GUI g) {
        this.gui = g;
        this.framesPerSecond = 60;
    }

    /**
     * Run the game -- start the animation loop.
     * @param animation the animation to place the running of the game.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
