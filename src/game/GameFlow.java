// ID: 209090000

package game;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.levels.LevelInformation;
import game.listener.Counter;

import java.util.List;

/**
 * @author Or Drukman
 * class GameFlow: A class that responsable for kipping the game running,
 * This class will be in charge of creating the different levels, and moving from one level to the next.
 */
public class GameFlow {
    // fields:
    private Counter score;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private GUI gui;

    /**
     * create the game flow.
     * @param keyboard keyboard game.
     * @param runner the runner.
     * @param score the score.
     * @param gui the current gui.
     */
    public GameFlow(AnimationRunner runner, KeyboardSensor keyboard, Counter score, GUI gui) {
        this.animationRunner = runner;
        this.keyboardSensor = keyboard;
        this.score = score;
        this.gui = gui;
    }

    /**
     * run the game flow.
     * @param levels all the level in the game flow.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(this.keyboardSensor, this.animationRunner, this.score, levelInfo);
            level.initialize();
            level.run();
            while (!(level.shouldStop())) {
                level.run();
            }
            if (level.getRemainBalls().getValue() == 0) {
                break;
            }
        }
    }
}

