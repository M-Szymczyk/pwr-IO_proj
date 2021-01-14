package System;


import System.Users.KlientTest;
import System.Users.PracownikTest;
import System.Users.Uzytkownik;
import System.Users.UzytkownikTest;
import System.data.Egzemplarz;
import System.data.EgzemplarzTest;
import System.data.ModelTest;
import System.data.WypozyczenieTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UzytkownikTest.class,
        PracownikTest.class,
        KlientTest.class,
        EgzemplarzTest.class,
        ModelTest.class,
        WypozyczenieTest.class,
        AplikacjaTest.class
})

public class SuitAll {
}