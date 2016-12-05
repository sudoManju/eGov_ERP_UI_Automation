package steps.ptis;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java8.En;
import org.junit.Assert;
import pages.ptis.DataEntryAcknowledgementPage;
import pages.ptis.PropertyAcknowledgementPage;
import steps.BaseSteps;

import static steps.BaseSteps.pageStore;
import static steps.BaseSteps.scenarioContext;

/**
 * Created by bimal on 5/12/16.
 */
public class DataEntryAcknowledgementSteps extends BaseSteps implements En {
    @Then("^dataEntry Details saved successfully$")
    public void dataentryDetailsSavedSuccessfully() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        String acknowledgementMessage = pageStore.get(DataEntryAcknowledgementPage.class).getdataentryAcknowledgementMessage();

   //     Assert.assertTrue(acknowledgementMessage.contains("Property Data Saved Successfully"));
        String assessmentNumber = pageStore.get(DataEntryAcknowledgementPage.class).getAssessmentNumber();
        System.out.println(assessmentNumber);
        scenarioContext.setAssessmentNumber(assessmentNumber);

    }
}
