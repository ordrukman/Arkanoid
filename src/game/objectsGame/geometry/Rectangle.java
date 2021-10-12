// ID: 20909000

package game.objectsGame.geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Or Drukman
 * class Rectangle: A Rectangle has edges points, width and height.
 */
public class Rectangle {
    // fields:
    private Point upperLeft;
    private Point upperRight;
    private Point lowerLeft;
    private Point lowerRight;
    private double width;
    private double height;

    // constructor:
    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft - the upperLeft point of the rectangle.
     * @param width - the width of the rectangle
     * @param height - the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        this.lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        this.lowerRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);

    }

    /**
     * creating the lines edges of the rectangle and check the intersection of those lines with the parameter line.
     * @param line the line that need to check if the line and the current rectangle has cut point.
     * @return Returns the intersection list of points of the intersections points,
     *         if there is no intersection the program returns empty list.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // create the lines of the rectangle
        Line topLine = new Line(upperLeft, upperRight);
        Line lowLine = new Line(lowerLeft, lowerRight);
        Line leftLine = new Line(upperLeft, lowerLeft);
        Line rightLine = new Line(upperRight, lowerRight);
        List listOfIntersections = new ArrayList<Point>();
        if (topLine.intersectionWith(line) != null) {
            listOfIntersections.add(topLine.intersectionWith(line));
        }
        if (lowLine.intersectionWith(line) != null) {
            listOfIntersections.add(lowLine.intersectionWith(line));
        }
        if (leftLine.intersectionWith(line) != null) {
            listOfIntersections.add(leftLine.intersectionWith(line));
        }
        if (rightLine.intersectionWith(line) != null) {
            listOfIntersections.add(rightLine.intersectionWith(line));
        }
        return listOfIntersections;
    }

    /**
     * @return return the Width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return return the Height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return Returns the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return Returns the upper-right point of the rectangle.
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * @return Returns the lower-left point of the rectangle.
     */
    public Point getLowerLeft() {
        return this.lowerLeft;
    }

    /**
     * @return Returns the lower-right point of the rectangle.
     */
    public Point getLowerRight() {
        return this.lowerRight;
    }

    /* // FOR CHECK THE FUNCTION <intersectionPoints>
    public static void main(String[] args) {
        Point upperLeft = new Point(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        double width = Integer.parseInt(args[2]);
        double height = Integer.parseInt(args[3]);
        Rectangle r1 = new Rectangle(upperLeft, width, height);
        Line l1 = new Line(Integer.parseInt(args[4]), Integer.parseInt(args[5]),
                    Integer.parseInt(args[6]), Integer.parseInt(args[7]));
        List<Point> listOfIntersection = r1.intersectionPoints(l1);
        for (Point p: listOfIntersection) {
            System.out.println(p.getX()+ " , " +p.getY());
        }
        // FOR CHECK THE FUNCTION <closestIntersectionToStartOfLine>
        Point closestPoint = l1.closestIntersectionToStartOfLine(r1);
        System.out.println(closestPoint.getX()+ " , " +closestPoint.getY());
    }
    */
}
