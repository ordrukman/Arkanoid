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
 * class LevelThree: the third level of the game.
 */
public class LevelFour implements LevelInformation {
    // fields:
    private List<Ball> balls;
    private List<Velocity> ballsVelocities;
    private List<Block> blocks;

    /**
     * constructor of the level.
     */
    public LevelFour() {
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
        for (int i = (this.balls.size() / 2); i > 0; i--) {
            this.ballsVelocities.add(Velocity.fromAngleAndSpeed(360 - (i * 5), 7));
        }
        for (int i = 0; i < (this.balls.size() / 2); i++) {
            this.ballsVelocities.add(Velocity.fromAngleAndSpeed(i * 6, 7));
        }
        return this.ballsVelocities;
    }

    /**
     * @return Returns the paddle speed.
     */
    public int paddleSpeed() {
        return 8;
    }

    /**
     * @return Returns the paddle Width.
     */
    public int paddleWidth() {
        return 300;
    }

    /**
     * @return Returns the level name will be displayed at the top of the screen.
     */
    public String levelName() {
        return "Level Four";
    }

    /**
     * @return Returns a sprite with the background of the level.
     */
    public Sprite getBackground() {
        Sprite background = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(java.awt.Color.cyan);
                // creating the black background:
                d.fillRectangle(20, 20, 760, 580);
                d.setColor(java.awt.Color.white);
                for (int i = 0; i < 14; i++) {
                    d.drawLine(110 + (i * 10), 400, 50 + (i * 10), 600);
                    d.drawLine(510 + (i * 10), 300, 450 + (i * 10), 600);
                }
                d.setColor(java.awt.Color.gray);
                d.fillCircle(150, 400, 30);
                d.fillCircle(210, 390, 40);
                d.fillCircle(120, 360, 35);
                d.setColor(java.awt.Color.lightGray);
                d.fillCircle(180, 360, 40);
                d.fillCircle(120, 410, 30);
                d.setColor(java.awt.Color.gray);
                d.fillCircle(550, 300, 30);
                d.fillCircle(610, 290, 40);
                d.fillCircle(520, 260, 35);
                d.setColor(java.awt.Color.lightGray);
                d.fillCircle(580, 260, 40);
                d.fillCircle(520, 310, 30);
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
                java.awt.Color.blue, java.awt.Color.pink, java.awt.Color.green, java.awt.Color.white};
        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < 15; i++) {
                java.awt.Color currentColor = colorsBlocks[j];
                Rectangle rec = new Rectangle(new Point(725 - (i * 50), 220 - (j * 20)), 50, 20);
                Block block = new Block(rec, currentColor);
                this.blocks.add(block);
            }
        }
        return blocks;
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
     * createBalls for the game: create 20 balls above the paddle.
     * @param game the game level.
     * @return list of balls.
     */
    public List createBalls(GameLevel game) {
        for (int i = 0; i < 20; i++) {
            Ball ball = new Ball(200 + (i * 20), 550, 5, java.awt.Color.black, game.getEnvironment());
            this.balls.add(ball);
        }
        return this.balls;
    }
}