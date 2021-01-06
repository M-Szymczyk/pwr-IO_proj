package System.Users;

import System.data.Egzemplarz;
import System.data.Model;
import System.data.StanSprzetu;
import System.data.Wypozyczenie;
import System.Aplikacja;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Klasa odpowiedzialana za przechowywanie inforamcji o wypozyczeniach klienta
 */
public class Klient extends Uzytkownik {
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
                if(x.getEgzemplarze().size() < ilosc) dataFail = true;
                wyp = x;
                break;
            }
        }

        if(!znaleziono){
            JOptionPane.showMessageDialog(null, "Nie znaleziono wypożyczenia podanego modelu");

        }
        else if(dataFail){
            JOptionPane.showMessageDialog(null,
                    "Niepoprawne dane! Zgłaszana ilość egzemplarzy jest większa niż ilość wypożyczonych egzemplarzy");

        }else
            zglosZgubienieZniszczenia(wyp, ilosc);
    }

    /**
     * Metoda wywowywana jezeli jeden z egzemplarzy wypozyczenia zostal zniszczony/zgubiony
     *
     * @param w     w ktorym wypozyczeniu zgubiono sprzet
     * @param ilosc ilosc ile przedmiotow z wypozyczenia zgubiono
     */
    private void zglosZgubienieZniszczenia(Wypozyczenie w, Integer ilosc) throws Exception {
        if(w == null) throw new Exception("Podano Nulla");
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
            throw new Exception("Brak dsotepnych egzemplarzy");
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
                    try {
                        //analiza stanu sprzętu
                        Pracownik pracownik = (Pracownik) Aplikacja.getWolnyPracownik();
                        pracownik.analizujStanSprzetu(egz);//stan jest zmieniany w tej metodzie
                    } catch (Exception e) {
                        throw e;
                    }
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
     * @param liczba     liczba wypozyczanego sprzetu
     * @throws Exception jezeli nie ssa dostepne zadne sprzety
     */
    public void wypozyczSprzet(Model model, Date dateWyp, Date dateZwrot, Integer liczba) throws Exception {
        //sprawdź dostęność egzemplarzy
        if (sprawdzDostepnosc(model,liczba)) {
            Wypozyczenie wyp = new Wypozyczenie(idKlienta, dateWyp, dateZwrot, model, liczba);
            int i = 0;
            int cnt = 0;
            Egzemplarz e;
            while (cnt < liczba) {
                e = model.getEgzemplarze().get(i);
                if (e.getStan_egzemplarza().equals(StanSprzetu.DOSTEPNY)) {
                    wyp.addToEgzemplarze(e);
                    e.zmienStanSprzetu(StanSprzetu.NIEDOSTEPNY);
                    //model.setIlosDostepnychEgzemplarzy(model.getIlosDostepnychEgzemplarzy() - 1);
                    cnt++;
                }
            }
            //przypisz klientowi wypożyczenie
            this.wypozyczenia.add(wyp);
        } else
            throw new Exception("Brak dostępnych egzemplarzy. Dostepne sa: "+model.getIlosDostepnychEgzemplarzy()+
                    " egzemplarzy, a wymaga sie: "+liczba);
    }

    public void wypozyczSprzet(String nazwa, Date dateWyp, Date dateZwrot, Integer ilosc) throws Exception {
        //wyszukaj model z podanymi atrybutami
        wypozyczSprzet(wyszukajModel(nazwa), dateWyp, dateZwrot, ilosc);
    }

    /**
     * Metoda sprawdza czy liczba dostepnych egzemplarzy w modelu jest wystarczajaca
     * @param model tu jest sprawdzana liczba dostepnych egzemplarzy
     * @param ilosc wymagana ilosc
     * @return warunek loginczny
     */
    public boolean sprawdzDostepnosc(Model model,int ilosc) {
        return (ilosc <= model.getIlosDostepnychEgzemplarzy());
    }

    /**
     * Metoda sprawdza czy liczba dostepnych egzemplarzy w modelu jest wystarczajaca
     * @param nazwa nazwa modelu
     * @param liczba wymagana liczba
     * @return true jezeli @liczba jest mniejsza od liczby dostepnych egzemplarzy w modeli
     * @throws Exception jezeli nie znaleziono modelu o podanej nazwie
     */
    public boolean sprawdzDostepnosc(String nazwa,int liczba) throws Exception {
        return sprawdzDostepnosc(wyszukajModel(nazwa),liczba);
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
     * @param user      typ uzytkownika//todo na pewno to powinoo byc tez pobierane
     * @param idKlienta id klienta //przydal by sie kostruktor z jakims przydzieleaniem id klienta
     */
    public Klient(String imie, String nazwisko, String login, String haslo, String email, TypUzytkownika user, Integer idKlienta) {
        super(imie, nazwisko, login, haslo, email, user);
        this.idKlienta = idKlienta;
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
