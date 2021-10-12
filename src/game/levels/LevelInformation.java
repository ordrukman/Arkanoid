// ID: 209090000

package game.levels;

import game.GameLevel;
import game.objectsGame.Block;
import game.objectsGame.Sprite;
import game.objectsGame.Velocity;

import java.util.List;

/**
 * @author Or Drukman
 * interface interface: creating all the characteristic of level in the game.
 */
public interface LevelInformation {

    /**
     * @return Returns the number of balls in the level.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls().
     * @return Returns list of velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return Returns the paddle speed.
     */
    int paddleSpeed();

    /**
     * @return Returns the paddle Width.
     */
    int paddleWidth();

    /**
     * @return Returns the level name will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * @return Returns a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * create The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return list of blocks.
     */
    List<Block> blocks();

    /**
     * number of blocks that should be removed,
     * before the level is considered to be "cleared".
     * @return number of blocks to remove.
     */
    int numberOfBlocksToRemove();

    /**
     * createBalls for the game: create 3 balls above the paddle.
     * @param game the game level.
     * @return list of balls.
     */
     List createBalls(GameLevel game);

}