package src.GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class viewData {

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
        Button sandwichButton = new Button("View Sandwich Recipes");
        sandwichButton.setPrefSize(275, 50);
        Button spudButton = new Button("View Spud Recipes");
        spudButton.setPrefSize(275, 50);
        Button saladButton = new Button("View Salad Recipes");
        saladButton.setPrefSize(275, 50);

        backButton.setOnAction(e -> homeScreen.display());
        // sandwichButton.setOnAction(e -> homeScreen.display());
        // spudButton.setOnAction(e -> homeScreen.display());
        // saladButton.setOnAction(e -> homeScreen.display());

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
}