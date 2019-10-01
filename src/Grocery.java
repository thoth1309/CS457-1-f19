import java.util.HashMap;

public class Grocery {
    private String name;
    private int size;
    private boolean bagged;
    private HashMap<String, Grocery> pairings;
    private boolean canPack;    // false means any appearing items CANNOT be packed with this item

    /**
     * Primary constructor for grocery items.
     *
     * @param name - the unique name of the item
     * @param size - the size of the item
     */
    public Grocery(String name, int size) {
        this.name = name;
        this.size = size;
        this.pairings = new HashMap<>();
        this.bagged = false;
        this.canPack = false;
    }

    /**
     * Alternate constructor for adding items from constraints.
     * Requires only the items name, and sets the items weight to -1,
     * expecting an actual value to be added later
     *
     * @param name - the unique name of the item
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
     * Adds a paired item to the pairings set. If canPack is set, these are the
     * ONLY items the grocery can be paired with. If canPack is not set, these
     * are the only items that this grocery cannot be paired with.
     *
     * @param pairings
     */
    public void addPairing(Grocery pairings){
        this.pairings.put(pairings.toString(), pairings);
    }

    /**
     * Sets the size of the item, if it wasn't set in the constructor
     *
     * @param size
     */
    public void setItemSize(int size) {
        if(this.size == -1)
            this.size = size;
    }

    /**
     * retrieves the size of the item
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * returns the name of the item as a string
     *
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     * Returns an boolean value indicating whether or not the potential
     * item can be packed with this item
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

    /**
     * Returns the name of the item. This may change in the future.
     *
     * @return
     */
    public String toString() {
        return name;
    }
}