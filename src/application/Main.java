package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("main_window.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("My JavaFX Application");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading FXML file.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}