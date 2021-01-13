package System;

import System.Users.Klient;
import System.Users.TypUzytkownika;
import System.data.*;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AplikacjaTest {
    static Aplikacja app;

    @BeforeClass
    static public void SetUp(){
        app = new Aplikacja();
        app.kategorie.add(new Kategoria("kategoria0", "opis0"));
        app.modele.add(new Model("model0", app.kategorie.get(0)));
    }

    //@Rule
    //public ExpectedException e = ExpectedException.none();

    @Test
    public void test1_1DodajKategorie(){
        try {
            app.dodajKategorie(new Kategoria("kategoria1", "opis1"));
            assertEquals("kategoria1", app.kategorie.get(app.kategorie.size() - 1).getNazwaKategorii());
        }catch (Exception e){
            fail("Exception: " + e.getMessage());
        }
    }

    @Test
    public void test1_2DodajKategorie(){
        try {
            app.dodajKategorie(new Kategoria("kategoria1", "opis1"));
            fail("Exception wasn't thrown!");
        }catch (Exception e) {
            assertEquals("Kategoria juz istnieje", e.getMessage());
        }
    }

    @Test
    public void test2_1DodajModel(){
        try{
            app.dodajModel(new Model("model1", app.kategorie.get(0)));
            assertEquals("model1", app.modele.get(app.modele.size()-1).getNazwa());
        }catch(Exception e){
            fail("Exception:" + e.getMessage());
        }
    }

    @Test
    public void test2_2DodajModel(){
        try{
            app.dodajModel(new Model("model1", app.kategorie.get(0)));
            fail("Exception wasn't thrown!");
        }catch(Exception e){
            assertEquals("Model juz istnieje", e.getMessage());
        }
    }

    @Test
    public void test3_1EdytujModel(){
        Kategoria k = app.kategorie.get(0);
        Model m = new Model("modelTest", 110.0, 220.0, k);
        int index = app.modele.size();
        try {
            app.dodajModel(m);
            app.edytujModel("modelTest", "nowa nazwa", 50.5, 66.5, k.getNazwaKategorii());
            assertTrue(m.equals(app.modele.get(index)));

        }catch (Exception e){
            fail(e.getMessage());
        }
    }


    @Test
    public void test3_2EdytujModel(){
        Kategoria k = app.kategorie.get(0);
        Model m = new Model("modelTest2", 110.0, 220.0, k);
        int index = app.modele.size()-1;
        try {
            app.dodajModel(m);
            app.edytujModel("nie ma takiej nazwy", "nowa nazwa2", 50.5, 66.5, app.kategorie.get(0).getNazwaKategorii());
            fail("Exception wasn't thrown");

        }catch (Exception e){
            assertEquals("Nie ma takiego modelu", e.getMessage());
        }
    }

    @Test
    public void test3_3EdytujModel(){
        Kategoria k = app.kategorie.get(0);
        Model m = new Model("modelTest3", 110.0, 220.0, k);
        int index = app.modele.size()-1;
        try {
            app.dodajModel(m);
            app.edytujModel("modelTest3", "nowa nazwa3", 50.5, 66.5,"nie ma takiej kategorii");
            fail("Exception wasn't thrown");

        }catch (Exception e){
            assertEquals("Nie ma takiej kategorii", e.getMessage());
        }
    }
    @Test
    public void test3_4EdytujModel(){
        Kategoria k = app.kategorie.get(0);
        Model m = new Model("modelTest4", 110.0, 220.0, k);
        int index = app.modele.size()-1;
        try {
            app.dodajModel(m);
            app.edytujModel("modelTest4", "nowa nazwa4", 50.5, 66.5,app.kategorie.get(0).getNazwaKategorii());
            assertEquals(k.getNazwaKategorii(), app.modele.get(index).getKategoria().getNazwaKategorii());

        }catch (Exception e){
            e.getMessage();
        }
    }

}