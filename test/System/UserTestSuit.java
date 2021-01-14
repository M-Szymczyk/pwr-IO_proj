package System;


import System.Users.KlientTest;
import System.Users.PracownikTest;
import System.Users.Uzytkownik;
import System.Users.UzytkownikTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UzytkownikTest.class,
        PracownikTest.class,
        KlientTest.class,

})

public class UserTestSuit {
}
