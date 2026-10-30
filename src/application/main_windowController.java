// Diese Klasse gehört zum Package "application".
// Packages dienen dazu, Java-Klassen zu organisieren.
package application;

// Imports machen Klassen und Annotationen aus JavaFX verfügbar.
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

//Label import für Checkboxen
import javafx.scene.control.Label;

// Der Controller enthält die Aktionen für die Oberfläche
// "public" bedeutet, dass die Klasse auch außerhalb ihres Packages
// verwendet werden kann
public class main_windowController {

    // @FXML verbindet dieses Feld mit einem Element aus der FXML-Datei
    // Dort muss die HBox das Attribut fx:id="titleBar" besitzen
    //
    // Eine HBox ist ein Layout-Container, der seine Elemente
    // horizontal nebeneinander anordnet
    //
    // "private" bedeutet: Nur diese Klasse kann direkt
    // auf das Feld zugreifen
    @FXML
    private HBox titleBar;

    // Diese Felder speichern, an welcher Stelle innerhalb der Scene
    // die Maus beim Drücken auf die Titelleiste war
    //
    // "double" ist ein Zahlentyp, der auch Nachkommastellen erlaubt
    private double xOffset;
    private double yOffset;

    // Labels für die Checkboxen
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

    // Überträgt die aktuellen Zahlen in die Oberfläche
    private void updateCountLabels() {
        lockedCountLabel.setText(Integer.toString(lockedCount));
        rentedCountLabel.setText(Integer.toString(rentedCount));
        blockedCountLabel.setText(Integer.toString(blockedCount));
        availableCountLabel.setText(Integer.toString(availableCount));
    }

    // Minimiert das Fenster
    //
    // @FXML erlaubt, diese private Methode aus der FXML-Datei
    // aufzurufen, beispielsweise mit onAction="#minimizeWindow"
    //
    // "void" bedeutet, dass die Methode keinen Wert zurückgibt
    @FXML
    private void minimizeWindow() {

        // getStage() liefert das Fenster
        // setIconified(true) minimiert es
        getStage().setIconified(true);
    }

    // Wechselt zwischen maximierter und normaler Fenstergröße
    @FXML
    private void maximizeWindow() {

        // Holt das Fenster und speichert die Referenz darauf
        // in der lokalen Variable "stage"
        //
        // "Stage" ist der Typ, "stage" ist der Variablenname
        Stage stage = getStage();

        // isMaximized() liefert:
        // true = Fenster ist maximiert
        // false = Fenster ist nicht maximiert
        //
        // Dadurch wird ein normales Fenster maximiert
        // und ein maximiertes Fenster wiederhergestellt.
        stage.setMaximized(!stage.isMaximized());
    }

    // Schließt dieses Fenster
    @FXML
    private void closeWindow() {

        // Holt das Fenster und ruft dessen close()-Methode auf
        getStage().close();
    }

    // Wird aufgerufen, wenn die Maustaste auf der Titelleiste
    // gedrückt wird, sofern das Ereignis in FXML verbunden ist
    //
    // "event" ist ein Parameter
    // JavaFX übergibt darüber Informationen zum Mausereignis
    @FXML
    private void onTitlePressed(MouseEvent event) {

        // Merkt sich die horizontale Position der Maus
        // relativ zur linken Kante der Scene
        xOffset = event.getSceneX();

        // Merkt sich die vertikale Position der Maus
        // relativ zur oberen Kante der Scene
        yOffset = event.getSceneY();
    }

    // Wird beim Ziehen der Maus mit gedrückter Taste aufgerufen,
    // sofern das Ereignis in FXML verbunden ist
    @FXML
    private void onTitleDragged(MouseEvent event) {

        // Holt das Fenster, das verschoben werden soll
        Stage stage = getStage();

        // "if" führt den folgenden Block nur aus,
        // wenn die Bedingung in den Klammern true ergibt
        //
        // Hier: Fenster nur bewegen, wenn es NICHT maximiert ist
        if (!stage.isMaximized()) {

            // getScreenX() liefert die horizontale Mausposition
            // im Bildschirmkoordinatensystem
            //
            // Durch das Abziehen von xOffset bleibt der angeklickte
            // Punkt unter dem Mauszeiger
            stage.setX(event.getScreenX() - xOffset);

            // Dasselbe für die vertikale Position
            stage.setY(event.getScreenY() - yOffset);
        }
    }

    // Hilfsmethode: Ermittelt das Fenster dieser Oberfläche
    //
    // Anders als die Methoden mit "void" gibt diese Methode
    // einen Wert zurück, und zwar ein Objekt vom Typ Stage
    private Stage getStage() {

        // titleBar.getScene():
        // Ermittelt die Scene, zu der die Titelleiste gehört
        //
        // .getWindow():
        // Ermittelt das Fenster, in dem die Scene angezeigt wird
        //
        // (Stage):
        // Wandelt die Fensterreferenz in den Typ Stage um
        // Diese Typumwandlung nennt man "Cast"
        // Der Code setzt voraus, dass das Fenster eine Stage ist
        //
        // "return" gibt das Ergebnis an die aufrufende Stelle zurück
        return (Stage) titleBar.getScene().getWindow();
    }
}