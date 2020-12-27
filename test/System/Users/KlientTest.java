package System.Users;

import System.data.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class KlientTest {
    Model model;
    Wypozyczenie wypozyczenie;
    Klient klient;
    @Before
    public void setUp() throws Exception {
        Kategoria kategoria=new Kategoria("testKat","");
        model=new Model("tsetModel",kategoria);
        ArrayList<Egzemplarz> egzemplarzArrayList=new ArrayList<>();
        for(int i=0;i<5;i++)
            egzemplarzArrayList.add(new Egzemplarz(StanSprzetu.DOSTEPNY,model));
        model.setEgzemplarze(egzemplarzArrayList);

        klient=new Klient("mikolaj","ktos","admin","admin","admin@admin.pl", TypUzytkownika.KLIENT,1);

        wypozyczenie=new Wypozyczenie(klient.getIdKlienta(), new Date(1), new Date(15), model, 5);
    }

    @Test
    public void zglosZgubienieZniszczenia() {
        double kasa=klient.getNaleznoscDoZaplaty();
        klient.zglosZgubienieZniszczenia(wypozyczenie,1);
        assertNotEquals(kasa,klient.getNaleznoscDoZaplaty());
    }

    @Test(expected = Exception.class)
    public void zglosZgubienieZniszczenia1() {
        double kasa=klient.getNaleznoscDoZaplaty();
        klient.zglosZgubienieZniszczenia(wypozyczenie,100);
    }
    @Test(expected = Exception.class)
    public void zglosZgubienieZniszczenia2() {
        double kasa=klient.getNaleznoscDoZaplaty();
        klient.zglosZgubienieZniszczenia(wypozyczenie,-1);
    }

    @Test
    public void wydluzWypozyczenie() throws Exception {
        Double naleznosc =klient.getNaleznoscDoZaplaty();
        klient.wydluzWypozyczenie(new Date(20),wypozyczenie);
        assertNotEquals(naleznosc,klient.getNaleznoscDoZaplaty());
    }

    @Test
    public void wypozyczSprzet() throws Exception {
        Integer iloscWypozyczen= klient.getiloscWypozyczen();
        klient.wypozyczSprzet(model, new Date(1), new Date(15), 5);
        //System.out.println(klient.getWypozyczenie(klient.getiloscWypozyczen()-1).equals(wypozyczenie));
        Assert.assertNotEquals(iloscWypozyczen,klient.getiloscWypozyczen());

    }
}