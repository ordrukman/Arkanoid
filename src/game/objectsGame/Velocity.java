// ID: 209090000

package game.objectsGame;

import game.objectsGame.geometry.Point;

/**
 * @author Or Drukman
 * class Velocity: Velocity specifies the change in position on the `x` and the `y` axes.
 */
// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    // fields:
    private double dx;
    private double dy;

    // constructor:
    /**
     * @param dx - the dx index.
     * @param dy - the dy index.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @param v - the ball velocity.
     * @return the speed of the velocity.
     */
    public static double fromVelToSpeed(Velocity v) {
        return Math.sqrt((v.dx * v.dx) + (v.dy * v.dy));
    }

    /**
     * @param angle - the angel of the ball velocity.
     * @param speed - the speed of the ball velocity.
     * @return new velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angelR = Math.toRadians(angle);
        double dx = Math.sin(angelR) * speed;
        double dy = -(Math.cos(angelR) * speed);
        return new Velocity(dx, dy);
    }

    /**
     * @return dx - the dx index.
     */
    public double getVelocityDx() {
        return this.dx;
    }
    /**
     * @return dx - the dx index.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return dy - the dy index.
     */
    public double getVelocityDy() {
        return this.dy;
    }
    /**
     * @return dy - the dy index.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * @param p - Take a point with position (x,y) and return a new point
     * @return with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        Point newPoint = new Point(this.dx + p.getX(), this.dy + p.getY());
        return newPoint;
    }
}