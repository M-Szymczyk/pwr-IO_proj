package System.data;

import System.Users.Klient;
import System.Users.TypUzytkownika;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class WypozyczenieTest {
    Kategoria kat;
    Model mod;
    Wypozyczenie wyp;
    Klient klient;
    @Before
    public void setUp() throws Exception {
        klient=new Klient("","","","","", TypUzytkownika.KLIENT,1);
        kat=new Kategoria("kat","");
        mod=new Model("",kat);
        wyp=new Wypozyczenie(klient.getIdKlienta(),new Date(0),new Date(10),mod,10);
    }

    @Test
    public void wydluzWypozyczenie() {
        Date staraData =wyp.getData_zwrotu();
        wyp.wydluzWypozyczenie(new Date(15));
        assertNotEquals(staraData,wyp.getData_zwrotu());
    }

    @Test
    public void naliczDodatkowaOplate() {
        Double staryStan=wyp.getKoszt_wypozyczenia();
        wyp.naliczDodatkowaOplate(100.0);
        assertNotEquals(staryStan,wyp.getKoszt_wypozyczenia());
    }
}