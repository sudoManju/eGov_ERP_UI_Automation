package tests;

import com.jayway.restassured.RestAssured;
import org.apache.commons.lang3.RandomUtils;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import utils.LoginAndLogoutHelper;
import utils.ResourceHelper;
import utils.ScenarioContext;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import static java.lang.String.format;
import static java.util.Calendar.*;

public class BaseAPITest {

    public static ScenarioContext scenarioContext;

    public BaseAPITest() {
        RestAssured.baseURI = new ResourceHelper().getBaseURL();
    }

    @BeforeMethod(alwaysRun = true)
    public void testSetup(Method method) {
        scenarioContext = new ScenarioContext();
        Reporter.log("Test Method Name -- " + method.getName(), true);
    }

    private static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    protected String getRandomDate() {
        GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(2010, 2016);
        gc.set(YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(DAY_OF_YEAR));
        gc.set(DAY_OF_YEAR, dayOfYear);
        return (gc.get(DAY_OF_MONTH) + "/" + (gc.get(MONTH) + 1) + "/" + gc.get(YEAR));
    }

    public String get3DigitRandomInt() {
        return String.valueOf((RandomUtils.nextInt(100, 999)));
    }

    protected String get5DigitRandomInt() {
        return get3DigitRandomInt() + get3DigitRandomInt().substring(0, 2);
    }

    public String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    protected void pilotLogoutService() {
        new LoginAndLogoutHelper();
        LoginAndLogoutHelper.logoutFromPilotService();
    }

    protected String pathBuilder(String parameterType, String data) {
        return parameterType + data;
    }
}
