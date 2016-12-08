package steps.ptis;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java8.En;
import org.junit.Assert;
import pages.ptis.DataEntryAcknowledgementPage;
import pages.ptis.PropertyAcknowledgementPage;
import steps.BaseSteps;
import utils.ScenarioContext;

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
        String dataentryassessmentNumber = pageStore.get(DataEntryAcknowledgementPage.class).getAssessmentNumber();
        scenarioContext.setDataScreenAssessmentNumber(dataentryassessmentNumber);




        String assessmentNumber = pageStore.get(DataEntryAcknowledgementPage.class).getAssessmentNumber();
        System.out.println(assessmentNumber);
        scenarioContext.setAssessmentNumber(assessmentNumber);

    }



    @And("^he choose to add edit DCB$")
    public void heChooseToAddEditDCB() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        pageStore.get(DataEntryAcknowledgementPage.class).geteditDCB();
        pageStore.get(DataEntryAcknowledgementPage.class).enterAddDemandDetails();

    }

    @And("^he choose to close the dataentry acknowledgement screen$")
    public void heChooseToCloseTheDataentryAcknowledgementScreen() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        pageStore.get(DataEntryAcknowledgementPage.class).close();


    }
}
