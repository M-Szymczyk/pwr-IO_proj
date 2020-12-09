package System;
import System.Users.Uzytkownik;
import System.Users.TypUzytkownika;
import System.data.AppException;
import System.data.Kategoria;
import System.data.Model;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;


import java.util.ArrayList;
import java.util.Iterator;


public class Aplikacja {
    static ArrayList<Uzytkownik> uzytkownicy;
    static ArrayList<Kategoria> kategorie;
    static ArrayList<Model> modele;
    public static void main(String[] args) {
        System.out.println("Hello");
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
                           // pracownikMenu();
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
                for(Kategoria a: kategorie){
                    if(a.getNazwaKategorii().equals(kategoria)){
                        x.setKategoria(a);
                        znalezionoKategorie = true;
                    }
                }
            }
        }
        if(znalezionoKategorie == false ){
            throw new AppException("Nie ma takiej kategorii");
        }
        if(!znalezionoModel){
            throw new AppException("Nie ma takiego modelu");
        }
    }

    public static void edytujOpisModelu(String name, String opis){

    }

    public static void dodajKategorie(Kategoria k){
        kategorie.add(k);
    }

}

