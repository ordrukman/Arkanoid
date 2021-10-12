// ID: 209090000

package game.levels;

import biuoop.DrawSurface;
import game.GameLevel;
import game.objectsGame.Ball;
import game.objectsGame.Block;
import game.objectsGame.Sprite;
import game.objectsGame.Velocity;
import game.objectsGame.geometry.Point;
import game.objectsGame.geometry.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Or Drukman
 * class LevelOne: the first level of the game.
 */
public class LevelThree implements LevelInformation {
    // fields:
    private List<Ball> balls;
    private List<Velocity> ballsVelocities;
    private List<Block> blocks;

    /**
     * constructor of the level.
     */
    public LevelThree() {
        this.balls = new ArrayList();
        this.blocks = new ArrayList();
        this.ballsVelocities = new ArrayList();
    }

    /**
     * @return Returns the number of balls in the level.
     */
    public int numberOfBalls() {
        return this.balls.size();
    }

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls().
     * @return Returns list of velocities.
     */
    public List<Velocity> initialBallVelocities() {
        int[] angels = new int[]{330, 0, 30};
        for (int i = 0; i < angels.length; i++) {
            this.ballsVelocities.add(Velocity.fromAngleAndSpeed(angels[i], 7));
        }
        return this.ballsVelocities;
    }

    /**
     * @return Returns the paddle speed.
     */
    public int paddleSpeed() {
        return 10;
    }

    /**
     * @return Returns the paddle Width.
     */
    public int paddleWidth() {
        return 120;
    }

    /**
     * @return Returns the level name will be displayed at the top of the screen.
     */
    public String levelName() {
        return "Level Three";
    }

    /**
     * @return Returns a sprite with the background of the level.
     */
    public Sprite getBackground() {
        Sprite background = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(java.awt.Color.blue);
                // creating the blue background:
                d.fillRectangle(20, 20, 760, 580);
            }
            @Override
            public void timePassed() {
                return;
            }
        };
        return background;
    }

    /**
     * create The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return list of blocks.
     */
    public List<Block> blocks() {
        // list of colors- one color for one row:
        java.awt.Color[] colorsBlocks = {java.awt.Color.gray, java.awt.Color.red, java.awt.Color.yellow,
                java.awt.Color.cyan, java.awt.Color.pink, java.awt.Color.green};
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 7 + j; i++) {
                java.awt.Color currentColor = colorsBlocks[j];
                Rectangle rec = new Rectangle(new Point(725 - (i * 50), 300 - (j * 20)), 50, 20);
                Block block = new Block(rec, currentColor);
                this.blocks.add(block);
            }
        }
        return this.blocks;
    }

    /**
     * number of blocks that should be removed,
     * before the level is considered to be "cleared".
     * @return number of blocks to remove.
     */
    public int numberOfBlocksToRemove() {
        return this.blocks.size();
    }

    /**
     * createBalls for the game: create 3 balls above the paddle.
     * @param game the game level.
     * @return list of balls.
     */
    public List createBalls(GameLevel game) {
        Ball ball1 = new Ball(380, 550, 5, java.awt.Color.white, game.getEnvironment());
        Ball ball2 = new Ball(400, 550, 5, java.awt.Color.white, game.getEnvironment());
        Ball ball3 = new Ball(420, 550, 5, java.awt.Color.white, game.getEnvironment());
        this.balls.add(ball1);
        this.balls.add(ball2);
        this.balls.add(ball3);
        return this.balls;
    }
}
