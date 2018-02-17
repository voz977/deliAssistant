package src.GUI;

// Imports from JavaFX and java.util

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.controller;

import java.util.ArrayList;
import java.util.HashMap;

// Imports the controller class from folder "src"

/**
 * Displays a new window with 3 buttons (Load Data from File, Enter Data from Keyboard, and Go Back)
 *
 * @author Jonah Waschek
 * Date Created: February 16, 2018
 */
public class loadData {

    private static String chosenFile = "";

    /**
     * Displays a Graphical User Interface with various options for the user.
     * <p>
     * Load Data from File - This button loads a new window which asks the user to pick a .txt file to load.
     * Enter Data from Keyboard - This button loads another window which allows the user to enter data such as:
     * item type, item name, ingredient name, ingredient amount, 2nd ingredient name, and 2nd ingredient amount.
     * Go Back - This button returns to the home screen.
     */
    public static void display() {

        /*
         * Sets up a new window in the GUI
         */
        Stage window = new Stage();
        window.setTitle("Deli Assistant");
        window.setMinWidth(250);

        /*
         * Creates various buttons with new sizes
         */
        Button backButton = new Button("Go Back");
        backButton.setPrefSize(275, 50);
        Button fileButton = new Button("Load Data from File");
        fileButton.setPrefSize(275, 50);
        Button keyboardButton = new Button("Enter Data from Keyboard");
        keyboardButton.setPrefSize(275, 50);

        /*
         * fileButton - Sends user to choose a file to input
         * keyboardButton - Asks user for information about new data
         * backButton - Returns to homeScreen
         */
        backButton.setOnAction(e -> homeScreen.display());

        fileButton.setOnAction(e -> {
            filePicker();
            controller.LoadData(chosenFile);
        });

        keyboardButton.setOnAction(e -> keyboardLoadData());


        // Horizontal Box which holds the above buttons
        HBox dataBox = new HBox();
        dataBox.getChildren().addAll(fileButton, keyboardButton);
        dataBox.setSpacing(15);
        dataBox.setAlignment(Pos.CENTER);


        // BorderPane for holding all of the various features of the GUI
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(dataBox);
        BorderPane.setAlignment(backButton, Pos.CENTER);
        borderPane.setBottom(backButton);


        // Creates a new scene to hold the borderPane and sets it on the window
        Scene scene = new Scene(borderPane, 900, 600);
        window.setScene(scene);
        window.showAndWait();
    }

    /**
     * Loads data from user's input, example user input:
     * <p>
     * Please enter the item category      - Sandwiches
     * Please enter item name              - Ham Toasty
     * Name of first Ingredient            - Ham
     * How many ounces of first Ingredient - 4
     * Name of second Ingredient           - Swiss Cheese
     * How many ounces of first Ingredient - 2
     */
    public static void keyboardLoadData() {


        // Sets up a new window in the GUI
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Deli Assistant");
        window.setMinWidth(250);


        // Creates a grid for asking the user questions
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);


        // The follwing labels hold the questions for the user
        Label question = new Label("Please enter the item category");
        Label question2 = new Label("Please enter item name");
        Label question3 = new Label("Name of first Ingredient?");
        Label question4 = new Label("How many ounces of first Ingredient?");
        Label question5 = new Label("Name of second Ingredient?");
        Label question6 = new Label("How many ounces of first Ingredient?");

        // This choice box provides the user with options on which itemType to input
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Sandwiches", "Spuds", "Salads");
        choiceBox.setValue("Sandwiches");


        // TextField allows user to input answers from the keyboard
        TextField itemName = new TextField();
        TextField ingredientName = new TextField();
        TextField ingredientCount = new TextField();
        TextField ingredientName2 = new TextField();
        TextField ingredientCount2 = new TextField();


        // This button sends data to addNewItem method
        Button addButton = new Button("Add Item");
        addButton.setOnAction(e -> {
            addNewItem(choiceBox.getValue(), itemName.getText(), ingredientName.getText(),
                    ingredientCount.getText(), ingredientName2.getText(), ingredientCount2.getText());
        });


