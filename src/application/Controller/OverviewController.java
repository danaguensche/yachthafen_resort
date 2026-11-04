package application.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
}