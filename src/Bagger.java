import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class Bagger {
    private StateStorage stateStorage;
    private boolean success;
    private boolean print;
//    private Solution solution;

    // TODO: take in your data, and create a Solution with the data

    /**
     *
     * @param storage
     * @param
     * @param
     */
    public Bagger(StateStorage storage) {//, ArrayList<Grocery> groceries, ArrayList<Bag> bags){
        this.stateStorage = storage;
        this.success = false;
        print = true;
//        solution = new Solution(groceries, bags);
    }

    /**
     * Public facing bagGroceries method... sends the solution to the correct method
     * @param solution
     * @return
     */
    public boolean bagGroceries(Solution solution) {
        if (stateStorage.isBFS())
            return bagGroceriesBFS(solution);
        else
            return bagGroceriesDFS(solution);
    }

    /**
     * Take a possible solution to the item bagging problem, and run it through
     * the user's chosen method of StateStorage to determine whether or not there
     * is a solution. If there is a solution, print it to the screen and return
     * success. Otherwise, print nothing and send failure.
     *
     * @param solution
     * @return success
     */
    public boolean bagGroceriesDFS(Solution solution){

        // Copy the state and prepare for the next solution
        Solution tmpSolution = solution;
        Solution newSolution;

        // Check for success. If we are successful, print the bag configuration
        // and return;
        success = tmpSolution.getSuccess();
        if (success) {
            if (print) {
                System.out.println(tmpSolution.toString());
                print = false;
            }
            return success;
        }

        // TODO: need to make sure these are COPIES, and not the original
        // TODO: REQUIRES FURTHER TESTING
        // Get the bags and groceries from the state
        ArrayList<Bag> tmpBags = solution.getBags();
        ArrayList<Grocery> tmpGroceries = solution.getGroceries();
        Grocery tmpGrocery = tmpGroceries.remove(tmpGroceries.size()-1);

        // try to bag the grocery. If it goes into a bag, we explore that bag until it doesn't work.
        // As long as success is false, we'll keep searching new bags.
        for ( Bag bag: tmpBags ) {
            if (bag.addToBag(tmpGrocery)) {
                // TODO: need to make sure this is a copy!!!
                // TODO: REQUIRES FURTHER TESTING. MAY NOT BE NECESSARY
                newSolution = new Solution();    // make new solution identical to old solution
                newSolution.setGroceries(tmpGroceries);     // change items
                newSolution.setBags(tmpBags);               // change bags
                newSolution.increaseDepth();                // increase depth
                stateStorage.put(newSolution);              // send it to the stack

                if (!stateStorage.isEmpty())
                    success = bagGroceriesDFS(stateStorage.getNext());
            }
        }

        // If we couldn't add it to any of the bags, put the grocery back so it's there when we return false
        if (!success)
            tmpGroceries.add(tmpGrocery);

        return success;
    }

    private boolean bagGroceriesBFS(Solution solution) {
        // put the seed solution into stateStorage, and declare some variables to be used later
        stateStorage.put(solution);
        Solution curSolution;
        ArrayList<Bag> curBags;

        // While we still have a state in stateStorage, try to find solutions
        while (!stateStorage.isEmpty() && !success) {
            curSolution = stateStorage.getNext();   // get the next solution from storage
            curBags = curSolution.getBags();    // grab its bags

            // see if we're successful, if so print the successful states and end
            success = curSolution.getSuccess();
            if (success) {
                int depth = curSolution.getDepth();
                while(curSolution.getDepth() == depth && !stateStorage.isEmpty()) {
                    if (curSolution.getSuccess())
                        System.out.println(curSolution.toString());
                    curSolution = stateStorage.getNext();
                }
                break;
            }

            // make a copy of curSolution and its objects
            Solution nextSolution = curSolution.copy();
            ArrayList<Grocery> nextGroceries = nextSolution.getGroceries();
            ArrayList<Bag> nextBags = nextSolution.getBags();
            Grocery nextGrocery;

            // for the current state, try to bag the next item. If successful,
            // save the state in stateStorage.
            for (int i = 0; i < curBags.size(); i++) {
                nextGrocery = nextGroceries.remove(nextGroceries.size()-1);
                if(nextBags.get(i).addToBag(nextGrocery)) {
                    nextSolution.increaseDepth();
                    stateStorage.put(nextSolution);
                }

                // make a fresh copy of curSolution to test the next bagging configuration
                nextSolution = curSolution.copy();
                nextGroceries = nextSolution.getGroceries();
                nextBags = nextSolution.getBags();
            }
        }

        return success;
    }

    /**
     * returns success for failure, and if success, it returns the successful
     * configuration of the bags in a string format.
     *
     * @return
     */
    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append("Why do you need this?\n\n\n\n");
        retString.append("...\n\n\n\n");
        retString.append("Okay. Fine. Contact me. I'll make this print whatever you need.\n");

        return retString.toString();
    }
}
