import java.util.ArrayList;

public class Bag {
    private int maxSize;
    private int curSize;
    private ArrayList<Grocery> inBag;

    /**
     * Constructor for creating bags
     *
     * @param maxSize
     */
    public Bag(int maxSize) {
        this.maxSize = maxSize;
        this.curSize = 0;
        this.inBag = new ArrayList<>();
    }

    /**
     * Checks to make sure the item can be added, and if it can, the item is added.
     * Returns success or failure
     *
     * @param grocery
     */
    public boolean addToBag(Grocery grocery) {
        if(canAdd(grocery) && ((curSize + grocery.getSize()) <= maxSize)){
            inBag.add(grocery);
            curSize += grocery.getSize();
            return true;
        } else
            return false;
    }

    /**
     * returns the maximum size the bag can store
     *
     * @return
     */
    public int getMaxSize() {
        return maxSize;
    }

   /**
     * Returns an arraylist containing the items in the bag
    *
     * @return
     */
    public ArrayList<Grocery> getInBag() {
        return inBag;
    }

    /**
     * private function to determine whether or not an item can
     * be added to a bag. Checks all items in the bag to make sure
     * they don't have restrictions against it, and then checks for
     * a restriction of the current item against the item in the bag
     *
     * @param grocery
     * @return
     */
    private boolean canAdd(Grocery grocery) {
        boolean retVal = true;

        for (Grocery item: inBag) {
            if(!item.validPairing(grocery.getName()) || !grocery.validPairing(item.getName())) {
                retVal = false;
                break;
            }
        }

        return retVal;
    }

    /**
     * Returns a string containing all of the items in the bag
     *
     * @return
     */
    public String toString() {
        StringBuilder contents;
        contents = new StringBuilder();

           for (Grocery item : inBag) {
            contents.append(item.getName() + " ");
        }

        return contents.toString();
    }
}