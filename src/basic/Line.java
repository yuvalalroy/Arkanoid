// ID: 315789461

package basic;

import java.util.Arrays;
import java.util.List;

/**
 * Line class:
 * A line (actually a line-segment) connects two points - a start point and an end point.
 * Lines have lengths, and may intersect with other lines.
 * It can also tell if it is the same as another line segment, and what is the line equation.
 *
 * @author Yuval Alroy
 */
public class Line {
    private Point start;
    private Point end;


    /**
     * first constructor.
     *
     * @param start represents the start point of the line
     * @param end   represents the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }


    /**
     * second constructor.
     *
     * @param x1 represents the x value of the start point.
     * @param y1 represents the y value of the start point.
     * @param x2 represents the x value of the end point.
     * @param y2 represents the y value of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }


    /**
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }


    /**
     * @return the middle point of the line.
     */
    public Point middle() {
        double midX = (this.start.getX() + this.end.getX()) / 2;
        double midY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(midX, midY);
    }


    /**
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }


    /**
     * @return the end point of the line.
     */
    public Point end() {
        return this.end;
    }


    /**
     * equation of a line is represented by: (1) y = mx + b or (2) x = c.
     * the function creates a double array for the line equation:
     * in case(1): array of size 2- index 0 represents m, index 1 represents b.
     * in case(2): array of size 1- represents c.
     *
     * @return lineEq - array for the line equation.
     */
    private double[] equation() {
        if (this.start.getX() == this.end.getX()) {
            return new double[]{this.start.getX()};
        }
        double m = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        double b = (-m) * this.start.getX() + this.start.getY();
        return new double[]{m, b};
    }


    /**
     * check if the lines have more than one point of intersection.
     *
     * @param other represents another line.
     * @return 'true' if the lines have more than one point of intersection, 'false' otherwise.
     */
    private boolean isCoincident(Line other) {

        // if one of the lines is actually only a point
        if (this.length() == 0 || other.length() == 0) {
            return false;
        }
        if (this.equals(other)) {
            return true;
        }

        // if there is only one point of intersection
        if (this.start.equals(other.end) || other.start.equals(this.end)) {
            return false;
        }

        // if there is more than one point of intersection
        if (Arrays.equals(this.equation(), other.equation())) {
            return this.start.betweenPoints(other.start, other.end)
                    || other.start.betweenPoints(this.start, this.end);
        }
        return false;
    }


    /**
     * in this method we assume that the lines are not coincident (at most one intersection point).
     * checks if there is a potential intersection point using the lines equations.
     *
     * @param other represents another line.
     * @return the potential intersection point, and 'null' if there is no such point.
     */
    private Point optionalIntersection(Line other) {
        double[] eq1 = this.equation();
        double[] eq2 = other.equation();

        /*
         * if the equations are equal, check if one of the lines is actually a point.
         * if none of them is a point, there is only one point of intersection (start\end).
         */
        if (Arrays.equals(eq1, eq2)) {
            if (this.length() == 0) {
                return this.start;
            }
            if (other.length() == 0) {
                return other.start;
            }
            return this.start.equals(other.end) ? this.start : this.end;
        }

        // if both lines are in the form of x = c
        if (eq1.length == 1 && eq2.length == 1) {
            return null;
        }

        // if only one line is in the form of x = c
        if (eq1.length == 1) {
            return new Point(eq1[0], eq2[0] * eq1[0] + eq2[1]);
        }
        if (eq2.length == 1) {
            return new Point(eq2[0], eq1[0] * eq2[0] + eq1[1]);
        }

        /*
         * if both lines are in the form of y = mx + b
         * remainder: index 0 represents m, index 1 represents b.
         */
        double x = (eq2[1] - eq1[1]) / (eq1[0] - eq2[0]);
        double y = eq1[0] * x + eq1[1];
        return new Point(x, y);
    }


    /**
     * check if the lines intersect.
     * check for an optional intersection, and if there is - check if it's on both segments.
     *
     * @param other represents another line.
     * @return 'true' if the lines intersect, 'false' otherwise.
     */
    public boolean isIntersecting(Line other) {
        if (this.isCoincident(other)) {
            return false;
        }
        Point p = this.optionalIntersection(other);
        if (p != null) {
            return p.betweenPoints(this.start, this.end) && p.betweenPoints(other.start, other.end);
        }
        return false;
    }


    /**
     * check if the lines intersects. if so - find the intersection point.
     *
     * @param other represents another line.
     * @return the intersection point if the lines intersect, null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (!this.isIntersecting(other)) {
            return null;
        }
        return this.optionalIntersection(other);
    }


    /**
     * check if the lines are exactly the same.
     *
     * @param other represents another line.
     * @return 'true' if the lines are equals, 'false' otherwise.
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start));
    }


    /**
     * check which of the intersection points of this line with a rectangle is the closest
     * to the starting point of this line.
     *
     * @param rect represents the rectangle.
     * @return the closest intersection point to the start of the line.
     * Otherwise, if this line does not intersect with the rectangle, return null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersections = rect.intersectionPoints(this);
        if (intersections.isEmpty()) {
            return null;
        }

        // default value for the minimal distance.
        double minDistance = Integer.MAX_VALUE;
        Point closest = null;
        for (Point point : intersections) {
            if (point.distance(this.start) < minDistance) {
                minDistance = point.distance(this.start);
                closest = point;
            }
        }
        return closest;
    }
}
