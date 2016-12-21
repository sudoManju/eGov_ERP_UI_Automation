package steps;

import cucumber.api.java8.En;
import entities.LoginDetails;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import pages.HomePage;
import utils.ExcelReader;

import java.io.IOException;

public class HomePageSteps extends BaseSteps implements En {
    public HomePageSteps() {
        Given("^(.*) logs in$", (String currentUser) -> {
            LoginDetails loginDetails = new ExcelReader(loginTestDataFileName).getLoginDetails(currentUser);
            if (System.getProperty("env").equalsIgnoreCase("qa"))
                loginDetails.setPassword("eGov@123");
            pageStore.get(HomePage.class).loginAs(loginDetails);

        });
    }

}
