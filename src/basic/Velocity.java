// ID: 315789461

package basic;

/**
 * Velocity class:
 * Velocity specifies the change in position on the 'x' and the 'y' axes.
 * We can also specify the velocity in terms and angle and a speed.
 *
 * @author Yuval Alroy
 */
public class Velocity {
    private double dx;
    private double dy;


    /**
     * constructor.
     *
     * @param dx represents the horizontal direction (movement in the x-axis)
     * @param dy represents the vertical direction (movement in the y-axis)
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }


    /**
     * changes the dx field of this velocity to the given velocity.
     *
     * @param velocity represents the new velocity
     */
    public void setVelocity(Velocity velocity) {
        this.dx = velocity.getDx();
        this.dy = velocity.getDy();
    }


    /**
     * @return this velocity's dx value.
     */
    public double getDx() {
        return dx;
    }


    /**
     * @return this velocity's dy value.
     */
    public double getDy() {
        return dy;
    }


    /**
     * changes the dx field of this velocity to the given dx.
     *
     * @param newDx represents the movement in the x-axis
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }


    /**
     * changes the dy field of this velocity to the given dy.
     *
     * @param newDy represents the movement in the y-axis
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }


    /**
     * Take a point with position (x,y) and return a new point after the movement in the x&y-axis.
     *
     * @param p represents the initial position.
     * @return the new position after the movement.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }


    /**
     * this method specifies the velocity in terms and angle and a speed.
     * assuming up is angle 0 (therefore we subtract 90 degrees from it).
     *
     * @param angle represents the direction in degrees.
     * @param speed represents the number of units in the angle direction.
     * @return a velocity according to the angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        final int diff = 90;
        angle -= diff;

        // calculates the dx and dy according to the angle and speed using trigonometric functions.
        double dx = speed * Math.cos(Math.toRadians(angle));
        double dy = speed * Math.sin(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }
}
