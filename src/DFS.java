import java.util.Stack;

public class DFS implements StateStorage {
    private Stack<Solution> stateStorage;
    private boolean isBFS;

    /**
     * creates a new DFS search container
     */
    public DFS() {
        stateStorage = new Stack();
        isBFS = false;
    }

    @Override
    public Solution getNext() {
        return stateStorage.pop();
    }

    @Override
    public void put(Solution solution) {
        stateStorage.push(solution);
    }

    @Override
    public boolean isEmpty() {
        return stateStorage.isEmpty();
    }

    @Override
    public boolean isBFS() {
        return isBFS;
    }
}
