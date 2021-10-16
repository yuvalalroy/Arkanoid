// ID: 315789461

package basic;

import java.util.ArrayList;
import java.util.List;

/**
 * Rectangle class:
 * Rectangles have a point (which represent the upper left point of the rectangle), width and height.
 *
 * @author Yuval alroy
 */
public class Rectangle {
    private double width;
    private double height;
    private Point upperLeft;

    // the number of ribs of a rectangle.
    private static final int RIBS_NUM = 4;


    /**
     * Constructor.
     *
     * @param upperLeft represents the location of the upper left point of the rectangle.
     * @param width     represents the width of the rectangle.
     * @param height    represents the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }


    /**
     * calculates the Lines which are the ribs of the rectangle,
     * according to the points that form the rectangle.
     *
     * @return a list of the ribs of this rectangle.
     */
    public java.util.List<Line> getRibs() {
        List<Line> ribs = new ArrayList<>(RIBS_NUM);

        // calculates the Lines which are the ribs of the rectangle and adds them to the list
        ribs.add(new Line(this.upperLeft, this.getUpperRight()));
        ribs.add(new Line(this.getLowerLeft(), this.upperLeft));
        ribs.add(new Line(this.getLowerRight(), this.getLowerLeft()));
        ribs.add(new Line(this.getUpperRight(), this.getLowerRight()));
        return ribs;
    }


    /**
     * stores in a list all the intersection points of this rectangle with the line.
     *
     * @param line represents the line we want to check intersections with.
     * @return a (possibly empty) List of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersections = new ArrayList<>();
        List<Line> ribs = this.getRibs();
        for (Line rib : ribs) {
            Point interP = rib.intersectionWith(line);

            // if there is an intersection point, add it to the list
            if (interP != null) {
                intersections.add(interP);
            }
        }
        return intersections;
    }


    /**
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }


    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }


    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }


    /**
     * @return the upper-right point of the rectangle.
     */
    public Point getUpperRight() {
        return new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
    }


    /**
     * @return the lower-left point of the rectangle.
     */
    public Point getLowerLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
    }


    /**
     * @return the lower-right point of the rectangle.
     */
    public Point getLowerRight() {
        return new Point(this.getUpperRight().getX(), this.getUpperRight().getY() + this.height);
    }
}
