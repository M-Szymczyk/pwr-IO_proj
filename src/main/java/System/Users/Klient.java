package System.Users;

import System.data.Model;
import System.data.Wypozyczenie;

import java.util.ArrayList;
import java.util.Date;

public class Klient extends Uzytkownik {
    private Integer idKlienta;
    private ArrayList<Wypozyczenie> wypozyczenia;
    private Double naleznoscDoZaplaty;

    public void zglosZgubienieZniszczenia(Model m, Wypozyczenie w){

    }
    public void wydluzWypozyczenie(Date date,Wypozyczenie wypozyczenie){


    }
    public void wypozyczSprzet(Model model,Date dateWyp,Date dateZwrot,Integer ilosc) throws Exception {
        dodajDoWypozyczenia(new Wypozyczenie(idKlienta,dateWyp,dateZwrot,model,ilosc));
    }
    public Model wyszukajModel(String nazwa){
        return null;
    }
    public void dodajDoWypozyczenia(Wypozyczenie w){

    }

    public Klient(String imie, String nazwisko, String login, String haslo, String email, TypUzytkownika user, Integer idKlienta) {
        super(imie, nazwisko, login, haslo, email, user);
        this.idKlienta = idKlienta;
        naleznoscDoZaplaty=0.0;
        wypozyczenia=new ArrayList<>();
    }

    public Double getNaleznoscDoZaplaty() {
        return naleznoscDoZaplaty;
    }

    public void setNaleznoscDoZaplaty(Double naleznoscDoZaplaty) {
        this.naleznoscDoZaplaty = naleznoscDoZaplaty;
    }

}
