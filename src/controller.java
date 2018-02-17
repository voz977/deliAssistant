package src;

// Imports from java.util and java.io

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Contains the various logical methods to operate the GUI (Graphical User Interface)
 *
 * @author Jonah Waschek
 * Date Created: February 16, 2018
 */
public class controller {

    public String chosenFile;

    /**
     * Constructor for the controller class, necessary to created controller objects
     *
     * @param chosenFile Stores the chosen file picked by user
     */
    public controller(String chosenFile) {

        this.chosenFile = chosenFile;
    }

    private static Scanner scan;

    /*
     * HashMaps are created and kept here for the entire program to add to/view
     */
    public static HashMap<String, ArrayList<String>> sandwichList = new HashMap<>();
    public static HashMap<String, ArrayList<String>> spudList = new HashMap<>();
    public static HashMap<String, ArrayList<String>> saladList = new HashMap<>();

    /**
     * Loads data from the chosen file in the GUI, into the associated HashMap
     * based on the itemType( Sandwich, Spud, Salad )
     *
     * @param chosenFile Stores the chosen file picked by user
     */
    public static void LoadData(String chosenFile) {

        int check = 0;

        String currentItem = "";
        /*
         * Try/Catch statement to check if the file can be found
         * Will print error message and cleanly exit if file can not be found
         */
        try {
            scan = new Scanner(new File(chosenFile));
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            System.exit(1);
        }

        String tmp;
        HashMap<String, ArrayList<String>> tmpList = new HashMap<>();
        ArrayList<String> ingredients = new ArrayList<>();
        ArrayList<String> oldList;

        while (scan.hasNextLine()) {
            tmp = scan.nextLine();
            System.out.println(tmp);

            /*
             * Series of if/else statements to load the recipes into the HashMap
             */
            if (tmp.equals("EOF")) {
                tmpList.put(currentItem, ingredients);
                currentItem = "";
                ingredients = new ArrayList<>();
            } else if (!tmp.contains(",")) {
                oldList = ingredients;
                ingredients = new ArrayList<>();
                if (check == 0) {
                    currentItem = tmp;
                    check = 1;
                } else {
                    tmpList.put(currentItem, oldList);
                    currentItem = tmp;
                }
            } else {
                ingredients.add(tmp);
            }
        }
        /*
         * Series of if/else if conditional statements to decide which
         *  file the user wants to load data from.
         */
        if (chosenFile.equals("./sandwiches.txt")) {
            sandwichList = tmpList;
        } else if (chosenFile.equals("./spuds.txt")) {
            spudList = tmpList;
        } else if (chosenFile.equals("./salads.txt")) {
            saladList = tmpList;
        } else {
            System.out.println("Error");
        }

    }

    /**
     * Performs the calculation to determine the amount of ingredients used per day
     *
     * @param chosenFile Stores the chosen file picked by user
     * @return ArrayList Containing the results after calculation
     */
    public static ArrayList<String> Calculate(String chosenFile) {

        int loc;

        /*
         * Try/Catch statement to check if the file can be found
         * Will print error message and cleanly exit if file can not be found
         */
        try {
            scan = new Scanner(new File("./sales.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            System.exit(1);
        }

        HashMap<String, ArrayList<String>> calcList = new HashMap<>();
        ArrayList<String> finalList = new ArrayList<>();
        ArrayList<String> valueList;

        /*
         * Series of if/else if conditional statements to decide which
         *  file the user wants to calculate data about.
         */
        if (chosenFile.equals("./sandwiches.txt")) {
            calcList = sandwichList;
        } else if (chosenFile.equals("./spuds.txt")) {
            calcList = spudList;
        } else if (chosenFile.equals("./salads.txt")) {
            calcList = saladList;
        } else {
            System.out.println("Error");
        }


        // Iterates through the ./sales.txt file until EOF to gather how many of each item were sold.
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            loc = line.indexOf(",");
            String item = line.substring(0, loc);
            String tmp = line.substring(loc + 1);
            int amountSold = Integer.parseInt(tmp);

            /*
             * Checks if itemName from the sales.txt is a key in the HashMap.
             * Computes calculation if it is found.
             */
            if (calcList.containsKey(item)) {
                valueList = calcList.get(item);
                for (String s : valueList) {
                    loc = s.indexOf(",");
                    String ingredient = s.substring(0, loc);
                    String tmps = s.substring(loc + 1);
                    int quantity = Integer.parseInt(tmps);
                    finalList.add("For Sandwich: " + item + ", " + quantity * amountSold + " ounces of " +
                            ingredient + " was used");
                }
            }
        }
        return finalList;
    }

    /**
     * Gathers data of the itemType chosen by the user from the associated HashMap and returns it to the GUI.
     *
     * @param chosenFile Viewing data stored in HashMap under this type
     * @return ArrayList Contains chosen data to show user
     */
    public static ArrayList<String> ViewData(String chosenFile) {

        String output;

        HashMap<String, ArrayList<String>> recipes = new HashMap<>();
        ArrayList<String> tmpList = new ArrayList<>();

        /*
         * Series of if/else if conditional statements to decide which
         *  file the user wants to view.
         */
        if (chosenFile.equals("./sandwiches.txt")) {
            recipes = sandwichList;
        } else if (chosenFile.equals("./spuds.txt")) {
            recipes = spudList;
        } else if (chosenFile.equals("./salads.txt")) {
            recipes = saladList;
        } else {
            System.out.println("error...file not found");
        }

        /*
         * Null check to see if user has loaded selected type of data yet
         * Returns ArrayList of error message to GUI if it is empty
         */
        if (recipes.isEmpty()) {
            String error = "Data of that type is not loaded yet";
            tmpList.add(error);
            return tmpList;
        }
        Iterator ii = recipes.entrySet().iterator();
        /*
         * Loops through the chosen HashMap to gather key/value pairs
         * and add them to the ArrayList which will be returned
         */
        while (ii.hasNext()) {
            Map.Entry set = (Map.Entry) ii.next();
            output = set.getKey() + " = " + set.getValue();
            tmpList.add(output);
            ii.remove();
        }
        return tmpList;
    }
}