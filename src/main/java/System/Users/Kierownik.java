package System.Users;
import  System.Aplikacja;
import System.data.AppException;
import System.data.Model;

public class Kierownik extends Pracownik {


    public Kierownik(String imie, String nazwisko, String login, String haslo, String email){
        super(imie, nazwisko, login, haslo, email);
        this.typUzytkownika = TypUzytkownika.KIEROWNIK;
    }

    private void dodajPracownika(String imie, String nazwisko, String login, String haslo, String email) throws AppException {
        Uzytkownik user = new Uzytkownik(imie, nazwisko, login, haslo, email, TypUzytkownika.PRACOWNIK);
        Aplikacja.rejestracja(user);
    }

    private void zakupSprzet(int ilosc, String nazwa) throws Exception {
        Aplikacja.dodajEgzemplarze(ilosc, nazwa);
    }
}
