package System.Users;

import System.Aplikacja;
import System.data.*;

import java.util.Scanner;


public class Pracownik extends Uzytkownik {

    Pracownik(String imie, String nazwisko, String login, String haslo, String email) {
        super(imie, nazwisko, login, haslo, email);
        this.typUzytkownika = TypUzytkownika.PRACOWNIK;
    }

    public void dodajNowyModel(String nazwa, Kategoria kat) throws AppException {
        Model currModel = new Model(nazwa, kat);
        Aplikacja.dodajModel(currModel);
    }

    public void dodajNowyModel(String nazwa, double cenaZaDzien, double cenaZaEgzemplarz,
                               Kategoria kategoria) throws AppException {
        Model currModel = new Model(nazwa, cenaZaDzien, cenaZaEgzemplarz, kategoria);
        Aplikacja.dodajModel(currModel);
    }

    /**
     * Metoda w ktorej analizowny jest sprzet
     *
     * @param e analizowny egzemplarz
     * @throws Exception jezeli stan sprzetu byl nullem
     */
    public void analizujStanSprzetu(Egzemplarz e) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("Czy sprzet jest uszkodzony? T/N");
        int wybor = scan.nextInt(); //todo nextLine?xd
                                    // porównuję kody znaków ASCII bo "==" wydaje mi się bardziej czytelne niż wybor.equal("T")
        if (wybor == 'T' || wybor == 't') {
            e.zmienStanSprzetu(StanSprzetu.USZKODZONY);
        } else if (wybor == 'N' || wybor == 'n') {
            e.zmienStanSprzetu(StanSprzetu.DOSTEPNY);
            //e.getModel().setIlosDostepnychEgzemplarzy(e.getModel().getIlosDostepnychEgzemplarzy() + 1);
        }
    }

    public void edytujModel(String name) throws AppException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Nowa nazwa: ");
        String newName = scan.nextLine();
        System.out.print("Cena za dzien wypozyczenia: ");
        double cenaZaDzien = -1.0;
        try {
            cenaZaDzien = Double.parseDouble(scan.nextLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.print("Cena za egzemplarz:");
        double cenaZaEgzemplarz = -1.0;
        try {
            cenaZaEgzemplarz = Double.parseDouble(scan.nextLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.print("Kategoria:");
        String kategoria = scan.nextLine();

        Aplikacja.edytujModel(name, newName, cenaZaDzien, cenaZaEgzemplarz, kategoria);
    }

    public void edytujOpisModelu(String nazwa, String opis) {
        Aplikacja.edytujOpisModelu(nazwa, opis);
    }

}


