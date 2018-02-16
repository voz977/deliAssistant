package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class controller {

    private static Scanner scan;
    private String itemType;

    public static HashMap<String, ArrayList<String>> sandwichList = new HashMap<>();
    public static HashMap<String, ArrayList<String>> spudList = new HashMap<>();
    public static HashMap<String, ArrayList<String>> saladList = new HashMap<>();

    public controller() {


    }

    public controller(String itemType) {
        this.itemType = itemType;
    }

    // Loads data from text files into hashMaps
    public static void LoadData(String chosenFile) {

        int check = 0;

        String currentItem = "";
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

    //    // Calculates total usage of ingrediants
//    public static void Calculate() throws IOException {
//
//        int loc;
//        scan = new Scanner(salesFile);
//        HashMap<String, ArrayList<String>> calcList = new HashMap<>();
//        ArrayList<String> valueList;
//
//        if (calChoice == 1) {
//            calcList = sandwichList;
//        } else if (calChoice == 2) {
//            calcList = spudList;
//        } else if (calChoice == 3) {
//            calcList = saladList;
//        }
//
//
//        while (scan.hasNextLine()) {
//            String line = scan.nextLine();
//            loc = line.indexOf(",");
//            String item = line.substring(0, loc);
//            String tmp = line.substring(loc + 1);
//            int amountSold = Integer.parseInt(tmp);
//
//
//            if (calcList.containsKey(item)) {
//                valueList = calcList.get(item);
//                for (String s : valueList) {
//                    loc = s.indexOf(",");
//                    String ingredient = s.substring(0, loc);
//                    String tmps = s.substring(loc + 1);
//                    int quantity = Integer.parseInt(tmps);
//                    System.out.println("For Sandwich: " + item + ", " + quantity * amountSold + " ounces of " +
//                            ingredient + " was used");
//                }
//            }
//        }
//    }
//
    // Loops through HashMaps and prints out key/value pairs
    public static ArrayList<String> ViewData(String chosenFile) {

        String output;

        HashMap<String, ArrayList<String>> recipes = new HashMap<>();
        ArrayList<String> tmpList = new ArrayList<>();

        if (chosenFile.equals("./sandwiches.txt")) {
            recipes = sandwichList;
        } else if (chosenFile.equals("./spuds.txt")) {
            recipes = spudList;
        } else if (chosenFile.equals("./salads.txt")) {
            recipes = saladList;
        } else {
            System.out.println("error...file not found");
        }

        if (recipes.isEmpty()) {
            System.out.println("Data of that type is not loaded yet");
        }
        Iterator ii = recipes.entrySet().iterator();
        while (ii.hasNext()) {
            Map.Entry set = (Map.Entry) ii.next();
            output = set.getKey() + " = " + set.getValue();
            tmpList.add(output);
            ii.remove();
        }
        return tmpList;
    }

//    // Main method to run program
//    public static void main(String args[]) throws IOException {
//
//        System.out.println("Welcome to Jonah's Food Cost calculator");
//        LoadData();
//        scan = new Scanner(System.in);
//        int taskChoice;
//        int stop = 1;
//
//        while (stop == 1) {
//            try {
//
//                System.out.println("Recipes are given in the following format.....Item Name = ingredient name,#oz");
//                System.out.println("Do you wish to view a recipe (1) or perform a calculation (2)?");
//                taskChoice = scan.nextInt();
//                if (taskChoice == 1) {
//                    System.out.println("Which recipes do you wish to see? Sandwiches(1) Spuds(2) Salads(3)");
//                    recipeChoice = scan.nextInt();
//                    if (recipeChoice == 0 | recipeChoice > 3) {
//                        System.out.println("Error...please enter correct number");
//                        continue;
//                    }
//                    ViewData();
//                }
//                if (taskChoice == 2) {
//                    System.out.println("Which recipe type do you wish to calculate? Sandwiches(1) Spuds(2) Salads(3) ");
//                    calChoice = scan.nextInt();
//                    if (calChoice == 0 | calChoice > 3) {
//                        System.out.println("Error...please enter correct number");
//                        continue;
//                    }
//                    Calculate();
//                }
//                if (taskChoice == 0 | taskChoice > 2) {
//                    System.out.println("Error...please enter correct number");
//                    continue;
//                }
//
//                scan = new Scanner(System.in);
//                System.out.println("\nDo you wish to do something else? Yes(1) No(0)");
//                stop = scan.nextInt();
//                if (stop == 0) {
//                    break;
//                }
//
//            } catch (InputMismatchException e) {
//                System.out.println("Error...input needs to be a number");
//                scan = new Scanner(System.in);
//            }
//        }
//    }
}