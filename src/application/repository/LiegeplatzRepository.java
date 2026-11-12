package application.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
    GSON ist eine Bibliothek, die JSON in Objekte umwandelt und umgekehrt
    Es wird eine gson Instanz gebaut und innerhalb dieses Repositorys immer wieder verwendet

    setPrettyPrinting() sorgt dafür, dass die Datei beim Speichern eingerückt und lesbar geschrieben wird (statt alles in einer Zeile)
    */
 private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
}
