package System;


import System.Users.*;
import System.data.*;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(EditTest.class)
@Categories.ExcludeCategory(DodawanieTest.class)
@Suite.SuiteClasses({
        Uzytkownik.class,
        KlientTest.class,
        Pracownik.class
})
public class UserTestWithoutAddMethods {
}
