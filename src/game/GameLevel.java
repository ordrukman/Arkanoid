// ID: 209090000

package game;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import game.levels.LevelInformation;
import game.listener.remover.BallRemover;
import game.listener.remover.BlockRemover;
import game.listener.Counter;
import game.listener.score.ScoreIndicator;
import game.listener.score.ScoreTrackingListener;
import game.objectsGame.Ball;
import game.objectsGame.Collidable;
import game.objectsGame.Paddle;
import game.objectsGame.Sprite;
import game.objectsGame.Velocity;
import game.objectsGame.Block;
import game.objectsGame.collections.GameEnvironment;
import game.objectsGame.collections.SpriteCollection;
import game.objectsGame.geometry.Point;
import game.objectsGame.geometry.Rectangle;
import game.screens.KeyPressStoppableAnimation;
import game.screens.PauseScreen;

import java.util.List;

/**
 * @author Or Drukman
 * class GameLevel: A GameLevel hold the sprites and the collidables, and will be in charge of the animation.
 */
public class GameLevel implements Animation {
    // fields:
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation level;
    private Paddle paddle;

    // constants:
    static final int WIDTHSCREEN = 800;
    static final int HEIGHTSCREEN = 600;

    /**
     * create the game level.
     * @param keyboard keyboard game.
     * @param runner the runner.
     * @param score the score.
     * @param level the current level.
     */
    public GameLevel(KeyboardSensor keyboard,  AnimationRunner runner, Counter score, LevelInformation level) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBalls = new Counter();
        this.remainingBlocks = new Counter();
        this.score = score;
        this.keyboard = keyboard;
        this.runner = runner;
        this.level = level;
    }

    /**
     * @return the game.
     */
    public GameLevel getGame() {
        return this;
    }

    /**
     * @return the game.
     */
    public Counter getRemainBalls() {
        return this.remainingBalls;
    }

    /**
     * @param l update the current level.
     */
    public void setGameLevel(LevelInformation l) {
        this.level = l;
    }

    /**
     * @return the GameEnvironment of the game.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * add the given collidable to the environment.
     * @param c the new Collidable object.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * remove the given collidable of the environment.
     *
     * @param c the new Collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * add the given Sprite to the SpriteCollection.
     *
     * @param s the new Sprite object.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * remove the given Sprite of the SpriteCollection.
     *
     * @param s the new Sprite object.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        // adding the Background :
        this.addSprite(this.level.getBackground());
        this.paddle = new Paddle(this.keyboard, java.awt.Color.orange, this.level.paddleSpeed(),
                this.level.paddleWidth());
        paddle.addToGame(this);
        // create score panel:
        ScoreIndicator scorePanel = new ScoreIndicator(this.score, this.level.levelName());
        scorePanel.addToGame(this);
        // create balls at the run function.
        BallRemover doInCaseOfHitBall = new BallRemover(this, this.remainingBalls);
        // create death-region block:
        Collidable c3 = new Block(new Rectangle(new Point(0, 610), WIDTHSCREEN, 20), java.awt.Color.blue);
        ((Block) c3).addToGame(this);
        ((Block) c3).addHitListener(doInCaseOfHitBall);
        // create all blocks:
        createEdges();
        createBlocks();
        this.remainingBlocks.increase(this.level.numberOfBlocksToRemove());
    }

    /**
     * adding the block from the current level to the game to initialize.
     */
    public void createBlocks() {
        BlockRemover doInCaseOfHitBlock = new BlockRemover(this, this.remainingBlocks);
        ScoreTrackingListener scoreTrack = new ScoreTrackingListener(this.score);
        for (Block block : this.level.blocks()) {
            block.addToGame(this);
            block.addHitListener(doInCaseOfHitBlock);
            block.addHitListener(scoreTrack);
        }
    }

    /**
     * createEdges for the screen: create 3 gray Blocks at the edges of the screen.
     */
    public void createEdges() {
        java.awt.Color colorEdge = java.awt.Color.gray;
        Collidable c1 = new Block(new Rectangle(new Point(0, 20), 25, HEIGHTSCREEN), colorEdge);
        ((Block) c1).addToGame(this);
        Collidable c2 = new Block(new Rectangle(new Point(0, 20), WIDTHSCREEN, 20), colorEdge);
        ((Block) c2).addToGame(this);
        Collidable c4 = new Block(new Rectangle(new Point(775, 20), 25, HEIGHTSCREEN), colorEdge);
        ((Block) c4).addToGame(this);
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @return  true or false if the game should stop.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * This function is in charge of the game logic and make the running of the game.
     * @param d the DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
        // if the run should stop.
        if ((this.remainingBlocks.getValue() == 0) || (this.remainingBalls.getValue() == 0)) {
            if (this.remainingBlocks.getValue() == 0) {
                this.score.increase(100);
            }
            Sleeper sleeper = new Sleeper();
            sleeper.sleepFor(1000);
            this.running = false;
        }
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        updateBalls();
        this.runner.run(new CountdownAnimation(3, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * adding the balls from the current level to the game and give them the wanted velocity.
     */
    public void updateBalls() {
        List<Ball> balls = this.level.createBalls(getGame());
        List<Velocity> ballsVelocities = this.level.initialBallVelocities();
        this.remainingBalls.increase(balls.size());
        int i = 0;
        for (Ball ball : balls) {
            ball.setVelocity(ballsVelocities.get(i));
            i++;
            ball.addToGame(this);
        }
    }
}
