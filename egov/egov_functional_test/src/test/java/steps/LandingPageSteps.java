package steps;

import cucumber.api.java8.En;
import entities.LoginDetails;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import pages.HomePage;
import utils.ExcelReader;

import java.io.IOException;

public class LandingPageSteps extends BaseSteps implements En {
    public LandingPageSteps() {
        Given("^user is logged in with details of (\\w+)$", (String loginAs) -> {
            LoginDetails loginDetails = null;
            try {
                loginDetails = new ExcelReader().getLoginDetails(loginAs);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
            pageStore.get(HomePage.class).loginAs(loginDetails);
        });
        Given("^(.*) logs in$", (String currentUser) -> {
            LoginDetails loginDetails = null;
            try {
                loginDetails = new ExcelReader().getLoginDetails(currentUser);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
            pageStore.get(HomePage.class).loginAs(loginDetails);

        });
    }

}
