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


    public static void dodajModel(Model model){
        for(Model x: modele){
            if(model.getNazwa().equals(x.getNazwa())) {
                System.out.println("Model już istnieje") ;
                return;
            }
        }
        System.out.println("Dodano pomyslnie");
        modele.add(model);
    }
    public static void dodajKategorie(Kategoria k){
        kategorie.add(k);
    }

}
