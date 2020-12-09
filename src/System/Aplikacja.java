package System;
import System.Users.Uzytkownik;
import System.Users.TypUzytkownika;
import System.data.AppException;
import System.data.Kategoria;
import System.data.Model;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;


import java.util.ArrayList;


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
                            klientMenu();
                            break;
                        case PRACOWNIK:
                            pracownikMenu();
                            break;
                        case KIEROWNIK:
                            kierownikMenu();
                            break;
                        case UNKNOWN:
                            throw new AppException("Nieokreslony uzytkownik (TypUzytkownika = UNKNOWN)");
                            break;
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
    public static void dodajKategorie(Kategoria k){
        kategorie.add(k);
    }

    public static Model wyszukajModel(Model m){
        for(Model mod:modele)
            m.equals(mod)
    }
}

