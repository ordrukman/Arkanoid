// ID: 209090000

package game.objectsGame.geometry;

import java.util.List;

/**
 * @author Or Drukman
 * class Line: A line connects two points a start point and an end point.
 * Lines have lengths, and may intersect with other lines.
 * It can also tell if it is the same as another line segment.
 */
public class Line {
    // fields
    private Point start;
    private Point end;

    // constructors:
    /**
     * @param start - the start point of the line.
     * @param end   - the end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    /**
     * @param x1 - the x value of the start point.
     * @param y1 - the y value of the start point.
     * @param x2 - the x value of the end point.
     * @param y2 - the y value of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return Return the length of the line.
     * checking the distance between the start and the end points.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * @return Returns the middle point of the line.
     * calculate the sum of the x segment and divide by 2, do the same for the y segment.
     */
    public Point middle() {
        Point middle = new Point((this.start.getX() + this.end.getX()) / 2,
                (this.start.getY() + this.end.getY()) / 2);
        return middle;
    }

    /**
     * @return Returns the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return Returns the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * @return Returns the slope of the line.
     * calculate the distance of the y segment and divide it by the distance of the x segment.
     */
    private double slopeLine() {
        return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
    }

    /**
     * @return true if the line is vertical.
     *         false if the line isn't vertical.
     */
    private boolean isVertical() {
        if (this.start.getX() == this.end.getX()) {
            return true;
        }
        return false;
    }

    /**
     * @return return the b value of -Equation of a line- y = mx + b << this value
     */
    private double b() {
        double mx = this.slopeLine() * this.start.getX();
        return this.start.getY() - mx;

    }

