package System.Users;

import System.data.Egzemplarz;
import System.data.Model;
import System.data.StanSprzetu;
import System.data.Wypozyczenie;
import System.Aplikacja;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Klasa odpowiedzialana za przechowywanie inforamcji o wypozyczeniach klienta
 */
public class Klient extends Uzytkownik {
    private final Integer idKlienta;
    private ArrayList<Wypozyczenie> wypozyczenia;
    private Double naleznoscDoZaplaty;

    public void zglosZgubienieZniszczenia(String nazwa, Integer ilosc){
        for(Wypozyczenie x: wypozyczenia){
            if(x.getEgzemplarze().get(0).getModel().getNazwa().equals(nazwa)){
                zglosZgubienieZniszczenia(x, ilosc);
            }
        }
    }

    /**
     * Metoda wywowywana jezeli jeden z egzemplarzy wypozyczenia zostal zniszczony/zgubiony
     * @param w w ktorym wypozyczeniu zgubiono sprzet
     * @param ilosc ilosc ile przedmiotow z wypozyczenia zgubiono
     */
    public void zglosZgubienieZniszczenia(Wypozyczenie w, Integer ilosc){
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
        //sprawdź dostęność egzemplarzy
        if(sprawdzDostepnosc(model)) {
            Wypozyczenie wyp = new Wypozyczenie(idKlienta, dateWyp, dateZwrot, model, ilosc);
            int i = 0;
            int cnt = 0;
            Egzemplarz e;
            while(cnt<ilosc){
                e = model.getEgzemplarze().get(i);
                if(e.getStan_egzemplarza().equals(StanSprzetu.DOSTEPNY)){
                    wyp.addToEgzemplarze(e);
                    e.zmienStanSprzetu(StanSprzetu.NIEDOSTEPNY);
                    model.setIlosDostepnychEgzemplarzy(model.getIlosDostepnychEgzemplarzy()-1);
                    cnt++;
                }
            }
            //przypisz klientowi wypożyczenie
            this.wypozyczenia.add(wyp);
        }
        else
            throw new Exception("Brak dostępnych egzemplarzy");
    }

    public void wypozyczSprzet(String nazwa,Date dateWyp,Date dateZwrot,Integer ilosc) throws Exception {
        //wyszukaj model z podanymi atrybutami
        wypozyczSprzet(wyszukajModel(nazwa), dateWyp, dateZwrot, ilosc);
    }

    public boolean sprawdzDostepnosc(Model model){
            return (0<model.getIlosDostepnychEgzemplarzy());
    }

    public boolean sprawdzDostepnosc(String nazwa) throws Exception {
        return sprawdzDostepnosc(wyszukajModel(nazwa));
    }


    /**
     * Dodaje wypozyczenie do arrayListy
     * @param w dodawne wypozyczenie
     */
    public void dodajWypożyczenie(Wypozyczenie w){
        wypozyczenia.add(w);
    }

    public void przeglądajWypozyczenia(){
        int iloscWyp = getiloscWypozyczen();
        int i = 1;

        System.out.println("Ilość aktywnych wypożyczeń: " + iloscWyp);

        for(Wypozyczenie x: wypozyczenia) {
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
