package System.Users;
import  System.Aplikacja;
import System.data.AppException;
import System.data.Model;

public class Kierownik extends Uzytkownik {


    public Kierownik(String imie, String nazwisko, String login, String haslo, String email){
        super();
        this.typUzytkownika = TypUzytkownika.KIEROWNIK;
    }

    private void dodajPracownika(String imie, String nazwisko, String login, String haslo, String email) throws AppException {
        Uzytkownik user = new Uzytkownik(imie, nazwisko, login, haslo, email, TypUzytkownika.PRACOWNIK);
        Aplikacja.rejestracja(user);
    }

    private void zakupSprzet(int ilosc, String nazwa) throws AppException {
        Aplikacja.dodajEgzemplarze(ilosc, nazwa);
    }
}
