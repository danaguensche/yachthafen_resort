package application.model;

public class Liegeplatz {

    public enum Status {
        GESPERRT("Gesperrt"),
        VERMIETET("Vermietet"),
        BELEGT("Belegt"),
        VERFUEGBAR("Verfügbar");

        private final String anzeigeName;

        Status(String anzeigeName) {
            this.anzeigeName = anzeigeName;
        }

        public String getAnzeigeName() {
            return anzeigeName;
        }
    }

    private static final double MAX_ABMESSUNG = 100;   // meter
    private static final int MAX_WASSERTIEFE = 1000;   // cm

    private final int id;
    private String bezeichnung;
    private double laenge;
    private double breite;
    private int wassertiefe;
    private Status status;

    public Liegeplatz(int id, String bezeichnung, double laenge,
                      double breite, int wassertiefe, Status status) {
        this.id = id;
        setBezeichnung(bezeichnung);
        setLaenge(laenge);
        setBreite(breite);
        setWassertiefe(wassertiefe);
        setStatus(status);
    }

    public int getId() {
        return id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        if (bezeichnung == null || bezeichnung.isBlank()) {
            throw new IllegalArgumentException("Bezeichnung darf nicht leer sein");
        }
        this.bezeichnung = bezeichnung;
    }

    public double getLaenge() {
        return laenge;
    }

    public void setLaenge(double laenge) {
        if (!isValidAbmessung(laenge)) {
            throw new IllegalArgumentException("Ungültige Länge: " + laenge);
        }
        this.laenge = round(laenge);
    }

    public double getBreite() {
        return breite;
    }

    public void setBreite(double breite) {
        if (!isValidAbmessung(breite)) {
            throw new IllegalArgumentException("Ungültige Breite: " + breite);
        }
        this.breite = round(breite);
    }

    public int getWassertiefe() {
        return wassertiefe;
    }

    public void setWassertiefe(int wassertiefe) {
        if (wassertiefe < 0 || wassertiefe > MAX_WASSERTIEFE) {
            throw new IllegalArgumentException("Ungültige Wassertiefe: " + wassertiefe);
        }
        this.wassertiefe = wassertiefe;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        if (status == null) {
            throw new IllegalArgumentException("Status darf nicht null sein");
        }
        this.status = status;
    }

    private boolean isValidAbmessung(double wert) {
        return wert > 0 && wert < MAX_ABMESSUNG;
    }

    private double round(double number) {
        return Math.round(number * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return bezeichnung + " (" + status.getAnzeigeName() + ")";
    }
}