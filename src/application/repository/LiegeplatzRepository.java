package application.repository;

import application.model.Liegeplatz;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import application.model.Liegeplatz.Status;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/*
Das Repository ist die einzige Klasse, die weiß WIE und WO die Liegeplätze gespeichert sind 
(Für dieses Projekt als JSON, aber ein Repository könnte auch eine Datenbankverbindung enthalten)

Alle anderen Klassen arbeiten nur mit List<Liegeplätze> und wissen nichts über JSON oder Dateien

Aufgaben der Klasse:

laden() -> JSON Datei lesen -> Liste von Liegeplatz-Objekten
speichern() -> Liste von Liegeplatz-Objekten -> JSON-Datei
*/

public class LiegeplatzRepository {

    /*
     * GSON ist eine Bibliothek, die JSON in Objekte umwandelt und umgekehrt
     * Es wird eine gson Instanz gebaut und innerhalb dieses Repositorys immer
     * wieder verwendet
     * 
     * setPrettyPrinting() sorgt dafür, dass die Datei beim Speichern eingerückt und
     * lesbar geschrieben wird (statt alles in einer Zeile)
     */
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /*
     * Liste mit Liegeplatzobjekten <Generics> als Rückgabetyp
     * Notiz: Generics werden verwenden, um erkenntlich zu machen welcher Datentyp
     * in einer Liste vorhanden sein darf, um zu verhindern, dass falsche Objekte
     * oder Typen reingesteckt werden.
     * Primitive Datentypen sind nicht erlaubt, also nur Objekte (z.B. Integer statt
     * int)
     * nimmt ein Path-Objekt -> also z.B.
     * laden('src\application\repository\LiegeplatzRepository.java')
     * throws IOException -> Methode warnt, dass eine IOException auftreten kann,
     * wenn z.B. keine Datei gefunden werden konnte
     * wenn die Methode aufgerufen wird, muss der Fehler dann mit try/catch
     * abgefangen werden
     */
    public List<Liegeplatz> laden(Path datei) throws IOException {
        // leere Liste
        List<Liegeplatz> liegeplaetze = new ArrayList<>();

        /*
         * try-with-resources: Der Reader wird am Ende des Blocks
         * automatisch geschlossen, auch wenn ein Fehler auftritt.
         * Man muss also nie selbst reader.close() aufrufen.
         *
         * UTF-8 geben wir explizit an, damit Umlaute (ä, ö, ü) auf
         * jedem Rechner richtig gelesen werden.
         */

        try (Reader reader = Files.newBufferedReader(datei, StandardCharsets.UTF_8)) {

            /*
             * den gesamten Text lesen und als JSON Baum darstellen
             * Array sollte so aussehen [{...}, {...}, {...}]
             */

            JsonArray array = JsonParser.parseReader(reader).getAsJsonArray();

            // Jedes Element des Array ist ein JSON-Objekt und ein Liegeplatz
            for (JsonElement element : array) {
                JsonObject o = element.getAsJsonObject();

                Liegeplatz liegeplatz = new Liegeplatz(
                        o.get("id").getAsInt(),
                        o.get("bezeichnung").getAsString(),
                        o.get("laenge").getAsDouble(),
                        o.get("breite").getAsDouble(),
                        o.get("wassertiefe").getAsInt(),
                        Status.valueOf(o.get("status").getAsString()));

                liegeplaetze.add(liegeplatz);
            }
        }

        return liegeplaetze;
    }

    /*
     * Gegenstück zu laden(): nimmt die Liste mit Liegeplatz-Objekten und
     * schreibt sie als JSON-Array in die Datei
     * Die Datei wird dabei komplett überschrieben, d.h. es wird immer die
     * gesamte Liste gespeichert und nicht nur einzelne Änderungen
     *
     * throws IOException -> z.B. wenn der Ordner nicht existiert oder die Datei
     * schreibgeschützt ist, auch hier muss der Aufrufer try/catch verwenden
     */
    public void speichern(Path datei, List<Liegeplatz> liegeplaetze) throws IOException {
        // leeres JSON-Array, wird gleich mit den Liegeplätzen gefüllt
        JsonArray array = new JsonArray();

        /*
         * Jeder Liegeplatz wird zu einem JSON-Objekt umgebaut
         * Die Schlüssel müssen genau so heißen wie in laden(), sonst kann
         * die Datei beim nächsten Start nicht mehr gelesen werden
         */
        for (Liegeplatz liegeplatz : liegeplaetze) {
            JsonObject o = new JsonObject();

            o.addProperty("id", liegeplatz.getId());
            o.addProperty("bezeichnung", liegeplatz.getBezeichnung());
            o.addProperty("laenge", liegeplatz.getLaenge());
            o.addProperty("breite", liegeplatz.getBreite());
            o.addProperty("wassertiefe", liegeplatz.getWassertiefe());

            /*
             * name() gibt den Namen der Konstante zurück (z.B. "VERFUEGBAR")
             * NICHT getAnzeigeName() verwenden ("Verfügbar"), denn
             * Status.valueOf() in laden() erwartet genau den Konstantennamen
             */
            o.addProperty("status", liegeplatz.getStatus().name());

            array.add(o);
        }

        /*
         * try-with-resources wie bei laden(): der Writer wird automatisch
         * geschlossen, dadurch wird der Inhalt auch wirklich in die Datei geschrieben
         *
         * gson.toJson() schreibt das Array dank setPrettyPrinting() eingerückt
         * in die Datei
         */
        try (Writer writer = Files.newBufferedWriter(datei, StandardCharsets.UTF_8)) {
            gson.toJson(array, writer);
        }
    }
}
