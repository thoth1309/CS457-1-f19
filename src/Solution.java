import java.util.ArrayList;

public class Solution {
    private ArrayList<Grocery> groceries;
    private ArrayList<Bag> bags;
    private int depthCounter;

    /**
     * Primary constructor for solution. Takes a list of groceries, and a
     * list of bags, and stores them with the current depth of the solution.
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
     * Alternate constructor used to create a new solution from an existing solution
     * @param solution
     */
    public Solution(Solution solution) {
        this(solution.getGroceries(), solution.getBags());
    }

    /**
     * Alternate constructor used to create a new, empty solution with no items
     * and no bags.
     *
     */
    public Solution() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Sets the list of groceries to the provided list of groceries
     *
     * @param groceries
     */
    public void setGroceries(ArrayList<Grocery> groceries) {
        this.groceries = groceries;
    }

    /**
     * returns the groceries in the solution
     *
     * @return
     */
    public ArrayList<Grocery> getGroceries() { return this.groceries; }

    /**
     * Sets the bags in this solution to the provided bags
     *
     * @param bags
     */
    public void setBags(ArrayList<Bag> bags) { this.bags = bags; }

    /**
     * retrieves the bags from this solution
     *
     * @return
     */
    public ArrayList<Bag> getBags() { return this.bags; }

    /**
     * increases the depth counter by 1
     */
    public void increaseDepth() {
        depthCounter++;
    }

    /**
     * sets the depth counter of this node, in the event that it is from a copied node
     *
     * @param depthCounter
     */
    private void setDepthCounter(int depthCounter) { this.depthCounter = depthCounter; }

    /**
     * returns the depth of this node
     *
     * @return
     */
    public int getDepth() {
        return depthCounter;
    }

    /**
     * returns whether or not this solution is successful
     *
     * @return
     */
    public boolean getSuccess() { return groceries.isEmpty(); }

    /**
     * returns the contents of the bags in this solution, and if this solution
     * is a goal solution, success is also returned
     *
     * @return
     */
    public String toString() {
        StringBuilder printString = new StringBuilder();
        if (getSuccess()) {
            printString.append("success\n");
        }
        for (Bag bag : bags)
                printString.append(bag.toString() + "\n");

        return printString.toString();
    }

    /**
     * makes a complete copy of this node and returns it, so that the new
     * node can be modified without affecting this node.
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
