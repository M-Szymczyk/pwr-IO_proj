package System.Users;

import System.data.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

    public class KlientTest {
    @Parameterized.Parameter
    Model model;
    @Parameterized.Parameter
    Wypozyczenie wypozyczenie;
    @Parameterized.Parameter
    Klient klient;

    @Before
    public void setUp() throws Exception {
        Kategoria kategoria = new Kategoria("testKat", "");
        model = new Model("testModel", kategoria);
        model.setCenaZaUszedzenia(10.0);
        model.setCenaZaDzienWypozyczenia(100.0);
        ArrayList<Egzemplarz> egzemplarzArrayList = new ArrayList<>();
        for (int i = 0; i < 14; i++)
            egzemplarzArrayList.add(new Egzemplarz(StanSprzetu.DOSTEPNY, model));
        model.setEgzemplarze(egzemplarzArrayList);
        model.setIlosDostepnychEgzemplarzy(egzemplarzArrayList.size());

        klient = new Klient("mikolaj", "ktos", "admin", "admin", "admin@admin.pl");

        wypozyczenie = new Wypozyczenie(klient.getIdKlienta(), new Date(1), new Date(15), model, 5);
    }

    @Test
    public void zglosZgubienieZniszczenia() throws Exception {
        double kasa = klient.getNaleznoscDoZaplaty();
        klient.zglosZgubienieZniszczenia(wypozyczenie, 1);
        assertNotEquals("Test czy oplata zostala zwiekszona", kasa, klient.getNaleznoscDoZaplaty());
    }

//    /**
//     * test w przypadku gdy podano zbyt duza liczbe uszkodzonego sprzetu
//     */
//    @Test(expected = Exception.class)
//    public void zglosZgubienieZniszczenia1() throws Exception {
//        double kasa = klient.getNaleznoscDoZaplaty();
//        klient.zglosZgubienieZniszczenia(wypozyczenie, 100);
//    }
//
//    /**
//     * test w przypadku gdy podao zbyt małą liczbe uszkodzonego sprzetu
//     */
//    @Test(expected = Exception.class)
//    public void zglosZgubienieZniszczenia2() throws Exception {
//        double kasa = klient.getNaleznoscDoZaplaty();
//        klient.zglosZgubienieZniszczenia(wypozyczenie, -1);
//    }

    @Test
    public void wydluzWypozyczenie() throws Exception {
        Double naleznosc = klient.getNaleznoscDoZaplaty();
        klient.wydluzWypozyczenie(new Date(20), wypozyczenie);
        assertNotEquals("test zwiekszenia naleznosci", naleznosc, klient.getNaleznoscDoZaplaty());
    }

    @Test
    public void wypozyczSprzet() throws Exception {
        Integer iloscWypozyczen = klient.getiloscWypozyczen();
        klient.wypozyczSprzet(model, new Date(1), new Date(15), 5);
        //System.out.println(klient.getWypozyczenie(klient.getiloscWypozyczen()-1).equals(wypozyczenie));
        Assert.assertNotEquals(iloscWypozyczen, klient.getiloscWypozyczen());
    }

}