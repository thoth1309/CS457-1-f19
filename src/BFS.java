import java.util.LinkedList;
import java.util.Queue;

public class BFS implements StateStorage {
    private Queue<Solution> storageSpace;
    private boolean isBFS;

    /**
     * Creates a new BFS search container
     */
    public BFS(){
        storageSpace = new LinkedList<>();
        isBFS = true;
    }

    @Override
    public Solution getNext() {
        return storageSpace.remove();
    }

    @Override
    public void put(Solution solution) {
        storageSpace.add(solution);
    }

    @Override
    public boolean isEmpty() {
        return storageSpace.isEmpty();
    }

    @Override
    public boolean isBFS() {
        return true;
    }
}
