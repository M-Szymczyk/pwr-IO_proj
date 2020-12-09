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

    public Model(String nazwaModelu, Double cenaZaDzienWypozyczenia,
                 Double cenaZaUszedzenia, Kategoria kategoria) {
        this.egzemplarze = new ArrayList<>();
        int ilosc=0;
        for(Egzemplarz e :egzemplarze)
            if(e.getStan_egzemplarza().equals(StanSprzetu.DOSTEPNY))
                ilosc++;
        this.ilosDostepnychEgzemplarzy = ilosc;
        this.nazwaModelu = nazwaModelu;
        this.cenaZaDzienWypozyczenia = cenaZaDzienWypozyczenia;
        this.cenaZaUszedzenia = cenaZaUszedzenia;
        this.kategoria = kategoria;
        this.opis = "";
    }
    public Model(String nazwaModelu, Kategoria kategoria) {
        this.egzemplarze = new ArrayList<>();
        this.ilosDostepnychEgzemplarzy = 0;
        this.nazwaModelu = nazwaModelu;
        this.cenaZaDzienWypozyczenia = 0.0;
        this.cenaZaUszedzenia = 0.0;
        this.kategoria = kategoria;
        this.opis = "";
    }

    public void usunEgzemplarz(int numerSeryjny){
        egzemplarze.removeIf(e -> e.getNumer_seryjny().equals(numerSeryjny));
    }

    public void setEgzemplarze(ArrayList<Egzemplarz> egzemplarze) {
        int ilosc=0;
        this.egzemplarze = egzemplarze;
        for(Egzemplarz e :egzemplarze)
            if(e.getStan_egzemplarza().equals(StanSprzetu.DOSTEPNY))
                ilosc++;
        ilosDostepnychEgzemplarzy=ilosc;
    }

    public Integer getIlosDostepnychEgzemplarzy() {
        return ilosDostepnychEgzemplarzy;
    }

    public void setIlosDostepnychEgzemplarzy(Integer ilosDostepnychEgzemplarzy) {
        this.ilosDostepnychEgzemplarzy = ilosDostepnychEgzemplarzy;
    }

    public String getNazwa() {
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
