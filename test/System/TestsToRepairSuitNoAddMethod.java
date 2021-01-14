package System;


import System.Users.*;
import System.data.*;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(TestsToRepair.class)
@Categories.ExcludeCategory(DodawanieTest.class)
@Suite.SuiteClasses({
        ModelTest.class,
        KlientTest.class
})
public class TestsToRepairSuitNoAddMethod {
}
