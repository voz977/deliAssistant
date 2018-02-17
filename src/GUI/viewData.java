package src.GUI;

// Imports from JavaFX and java.util

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.controller;

import java.util.ArrayList;

// Imports the controller class from folder "src"

/**
 * Displays a new window for the user to view Data already loaded into the program.
 * <p>
 * Contains 4 buttons ( View Sandwich Recipes, View Spud Recipes, View Salad Recipes, and Go Back)
 *
 * @author Jonah Waschek
 * Date Created: February 16, 2018
 */
public class viewData {

    /**
     * Provides the user a way to view previously loaded recipes
     * <p>
     * View Sandwich Recipes - Displays in a new window the current Sandwich recipes currently loaded
     * View Spud Recipes - Displays in a new window the current Spud recipes currently loaded
     * View Salad Recipes - Displays in a new window the current Salad recipes currently loaded
     * Go Back - This button returns to the home screen.
     */
    public static void display() {

        Stage window = new Stage();
        window.setTitle("Deli Assistant");
        window.setMinWidth(250);

        // Buttons for the user to decide what happens next
        Button backButton = new Button("Go Back");
        backButton.setPrefSize(275, 50);
        Button sandwichButton = new Button("View Sandwich Recipes");
        sandwichButton.setPrefSize(275, 50);
        Button spudButton = new Button("View Spud Recipes");
        spudButton.setPrefSize(275, 50);
        Button saladButton = new Button("View Salad Recipes");
        saladButton.setPrefSize(275, 50);

        // Controls what happens when the user presses a button
        backButton.setOnAction(e -> homeScreen.display());
        sandwichButton.setOnAction(e -> showData("./sandwiches.txt"));
        spudButton.setOnAction(e -> showData("./spuds.txt"));
        saladButton.setOnAction(e -> showData("./salads.txt"));

        // Horizontal dataBox to hold the buttons
        HBox dataBox = new HBox();
        dataBox.getChildren().addAll(sandwichButton, spudButton, saladButton);
        dataBox.setSpacing(15);
        dataBox.setAlignment(Pos.CENTER);

        // BorderPane for holding the other features of the GUI
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(dataBox);
        BorderPane.setAlignment(backButton, Pos.CENTER);
        borderPane.setBottom(backButton);
        Scene scene = new Scene(borderPane, 900, 600);
        window.setScene(scene);
        window.showAndWait();
    }

    /**
     * Displays the requested data by calling the ViewData method in the controller class.
     * <p>
     * Iterates through the arrayList and grid at the same time, and placing newly created Labels in their proper spots.
     *
     * @param itemType Identifies the type of the item ( Sandwich, Spud, Salad )
     */
    public static void showData(String itemType) {
        ArrayList<String> tmpList = controller.ViewData(itemType);


        // Creates grid for showing data to user
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        int rows = tmpList.size();

        // Iterates through arrayList and sets the text of the Label to the corresponding value
        for (int i = 0; i < rows; i++) {
            String line = tmpList.get(i);
            Label lineOfText = new Label(line);
            GridPane.setConstraints(lineOfText, 0, i);
            grid.getChildren().add(lineOfText);
        }

        Stage window2 = new Stage();
        window2.initModality(Modality.APPLICATION_MODAL);
        window2.setTitle("Deli Assistant");
        window2.setMinWidth(250);

        Scene scene2 = new Scene(grid, 600, 500);
        window2.setScene(scene2);
        window2.showAndWait();
    }
}