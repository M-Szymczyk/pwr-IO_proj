package System.Users;
import System.Aplikacja;
import System.data.AppException;
import System.data.Model;

import java.util.concurrent.atomic.AtomicInteger;


public class Uzytkownik {
    private static final AtomicInteger count = new AtomicInteger(0);
    public long id;
    public String imie;
    public String nazwisko;
    public String login;
    private String haslo;
    public String email;
    public TypUzytkownika typUzytkownika;

    public long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getImie() {
        return imie;
    }
    public void setImie(String imie) {
        this.imie = imie;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getNazwisko() {
        return nazwisko;
    }
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
    public TypUzytkownika getTypUzytkownika() {
        return typUzytkownika;
    }

    public void setTypUzytkownika(TypUzytkownika typUzytkownika) {
        this.typUzytkownika = typUzytkownika;
    }
    public void setTypUzytkownika(String user) {
        if (user == null || user.equals("")) {
            this.typUzytkownika = TypUzytkownika.UNKNOWN;
            return;
        }
        for (TypUzytkownika x : TypUzytkownika.values()) {
            if (x.nazwaTypuUzytkownika.equals(user)) {
                this.typUzytkownika = x;
                return;
            }
        }
    }

    Uzytkownik(String imie, String nazwisko, String login, String haslo, String email, TypUzytkownika user){
        id = count.incrementAndGet();
        this.setImie(imie);
        this.setNazwisko(nazwisko);
        this.setEmail(email);
        this.haslo = haslo;
        this.setLogin(login);
        this.setTypUzytkownika(user);
    }

    Uzytkownik(String imie, String nazwisko, String login, String haslo, String email, String user){
        id = count.incrementAndGet();
        this.setImie(imie);
        this.setNazwisko(nazwisko);
        this.setEmail(email);
        this.haslo = haslo;
        this.setLogin(login);
        this.setTypUzytkownika(user);
    }
    Uzytkownik(String imie, String nazwisko, String login, String haslo, String email){
        id = count.incrementAndGet();
        this.setImie(imie);
        this.setNazwisko(nazwisko);
        this.setEmail(email);
        this.haslo = haslo;
        this.setLogin(login);
    }


    Uzytkownik(){}


    //chyba niepotrzebne, przynajmniej rejestracja
//    public void rejestracja(String imie, String nazwisko, String login, String haslo, String email, TypUzytkownika user) throws AppException {
//        Uzytkownik currUser = new Uzytkownik(imie, nazwisko, login, haslo, email,user);
//        Aplikacja.rejestracja(currUser);
//    }
//
//    public void logowanie(String login_email, String haslo) throws AppException {
//        Aplikacja.logowanie(login_email, haslo);
//    }

    public boolean checkPassword(String haslo){
        if(this.haslo.equals(haslo)) return true;
        return false;
    }

    /**
     * //todo metoda do uzupelnienia
     * Wyszukuje modele po danje nazwie???
     * @param nazwa nazwa szukanego modelu
     * @return referencje do modelu
     */
    public static Model wyszukajModel(String nazwa) throws Exception {
        boolean znalezionoModel = false;
        Model curr = null;

        for (Model x : Aplikacja.getModele()) {
            if (x.getNazwa().equals(nazwa)) {
                znalezionoModel = true;
                curr = x;
            }
        }

        //wyjątek w przypadku nieznalezienia
        if(curr == null) throw new Exception("Nie znaleziono modelu");
        return curr;

    }

}
