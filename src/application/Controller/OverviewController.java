package application.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class OverviewController extends WindowController {

    @FXML
    private Label lockedCountLabel;

    @FXML
    private Label rentedCountLabel;

    @FXML
    private Label blockedCountLabel;

    @FXML
    private Label availableCountLabel;

    private int lockedCount = 1;
    private int rentedCount = 5;
    private int blockedCount = 3;
    private int availableCount = 2;

    private Scene dialogScene;
    private Stage dialogStage;

    @FXML
    private void initialize() {
        updateCountLabels();
    }

    private void updateCountLabels() {
        lockedCountLabel.setText(Integer.toString(lockedCount));
        rentedCountLabel.setText(Integer.toString(rentedCount));
        blockedCountLabel.setText(Integer.toString(blockedCount));
        availableCountLabel.setText(Integer.toString(availableCount));
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
