// ID: 20909000

package game.listener;

/**
 * @author Or Drukman
 * class Counter: A Counter is keeping track of the number of remained blocks.
 */
public class Counter {
    private int numberOfBlock;

    /**
     * contractor of the Counter object.
     * @param numberOfBlock starting value of the counter.
     */
    public Counter(int numberOfBlock) {
        this.numberOfBlock = numberOfBlock;
    }

    /**
     * contractor of the Counter object, update the counter to 0.
     */
    public Counter() {
        this.numberOfBlock = 0;
    }

    /**
     * add number to current count.
     * @param number - the value to add for the counter.
     */
    public void increase(int number) {
        this.numberOfBlock = this.numberOfBlock + number;
    }

    /**
     * subtract number from current count.
     * @param number - the value to subtract from the counter.
     */
    public void decrease(int number) {
        this.numberOfBlock = this.numberOfBlock - number;
    }

    /**
     * get the current count.
     * @return the counter value.
     */
    public int getValue() {
        return this.numberOfBlock;
    }
}
