package System.data;

import System.Users.Klient;
import System.Users.TypUzytkownika;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;
import System.EditTest;

public class WypozyczenieTest {
    Kategoria kat;
    Model mod;
    Wypozyczenie wyp;
    Klient klient;

    @Before
    public void setUp() throws Exception {
        klient = new Klient("", "", "", "", "");
        kat = new Kategoria("kat", "");
        mod = new Model("", kat);
        ArrayList<Egzemplarz> egzemplarzs = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            egzemplarzs.add(new Egzemplarz(StanSprzetu.DOSTEPNY, mod));
        mod.setEgzemplarze(egzemplarzs);
        mod.setIlosDostepnychEgzemplarzy(egzemplarzs.size());
        wyp = new Wypozyczenie(klient.getIdKlienta(), new Date(0), new Date(10), mod, 10);
    }

    @Category(EditTest.class)
    @Test
    public void wydluzWypozyczenie() {
        Date staraData = wyp.getData_zwrotu();
        wyp.wydluzWypozyczenie(new Date(15));
        assertNotEquals(staraData, wyp.getData_zwrotu());
    }

    @Category(EditTest.class)
    @Test
    public void naliczDodatkowaOplate() {
        Double staryStan = wyp.getKoszt_wypozyczenia();
        wyp.naliczDodatkowaOplate(100.0);
        assertNotEquals(staryStan, wyp.getKoszt_wypozyczenia());
    }
}