package System.data;

import System.Aplikacja;

import System.Users.Uzytkownik;
import sun.util.calendar.BaseCalendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Klasa reprezentujaca wypozyczenie
 */
public class Wypozyczenie {
    private Integer id_wypozyczajacego;
    private Date data_wypozyczenia;
    private Date data_zwrotu;
    private Double koszt_wypozyczenia;
    private ArrayList<Egzemplarz> egzemplarze;


    /**
     * metoda wydluza okres wypozyczenia
     *
     * @param nowaData nowa data zwrotu
     */
    public void wydluzWypozyczenie(Date nowaData) {
        setData_zwrotu(nowaData);
    }

    /**
     * Nalicza dodatkowa oplate w wyozyczeniu
     *
     * @param oplata naliczona oplata
     */
    public void naliczDodatkowaOplate(Double oplata) {
        setKoszt_wypozyczenia(getKoszt_wypozyczenia() + oplata);
    }

    /**
     * Konstruktor
     *
     * @param id_wypozyczajacego nr idendyfikacjyjny wypozyczenia
     * @param data_wypozyczenia  data wypozyczenia
     * @param data_zwrotu        data zwtotu
     * @param model              model wyporzyczanego sprzetu
     * @param ilosc              ilosc wyporzyczanych egzemplarzy
     * @throws Exception brak wystarczajacej liczby dostepnych egzemplarzy
     */
    public Wypozyczenie(Integer id_wypozyczajacego, Date data_wypozyczenia, Date data_zwrotu, Model model, Integer ilosc) throws Exception {
        this.id_wypozyczajacego = id_wypozyczajacego;
        this.data_wypozyczenia = data_wypozyczenia;
        this.data_zwrotu = data_zwrotu;
        this.egzemplarze = new ArrayList<>();
        this.koszt_wypozyczenia = (data_zwrotu.getTime() - data_wypozyczenia.getTime()) * model.getCenaZaDzienWypozyczenia();
        if (model.getIlosDostepnychEgzemplarzy() > ilosc)
            throw new Exception("Brak dostepnych egzemplarzy");
        for (Egzemplarz e : model.getEgzemplarze())
            if (e.getStan_egzemplarza().equals(StanSprzetu.DOSTEPNY)) {
                this.egzemplarze.add(e);
                e.zmienStanSprzetu(StanSprzetu.NIEDOSTEPNY);
            }
    }

    public Wypozyczenie(Integer id_wypozyczajacego, Date data_wypozyczenia, Date data_zwrotu, String nazwa, Integer ilosc) throws Exception {
        Model model = Uzytkownik.wyszukajModel(nazwa);
        new Wypozyczenie(id_wypozyczajacego, data_wypozyczenia, data_zwrotu, model, ilosc);

    }

    Model getModel(){
        return egzemplarze.get(0).getModel();
    }

    /////////////////////////// gettery i settery ///////////////////////////////////////
    public void setData_zwrotu(Date data_zwrotu) {
        this.data_zwrotu = data_zwrotu;
    }

    public void setKoszt_wypozyczenia(Double koszt_wypozyczenia) {
        this.koszt_wypozyczenia = koszt_wypozyczenia;
    }

    public Integer getId_wypozyczajacego() {
        return id_wypozyczajacego;
    }

    public Date getData_wypozyczenia() {
        return data_wypozyczenia;
    }

    public Date getData_zwrotu() {
        return data_zwrotu;
    }

    public Double getKoszt_wypozyczenia() {
        return koszt_wypozyczenia;
    }

    public ArrayList<Egzemplarz> getEgzemplarze() {
        return egzemplarze;
    }

    public void addToEgzemplarze(Egzemplarz e){
        this.egzemplarze.add(e);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wypozyczenie)) return false;

        Wypozyczenie that;
        that = (Wypozyczenie) o;

        if (!getId_wypozyczajacego().equals(that.getId_wypozyczajacego())) return false;
        if (getData_wypozyczenia() != null ? !getData_wypozyczenia().equals(that.getData_wypozyczenia()) : that.getData_wypozyczenia() != null)
            return false;
        if (getData_zwrotu() != null ? !getData_zwrotu().equals(that.getData_zwrotu()) : that.getData_zwrotu() != null)
            return false;
        if (getKoszt_wypozyczenia() != null ? !getKoszt_wypozyczenia().equals(that.getKoszt_wypozyczenia()) : that.getKoszt_wypozyczenia() != null)
            return false;
        return getEgzemplarze() != null ? getEgzemplarze().equals(that.getEgzemplarze()) : that.getEgzemplarze() == null;
    }

    @Override
    public int hashCode() {
        int result = getId_wypozyczajacego().hashCode();
        result = 31 * result + (getData_wypozyczenia() != null ? getData_wypozyczenia().hashCode() : 0);
        result = 31 * result + (getData_zwrotu() != null ? getData_zwrotu().hashCode() : 0);
        result = 31 * result + (getKoszt_wypozyczenia() != null ? getKoszt_wypozyczenia().hashCode() : 0);
        result = 31 * result + (getEgzemplarze() != null ? getEgzemplarze().hashCode() : 0);
        return result;
    }

}
