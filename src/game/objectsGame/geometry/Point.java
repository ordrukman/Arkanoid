// ID: 209090000

package game.objectsGame.geometry;

/**
 * @author Or Drukman
 * class Point: A point has an x and a y value,
 * and can measure the distance to other points, and if it is equal to another point.
 */
public class Point {
    // fields
    private double x;
    private double y;

    // constructor:
    /**
     * @param x - the x value of the point.
     * @param y - the y value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other - the other point for measuring the distance between the two points.
     * @return return the distance of this point to the other point.
     */
    public double distance(Point other) {
        return Math.sqrt(((this.x - other.getX()) * (this.x - other.getX()))
                + ((this.y - other.getY()) * (this.y - other.getY())));
    }

    /**
     * @param other - the other point for check if the two points are identical.
     * @return return true if the points are equal.
     *         return false if the points aren't equal.
     */
    public boolean equals(Point other) {
        double epsilon = Math.pow(10, -2);
        if ((Math.abs(this.x - other.getX()) < epsilon) && (Math.abs(this.y - other.getY()) < epsilon)) {
            // this checks if a-b is closer to 0 than epsilon, if true, then a probably equals b
            return true;
        }
        return false;
    }

    /**
     * @return return the x value of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return return the y value of the point.
     */
    public double getY() {
        return this.y;
    }
}
