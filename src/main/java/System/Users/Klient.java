package System.Users;

import System.data.*;
import System.Aplikacja;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Klasa odpowiedzialana za przechowywanie inforamcji o wypozyczeniach klienta
 */
public class Klient extends Uzytkownik {
    private int idCnt = 0;
    private final Integer idKlienta;
    private ArrayList<Wypozyczenie> wypozyczenia;
    private Double naleznoscDoZaplaty;


    public void zglosZgubienieZniszczenia(String nazwa, Integer ilosc) throws Exception {
        Model model = wyszukajModel(nazwa);
        boolean znaleziono = false;
        boolean dataFail = false;
        Wypozyczenie wyp = null;
        for (Wypozyczenie x : wypozyczenia) {
            if (model.getNazwa().equals(nazwa)) {
                znaleziono = true;
                if (x.getEgzemplarze().size() < ilosc) dataFail = true;
                wyp = x;
                break;
            }
        }

        if (!znaleziono) {
            throw new AppException("Nie znaleziono wypożyczenia podanego modelu");

        } else if (dataFail) {
            throw new AppException("Podana ilość jest większa niż ilość wypożyczonych egzemplarzy");

        } else
            zglosZgubienieZniszczenia(wyp, ilosc);
    }

    /**
     * Metoda wywowywana jezeli jeden z egzemplarzy wypozyczenia zostal zniszczony/zgubiony
     *
     * @param w     w ktorym wypozyczeniu zgubiono sprzet
     * @param ilosc ilosc ile przedmiotow z wypozyczenia zgubiono
     */
    void zglosZgubienieZniszczenia(Wypozyczenie w, Integer ilosc) throws Exception {
        if (w == null) throw new Exception("Podano Nulla");
        setNaleznoscDoZaplaty(getNaleznoscDoZaplaty() + w.getEgzemplarze().get(0).getModel().getCenaZaUszedzenia() * ilosc);
    }


    /**
     * Moetoda wykorzystywana do przedluzania wypozyczenia
     *
     * @param date         nowa data wypozyczenia
     * @param wypozyczenie ktore wypozyczenie przedluza
     * @throws Exception jezeli nie ma dostepnych egzemplarzy do wypozyczenia
     */
    void wydluzWypozyczenie(Date date, Wypozyczenie wypozyczenie) throws Exception {

        if (wypozyczenie.getEgzemplarze().get(0).getModel().getIlosDostepnychEgzemplarzy() <
                wypozyczenie.getEgzemplarze().size())
            throw new Exception("Brak dostepnych egzemplarzy. Dostepnych egzemplarzy: "
                    + wypozyczenie.getEgzemplarze().get(0).getModel().getIlosDostepnychEgzemplarzy() + ",a wypozycza sie: " + wypozyczenie.getEgzemplarze().size());
        else {
            //naliczenie opłaty
            setNaleznoscDoZaplaty(wypozyczenie.getEgzemplarze().get(0).getModel().getCenaZaDzienWypozyczenia() *
                    (date.getTime() - wypozyczenie.getData_zwrotu().getTime()));
            //zmiana danych wypożyczenia (data zwrotu)
            wypozyczenie.setData_zwrotu(date);
        }
    }

    /**
     * Metoda ktora zwraca sprzet do wypozyczalni i konczy wypozyczenie
     *
     * @param wypozyczenie wypozyczenie ktore jest zwracane
     * @throws Exception jezeli nie jest dostpeny zaden pracownik
     */
    public void zwrocSprzet(Wypozyczenie wypozyczenie) throws Exception {
        for (Wypozyczenie w : wypozyczenia) {
            if (w.equals(wypozyczenie)) {
                for (Egzemplarz egz : w.getEgzemplarze()) {
                    //analiza stanu sprzętu
                    Pracownik pracownik = (Pracownik) Aplikacja.getWolnyPracownik();
                    pracownik.analizujStanSprzetu(egz);//stan jest zmieniany w tej metodzie
                }
                wypozyczenia.remove(w);
                return;
            }
        }
    }

    /**
     * Metoda wypozycza sprzet
     *
     * @param model     jaki model sprzetu wypozyczasz
     * @param dateWyp   data wypozyczenia
     * @param dateZwrot data zwrotu
     * @param ilosc     ilosc wypozyczanego sprzetu
     * @throws Exception jezeli nie ssa dostepne zadne sprzety
     */
    void wypozyczSprzet(Model model, Date dateWyp, Date dateZwrot, Integer ilosc) throws Exception {

        Wypozyczenie wyp = new Wypozyczenie(idKlienta, dateWyp, dateZwrot, model, ilosc);
        //przypisz klientowi wypożyczenie
        this.wypozyczenia.add(wyp);
        //dolicz należność do zapłaty do rachunku klienta
        this.naleznoscDoZaplaty += wyp.getKoszt_wypozyczenia();
    }

    public void wypozyczSprzet(String nazwa, Date dateWyp, Date dateZwrot, Integer ilosc) throws Exception {
        //wyszukaj model z podanymi atrybutami
        wypozyczSprzet(wyszukajModel(nazwa), dateWyp, dateZwrot, ilosc);
    }

    public boolean sprawdzDostepnosc(Model model) {
        return (0 < model.getIlosDostepnychEgzemplarzy());
    }

    public boolean sprawdzDostepnosc(String nazwa) throws Exception {
        return sprawdzDostepnosc(wyszukajModel(nazwa));
    }

    /**
     * Dodaje wypozyczenie do arrayListy
     *
     * @param w dodawne wypozyczenie
     */
    public void dodajWypozyczenie(Wypozyczenie w) {
        wypozyczenia.add(w);
    }

    public void przegladajWypozyczenia() {
        int iloscWyp = getiloscWypozyczen();
        int i = 1;

        System.out.println("Ilość aktywnych wypożyczeń: " + iloscWyp);

        for (Wypozyczenie x : wypozyczenia) {
            System.out.println("Wypożyczenie " + i + ":");
            System.out.println("\t Data wypożyczenia: \t" + x.getData_wypozyczenia());
            System.out.println("\t Data zwrotu: \t\t" + x.getData_zwrotu());
            System.out.println("\t Moedel: \t\t" + x.getEgzemplarze().get(0).getModel().getNazwa());
            System.out.println("\t Ilość egzemplarzy: \t" + x.getEgzemplarze().size());
            System.out.println("\t Koszt wypożyczenia: \t" + x.getKoszt_wypozyczenia());
        }

    }


    /**
     * Konstruktor
     *
     * @param imie      imie klienta
     * @param nazwisko  nazwisko klienta
     * @param login     login klienta
     * @param haslo     haslo klienta
     * @param email     email kleinta
     * @param idKlienta id klienta //przydal by sie kostruktor z jakims przydzieleaniem id klienta
     */
    public Klient(String imie, String nazwisko, String login, String haslo, String email) {
        super(imie, nazwisko, login, haslo, email, TypUzytkownika.KLIENT);
        this.idKlienta = idCnt + 1;
        idCnt++;
        naleznoscDoZaplaty = 0.0;
        wypozyczenia = new ArrayList<>();
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


    public Integer getiloscWypozyczen() {
        return wypozyczenia.size();
    }

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
