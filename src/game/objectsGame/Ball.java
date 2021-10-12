// ID: 209090000

package game.objectsGame;
import biuoop.DrawSurface;
import game.GameLevel;
import game.objectsGame.collections.GameEnvironment;
import game.objectsGame.geometry.Line;
import game.objectsGame.geometry.Point;

/**
 * @author Or Drukman
 * class Ball: A Ball (actually, a circle). Balls have size (radius), color, and location (a Point).
 * Balls also know how to draw themselves on a DrawSurface.
 */
public class Ball implements Sprite {
    // const for moveOneStep function
    static final int SMALLBALLSIZE = 10;
    // fields
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity vel;
    private GameEnvironment gameEnvironment;

    // constructors:

    /**
     * @param center          - the location of the point.
     * @param r               - the size of the ball, the radius.
     * @param color           - ths color of the ball.
     * @param gameEnvironment - the game environment.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.vel = Velocity.fromAngleAndSpeed(0, 5);
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * @param x               - the location of the x segment of the center point.
     * @param y               - the location of the y segment of the center point.
     * @param r               - the size of the ball, the radius.
     * @param color           - ths color of the ball.
     * @param gameEnvironment - the game environment.
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        Point center1 = new Point(x, y);
        this.center = center1;
        this.radius = r;
        this.color = color;
        this.vel = Velocity.fromAngleAndSpeed(0, 5);
        this.gameEnvironment = gameEnvironment;
    }

    // constructors without gameEnvironment:

    /**
     * @param center - the location of the point.
     * @param r      - the size of the ball, the radius.
     * @param color  - ths color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.vel = Velocity.fromAngleAndSpeed(0, 5);
    }

    /**
     * @param x     - the location of the x segment of the center point.
     * @param y     - the location of the y segment of the center point.
     * @param r     - the size of the ball, the radius.
     * @param color - ths color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        Point center1 = new Point(x, y);
        this.center = center1;
        this.radius = r;
        this.color = color;
        this.vel = Velocity.fromAngleAndSpeed(0, 5);
    }

    // accessors:

    /**
     * @return return the X value of the center point.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return return the Y value of the center point.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return return the size value of the point, the radius.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return return the color of the point.
     */
    public java.awt.Color getColor() {
        return this.color;
    }


    // draw the ball on the given DrawSurface:

    /**
     * @param surface the surface to print the ball on, with the size location and color of the ball.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }

    // Velocity:

    /**
     * @param v the Velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.vel = v;
    }

    /**
     * @param dx the x axes Velocity of the ball.
     * @param dy the y axes Velocity of the ball.
     */
    public void setVelocity(double dx, double dy) {
        Velocity v = new Velocity(dx, dy);
        this.vel = v;
    }

    /**
     * @return the Velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.vel;
    }


    /**
     * chancing the location of the ball.
     * adding (SMALLBALLSIZE / this.getSize()) to make sure small balls won't exceed the limits of the window.
     *
     * @param minScope the start point of the scope.
     * @param maxScope the end point of the scope.
     */
    public void moveOneStep(int minScope, int maxScope) {
        this.center = this.getVelocity().applyToPoint(this.center);
        if (this.center.getX() <= minScope + this.getSize() + (SMALLBALLSIZE / this.getSize())) {
            setVelocity(-this.vel.getVelocityDx(), this.vel.getVelocityDy());
        }
        if (this.center.getY() <= minScope + this.getSize() + (SMALLBALLSIZE / this.getSize())) {
            setVelocity(this.vel.getVelocityDx(), -this.vel.getVelocityDy());
        }
        if (this.center.getX() >= maxScope - this.getSize() - (SMALLBALLSIZE / this.getSize())) {
            setVelocity(-this.vel.getVelocityDx(), this.vel.getVelocityDy());
        }
        if (this.center.getY() >= maxScope - this.getSize() - (SMALLBALLSIZE / this.getSize())) {
            setVelocity(this.vel.getVelocityDx(), -this.vel.getVelocityDy());
        }
    }


    /**
     * 1) compute the ball trajectory (the trajectory is "how the ball will move
     * without any obstacles" -- its a line starting at current location, and
     * ending where the velocity will take the ball if no collisions will occur).
     * <p>
     * 2) Check (using the game environment) if moving on this trajectory will hit anything.
     * <p>
     * 2.1) If no, then move the ball to the end of the trajectory.
     * <p>
     * 2.2) Otherwise (there is a hit):
     * 2.2.2) move the ball to "almost" the hit point, but just slightly before it.
     * 2.2.3) notify the hit object (using its hit() method) that a collision occurred.
     * 2.2.4) update the velocity to the new velocity returned by the hit() method.
     */
    public void moveOneStep() {
        Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center));
        CollisionInfo c1 = this.gameEnvironment.getClosestCollision(trajectory);
        // check if there is collision that is going to happened.
        if (c1 == null) {
            // if not keep in the ball trajectory
            this.center = trajectory.end();
        } else {
            // for the case when the ball come from down and the ball stack
            if (c1.collisionPoint().getY() == 560 && this.vel.getVelocityDy() < 0) {
                this.center = trajectory.end();
                return;
            }
            // if yes move the ball to "almost" the hit point and set the velocity to a new one.
            Point almostHit = new Point(c1.collisionPoint().getX() - this.getVelocity().getVelocityDx() / this.radius,
                    c1.collisionPoint().getY() - this.getVelocity().getVelocityDy() / this.radius);
            this.center = almostHit;
            setVelocity(c1.collisionObject().hit(this, c1.collisionPoint(), this.getVelocity()));
        }
    }

    /**
     * notify the sprite that time has passed and the ball should to moveOneStep.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * adding the ball to the list of Sprites in the gameLevel.
     * @param gameLevel the gameLevel object.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * removing the ball from the list of Sprites in the gameLevel.
     * @param gameLevel the gameLevel object.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}
