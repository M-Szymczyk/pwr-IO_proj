
public class Pracownik extends Uzytkownik{

    Pracownik(String imie, String nazwisko, String login, String haslo, String email){
        super();
        setTypUzytkownika("pracownik");
    }

    public void dodajNowyModel(String nazwa){
        Model currModel = new Model(nazwa);
        Aplikacja.dodajModel(currModel);
    };
    public void dodajNowyModel(String nazwa, double cenaZaDzien, double cenaZaEgzemplarz, Kategoria kategoria){
        Model currModel = new Model(nazwa, cenaZaDzien, cenaZaEgzemplarz, kategoria);
        Aplikacja.dodajModel(currModel);
    };

}
