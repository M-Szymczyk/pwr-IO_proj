package System.data;

import java.util.ArrayList;

public class Model {
    private ArrayList<Egzemplarz> egzemplarze;
    private Integer ilosDostepnychEgzemplarzy;
    private String nazwaModelu;
    private Double cenaZaDzienWypozyczenia;
    private Double cenaZaUszedzenia;
    private Kategoria kategoria;
    private String opis;

    public ArrayList<Egzemplarz> getEgzemplarze() {
        return egzemplarze;
    }

    public Model(ArrayList<Egzemplarz> egzemplarze, Integer ilosDostepnychEgzemplarzy,
                 String nazwaModelu, Double cenaZaDzienWypozyczenia,
                 Double cenaZaUszedzenia, Kategoria kategoria, String opis) {
        this.egzemplarze = egzemplarze;
        this.ilosDostepnychEgzemplarzy = ilosDostepnychEgzemplarzy;
        this.nazwaModelu = nazwaModelu;
        this.cenaZaDzienWypozyczenia = cenaZaDzienWypozyczenia;
        this.cenaZaUszedzenia = cenaZaUszedzenia;
        this.kategoria = kategoria;
        this.opis = opis;
    }

    public void usunEgzemplarz(int numerSeryjny){

    }

    public void setEgzemplarze(ArrayList<Egzemplarz> egzemplarze) {
        this.egzemplarze = egzemplarze;
    }

    public Integer getIlosDostepnychEgzemplarzy() {
        return ilosDostepnychEgzemplarzy;
    }

    public void setIlosDostepnychEgzemplarzy(Integer ilosDostepnychEgzemplarzy) {
        this.ilosDostepnychEgzemplarzy = ilosDostepnychEgzemplarzy;
    }

    public String getNazwaModelu() {
        return nazwaModelu;
    }

    public void setNazwaModelu(String nazwaModelu) {
        this.nazwaModelu = nazwaModelu;
    }

    public Double getCenaZaDzienWypozyczenia() {
        return cenaZaDzienWypozyczenia;
    }

    public void setCenaZaDzienWypozyczenia(Double cenaZaDzienWypozyczenia) {
        this.cenaZaDzienWypozyczenia = cenaZaDzienWypozyczenia;
    }

    public Double getCenaZaUszedzenia() {
        return cenaZaUszedzenia;
    }

    public void setCenaZaUszedzenia(Double cenaZaUszedzenia) {
        this.cenaZaUszedzenia = cenaZaUszedzenia;
    }

    public Kategoria getKategoria() {
        return kategoria;
    }

    public void setKategoria(Kategoria kategoria) {
        this.kategoria = kategoria;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
