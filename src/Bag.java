import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class Bag {
    private int maxSize;
    private int curSize;
    private ArrayList<Grocery> inBag;
//    private BitSet parameters;

    /**
     *
     * @param maxSize
     */
    public Bag(int maxSize) {
        this.maxSize = maxSize;
        this.curSize = 0;
        this.inBag = new ArrayList<>();
//        this.parameters = new BitSet();
    }

    /**
     *
     * @param grocery
     */
    public boolean addToBag(Grocery grocery) {
        if(canAdd(grocery) && (curSize + grocery.getSize() <= maxSize)){
            inBag.add(grocery);
            curSize += grocery.getSize();
            return true;
        } else
            return false;
           // grocery.toggleBagged();
        // TODO: add the bitset!
    }

    // TODO: I think this will be deprecated
    /**
     *
     * @param grocery
     */
    public void takeFromBag(Grocery grocery) {
        if (inBag.remove(grocery)){}
    }

    /**
     *
     * @return
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     *
     * @return
     */
    public int getCurSize() {
        return curSize;
    }

    /**
     *
     * @return
     */
    public ArrayList<Grocery> getInBag() {
        return inBag;
    }

    /**
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
     *
     * @return
     */
    public String toString() {
        StringBuilder contents;
        contents = new StringBuilder();

        // TODO: build the string containing the contents of the bag
        for (Grocery item : inBag) {
            contents.append(item.getName() + " ");
        }

        return contents.toString();
    }
}
