package System.data;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Klasa przechowuje informacje o danym modelu
 */
public class Model {
    private ArrayList<Egzemplarz> egzemplarze;
    private Integer ilosDostepnychEgzemplarzy;
    private String nazwaModelu;
    private Double cenaZaDzienWypozyczenia;
    private Double cenaZaUszedzenia;
    private Kategoria kategoria;
    private String opis;

    /**
     * @return arrayList egzemplarzy
     */
    public ArrayList<Egzemplarz> getEgzemplarze() {
        return egzemplarze;
    }

    /**
     * kostruktor
     * @param nazwaModelu nazwa
     * @param cenaZaDzienWypozyczenia cena za dzien
     * @param cenaZaUszedzenia cena wykorzystywana w razie zniszczenia lub uszkodzenia jednego z egzemplarzy
     * @param kategoria w jakiej kategorii jest model
     */
    public Model(String nazwaModelu, Double cenaZaDzienWypozyczenia,
                 Double cenaZaUszedzenia, Kategoria kategoria) {
        this.egzemplarze = new ArrayList<>();

        this.ilosDostepnychEgzemplarzy = 0;
        this.nazwaModelu = nazwaModelu;
        this.cenaZaDzienWypozyczenia = cenaZaDzienWypozyczenia;
        this.cenaZaUszedzenia = cenaZaUszedzenia;
        this.kategoria = kategoria;
        this.opis = "";
    }

    /**
     * kostruktor
     * @param nazwaModelu nazwa
     * @param kategoria w jakiej kategorii jest model
     *
     */
    public Model(String nazwaModelu, Kategoria kategoria) {
        this.egzemplarze = new ArrayList<>();
        this.ilosDostepnychEgzemplarzy = 0;
        this.nazwaModelu = nazwaModelu;
        this.cenaZaDzienWypozyczenia = 0.0;
        this.cenaZaUszedzenia = 0.0;
        this.kategoria = kategoria;
        this.opis = "";
    }

    /**
     * metoda usuwa pojednczy egzemplarz z listy modeli
     * @param numerSeryjny numer seryjny modeli
     */
    public void usunEgzemplarz(int numerSeryjny){
        egzemplarze.removeIf(e -> e.getNumer_seryjny().equals(numerSeryjny));
        //todo pytanie czy powinno tez usuwac przypisanie modelu w egzemplarzu
    }

    /**
     * Ustawia aktualna liste egzemplarzy
     * @param egzemplarze nowa arraylista
     */
    public void setEgzemplarze(ArrayList<Egzemplarz> egzemplarze) {
        int ilosc=0;
        this.egzemplarze = egzemplarze;
        for(Egzemplarz e :egzemplarze)
            if(e.getStan_egzemplarza().equals(StanSprzetu.DOSTEPNY))
                ilosc++;
        ilosDostepnychEgzemplarzy=ilosc;
    }

    /**
     * dodaje pojedynczy egzemplarz do listy
     * @param egzemplarz dodawny egzemplarz
     */
    public void dodajEgzemplarz(Egzemplarz egzemplarz){
        this.ilosDostepnychEgzemplarzy++;
        egzemplarze.add(egzemplarz);
    }


    /*Gettery i Settery\*/
///////////////////////////////////////////////////////////////////////////////////////////////
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Model)) return false;

        Model model = (Model) o;

        if (getEgzemplarze() != null ? !getEgzemplarze().equals(model.getEgzemplarze()) : model.getEgzemplarze() != null)
            return false;
        if (!getIlosDostepnychEgzemplarzy().equals(model.getIlosDostepnychEgzemplarzy())) return false;
        if (!Objects.equals(nazwaModelu, model.nazwaModelu)) return false;
        if (getCenaZaDzienWypozyczenia() != null ? !getCenaZaDzienWypozyczenia().equals(model.getCenaZaDzienWypozyczenia()) : model.getCenaZaDzienWypozyczenia() != null)
            return false;
        if (getCenaZaUszedzenia() != null ? !getCenaZaUszedzenia().equals(model.getCenaZaUszedzenia()) : model.getCenaZaUszedzenia() != null)
            return false;
        if (!getKategoria().equals(model.getKategoria())) return false;
        return getOpis() != null ? getOpis().equals(model.getOpis()) : model.getOpis() == null;
    }

    @Override
    public int hashCode() {
        int result =  getIlosDostepnychEgzemplarzy().hashCode();
        result = 31 * result + (nazwaModelu != null ? nazwaModelu.hashCode() : 0);
        result = 31 * result + (getCenaZaDzienWypozyczenia() != null ? getCenaZaDzienWypozyczenia().hashCode() : 0);
        result = 31 * result + (getCenaZaUszedzenia() != null ? getCenaZaUszedzenia().hashCode() : 0);
        result = 31 * result + getKategoria().hashCode();
        result = 31 * result + (getOpis() != null ? getOpis().hashCode() : 0);
        return result;
    }
}
