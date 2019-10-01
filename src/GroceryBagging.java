import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GroceryBagging {
    public static void main(String args[]) {
        ArrayList<String> flags;
        ArrayList<Bag> bags;
        ArrayList<Grocery> groceries;
        String searchMethod;
        StateStorage stateStorage;
        boolean success;
        int bagCount;
        int bagSize;
        int maxBagSize = 0;
        int totalItemSize = 0;

        // populating valid flags for command line args
        flags = new ArrayList<>();
        flags.add("-depth");
        flags.add("-d");
        flags.add("-breadth");
        flags.add("-b");

        // Validating args before we start
        if (args.length != 2 || !flags.contains(args[1]))
            usage();

        String fileName = args[0];

        // Let's try to parse this file!
        try {
            File inFile;
            inFile = new File(fileName);
            String line;
            String token;
            Scanner scanner;
            Scanner lineScanner;

            // Determine the type of search we're using. Right now, we're only
            // supporting Breadth First and Depth First.
            // Default is Depth First, as it doesn't have to find as many final states.
            searchMethod = args[1];
            if (searchMethod.charAt(1) == 'b')
                stateStorage = new BFS();
            else if (searchMethod.charAt(1) == 'd')
                stateStorage = new DFS();
            else {
                stateStorage = new DFS();
                System.out.print("Default storage: DFS\n");
            }

            scanner = new Scanner(inFile);

            // get bag quantity and size, then create our set of bags
            bagCount = Integer.parseInt(scanner.nextLine().trim());
            bagSize = Integer.parseInt(scanner.nextLine().trim());
            bags = new ArrayList<>();
            for(int i = 0; i < bagCount; i++)
                bags.add(new Bag(bagSize));

            // useful for catching size failure before we try to bag
            maxBagSize = bagSize * bagCount;

            // Initialize our groceries container
            groceries = new ArrayList<>();

            // parse the file to find our items
            while (scanner.hasNextLine()) {
                Grocery grocery = null;
                line = scanner.nextLine();
                lineScanner = new Scanner(line);
                token = lineScanner.next().trim();
                int itemSize = Integer.parseInt(lineScanner.next().trim());

                // If the item was made from a parameter, it'll be in the list
                // and all we'll do is add the size to the item
                for (Grocery item:groceries) {
                    if (item.getName().equals(token)) {
                        grocery = item;
                        grocery.setItemSize(itemSize);
                    }
                }

                // if the grocery is a new item, create it and add it to the list
                if(grocery == null) {
                    grocery = new Grocery(token, itemSize);
                    groceries.add(grocery);
                }

                // keep track of total size of all items
                totalItemSize += itemSize;

                // Determine whether we can or can't pair with the following items
                if (lineScanner.hasNext()) {
                    token = lineScanner.next();
                    if(token.equals("+"))
                        grocery.setCanPack();

                    while(lineScanner.hasNext()) {
                        Grocery pairGrocery = null;
                        token = lineScanner.next();

                        // check to see if the item exists
                        for (Grocery item:groceries) {
                            if (item.getName().equals(token)) {
                                pairGrocery = item;
                            }
                        }

                        // if the item doesn't exist, create it
                        if (pairGrocery == null) {
                            pairGrocery = new Grocery(token);
                            groceries.add(pairGrocery);
                        }

                        // add the pairing, whether it be good or ill
                        grocery.addPairing(pairGrocery);
                    }
                }
                lineScanner.close();
            }
            scanner.close();

            // Now we have our items, and our bags, and our storage system,
            // we're going to do a basic check to see if there's more stuff
            // than we can fit in the bags, and then run our bagger!
            //
            // If there is too much stuff, we'll just print failure without
            // even wasting our time trying to bag the items.
            if(maxBagSize > totalItemSize) {
                Solution seed = new Solution(groceries, bags);
                Bagger bagger = new Bagger(stateStorage);//, groceries, bags);
                success = bagger.bagGroceries(seed);//stateStorage.getNext());

                // make sure we succeeded. If so, we can just end. If not, tell the user
                if (!success)
                    System.out.print("failure");
            } else
                System.out.print("failure");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a usage message in the event that the program is
     * run incorrectly
     *
     * @return usage message
     */
    private static void usage() {
        StringBuilder usageMessage;
        usageMessage = new StringBuilder();

        usageMessage.append("usage: $ bagit filename [-depth | -breadth]\n");
        usageMessage.append("\t-depth | -d: algorithm will utilize depth first search\n");
        usageMessage.append("\t-breadth | -b: algorithm will utilize breadth first search\n");
        usageMessage.append("\t\t(default is depth first search)\n");

        System.out.println(usageMessage.toString());
        System.exit(0);
    }
}