package src.GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.controller;

import java.util.ArrayList;
import java.util.HashMap;

public class loadData {

    private static String chosenFile = "";

    public static void display() {

        Stage window = new Stage();
        window.setTitle("Deli Assistant");
        window.setMinWidth(250);

        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        Menu edit = new Menu("Edit");
        Menu help = new Menu("Help");
        menuBar.getMenus().addAll(file, edit, help);
        Button backButton = new Button("Go Back");
        backButton.setPrefSize(275, 50);
        Button fileButton = new Button("Load Data from File");
        fileButton.setPrefSize(275, 50);
        Button keyboardButton = new Button("Enter Data from Keyboard");
        keyboardButton.setPrefSize(275, 50);

        backButton.setOnAction(e -> homeScreen.display());

        fileButton.setOnAction(e -> {
            filePicker();
            controller.LoadData(chosenFile);
        });

        keyboardButton.setOnAction(e -> keyboardLoadData());

        HBox dataBox = new HBox();
        dataBox.getChildren().addAll(fileButton, keyboardButton);
        dataBox.setSpacing(15);
        dataBox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(dataBox);
        BorderPane.setAlignment(backButton, Pos.CENTER);
        borderPane.setBottom(backButton);
        Scene scene = new Scene(borderPane, 900, 600);
        window.setScene(scene);
        window.showAndWait();
    }

    public static void keyboardLoadData() {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Deli Assistant");
        window.setMinWidth(250);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label question = new Label("Please enter the item category");
        Label question2 = new Label("Please enter item name");
        Label question3 = new Label("Name of first Ingredient?");
        Label question4 = new Label("How many ounces of first Ingredient?");
        Label question5 = new Label("Name of second Ingredient?");
        Label question6 = new Label("How many ounces of first Ingredient?");

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Sandwiches", "Spuds", "Salads");
        choiceBox.setValue("Sandwiches");

        TextField itemName = new TextField();
        TextField ingredientName = new TextField();
        TextField ingredientCount = new TextField();
        TextField ingredientName2 = new TextField();
        TextField ingredientCount2 = new TextField();

        Button addButton = new Button("Add Item");
        addButton.setOnAction(e -> {
            addNewItem(choiceBox.getValue(), itemName.getText(), ingredientName.getText(),
                    ingredientCount.getText(), ingredientName2.getText(), ingredientCount2.getText());
            // Reset fields
        });

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

        Scene scene = new Scene(grid, 600, 500);
        window.setScene(scene);
        window.showAndWait();
    }

    public static void addNewItem(
            String category, String itemName, String ingredientName,
            String ingredientCount, String ingredientName2, String ingredientCount2) {

        HashMap<String, ArrayList<String>> tmpHashMap = new HashMap<>();
        ArrayList<String> tmpArray = new ArrayList<>();

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

    public static void filePicker() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Deli Assistant");
        window.setMinWidth(250);

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("./sandwiches.txt", "./spuds.txt");
        choiceBox.getItems().addAll("./salads.txt", "./sales.txt");
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

        Scene scene = new Scene(borderP, 300, 200);
        window.setScene(scene);
        window.showAndWait();
    }
}