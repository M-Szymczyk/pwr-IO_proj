package System.Users;

import System.Aplikacja;
import System.data.AppException;
import System.data.Model;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;


public class Uzytkownik {
    private static final AtomicInteger count = new AtomicInteger(0);
    private final long id;
    private String imie;
    private String nazwisko;
    private String login;
    private final String haslo;
    private String email;
    TypUzytkownika typUzytkownika;

    /**
     * Kostruktor
     *
     * @param imie     imie uzytkownika
     * @param nazwisko nazwisko
     * @param login    login
     * @param haslo    haslo
     * @param email    email
     * @param user     typ uzytkonika
     */
    public Uzytkownik(String imie, String nazwisko, String login, String haslo, String email, TypUzytkownika user) {
        id = count.incrementAndGet();
        this.setImie(imie);
        this.setNazwisko(nazwisko);
        this.setEmail(email);
        this.haslo = haslo;
        this.setLogin(login);
        this.setTypUzytkownika(user);
    }


    /**
     * Metoda sprawdza poprawnosc hasla
     *
     * @param haslo wprowadzane haslo
     * @return true jezeli hasla sa takie same
     */
    public boolean checkPassword(String haslo) {
        return this.haslo.equals(haslo);
    }

    /**
     * Wyszukuje modele po danje nazwie
     *
     * @param nazwa nazwa szukanego modelu
     * @return referencje do modelu
     */
    public static Model wyszukajModel(String nazwa) throws Exception {
        //boolean znalezionoModel = false;
        Model curr = null;

        for (Model x : Aplikacja.getModele()) {
            if (x.getNazwa().equals(nazwa)) {
                //znalezionoModel = true;
                curr = x;
            }
        }

        // w przypadku nieznalezienia
        if (curr == null) JOptionPane.showMessageDialog(null, "Nie znaleziono modelu o podanej nazwie");
        return curr;

    }

    //////////////////////// gettety i settery ////////////////////
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


}
