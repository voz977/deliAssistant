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


public class viewData {

    public static void display() {

        Stage window = new Stage();
        window.setTitle("Deli Assistant");
        window.setMinWidth(250);

        MenuBar menuBar = new MenuBar();
        final TextField output = new TextField();
        Menu file = new Menu("File");
        Menu edit = new Menu("Edit");
        Menu help = new Menu("Help");
        menuBar.getMenus().addAll(file, edit, help);

        Button backButton = new Button("Go Back");
        backButton.setPrefSize(275, 50);
        Button sandwichButton = new Button("View Sandwich Recipes");
        sandwichButton.setPrefSize(275, 50);
        Button spudButton = new Button("View Spud Recipes");
        spudButton.setPrefSize(275, 50);
        Button saladButton = new Button("View Salad Recipes");
        saladButton.setPrefSize(275, 50);

        backButton.setOnAction(e -> homeScreen.display());
        sandwichButton.setOnAction(e -> showData("./sandwiches.txt"));
        spudButton.setOnAction(e -> showData("./spuds.txt"));
        saladButton.setOnAction(e -> showData("./salads.txt"));

        HBox dataBox = new HBox();
        dataBox.getChildren().addAll(sandwichButton, spudButton, saladButton);
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

    public static void showData(String itemType) {
        ArrayList<String> tmpList = controller.ViewData(itemType);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        int rows = tmpList.size();

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