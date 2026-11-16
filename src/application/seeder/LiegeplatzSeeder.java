package application.seeder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import application.model.Liegeplatz;
import application.model.Liegeplatz.Status;
import application.repository.LiegeplatzRepository;
import java.nio.file.Path;
import java.io.IOException;
public class LiegeplatzSeeder {

    private final LiegeplatzRepository repo = new LiegeplatzRepository();
    private final Random rnd = new Random();
    private final List<Liegeplatz> liegeplaetze = new ArrayList<>();

    private final char[] buchstaben = { 'A', 'B', 'C', 'D', 'E', 'F' };
    private final Status[] alleStatus = Status.values();


    public void seed(int anzahl, Path datei) {
        generateLiegeplaetze(anzahl);
        try {
            repo.speichern(datei, liegeplaetze);
        } catch (IOException e) {
            System.out.println("Fehler beim Speichern: " + e.getMessage());
        }
    }

    private void generateLiegeplaetze(int anzahl) {
        for (int i = 1; i <= anzahl; i++) {
            char buchstabe = buchstaben[rnd.nextInt(buchstaben.length)];
            String bezeichnung = buchstabe + "-" + i;
            double laenge = rnd.nextDouble(1.0, 25.0);
            double breite = rnd.nextDouble(1.0, 6.0); 
            int wassertiefe = rnd.nextInt(10, 501); 
            Status status = alleStatus[rnd.nextInt(alleStatus.length)];

            Liegeplatz liegeplatz = new Liegeplatz(i, bezeichnung, laenge, breite, wassertiefe, status);
            liegeplaetze.add(liegeplatz);
        }
    }
}