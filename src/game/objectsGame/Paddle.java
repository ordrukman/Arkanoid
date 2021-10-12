// ID: 20909000

package game.objectsGame;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import game.objectsGame.geometry.Point;
import game.objectsGame.geometry.Rectangle;

/**
 * @author Or Drukman
 * class Paddle: A Paddle object is the player in the game.
 * It is a rectangle - Block that is controlled by the arrow keys, and moves according to the player key presses.
 */

public class Paddle implements Sprite, Collidable {
    // fields:
    private biuoop.KeyboardSensor keyboard;
    private Block playerBlock;
    private int paddleSpeed;
    private int paddleWidth;

    // constants:
    static final int WIDTHSCREEN = 800;
    static final int HEIGHTSCREEN = 600;

    // constructor:
    /**
     * @param keyboard - the KeyboardSensor of the Paddle.
     * @param color - the color of the Paddle - playerBlock.
     * @param paddleSpeed - the paddle speed.
     * @param paddleWidth - the paddle width.
     *
     */
    public Paddle(KeyboardSensor keyboard, java.awt.Color color, int paddleSpeed, int paddleWidth) {
        this.keyboard = keyboard;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        // creating the playerBlock:
        this.playerBlock = new Block(new Rectangle(new Point(400 - (this.paddleWidth / 2), 560),
                this.paddleWidth, 20), color);

    }

    /**
     * change the paddle location to new one a little bit left.
     */
    public void moveLeft() {
        this.playerBlock = new Block(new Rectangle(new Point(
                this.playerBlock.getCollisionRectangle().getUpperLeft().getX() - this.paddleSpeed, 560)
                ,  this.paddleWidth, 20), this.playerBlock.getColor());
    }

    /**
     * change the paddle location to new one a little bit right.
     */
    public void moveRight() {
        this.playerBlock = new Block(new Rectangle(new Point(
                this.playerBlock.getCollisionRectangle().getUpperLeft().getX() + this.paddleSpeed, 560)
                ,  this.paddleWidth, 20), this.playerBlock.getColor());
    }

    // Sprite:
    /**
     * notify the sprite that time has passed and the paddle should to moveLeft or moveRight if the user pressed
     * on the LEFT_KEY or the RIGHT_KEY.
     * Moreover check if the paddle in the screen range.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)
                && this.playerBlock.getCollisionRectangle().getUpperLeft().getX() > 20) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)
                && this.playerBlock.getCollisionRectangle().getUpperLeft().getX() < (800 - 20 - this.paddleWidth)) {
            this.moveRight();
        }
    }

    /**
     * draw the paddle on the given DrawSurface using the block function to draw on, playerBlock.
     * @param d the surface to print the paddle on.
     */
    public void drawOn(DrawSurface d) {
        this.playerBlock.drawOn(d);
    }

    // Collidable:
    /**
     * @return  - return the rectangle of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.playerBlock.getCollisionRectangle();
    }

    /**
     * Notify the object that we collided with it at collisionPoint with
     *    a given velocity.
     *    The return is the new velocity expected after the hit (based on
     *    the force the object inflicted on us).
     * @param collisionPoint - the collision Point with the paddle.
     * @param currentVelocity - the currentVelocity of the hit object with the paddle.
     * @param hitter - the ball that collide with the block.
     * @return Returns the new Velocity depends on the hit point.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // true is the ball hit the paddle on the upper line of the paddle.
        if (collisionPoint.getY() == this.playerBlock.getCollisionRectangle().getUpperLeft().getY()) {
            // calculating the part of impact of the ball on the paddle, to update the new Velocity.
            double placeOfImpact =
                    (collisionPoint.getX() - this.playerBlock.getCollisionRectangle().getUpperLeft().getX())
                            / (this.playerBlock.getCollisionRectangle().getWidth() / 5);
            switch ((int) placeOfImpact) {
                case 0: return Velocity.fromAngleAndSpeed(300, Velocity.fromVelToSpeed(hitter.getVelocity()));
                case 1: return Velocity.fromAngleAndSpeed(330, Velocity.fromVelToSpeed(hitter.getVelocity()));
                case 2: break;
                case 3: return Velocity.fromAngleAndSpeed(30, Velocity.fromVelToSpeed(hitter.getVelocity()));
                default: return Velocity.fromAngleAndSpeed(60, Velocity.fromVelToSpeed(hitter.getVelocity()));
            }
        }
        // if the ball hit the paddle sides or on the lower lin of the paddle.
        return this.playerBlock.hit(hitter, collisionPoint, currentVelocity);
    }

    /**
     * adding the paddle  the gameLevel.
     * @param gameLevel the gameLevel object.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }
}
