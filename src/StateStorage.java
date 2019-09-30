import java.util.Collection;

public interface StateStorage {

    /**
     * returns to the user the next grocery item from StateStorage
     *
     * @return next solution Item
     */
    Solution getNext();

    /**
     * places a new solution item into StateStorage
     * @param solution
     */
    void put(Solution solution);

    /**
     * tells user whether or not there is a next item in the list.
     *
     * @return true or false
     */
    boolean isEmpty();

    boolean isBFS();
}
