// ID: 209090000

package game.screens;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Animation;

/**
 * @author Or Drukman
 * class KeyPressStoppableAnimation: A class that responsable for manging all the waiting-for-key-press class.
 */
public class KeyPressStoppableAnimation implements Animation {
    // fields:
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    // constructor:
    /**
     * create new KeyPressStoppableAnimation.
     * @param sensor the KeyboardSensor.
     * @param key the ky that stop the run.
     * @param animation the animation that run.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.stop = false;
        this.animation = animation;
        this.isAlreadyPressed = true;

    }

    /**
     * This function is in charge of the game logic and make the running of the animation.
     * @param d the DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (!this.keyboardSensor.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
        if (this.keyboardSensor.isPressed(this.key) && !isAlreadyPressed) {
            this.stop = true;
        }
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @return  true or false if the game should stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
