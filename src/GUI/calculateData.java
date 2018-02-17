package src.GUI;

// Imports from JavaFX and java.util

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
 * Displays a new window for the computation of the loaded data.
 * <p>
 * Only one button is found on this window ( Show Total Usage )
 *
 * @author Jonah Waschek
 * Date Created: February 16, 2018
 */
public class calculateData {

    /**
     * Provides the user with the ability to calculate how much of each ingredient is used that day to the ounce
     * <p>
     * Show Total Usage - Calculates total usage of ingredients by multiplying amount used in each recipe
     * by the amount of each item sold located in the sales.txt file
     */
    public static void display() {

        Stage window = new Stage();
        window.setTitle("Deli Assistant");
        window.setMinWidth(250);

        // Creates buttons for the user to control what happens
        Button backButton = new Button("Go Back");
        backButton.setPrefSize(275, 50);
        Button calculateButton = new Button("Show Total Usage");
        calculateButton.setPrefSize(275, 50);

        // Decides what happens when a button is pressed
        backButton.setOnAction(e -> homeScreen.display());
        calculateButton.setOnAction(e -> computeData());

        // Horizontal box for holding the above buttons
        HBox dataBox = new HBox();
        dataBox.getChildren().addAll(calculateButton);
        dataBox.setSpacing(15);
        dataBox.setAlignment(Pos.CENTER);

        // BorderPane for holding the already created features
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(dataBox);
        BorderPane.setAlignment(backButton, Pos.CENTER);
        borderPane.setBottom(backButton);
        Scene scene = new Scene(borderPane, 900, 600);
        window.setScene(scene);
        // Waits to exit until user presses close button
        window.showAndWait();
    }

    /**
     * Calls the calculate method in the controller class to compute the required formulas and asks user which type
     * of item he wishes to calculate.
     */
    public static void computeData() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Deli Assistant");
        window.setMinWidth(250);

        Label label = new Label("Please select the file you wish to calculate");

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("./sandwiches.txt", "./spuds.txt", "./salads.txt");
        choiceBox.setValue("./sandwiches.txt");

        HBox layout = new HBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(choiceBox);

        Button submit = new Button("Submit");
        submit.setOnAction(e -> {
            String chosenFile = choiceBox.getValue();
            showData(chosenFile);
        });

        BorderPane borderP = new BorderPane();
        BorderPane.setAlignment(label, Pos.CENTER);
        borderP.setTop(label);
        borderP.setCenter(layout);
        borderP.setBottom(submit);

        Scene scene = new Scene(borderP, 300, 200);
        window.setScene(scene);
        // Waits to exit until user presses close button
        window.showAndWait();
    }

    /**
     * Shows the finished result of the calculation to the user in a new window. Iterates through the arrayList
     * and grid at the same time, and placing newly created Labels in their proper spots.
     *
     * @param itemType Identifies the type of the item ( Sandwich, Spud, Salad )
     */
    public static void showData(String itemType) {
        ArrayList<String> tmpList = controller.Calculate(itemType);

        // Grid for displaying results to the user
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        int rows = tmpList.size();

        // Iterates through arrayList and assigning each line to a new Label
        for (int i = 0; i < rows; i++) {
            String line = tmpList.get(i);

            Label lines = new Label(line);

            GridPane.setConstraints(lines, 0, i);

            grid.getChildren().add(lines);
        }

        // Displays newly found data in this window
        Stage window2 = new Stage();
        window2.initModality(Modality.APPLICATION_MODAL);
        window2.setTitle("Deli Assistant");
        window2.setMinWidth(250);

        Scene scene2 = new Scene(grid, 600, 500);
        window2.setScene(scene2);
        // Waits to exit until user presses close button
        window2.showAndWait();
    }
}