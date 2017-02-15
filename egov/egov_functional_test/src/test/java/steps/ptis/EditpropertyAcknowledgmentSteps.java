package steps.ptis;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.ptis.EditPropertyAcknowledgementPage;
import pages.ptis.PropertyAcknowledgementPage;
import steps.PageStore;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;
import static steps.BaseSteps.pageStore;

/**
 * Created by bimal on 5/12/16.
 */
public class EditpropertyAcknowledgmentSteps {
    @Then("^edit property details get saved successfully$")
    public void editPropertyDetailsGetSavedSuccessfully() throws Throwable {

        pageStore.get(PropertyAcknowledgementPage.class).close();
    }

}
