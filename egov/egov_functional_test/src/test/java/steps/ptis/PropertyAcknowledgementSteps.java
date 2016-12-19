package steps.ptis;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import org.junit.Assert;
import pages.ptis.PropertyAcknowledgementPage;
import steps.BaseSteps;


public class PropertyAcknowledgementSteps extends BaseSteps implements En {
    public PropertyAcknowledgementSteps() {
        Then("^property details get saved successfully", () -> {
            String acknowledgementMessage = pageStore.get(PropertyAcknowledgementPage.class).getAcknowledgementMessage();
            Assert.assertTrue(acknowledgementMessage.contains("Property Data Saved Successfully"));
            String applicationNumber = pageStore.get(PropertyAcknowledgementPage.class).getApplicationNumber();
            scenarioContext.setApplicationNumber(applicationNumber);
        });
        And("^current user closes acknowledgement$", () -> {
            String assessmentNumber = pageStore.get(PropertyAcknowledgementPage.class).getApplicationNumber();
            scenarioContext.setAssessmentNumber(assessmentNumber);
            pageStore.get(PropertyAcknowledgementPage.class).close();
        });
        Then("^he is notified that \"([^\"]*)\"$", (String expectedMessage) -> {
            // Write code here that turns the phrase above into concrete actions
            String acknowledgementMessage = pageStore.get(PropertyAcknowledgementPage.class).getSignatureNotification();
            Assert.assertEquals(expectedMessage, acknowledgementMessage);
        });
        When("^commissioner closes acknowledgement$", () -> {
           pageStore.get(PropertyAcknowledgementPage.class).closeFromCommisionersLogin();
        });

        And("^he cancels the print$", () -> {
            pageStore.get(PropertyAcknowledgementPage.class).cancelPrint();
        });

        And("^user will see the successfull page and view the details$", () -> {
            pageStore.get(PropertyAcknowledgementPage.class).toViewSubmissionPage();
        });

        And("^user will close the data entry page$", () -> {
            pageStore.get(PropertyAcknowledgementPage.class).toCloseDataEntryPage();
        });

        And("^user closes acknowledgement form$", () -> {
            pageStore.get(PropertyAcknowledgementPage.class).toCloseAdditionalConnectionPage();
        });
    }
}
