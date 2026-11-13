package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import application.model.Liegeplatz;
import application.model.Liegeplatz.Status;
import application.model.LiegeplatzVerwaltung;

import java.io.IOException;

public class DialogController extends WindowController {
    @FXML private TextField bezeichnungInput, laengeInput, breiteInput, wassertiefeInput;
    @FXML private ComboBox<Status> statusSelect;

    private LiegeplatzVerwaltung verwaltung;
    private Liegeplatz aktuell;

    public void setVerwaltung(LiegeplatzVerwaltung v) { this.verwaltung = v; }

    @FXML
    private void initialize() {
        statusSelect.getItems().setAll(Status.values());
    }

    public void zeigeLiegeplatz(Liegeplatz lp) {
        aktuell = lp;
        bezeichnungInput.setText(lp.getBezeichnung());
        laengeInput.setText(String.valueOf(lp.getLaenge()));
        breiteInput.setText(String.valueOf(lp.getBreite()));
        wassertiefeInput.setText(String.valueOf(lp.getWassertiefe()));
        statusSelect.setValue(lp.getStatus());
    }

    @FXML
    private void speichern() {
        try {
            aktuell.setBezeichnung(bezeichnungInput.getText());
            aktuell.setLaenge(Double.parseDouble(laengeInput.getText()));
            aktuell.setBreite(Double.parseDouble(breiteInput.getText()));
            aktuell.setWassertiefe(Integer.parseInt(wassertiefeInput.getText()));
            aktuell.setStatus(statusSelect.getValue());
            verwaltung.wurdeGeaendert(aktuell);
            verwaltung.speichern();
        } catch (IllegalArgumentException e) { 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}