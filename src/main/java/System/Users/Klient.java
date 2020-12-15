package System.Users;

import System.data.Model;
import System.data.Wypozyczenie;

import java.util.ArrayList;
import java.util.Date;

/**
 * Klasa odpowiedzialana za przechowywanie inforamcji o wypozyczeniach klienta
 */
public class Klient extends Uzytkownik {
    private final Integer idKlienta;
    private ArrayList<Wypozyczenie> wypozyczenia;
    private Double naleznoscDoZaplaty;

    /**
     * Metoda wywowywana jezeli jeden z egzemplarzy wypozyczenia zostal zniszczony/zgubiony
     * @param w w ktorym wypozyczeniu zgubiono sprzet
     * @param ilosc ilosc ile przedmiotow z wypozyczenia zgubiono
     */
    public void zglosZgubienieZniszczenia(Wypozyczenie w,Integer ilosc){
        setNaleznoscDoZaplaty(getNaleznoscDoZaplaty()+w.getEgzemplarze().get(0).getModel().getCenaZaUszedzenia()*ilosc);
    }

    /**
     * Moetoda wykorzystywana do przedluzania wypozyczenia
     * @param date nowa data wypozyczenia
     * @param wypozyczenie ktore wypozyczenie przedluza
     * @throws Exception jezeli nie ma dostepnych egzemplarzy do wypozyczenia
     */
    public void wydluzWypozyczenie(Date date,Wypozyczenie wypozyczenie) throws Exception {
        if(wypozyczenie.getEgzemplarze().get(0).getModel().getIlosDostepnychEgzemplarzy()<
                 wypozyczenie.getEgzemplarze().size())
            throw new Exception("Brak dsotepnych egzemplarzy");
        else
            setNaleznoscDoZaplaty(wypozyczenie.getEgzemplarze().get(0).getModel().getCenaZaDzienWypozyczenia()*
                (date.getTime()-wypozyczenie.getData_zwrotu().getTime()));
    }

    /**
     * Metoda wypozycza sprzet
     * @param model jaki model sprzetu wypozyczasz
     * @param dateWyp data wypozyczenia
     * @param dateZwrot data zwrotu
     * @param ilosc ilosc wypozyczanego sprzetu
     * @throws Exception jezeli nie ssa dostepne zadne sprzety
     */
    public void wypozyczSprzet(Model model,Date dateWyp,Date dateZwrot,Integer ilosc) throws Exception {
        dodajDoWypozyczenia(new Wypozyczenie(idKlienta,dateWyp,dateZwrot,model,ilosc));
    }

    /**
     * //todo metoda do uzupelnienia
     * Wyszukuje modele po danje nazwie???
     * @param nazwa nazwa szukanego modelu
     * @return referencje do modelu
     */
    public Model wyszukajModel(String nazwa){
        return null;
    }

    /**
     * Dodaje wypozyczenie do arrayListy
     * @param w dodawne wypozyczenie
     */
    public void dodajDoWypozyczenia(Wypozyczenie w){
        wypozyczenia.add(w);
    }

    /**
     * Konstruktor
     * @param imie imie klienta
     * @param nazwisko nazwisko klienta
     * @param login login klienta
     * @param haslo haslo klienta
     * @param email email kleinta
     * @param user typ uzytkownika//todo na pewno to powinoo byc tez pobierane
     * @param idKlienta id klienta //przydal by sie kostruktor z jakims przydzieleaniem id klienta
     */
    public Klient(String imie, String nazwisko, String login, String haslo, String email, TypUzytkownika user, Integer idKlienta) {
        super(imie, nazwisko, login, haslo, email, user);
        this.idKlienta = idKlienta;
        naleznoscDoZaplaty=0.0;
        wypozyczenia=new ArrayList<>();
    }

    ///////////////////////////// Gettery i settery ////////////////////////
    public Double getNaleznoscDoZaplaty() {
        return naleznoscDoZaplaty;
    }

    public void setNaleznoscDoZaplaty(Double naleznoscDoZaplaty) {
        this.naleznoscDoZaplaty = naleznoscDoZaplaty;
    }

    public Wypozyczenie getWypozyczenie(int id) {
        return wypozyczenia.get(id);
    }
    public Integer getiloscWypozyczen(){return  wypozyczenia.size();}

    public Integer getIdKlienta() {
        return idKlienta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Klient)) return false;

        Klient klient = (Klient) o;

        if (!getIdKlienta().equals(klient.getIdKlienta())) return false;
        return getNaleznoscDoZaplaty() != null ? getNaleznoscDoZaplaty().equals(klient.getNaleznoscDoZaplaty()) : klient.getNaleznoscDoZaplaty() == null;
    }

    @Override
    public int hashCode() {
        int result = getIdKlienta().hashCode();
        result = 31 * result + (getNaleznoscDoZaplaty() != null ? getNaleznoscDoZaplaty().hashCode() : 0);
        return result;
    }
}
