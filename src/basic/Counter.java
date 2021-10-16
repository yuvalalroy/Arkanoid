// ID: 315789461

package basic;

/**
 * class Counter.
 * used for counting things.
 *
 * @author Yuval Alroy.
 */
public class Counter {
    private int count;


    /**
     * Constructor.
     */
    public Counter() {
        this.count = 0;
    }


    /**
     * Add number to current count.
     *
     * @param number represents a number.
     */
    public void increase(int number) {
        this.count += number;
    }


    /**
     * Subtract number from current count.
     *
     * @param number represents a number.
     */
    public void decrease(int number) {
        this.count -= number;
    }


    /**
     * @return current count.
     */
    public int getValue() {
        return this.count;
    }
}