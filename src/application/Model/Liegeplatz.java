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

    public Liegeplatz(int id, String bezeichnung, float laenge, float breite, int wassertiefe, String status) {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.laenge = laenge;
        this.breite = breite;
        this.wassertiefe = wassertiefe;
        if (!this.validateStatus(status)) {
            throw new IllegalArgumentException(
                    "Ungültiger Status: " + status);
        }
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

    public void setLaenge(float laenge) {
        this.laenge = laenge;
    }

    public float getBreite() {
        return this.breite;
    }

    public void setBreite(float breite) {
        this.breite = breite;
    }

    public int getWassertiefe() {
        return this.wassertiefe;
    }

    public void setWassertiefe(int wassertiefe) {
        this.wassertiefe = wassertiefe;
    }

    public String getStatus() {
        return this.status;
    }

    public String setStatus(String status) {
        if (this.validateStatus(status)) {
            this.status = status;
            return "Status wurde erfolgreich geändert";
        } else {
            return "Der Status enthält keinen gültigen Wert.";
        }
    }

    public boolean validateStatus(String status) {
        for (int i = 0; i < this.allowedStatusValues.length; i++) {
            if (allowedStatusValues[i].equals(status)) {
                return true;
        }
        return false;
    }
}
