package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import application.model.Liegeplatz;
import application.repository.LiegeplatzRepository;

import java.nio.file.Path;
import java.util.List;
import java.io.IOException;

public class OverviewController extends WindowController {

    @FXML
    private Label gesperrtCountLabel;

    @FXML
    private Label vermietetCountLabel;

    @FXML
    private Label belegtCountLabel;

    @FXML
    private Label verfuegbarCountLabel;

    @FXML
    private Label totalCountLabel;

    private int totalCount = 0;
    private int gesperrtCount = 0;
    private int vermietetCount = 0;
    private int belegtCount = 0;
    private int verfuegbarCount = 0;

    private Scene dialogScene;
    private Stage dialogStage;

    private LiegeplatzRepository repo = new LiegeplatzRepository();;
    private List<Liegeplatz> liegeplaetze;

    @FXML
    private void initialize() {
        loadJson();
        this.totalCount = liegeplaetze.size();
        setCounterLabels();
        updateCountLabels();
    }

    private void loadJson() {
        try {
            liegeplaetze = repo.laden(Path.of("src/application/storage/liegeplaetze.json"));
            System.out.println("Anzahl geladen: " + liegeplaetze.size());
            for (Liegeplatz lp : liegeplaetze) {
                System.out.println(lp);
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Laden des Repositorys:");
            e.printStackTrace();
        }
    }

    private void setCounterLabels() {
        for (Liegeplatz lp : liegeplaetze) {
            switch (lp.getStatus()) {
                case GESPERRT:
                    gesperrtCount += 1;
                    break;
                case VERMIETET:
                    vermietetCount += 1;
                    break;
                case BELEGT:
                    belegtCount += 1;
                    break;
                case VERFUEGBAR:
                    verfuegbarCount += 1;
                    break;
            }
        }
    }

    private void updateCountLabels() {
        totalCountLabel.setText(Integer.toString(totalCount));
        gesperrtCountLabel.setText(Integer.toString(gesperrtCount));
        vermietetCountLabel.setText(Integer.toString(vermietetCount));
        belegtCountLabel.setText(Integer.toString(belegtCount));
        verfuegbarCountLabel.setText(Integer.toString(verfuegbarCount));
    }

    public void setDialogScene(Scene dialogScene) {
        this.dialogScene = dialogScene;
    }

    @FXML
    private void openDialog() {

        // Das zweite Fenster nur beim ersten Öffnen erstellen
        if (dialogStage == null) {
            dialogStage = new Stage();

            // Der Dialog gehört zum Hauptfenster
            dialogStage.initOwner(getStage());

            // Beide Fenster bleiben bedienbar
            dialogStage.initModality(Modality.NONE);

            // Eigene Titelleiste aus deiner FXML verwenden
            dialogStage.initStyle(StageStyle.UNDECORATED);

            // Die Dialogoberfläche gehört jetzt zum zweiten Fenster
            dialogStage.setScene(dialogScene);
        }

        // Fenster anzeigen bzw. erneut öffnen
        dialogStage.show();

        // Falls es bereits geöffnet ist, nach vorne holen
        dialogStage.toFront();
    }
}
