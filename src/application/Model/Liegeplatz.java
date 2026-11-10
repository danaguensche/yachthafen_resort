package application.Model;

public class Liegeplatz {
    private int id;
    private String bezeichnung;
    private float laenge;
    private float breite;
    private int wassertiefe;
    private String status;
    private String[] allowedStatusValues = {
            "gesperrt",
            "vermietet",
            "belegt",
            "verfügbar"
    };

    public Liegeplatz(int id, String bezeichnung, float laenge,
            float breite, int wassertiefe, String status) {

        if (!this.validateLaenge(laenge)) {
            throw new IllegalArgumentException("Ungültige Länge: " + laenge);
        }

        if (!this.validateBreite(breite)) {
            throw new IllegalArgumentException("Ungültige Breite: " + breite);
        }

        if (!this.validateWassertiefe(wassertiefe)) {
            throw new IllegalArgumentException("Ungültige Wassertiefe: " + wassertiefe);
        }

        if (!this.validateStatus(status)) {
            throw new IllegalArgumentException("Ungültiger Status: " + status);
        }

        this.id = id;
        this.bezeichnung = bezeichnung;
        this.laenge = Math.round(laenge * 100f) / 100f;
        this.breite = Math.round(breite * 100f) / 100f;
        this.wassertiefe = wassertiefe;
        this.status = status;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBezeichnung() {
        return this.bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public float getLaenge() {
        return this.laenge;
    }

    public boolean setLaenge(float laenge) {
        if (this.validateLaenge(laenge)) {
            this.laenge = Math.round(laenge * 100f) / 100f;
            return true;
        }
        return false;
    }

    private boolean validateLaenge(float laenge) {
        return laenge >= 0 && laenge <= 100;
    }

    public float getBreite() {
        return this.breite;
    }

    public boolean setBreite(float breite) {
        if (this.validateBreite(breite)) {
            // 2 Nachkommastellen
            this.breite = Math.round(breite * 100f) / 100f;
            return true;
        }
        return false;
    }

    private boolean validateBreite(float breite) {

        return breite >= 0 && breite <= 100;
    }

    public int getWassertiefe() {
        return this.wassertiefe;
    }

    public boolean setWassertiefe(int wassertiefe) {
        if (this.validateWassertiefe(wassertiefe)) {
            this.wassertiefe = wassertiefe;
            return true;
        }
        return false;
    }

    private boolean validateWassertiefe(int wassertiefe) {

        return wassertiefe >= 0 && wassertiefe <= 1000;

    }

    public String getStatus() {
        return this.status;
    }

    public boolean setStatus(String status) {
        if (this.validateStatus(status)) {
            this.status = status;
            return true;
        }
        return false;
    }

    private boolean validateStatus(String status) {
        for (int i = 0; i < this.allowedStatusValues.length; i++) {
            // equals weil == in Java bei Strings nicht funktioniert
            if (allowedStatusValues[i].equals(status)) {
                return true;
            }
        }
        return false;
    }
}
