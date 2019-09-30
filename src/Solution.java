import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Solution {
    private ArrayList<Grocery> groceries;
    private ArrayList<Bag> bags;
    private int depthCounter;

    /**
     *
     * @param groceries
     * @param bags
     */
    public Solution(ArrayList<Grocery> groceries, ArrayList<Bag> bags) {
        this.groceries = groceries;
        this.bags = bags;
        this.depthCounter = 0;
    }

    /**
     *
     * @param solution
     */
    public Solution(Solution solution) {
        this(solution.getGroceries(), solution.getBags());
    }

    public Solution() {
        this(new ArrayList<Grocery>(), new ArrayList<Bag>());
    }

//    public void addGrocery(Grocery grocery) { this.groceries.add(grocery); }

    /**
     *
     * @param groceries
     */
    public void setGroceries(ArrayList<Grocery> groceries) {
        this.groceries = groceries;
    }

    /**
     *
     * @return
     */
    public ArrayList<Grocery> getGroceries() { return this.groceries; }

    /**
     *
     * @param bags
     */
    public void setBags(ArrayList<Bag> bags) { this.bags = bags; }

    /**
     *
     * @return
     */
    public ArrayList<Bag> getBags() { return this.bags; }

    /**
     *
     */
    public void increaseDepth() {
        depthCounter++;
    }

    private void setDepthCounter(int depthCounter) { this.depthCounter = depthCounter; }

    /**
     *
     * @return
     */
    public int getDepth() {
        return depthCounter;
    }

    /**
     *
     * @return
     */
    public boolean getSuccess() { return groceries.isEmpty(); }

    public String toString() {
        StringBuilder printString = new StringBuilder();
        // TODO: print success or failure
        if (getSuccess()) {
            printString.append("success\n");
        }
        // TODO: print out the items in the bags
        for (Bag bag : bags)
                printString.append(bag.toString() + "\n");

        return printString.toString();
    }

    /**
     *
     * @return
     */
    public Solution copy() {
        ArrayList<Grocery> copyGroceries = new ArrayList<>();
        ArrayList<Bag> copyBags = new ArrayList<>();

        for (Grocery grocery: this.groceries) {
            copyGroceries.add(grocery);
        }

        for (int i=0; i<this.bags.size(); i++) {
            Bag copyBag = new Bag(this.bags.get(i).getMaxSize());
//            copyBags.add(copyBag);
            ArrayList<Grocery> inBag = this.bags.get(i).getInBag();
            for (Grocery item: inBag) {
                if(!copyBag.addToBag(item))
                    System.out.println("BAD");
            }
            copyBags.add(i, copyBag);
        }

        Solution copy = new Solution(copyGroceries, copyBags);
        copy.setDepthCounter(this.depthCounter);

        return copy;
    }
}
