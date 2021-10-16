// ID: 315789461

package basic;

/**
 * Point class:
 * A point has an x and a y value, and can measure the distance to other points,
 * if it is equal to another point, and if it is between two other points.
 *
 * @author Yuval Alroy
 */
public class Point {
    private double x;
    private double y;


    /**
     * constructor.
     *
     * @param x represents the x value of the point.
     * @param y represents the y value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }


    /**
     * calculate the distance between two points.
     *
     * @param other represents another point.
     * @return the distance of this point to the other point.
     */
    public double distance(Point other) {
        double xDiff = Math.abs(this.x - other.x);
        double yDiff = Math.abs(this.y - other.y);
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }


    /**
     * check if the points are exactly the same point.
     *
     * @param other represents another point.
     * @return 'true' if the points are equals, 'false' otherwise.
     */
    public boolean equals(Point other) {
        double epsilon = Math.pow(10, -2);
        return ((this.y <= other.y + epsilon) && (this.y >= other.y - epsilon)
                && (this.x <= other.x + epsilon) && (this.x >= other.x - epsilon));
    }


    /**
     * check if this point is between two other points (x and y values).
     *
     * @param a is a point.
     * @param b is another point.
     * @return 'true' if this point is between the points a and b, 'false' otherwise.
     */
    public boolean betweenPoints(Point a, Point b) {
        double maxX = Math.max(a.x, b.x);
        double minX = Math.min(a.x, b.x);
        double maxY = Math.max(a.y, b.y);
        double minY = Math.min(a.y, b.y);
        return (this.x <= maxX) && (this.x >= minX) && (this.y <= maxY) && (this.y >= minY);
    }


    /**
     * check if this point is inside a rectangle.
     *
     * @param rectangle represents a rectangle
     * @return true if this point is inside the rectangle, false otherwise.
     */
    public boolean pointInRectangle(Rectangle rectangle) {
        return this.betweenPoints(rectangle.getUpperLeft(), rectangle.getLowerRight());
    }


    /**
     * check if this point is on the line.
     *
     * @param line represents a line.
     * @return true if this point is on the line, false otherwise.
     */
    public boolean isOnLine(Line line) {
        return line.isIntersecting(new Line(this, this));
    }


    /**
     * @return the x value of this point.
     */
    public double getX() {
        return this.x;
    }


    /**
     * @return the y value of this point.
     */
    public double getY() {
        return this.y;
    }
}
