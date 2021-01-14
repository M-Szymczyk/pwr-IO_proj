package System.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.experimental.categories.Category;

import System.EditTest;
import static org.junit.Assert.*;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EgzemplarzTest {
    @Parameterized.Parameter
    Model model;
    @Parameterized.Parameter
    Kategoria kat;
    @Parameterized.Parameter
    Egzemplarz eg;


    @Before
    public void setUp() {
        kat = new Kategoria("kat", "");
        model = new Model("mod", kat);
        eg = new Egzemplarz(StanSprzetu.DOSTEPNY, model);
    }

    @Test
    public void getStan_egzemplarza() {
        assertEquals(StanSprzetu.DOSTEPNY, eg.getStan_egzemplarza());
    }

    @Test
    public void getNumer_seryjny() {
        String one = "00001";
        assertEquals(one, eg.getNumer_seryjny());
    }

    @Test
    public void getModel() {
        assertEquals(model, eg.getModel());
    }


    @Category(EditTest.class)
    @Test(expected = Exception.class)
    public void zmienStanSprzetu() throws Exception {
        try {
            eg.zmienStanSprzetu(StanSprzetu.USZKODZONY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(StanSprzetu.USZKODZONY, eg.getStan_egzemplarza());
        eg.zmienStanSprzetu(null);
        assertEquals(StanSprzetu.USZKODZONY, eg.getStan_egzemplarza());
    }
}