    /**
     * @param other the other line that need to check if the other line and the current line has cut point.
     * @return true if both lines has cut point in the overall scale of the lines
     *         (not between the start and the end points)
     *         false if both lines are parallels.
     */
    private boolean hasCut(Line other) {
        // if they both vertical they are parallels
        if (this.isVertical() && other.isVertical()) {
            return false;
        }
        if (!this.isVertical() && !other.isVertical()) {
            // if they both has the same slope they are parallels
            if (this.slopeLine() == other.slopeLine()) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param other the other line that need to check the cut point with the current line.
     * @return Point of the intersection of the other line and the current line, in the overall scale of the lines
     *         (not between the start and the end points)
     *         this function assume that - hasCut is true.
     */
    private Point calculateCut(Line other) {
        // if both lines aren't vertical, I can calculate their slope.
        if (!this.isVertical() && !other.isVertical()) {
            // doing algebraic transitions to find out the x and y segments of the cut point.
            double slopeDif = this.slopeLine() - other.slopeLine();
            double bDif = other.b() - this.b();
            double xCut = bDif / slopeDif;
            double yCut = this.slopeLine() * xCut + this.b();
            Point cut = new Point(xCut, yCut);
            return cut;
        }
        // if the current line is vertical:
        if (this.isVertical() && !other.isVertical()) {
            // doing algebraic transitions to find out the x and y segments of the cut point.
            double yCut = other.slopeLine() * this.start.getX() + other.b();
            Point cut = new Point(this.start.getX(), yCut);
            return cut;
        }
        // if the outer line is vertical:
        // doing algebraic transitions to find out the x and y segments of the cut point.
        double yCut = this.slopeLine() * other.start.getX() + this.b();
        Point cut = new Point(other.start.getX(), yCut);
        return cut;
    }

    /**
     * @param p1 point the check.
     * @return Returns true if the point is in the line, false otherwise.
     */
    private boolean pointInLine(Point p1) {
        if ((p1.getX() >= this.start.getX() && p1.getX() <= this.end.getX())
                || (p1.getX() <= this.start.getX() && p1.getX() >= this.end.getX())) {
            if ((p1.getY() >= this.start.getY() && p1.getY() <= this.end.getY())
                    || (p1.getY() <= this.start.getY() && p1.getY() >= this.end.getY())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param other the other line that need to check if the other line and the current line has cut point.
     *              checking if the cut point is in the start and end points of both lines.
     * @return Returns true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        // for case of one line or more is point
        if (checkIfLineIsPoint(other) != null) {
            return true;
        }
        if (this.hasCut(other)) {
            Point cut = this.calculateCut(other);
            if (this.pointInLine(cut) && other.pointInLine(cut)) {
                return true;
            }
        }
        // if both lines are vertical or both with the same slope.
        if (this.isVertical() && other.isVertical()) {
            if (this.start.getX() == other.start.getX()) {
                // both vertical with the same x index.
                if (this.checkIfLinesMeetWithTheSameSlope(other) != null) {
                    return true;
                }
            }
        } else if (this.slopeLine() == other.slopeLine()) {
            if (this.checkIfLinesMeetWithTheSameSlope(other) != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param other the other line that need to check if the other line and the current line has cut point.
     *              both lines has the same slope so check if they has one meet point.
     * @return Returns the intersection point if the lines intersect,
     *         and null otherwise.
     */
    public Point checkIfLinesMeetWithTheSameSlope(Line other) {
        // calculate the length
        double sumDis = this.end.distance(this.start) + other.end.distance(other.start);
        // check if there is only one point both lines meet.
        if (this.start.equals(other.start)) {
            if (this.end.distance(other.end) == sumDis) {
                return this.start;
            }
        } else if (this.start.equals(other.end)) {
            if (this.end.distance(other.start) == sumDis) {
                return this.start;
            }
        } else if (this.end.equals(other.start)) {
            if (this.start.distance(other.end) == sumDis) {
                return this.end;
            }
        } else if (this.end.equals(other.end)) {
            if (this.start.distance(other.start) == sumDis) {
                return this.end;
            }
        }
        return null;
    }

    /**
     * @param other the other line that need to check if the other line or the current line is point.
     * @return Returns the intersection point if one of the lines is point and the point is in the other line,
     *         and null otherwise.
     */
    public Point checkIfLineIsPoint(Line other) {
        if (this.start.equals(this.end)) {
            if (other.start.equals(other.end)) {
                return this.start;
            } else {
                if (other.pointInLine(this.start())) {
                    return this.start;
                }
            }
        } else {
            if (other.start.equals(other.end)) {
                if (this.pointInLine(other.start())) {
                    return other.start;
                }
            }
        }
        return null;
    }

    /**
     * @param other the other line that need to check if the other line and the current line has cut point.
     *              checking if the cut point is in the start and end points of both lines. If so return that point.
     * @return Returns the intersection point if the lines intersect,
     *         and null otherwise.
     */
    public Point intersectionWith(Line other) {
        // for case of one line or more is point
        Point lineIsPoint = checkIfLineIsPoint(other);
        if (lineIsPoint != null) {
            return lineIsPoint;
        }
        if (this.isIntersecting(other)) {
            // if both lines are vertical or both with the same slope.
            if (this.isVertical() && other.isVertical()) {
                // both vertical with the same x index.
                if (this.start.getX() == other.start.getX()) {
                    // check if there is only one point both lines meet.
                    if (this.checkIfLinesMeetWithTheSameSlope(other) != null) {
                        return this.checkIfLinesMeetWithTheSameSlope(other);
                    }
                }
            } else if (this.slopeLine() == other.slopeLine()) {
                // check if there is only one point both lines meet.
                if (this.checkIfLinesMeetWithTheSameSlope(other) != null) {
                    return this.checkIfLinesMeetWithTheSameSlope(other);
                }
            }
            // both lines with different slopes
            return this.calculateCut(other);
        }
        return null;
    }

    /**
     * @param other - the other line for check if the two lines are identical.
     *              checking their start and end points.
     * @return return true if the lines are equal.
     *         return false if the lines aren't equal.
     */
    public boolean equals(Line other) {
        if ((this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start))) {
            return true;
        }
        return false;
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     *     Otherwise, return the closest intersection point to the start of the line.
     * @param rect the Rectangle that need to check if the current line and the rectangle has cut points.
     * @return Returns the closest intersection point of the start of the line and the rectangle,
     *         if there is no intersection the program returns null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> listOfIntersection = rect.intersectionPoints(this);
        if (listOfIntersection.isEmpty()) {
            return null;
        }
        Point closestPoint = listOfIntersection.get(0);
        for (Point p: listOfIntersection) {
            if (p.distance(this.start) < closestPoint.distance(this.start)) {
                closestPoint = p;
            }
        }
        return closestPoint;
    }
}