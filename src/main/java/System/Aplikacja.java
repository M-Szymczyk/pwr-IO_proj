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
    static ArrayList<Uzytkownik> uzytkownicy;
    static ArrayList<Kategoria> kategorie;
    static ArrayList<Model> modele;


    public static void main(String[] args) throws AppException {
        start();
    }

    private static void consoleClean(){
        for(int i=0; i<10; i++){
            System.out.println();
        }
    }
    private static void start() throws AppException {
        Scanner scan = new Scanner(System.in);
        System.out.println("1.Rejestracja");
        System.out.println("2.Logowanie");
        int choose = scan.nextInt();

        switch (choose){
            case 1: rejestracjaMenu();
                break;
            case 2: logowanieMenu();
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
        if(wybor == 1) rejestracjaMenu();
        else if(wybor != 2) sprawdzDane();
    }
    public static void rejestracja(Uzytkownik user) throws AppException {
        for(Uzytkownik x: uzytkownicy){
            if(x.getLogin().equals(user.getLogin())){
                throw new AppException("Login jest zajety");
            }else if (x.getEmail().equals(user.getEmail())){
                throw new AppException("email jest juz zarejestrowany");
            }else{
                uzytkownicy.add(user);
            }
        }
    }
    public static void logowanie(String login_email, String haslo)throws AppException{
        for(Uzytkownik x: uzytkownicy){
            if(x.getLogin().equals(login_email) || x.getEmail().equals(login_email)){
                if(!x.checkPassword(haslo)){
                    throw new AppException("Niepoprawne dane");
                }else{
                    TypUzytkownika typ = x.getTypUzytkownika();
                    switch(typ){
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
    public static void dodajModel(Model model) throws AppException{
        for(Model x: modele){
            if(model.getNazwa().equals(x.getNazwa())) {
                throw new AppException("Model juz istnieje");
            }
        }
        modele.add(model);
    }
    public static void edytujModel(String oldName, String newName, double cenaZaDzien, double cenaZaEgzemplarz, String kategoria)throws AppException{
        boolean znalezionoKategorie = false;
        boolean znalezionoModel = false;
        for(Model x: modele){
            if(x.getNazwa().equals(oldName)){
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
        if (znalezionoKategorie == false) {
            throw new AppException("Nie ma takiej kategorii");
        }
        if (!znalezionoModel) {
            throw new AppException("Nie ma takiego modelu");
        }
    }

    public static void edytujOpisModelu(String name, String opis) {

    }

    public static ArrayList<Model> getModele() {
        return modele;
    }

    public static void dodajEgzemplarze(int ilosc, String nazwa) throws Exception {
        Scanner sc = new Scanner(System.in);
        Model x = Uzytkownik.wyszukajModel(nazwa);
        if (x != null) {
            for (int i = 0; i < ilosc; i++) {
                Egzemplarz egzemplarz = new Egzemplarz(StanSprzetu.DOSTEPNY, x);
                x.dodajEgzemplarz(egzemplarz);
            }
        } else {
            System.out.println("Nie znaleziono modelu o podanej nazwie");
            System.out.print("Chcesz stworzyć nowy model? 1.Tak/2.Nie");
            int wybor = sc.nextInt();
            switch (wybor) {
                case 1:
                    System.out.print("cena za dzien wypozyczenia: ");
                    Double cenaZaDzien = sc.nextDouble();
                    System.out.print("Cena za egzemplarz: ");
                    Double cenaZaEgzemplarz = sc.nextDouble();
                    System.out.print("Kategoria: ");
                    String kategoria = sc.nextLine();
                    Kategoria kat = new Kategoria(kategoria, "");
                    Model model = new Model(nazwa, kat);
                    dodajModel(model);
                    for (int i = 0; i < ilosc; i++) {
                        Egzemplarz egzemplarz = new Egzemplarz(StanSprzetu.DOSTEPNY, model);
                        model.dodajEgzemplarz(egzemplarz);
                    }
                    break;
                case 2:
                    return;
            }
        }
    }



    public static void dodajKategorie(Kategoria k) {
        kategorie.add(k);
    }

}

