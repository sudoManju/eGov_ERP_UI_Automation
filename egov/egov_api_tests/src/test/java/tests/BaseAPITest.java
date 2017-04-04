package tests;

import builders.login.LoginRequestBuilder;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import entities.requests.login.LoginRequest;
import entities.responses.login.LoginResponse;
import entities.responses.logout.LogoutResponse;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import resources.LoginResource;
import utils.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.GregorianCalendar;
import java.util.Map;

import static java.lang.String.format;

public class BaseAPITest {

    public BaseAPITest() {
        RestAssured.baseURI = new ResourceHelper().getBaseURI();
    }

    public static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    @BeforeMethod(alwaysRun = true)
    public void testSetup(Method method) {
        Reporter.log("Test Method Name -- " + method.getName(), true);
    }

    @BeforeGroups(groups = Categories.SANITY, alwaysRun = true)
    public void setUp() throws IOException {

    }

    public String getRandomDate() {

        GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(2010, 2017);

        gc.set(gc.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

        gc.set(gc.DAY_OF_YEAR, dayOfYear);

        String finalDate = format(gc.get(gc.DAY_OF_MONTH) + "/" + (gc.get(gc.MONTH) + 1) + "/" + gc.get(gc.YEAR));

        return finalDate;
    }
}
