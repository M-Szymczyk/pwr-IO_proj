package System.Users;

import System.data.Egzemplarz;
import System.data.Kategoria;
import System.data.Model;
import System.data.StanSprzetu;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import System.DodawanieTest;
import System.EditTest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class PracownikTest {
    Egzemplarz e;
    Pracownik p;
    Model m;
    Kategoria kat;

    @Before
    public void setUp() {
        kat = new Kategoria("kat", "");
        m = new Model("", kat);
        e = new Egzemplarz(StanSprzetu.DOSTEPNY, m);
        p = new Pracownik("im", "naz", "log", "has", "mail");
    }

    @Category(DodawanieTest.class)
    @Test
    public void dodajNowyModel() {
        //todo napisac test
    }
    @Category(DodawanieTest.class)
    @Test
    public void testDodajNowyModel() {
        //todo napisac test
    }

    @Test
    public void analizujStanSprzetu() throws Exception {
        //todo napisac to z jmockit
//        //Mockito
//        String input = "T";
//        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
//        System.setIn(inputStream);
//        p.analizujStanSprzetu(e);
//        assertNotEquals("sprzet uszkodzony", StanSprzetu.USZKODZONY, e.getStan_egzemplarza());
//
//        input = "N";
//        inputStream = new ByteArrayInputStream(input.getBytes());
//        System.setIn(inputStream);
//        p.analizujStanSprzetu(e);
//        assertNotEquals("sprzet nieuszkodzony", StanSprzetu.DOSTEPNY, e.getStan_egzemplarza());
//
//        input = "s";
//        inputStream = new ByteArrayInputStream(input.getBytes());
//        System.setIn(inputStream);
//        p.analizujStanSprzetu(e);
//        assertNotEquals("inna odp", StanSprzetu.USZKODZONY, e.getStan_egzemplarza());

    }

    @Category(EditTest.class)
    @Test
    public void edytujModel() {
        String input = "nowa nazwa";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        //p.edytujModel(e);
        assertNotEquals("sprzet uszkodzony", StanSprzetu.USZKODZONY, e.getStan_egzemplarza());
    }

    @Category(EditTest.class)
    @Test
    public void edytujOpisModelu() {
        //todo napisac test
    }
}