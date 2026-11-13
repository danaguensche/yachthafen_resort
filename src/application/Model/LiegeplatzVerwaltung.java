package application.model;

import application.model.Liegeplatz.Status;
import application.repository.LiegeplatzRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.nio.file.Path;

public class LiegeplatzVerwaltung {

    private final LiegeplatzRepository repo = new LiegeplatzRepository();
    private final Path datei;

    //andere Klassen können sich benachrichtigen lassen, wenn sich etwas ändert
    private final ObservableList<Liegeplatz> liegeplaetze = FXCollections.observableArrayList();

    public LiegeplatzVerwaltung(Path datei) {
        this.datei = datei;
    }

    public void laden() throws IOException {
        liegeplaetze.setAll(repo.laden(datei));
    }

    public void speichern() throws IOException {
        repo.speichern(datei, liegeplaetze);
    }

    public ObservableList<Liegeplatz> getLiegeplaetze() {
        return liegeplaetze;
    }

    public Liegeplatz findeNachId(int id) {
        for (Liegeplatz lp : liegeplaetze) {
            if (lp.getId() == id) return lp;
        }
        return null;
    }

    public int anzahl(Status status) {
        int n = 0;
        for (Liegeplatz lp : liegeplaetze) {
            if (lp.getStatus() == status) n++;
        }
        return n;
    }

    // Nach einer Änderung an einem Objekt aufrufen set() an derselben Stelle
    // löst ein Change-Event aus, damit die Übersicht neu zählt
    public void wurdeGeaendert(Liegeplatz lp) {
        int i = liegeplaetze.indexOf(lp);
        if (i >= 0) liegeplaetze.set(i, lp);
    }
}