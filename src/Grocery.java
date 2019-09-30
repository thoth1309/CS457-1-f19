import java.math.BigInteger;
import java.util.BitSet;
import java.util.HashMap;

/**
 *
 */
public class Grocery {
    private String name;
    private int size;
    private boolean bagged;
    private HashMap<String, Grocery> pairings;
    private boolean canPack;    // false means any appearing items CANNOT be packed with this item

    /**
     *
     * @param name
     * @param size
     */
    public Grocery(String name, int size) {
        this.name = name;
        this.size = size;
        this.pairings = new HashMap<>();
        this.bagged = false;
        this.canPack = false;
    }

    /**
     *
     * @param name
     */
    public Grocery(String name) {
        this(name, -1);
    }

    /**
     * If the items in the pairings list are the VALID pair items, this method
     * should be called. Otherwise, any items that appear in the pairings map
     * will be items that this item CANNOT be paired with
     */
    public void setCanPack() {
        this.canPack = true;
    }

    /**
     *
     * @return
     */
    public boolean getCanPack() {
        return canPack;
    }

    /**
     *
     * @param pairings
     */
    public void addPairing(Grocery pairings){
        this.pairings.put(pairings.toString(), pairings);
    }

    /**
     *
     * @param size
     */
    public void setItemSize(int size) {
        this.size = size;
    }

    // TODO: I think this is deprecated
    public void toggleBagged() {
        bagged = !bagged;
    }

    /**
     *
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     *
     * @param potential
     * @return
     */
    public boolean validPairing(String potential) {
        if(canPack)
            return pairings.containsKey(potential);
        else
            return !pairings.containsKey(potential);
    }

    // TODO: delete this deprecated method
    public HashMap<String, Grocery> getPairings() {
        return this.pairings;
    }

    // TODO: I think this is deprecated
    public boolean isBagged() {
        return bagged;
    }

    /**
     *
     * @return
     */
    public String toString() {
        return name;
    }
}