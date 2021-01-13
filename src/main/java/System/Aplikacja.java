package System;

import System.Users.Klient;
import System.Users.Uzytkownik;
import System.Users.TypUzytkownika;
import System.data.*;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class Aplikacja {
    static ArrayList<Uzytkownik> uzytkownicy = new ArrayList<>();
    static ArrayList<Kategoria> kategorie = new ArrayList<>();
    static ArrayList<Model> modele = new ArrayList<>();


    public static void main(String[] args) throws AppException {
        start();
    }

    private static void consoleClean() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    private static void start() throws AppException {
        Scanner scan = new Scanner(System.in);
        System.out.println("1.Rejestracja");
        System.out.println("2.Logowanie");
        int choose = scan.nextInt();

        switch (choose) {
            case 1:
                rejestracjaMenu();
                break;
            case 2:
                logowanieMenu();
                break;
            default:
                consoleClean();
                System.out.println("Menu start");
                start();
                break;
        }
    }

    private static void logowanieMenu() throws AppException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Login lub email: ");
        String login_email = sc.nextLine();
        System.out.print("Haslo: ");
        String haslo = sc.nextLine();

        logowanie(login_email, haslo);
    }

    private static void rejestracjaMenu() throws AppException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Imie: ");
        String imie = scan.nextLine();
        System.out.print("Nazwisko: ");
        String nazwisko = scan.nextLine();
        System.out.print("Email: ");
        String email = scan.nextLine();
        System.out.print("Login: ");
        String login = scan.nextLine();
        System.out.print("Haslo: ");
        String haslo = scan.nextLine();
        System.out.println("\n\nTwoje dane uzytkownika: ");
        System.out.println(imie);
        System.out.println(nazwisko);
        System.out.println(login);
        System.out.println(haslo);
        System.out.println(email);

        sprawdzDane();

        Uzytkownik user = new Uzytkownik(imie, nazwisko, login, haslo, email, TypUzytkownika.KLIENT);
        rejestracja(user);

    }

    public static void sprawdzDane() throws AppException {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nPowtórzyć wpisywanie danych? 1.Tak/2.Nie");
        int wybor = scan.nextInt();
        if (wybor == 1) rejestracjaMenu();
        else if (wybor != 2) sprawdzDane();
    }

    public static void rejestracja(Uzytkownik user) throws AppException {
        if(uzytkownicy.size()>0) {
            for (Uzytkownik x : uzytkownicy) {
                if (x.getLogin().equals(user.getLogin())) {
                    throw new AppException("Login jest zajety");
                } else if (x.getEmail().equals(user.getEmail())) {
                    throw new AppException("email jest juz zarejestrowany");
                } else {
                    uzytkownicy.add(user);
                }
            }
        }else
            uzytkownicy.add(user);
    }

    public static void logowanie(String login_email, String haslo) throws AppException {
        if(uzytkownicy.size() < 1) throw new AppException("Nie znaleziono użytkownika()brak użytkowników w bazie");
        for (Uzytkownik x : uzytkownicy) {
            if (x.getLogin().equals(login_email) || x.getEmail().equals(login_email)) {
                if (!x.checkPassword(haslo)) {
                    throw new AppException("Niepoprawne dane");
                } else {
                    TypUzytkownika typ = x.getTypUzytkownika();
                    switch (typ) {
                        case KLIENT:
                            //klientMenu();
                            break;
                        case PRACOWNIK:
                            //pracownikMenu();
                            break;
                        case KIEROWNIK:
                            //kierownikMenu();
                            break;
                        case UNKNOWN:
                            throw new AppException("Nieokreslony uzytkownik (TypUzytkownika = UNKNOWN)");
                    }
                }

            }
        }
    }



    /*--------------------------------------------------------------------------------------------------------------------*/

    public static void dodajModel(Model model) throws Exception {
        for (Model x : modele) {
            if (model.getNazwa().equals(x.getNazwa())) {
                throw new Exception("Model juz istnieje");
            }
        }
        modele.add(model);
    }

    public static void edytujModel(String oldName, String newName, double cenaZaDzien, double cenaZaEgzemplarz, String kategoria) throws Exception {
        boolean znalezionoKategorie = false;
        boolean znalezionoModel = false;
        for (Model x : modele) {
            if (x.getNazwa().equals(oldName)) {
                znalezionoModel = true;
                x.setNazwaModelu(newName);
                x.setCenaZaDzienWypozyczenia(cenaZaDzien);
                x.setCenaZaUszedzenia(cenaZaEgzemplarz);
                for (Kategoria a : kategorie) {
                    if (a.getNazwaKategorii().equals(kategoria)) {
                        x.setKategoria(a);
                        znalezionoKategorie = true;
                    }
                }
            }
        }
        if (!znalezionoModel) {
            throw new Exception("Nie ma takiego modelu");
        }
        if (znalezionoKategorie == false) {
            throw new Exception("Nie ma takiej kategorii");
        }

    }

    public static Kategoria wyszukajKategorie(String nazwa) throws Exception {

        boolean znaleziono = false;
        Kategoria k = null;
        for(Kategoria x: kategorie){
            if(x.getNazwaKategorii().equals(nazwa))
                znaleziono = true;
                k = x;
                break;
        }

        if(znaleziono) throw new Exception("Nie znaleziono kategorii o podanej nazwie");
        return k;
    }


    public static void edytujOpisModelu(String name, String opis) throws Exception {
        Model m = Uzytkownik.wyszukajModel(name);
        m.setOpis(opis);
    }

    public static ArrayList<Model> getModele() {
        return modele;
    }

    public static void dodajEgzemplarze(int ilosc, String nazwa) {
        Scanner sc = new Scanner(System.in);
        try {
            Model x = Uzytkownik.wyszukajModel(nazwa);
            for (int i = 0; i < ilosc; i++) {
                Egzemplarz egzemplarz = new Egzemplarz(StanSprzetu.DOSTEPNY, x);
                x.dodajEgzemplarz(egzemplarz);
            }
        } catch (Exception e) {
            System.out.println("Nie znaleziono modelu o podanej nazwie");
            System.out.print("Chcesz stworzyć nowy model? 1.Tak/2.Nie");
            int wybor = sc.nextInt();
            switch (wybor) {
                case 1:
                    boolean warPetli = false;
                    do {
                        System.out.print("cena za dzien wypozyczenia: ");
                        Double cenaZaDzien = sc.nextDouble();
                        System.out.print("Cena za egzemplarz: ");
                        Double cenaZaEgzemplarz = sc.nextDouble();
                        System.out.print("Kategoria: ");
                        String kategoria = sc.nextLine();
                        Kategoria kat = new Kategoria(kategoria, "");
                        Model model = new Model(nazwa, kat);
                        try {
                            dodajModel(model);
                            warPetli = false;
                        } catch (Exception exception) {
                            exception.printStackTrace();
                            warPetli = true;
                            System.out.println("Model o podanej nazwie nie istnieje. Sprubuj jeszcze raz");
                        }
                        for (int i = 0; i < ilosc; i++) {
                            Egzemplarz egzemplarz = new Egzemplarz(StanSprzetu.DOSTEPNY, model);
                            model.dodajEgzemplarz(egzemplarz);
                        }
                    } while (warPetli);
                    break;
                case 2:
                    return;
            }
        }
    }


    /**
     * @return Metoda zwraca Pracownika
     * @throws Exception jezeli brak pracownikow
     */
    public static Uzytkownik getWolnyPracownik() throws Exception {
        //todo prawdopodobnie bedzie trzeba poprawic to kiedys
        int iloscPracownikow = 0;
        for (Uzytkownik uz : uzytkownicy) {
            if (uz.getTypUzytkownika().equals(TypUzytkownika.PRACOWNIK))
                return uz;
        }
        throw new Exception("Brak Pracownikow lub kierownikow");

    }

    public static void dodajKategorie(Kategoria k) throws Exception {
        for (Kategoria x : kategorie) {
            if (k.getNazwaKategorii().equals(x.getNazwaKategorii())) {
                throw new AppException("Kategoria juz istnieje");
            }
        }
        kategorie.add(k);
    }

}

