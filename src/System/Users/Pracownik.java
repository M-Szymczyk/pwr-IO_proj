package System.Users;

import System.Users.Uzytkownik;
import System.Aplikacja;
import System.data.AppException;
import System.data.Kategoria;
import System.data.Model;


public class Pracownik extends Uzytkownik {

    Pracownik(String imie, String nazwisko, String login, String haslo, String email){
        super();
        setTypUzytkownika("pracownik");
    }

    public void dodajNowyModel(String nazwa, Kategoria kat) throws AppException {
        Model currModel = new Model(nazwa, kat);
        Aplikacja.dodajModel(currModel);
    }
    public void dodajNowyModel(String nazwa, double cenaZaDzien, double cenaZaEgzemplarz, Kategoria kategoria) throws AppException {
        Model currModel = new Model(nazwa, cenaZaDzien, cenaZaEgzemplarz, kategoria);
        Aplikacja.dodajModel(currModel);
    }
}
