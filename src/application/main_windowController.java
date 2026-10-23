package application;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class main_windowController {

    @FXML
    private HBox titleBar;

    private double xOffset;
    private double yOffset;

    @FXML
    private void minimizeWindow() {
        getStage().setIconified(true);
    }

    @FXML
    private void maximizeWindow() {
        Stage stage = getStage();
        stage.setMaximized(!stage.isMaximized());
    }

    @FXML
    private void closeWindow() {
        getStage().close();
    }

    @FXML
    private void onTitlePressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    @FXML
    private void onTitleDragged(MouseEvent event) {
        Stage stage = getStage();

        if (!stage.isMaximized()) {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        }
    }

    private Stage getStage() {
        return (Stage) titleBar.getScene().getWindow();
    }
}