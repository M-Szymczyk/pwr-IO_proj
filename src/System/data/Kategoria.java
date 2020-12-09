package System.data;

public class Kategoria {
    private String nazwaKategorii;
    private String opis;

    public Kategoria(String nazwaKategorii, String opis) {
        this.nazwaKategorii = nazwaKategorii;
        this.opis = opis;
    }

    public String getNazwaKategorii() {
        return nazwaKategorii;
    }

    public void setNazwaKategorii(String nazwaKategorii) {
        this.nazwaKategorii = nazwaKategorii;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
