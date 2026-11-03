package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override

    // Stage repräsentiert das Fenster der Anwendung
    // Man kann mehrere Fenster erstellen
    // Stage stage2 = new Stage();

    public void start(Stage stage) throws Exception {
        // FXML-Datei wird geladen und in ein Parent-Objekt umgewandelt
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main_window.fxml"));
        Parent root = loader.load();

        // Einer Stage wird eine Szene hinzugefügt
        // Unterschiedliche Szenen bedeuten unterschiedliche Fensterinhalte
        Scene scene = new Scene(root);

        // Eigenschaften der Stage festlegen
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(true);
        stage.setMinWidth(800);
        stage.setMinHeight(600);

        //Szene hinzufügen und Stage anzeigen
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}