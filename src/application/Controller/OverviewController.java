package application.controller;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import application.model.Liegeplatz;
import application.model.Liegeplatz.Status;
import application.model.LiegeplatzVerwaltung;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

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

    @FXML
    private Pane harborPane;

    private Scene dialogScene;
    private Stage dialogStage;
    private DialogController dialogController;

    // Gemeinsame Verwaltung – wird von Main übergeben, dieselbe wie im Dialog
    private LiegeplatzVerwaltung verwaltung;

    /*
     * Wird von Main aufgerufen, NACHDEM die FXML geladen wurde.
     * Deshalb wird hier gezählt und nicht in initialize(): initialize()
     * läuft schon während loader.load(), da gibt es die Verwaltung noch nicht.
     */
    public void setVerwaltung(LiegeplatzVerwaltung verwaltung) {
        this.verwaltung = verwaltung;

        // Bei jeder Änderung der Liste (z.B. Speichern im Dialog)
        // werden die Zähler automatisch neu berechnet
        verwaltung.getLiegeplaetze().addListener(
                (ListChangeListener<Liegeplatz>) change -> {
                    updateCountLabels();
                    aktualisiereButtons();
                });

        aktualisiereButtons();
        updateCountLabels();
    }

    // Zählt jedes Mal neu, deshalb können sich die Zahlen nicht aufaddieren
    private void updateCountLabels() {
        totalCountLabel.setText(Integer.toString(verwaltung.getLiegeplaetze().size()));
        gesperrtCountLabel.setText(Integer.toString(verwaltung.anzahl(Status.GESPERRT)));
        vermietetCountLabel.setText(Integer.toString(verwaltung.anzahl(Status.VERMIETET)));
        belegtCountLabel.setText(Integer.toString(verwaltung.anzahl(Status.BELEGT)));
        verfuegbarCountLabel.setText(Integer.toString(verwaltung.anzahl(Status.VERFUEGBAR)));
    }

    // Ersetzt setDialogScene(): Szene UND Controller des Dialogs merken
    public void setDialog(Scene dialogScene, DialogController dialogController) {
        this.dialogScene = dialogScene;
        this.dialogController = dialogController;
    }

    @FXML
    private void openDialog(ActionEvent event) {

        // Welcher Button wurde geklickt? Der Text ("1", "2", ...) ist die ID
        Button button = (Button) event.getSource();
        int id = Integer.parseInt(button.getText());

        Liegeplatz liegeplatz = verwaltung.findeNachId(id);
        if (liegeplatz == null) {
            System.out.println("Kein Liegeplatz mit ID " + id + " gefunden");
            return;
        }

        // Dem Dialog den gewählten Liegeplatz geben (füllt die Eingabefelder)
        dialogController.zeigeLiegeplatz(liegeplatz);

        // Das zweite Fenster nur beim ersten Öffnen erstellen
        if (dialogStage == null) {
            dialogStage = new Stage();

            // Der Dialog gehört zum Hauptfenster
            dialogStage.initOwner(getStage());

            // Beide Fenster bleiben bedienbar
            dialogStage.initModality(Modality.NONE);

            // Titelleiste
            dialogStage.initStyle(StageStyle.UNDECORATED);

            // Die Dialogoberfläche gehört jetzt zum zweiten Fenster
            dialogStage.setScene(dialogScene);
        }

        // Fenster anzeigen bzw. erneut öffnen
        dialogStage.show();

        // Falls es bereits geöffnet ist, nach vorne holen
        dialogStage.toFront();
    }

    // Gibt jedem Button die CSS-Klasse seines Status
    private void aktualisiereButtons() {
        for (Node node : harborPane.getChildren()) {

            if (!(node instanceof Button)) {
                continue;
            }
            Button button = (Button) node;

            int id = Integer.parseInt(button.getText());
            Liegeplatz lp = verwaltung.findeNachId(id);

            /*
             * Alte Status entfernen, sonst hätte der Button nach einer
             * Änderung zwei Status-Klassen gleichzeitig
             */
            button.getStyleClass().removeAll("gesperrt", "vermietet", "belegt", "verfuegbar");

            if (lp == null) {
                continue;
            }

            switch (lp.getStatus()) {
                case GESPERRT:
                    button.getStyleClass().add("gesperrt");
                    break;
                case VERMIETET:
                    button.getStyleClass().add("vermietet");
                    break;
                case BELEGT:
                    button.getStyleClass().add("belegt");
                    break;
                case VERFUEGBAR:
                    button.getStyleClass().add("verfuegbar");
                    break;
            }
        }
    }
}