        // Places each question/answer in the correct space
        GridPane.setConstraints(question, 0, 0);
        GridPane.setConstraints(choiceBox, 1, 0);
        GridPane.setConstraints(question2, 0, 1);
        GridPane.setConstraints(itemName, 1, 1);
        GridPane.setConstraints(question3, 0, 2);
        GridPane.setConstraints(ingredientName, 1, 2);
        GridPane.setConstraints(question4, 0, 3);
        GridPane.setConstraints(ingredientCount, 1, 3);
        GridPane.setConstraints(question5, 0, 4);
        GridPane.setConstraints(ingredientName2, 1, 4);
        GridPane.setConstraints(question6, 0, 5);
        GridPane.setConstraints(ingredientCount2, 1, 5);
        GridPane.setConstraints(addButton, 0, 6);

        grid.getChildren().addAll(question, choiceBox, question2, itemName);
        grid.getChildren().addAll(question3, ingredientName, question4, ingredientCount);
        grid.getChildren().addAll(question5, ingredientName2, question6, ingredientCount2, addButton);


        // Creates a new scene to hold the borderPane and sets it on the window
        Scene scene = new Scene(grid, 600, 500);
        window.setScene(scene);
        window.showAndWait();
    }

    /**
     * Takes user entered data from the method, keyboardLoadData(), and puts it into HashMaps.
     * This occurs in this way so when the user wants to view the data, he can see data imported from
     * both files and user input at the same time.
     *
     * @param category         Identifies the item as a ( Sandwich, Spud, or Salad )
     * @param itemName         Contains the name of the created item
     * @param ingredientName   Name of the first ingredient
     * @param ingredientCount  Amount used of first ingredient in a single item
     * @param ingredientName2  Name of the second ingredient
     * @param ingredientCount2 Amount used of second ingredient in a single item
     */
    public static void addNewItem(
            String category, String itemName, String ingredientName,
            String ingredientCount, String ingredientName2, String ingredientCount2) {


        // Temporary HashMap and ArrayList for storing variables
        HashMap<String, ArrayList<String>> tmpHashMap = new HashMap<>();
        ArrayList<String> tmpArray = new ArrayList<>();


        // Checks which itemType the user wants and grabs that HashMap
        if (category.equals("Sandwiches")) {

            tmpHashMap = controller.sandwichList;

        } else if (category.equals("Spuds")) {

            tmpHashMap = controller.spudList;

        } else if (category.equals("Salads")) {

            tmpHashMap = controller.saladList;
        }

        if (tmpHashMap.containsKey(itemName)) {
            System.out.println(itemName + " is already here");
        } else {
            String line = ingredientName + "," + ingredientCount;
            String line2 = ingredientName2 + "," + ingredientCount2;
            tmpArray.add(line);
            tmpArray.add(line2);
            System.out.println(tmpArray);
            tmpHashMap.put(itemName, tmpArray);
        }
    }

    /**
     * Displays a new window for the user to pick a file to import from the following options:
     * ./sandwiches.txt --- ./spuds.txt --- ./salads.txt
     */
    public static void filePicker() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Deli Assistant");
        window.setMinWidth(250);

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("./sandwiches.txt", "./spuds.txt", "./salads.txt");
        choiceBox.setValue("./sandwiches.txt");

        HBox layout = new HBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(choiceBox);

        Label label = new Label("Please select the file you wish to load");

        Button submit = new Button("Submit");
        submit.setOnAction(e -> {
            chosenFile = choiceBox.getValue();
            window.close();
        });

        BorderPane borderP = new BorderPane();
        BorderPane.setAlignment(label, Pos.CENTER);
        borderP.setTop(label);
        borderP.setCenter(layout);
        borderP.setBottom(submit);


        // Creates a new scene to hold the borderPane and sets it on the window
        Scene scene = new Scene(borderP, 300, 200);
        window.setScene(scene);
        window.showAndWait();
    }
}