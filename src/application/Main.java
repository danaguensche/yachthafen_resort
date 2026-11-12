package application;

import java.util.List;

import application.controller.OverviewController;
import application.model.Liegeplatz;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import application.repository.LiegeplatzRepository;
import java.nio.file.Path;

public class Main extends Application {

    @Override

    // Stage repräsentiert das Fenster der Anwendung
    // Man kann mehrere Fenster erstellen
    // Stage stage2 = new Stage();

    public void start(Stage stage) throws Exception {

        LiegeplatzRepository repo = new LiegeplatzRepository();
        try {
            List<Liegeplatz> liste = repo.laden(Path.of("src/application/storage/liegeplaetze.json"));
            System.out.println("Anzahl geladen: " + liste.size());
            for (Liegeplatz lp : liste) {
                System.out.println(lp);
            }
        } catch (Exception e) {
            System.out.println("Fehler beim Laden:");
            e.printStackTrace();
        }

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