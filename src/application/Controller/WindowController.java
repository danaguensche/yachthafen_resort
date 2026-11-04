package application.Controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

//Methoden für die Fenstersteuerung 

public abstract class WindowController {

    @FXML
    private HBox titleBar;

    private double xOffset;
    private double yOffset;

    @FXML
    protected void minimizeWindow() {
        getStage().setIconified(true);
    }

    @FXML
    protected void maximizeWindow() {
        Stage stage = getStage();
        stage.setMaximized(!stage.isMaximized());
    }

    @FXML
    protected void closeWindow() {
        getStage().close();
    }

    @FXML
    protected void onTitlePressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    @FXML
    protected void onTitleDragged(MouseEvent event) {
        Stage stage = getStage();

        if (!stage.isMaximized()) {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        }
    }

    protected Stage getStage() {
        return (Stage) titleBar.getScene().getWindow();
    }
}