package src.GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class homeScreen extends Application {

    private static Stage window;

    public static void main(String args[]) {
        launch(args);
    }

    public static void display() {

        Button loadDataButton, viewDataButton, calculateDataButton;

        window = new Stage();
        window.setMinWidth(250);
        window.setTitle("Deli Assistant");
        window.setOnCloseRequest(e -> window.close());

        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        Menu edit = new Menu("Edit");
        Menu help = new Menu("Help");
        menuBar.getMenus().addAll(file, edit, help);

        loadDataButton = new Button("Load Data");
        loadDataButton.setPrefSize(275, 50);

        viewDataButton = new Button("View Data");
        viewDataButton.setPrefSize(275, 50);

        calculateDataButton = new Button("Print Data");
        calculateDataButton.setPrefSize(275, 50);

        loadDataButton.setOnAction(e -> {
            loadData.display();
            window.close();
        });

        viewDataButton.setOnAction(e -> {
            viewData.display();
            window.close();
        });

        calculateDataButton.setOnAction(e -> {
            calculateData.display();
            window.close();
        });

        HBox userOptions = new HBox();
        userOptions.getChildren().addAll(loadDataButton, viewDataButton, calculateDataButton);
        userOptions.setSpacing(15);
        userOptions.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(userOptions);

        Scene scene = new Scene(borderPane, 900, 600);
        window.setScene(scene);
        window.show();
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Deli Assistant");
        display();
    }
}