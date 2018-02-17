package src.GUI;

// Imports from JavaFX
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Creates the Home Screen of the GUI Application with 3 buttons (Load Data, View Data, and Calculate Data)
 *
 * @author Jonah Waschek
 * Date Created: February 16, 2018
 */
public class homeScreen extends Application {

    public static void main(String args[]) {
        launch(args);
    }

    /**
     * Displays a Graphical User Interface with various options for the user.
     * <p>
     * Load Data - This button calls the loadData class. That class handles the ingestion of files and user input.
     * View Data - This button calls the viewData class. That class shows the user the different data currently stored.
     * Calculate Data - The final button calls the calculateData class. That class performs a calculation on the data
     * to determine the total usage of ingredients. Later updates of this project will include other factors such as
     * food waste and cost of ingredients so a profit can be calculated.
     */
    public static void display() {


        // Creates window and sets minWidth/title
        Stage window;
        window = new Stage();
        window.setMinWidth(250);
        window.setTitle("Deli Assistant");
        window.setOnCloseRequest(e -> window.close());


        //Creates buttons and changes default sizes
        Button loadDataButton, viewDataButton, calculateDataButton;
        loadDataButton = new Button("Load Data");
        loadDataButton.setPrefSize(275, 50);
        viewDataButton = new Button("View Data");
        viewDataButton.setPrefSize(275, 50);
        calculateDataButton = new Button("Calculate Data");
        calculateDataButton.setPrefSize(275, 50);

        /*
         * Setting various tasks to run if specific button is pressed
         * loadDataButton - Loads Data into program
         * viewDataButton - Views Data already stored
         * calculateDataButton - Performs calculation
         */
        loadDataButton.setOnAction(e -> {
            window.close();
            loadData.display();
        });

        viewDataButton.setOnAction(e -> {
            window.close();
            viewData.display();
        });

        calculateDataButton.setOnAction(e -> {
            window.close();
            calculateData.display();
        });


        // Horizontal Box which contains the above buttons
        HBox userOptions = new HBox();
        userOptions.getChildren().addAll(loadDataButton, viewDataButton, calculateDataButton);
        userOptions.setSpacing(15);
        userOptions.setAlignment(Pos.CENTER);


        // BorderPane that holds the Horizontal box (Will show menu bar on top in later update)
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(userOptions);


        // Creates the scene which contains the borderPane, and associates it with the window
        Scene scene = new Scene(borderPane, 900, 600);
        window.setScene(scene);
        window.show();
    }

    /**
     * Calls display() to show the initial window, and sets primaryStage to window.
     *
     * @param primaryStage Beginning stage of the GUI
     */
    @Override
    public void start(Stage primaryStage) {
        Stage window;
        window = primaryStage;
        window.setTitle("Deli Assistant");
        display();
    }
}