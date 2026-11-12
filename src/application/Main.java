package application;

import application.controller.OverviewController;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/FXML/main_window.fxml"));
        Parent root = loader.load();

        // Dialogoberfläche laden
        FXMLLoader dialogLoader = new FXMLLoader(
                getClass().getResource("/application/FXML/dialog.fxml"));
        Parent dialogRoot = dialogLoader.load();

        // Einer Stage wird eine Szene hinzugefügt
        // Unterschiedliche Szenen bedeuten unterschiedliche Fensterinhalte

        // root ist die FXML-Datei, die geladen wurde und die Benutzeroberfläche
        // darstellt
        Scene scene = new Scene(root);

        Scene dialogScene = new Scene(dialogRoot);

        // Den zur Hauptoberfläche gehörenden Controller abrufen
        OverviewController overviewController = loader.getController();

        // Das vorhandene Fenster und die Dialog-Szene übergeben
        overviewController.setDialogScene(dialogScene);

        // Eigenschaften der Stage festlegen
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(true);
        stage.setMinWidth(600);
        stage.setMinHeight(250);

        // Szene hinzufügen und Stage anzeigen
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}