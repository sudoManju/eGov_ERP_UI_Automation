package tests;

import org.testng.Reporter;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import utils.Categories;

import java.io.IOException;
import java.lang.reflect.Method;

public class BaseAPITest {

    @BeforeMethod(alwaysRun = true)
    public void testSetup(Method method) {
        Reporter.log("Test Method Name -- " + method.getName(), true);
    }

    @BeforeGroups(groups = Categories.SANITY, alwaysRun = true)
    public void setUp() throws IOException {
    }
}
