package System.Users;

public enum TypUzytkownika{
    KLIENT("klient"),
    PRACOWNIK("pracownik"),
    KIEROWNIK("kierownik"),
    UNKNOWN("");

    String nazwaTypuUzytkownika;
    TypUzytkownika(String uzytkownik){ nazwaTypuUzytkownika = uzytkownik;}

    @Override
    public String toString(){return nazwaTypuUzytkownika;}
}
