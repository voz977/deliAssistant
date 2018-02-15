package src.GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class calculateData {

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
        Button calculateButton = new Button("Show Total Usage");
        calculateButton.setPrefSize(275, 50);

        backButton.setOnAction(e -> homeScreen.display());
        // calculateButton.setOnAction(e -> homeScreen.display());

        HBox dataBox = new HBox();
        dataBox.getChildren().addAll(calculateButton);
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