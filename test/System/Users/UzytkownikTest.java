package System.Users;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UzytkownikTest {
    Uzytkownik uzytkownik;


    @Before
    public void setUp() throws Exception {
        uzytkownik = new Klient("admin", "admin", "admin",
                "admin", "admin@admin");
    }

    @Test
    public void checkPassword() {
        assertTrue("sprawdzenie poprawnosci hasla", uzytkownik.checkPassword("admin"));
    }


}