package application;

import application.controller.DialogController;
import application.controller.OverviewController;
import application.model.LiegeplatzVerwaltung;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.nio.file.Path;

public class Main extends Application {

    // Stage repräsentiert das Fenster der Anwendung
    // Man kann mehrere Fenster erstellen
    // Stage stage2 = new Stage();

    @Override
    public void start(Stage stage) throws Exception {

        // Daten einmal laden – dieses Objekt teilen sich beide Controller
        LiegeplatzVerwaltung verwaltung = new LiegeplatzVerwaltung(
                Path.of("src/application/storage/liegeplaetze.json"));
        verwaltung.laden();

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
        // (erst NACH load() möglich)
        OverviewController overviewController = loader.getController();

        // Den zum Dialog gehörenden Controller abrufen
        DialogController dialogController = dialogLoader.getController();

        // Beiden Controllern dieselbe Verwaltung geben
        dialogController.setVerwaltung(verwaltung);
        overviewController.setVerwaltung(verwaltung);

        // Die Dialog-Szene und den Dialog-Controller übergeben
        overviewController.setDialog(dialogScene, dialogController);

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