package application.test;

import application.model.Liegeplatz;
import application.repository.LiegeplatzRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class LiegeplatzRepositoryTest {

    public static void main(String[] args) {
        LiegeplatzRepository repo = new LiegeplatzRepository();

        try {
            // 1. laden
            List<Liegeplatz> liste = repo.laden(Path.of("src/application/storage/liegeplaetze.json"));
            System.out.println("Anzahl geladen: " + liste.size());
            for (Liegeplatz lp : liste) {
                System.out.println(lp);
            }

            // 2. in eine neue Datei speichern
            Path neueDatei = Path.of("src/application/storage/liegeplaetze_test.json");
            repo.speichern(neueDatei, liste);
            System.out.println("Gespeichert in: " + neueDatei);

        } catch (IOException e) {
            System.out.println("Fehler beim Testen des Repositorys:");
            e.printStackTrace();
        }
    }
}