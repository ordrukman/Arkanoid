// ID: 20909000

import biuoop.GUI;
import biuoop.KeyboardSensor;

import game.AnimationRunner;
import game.GameFlow;
import game.levels.LevelOne;
import game.levels.LevelFour;
import game.levels.LevelInformation;
import game.levels.LevelThree;
import game.levels.LevelTwo;
import game.listener.Counter;
import game.screens.GameOver;
import game.screens.KeyPressStoppableAnimation;
import game.screens.YouWin;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Or Drukman
 * class Ass6Game: A Ass6Game is creating the game, initialize and run it.
 */
public class Ass6Game {
    private static final int WIDTHSCREEN = 800;
    private static final int HEIGHTSCREEN = 600;

    /**
     * @param  args - not in use
     * The main function that calls all the other functions to start the game.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", WIDTHSCREEN, HEIGHTSCREEN);
        AnimationRunner runner = new AnimationRunner(gui);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        Counter score = new Counter();
        GameFlow gF = new GameFlow(runner, keyboard, score, gui);
        List<LevelInformation> levels = new ArrayList<>();
        // if the program runs with arguments:
        if (args.length > 1) {
            for (String str : args) {
                switch (str) {
                    case "1":
                        LevelOne l1 = new LevelOne();
                        levels.add(l1);
                        break;
                    case "2":
                        LevelTwo l2 = new LevelTwo();
                        levels.add(l2);
                        break;
                    case "3":
                        LevelThree l3 = new LevelThree();
                        levels.add(l3);
                        break;
                    case "4":
                        LevelFour l4 = new LevelFour();
                        levels.add(l4);
                        break;
                    default:
                        break;
                }
            }
        }
        // if no levels are inserted
        if (levels.isEmpty()) {
            LevelOne l1 = new LevelOne();
            LevelTwo l2 = new LevelTwo();
            LevelThree l3 = new LevelThree();
            LevelFour l4 = new LevelFour();
            levels.add(l1);
            levels.add(l2);
            levels.add(l3);
            levels.add(l4);
        }
        // run all the levels:
        gF.runLevels(levels);
        int winScore = 0;
        // calculate the winning score:
        for (LevelInformation level : levels) {
            winScore = winScore + (level.numberOfBlocksToRemove() * 5);
        }
        winScore = winScore + (levels.size() * 100);
        if (score.getValue() >= winScore) {
            runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, new YouWin(score)));
        } else {
             runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, new GameOver(score)));
        }
        // end of the program:
        gui.close();
    }
}
