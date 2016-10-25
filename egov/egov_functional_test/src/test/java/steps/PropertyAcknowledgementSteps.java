package steps;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import org.junit.Assert;
import pages.PropertyAcknowledgementPage;


public class PropertyAcknowledgementSteps extends BaseSteps implements En {
    public PropertyAcknowledgementSteps() {
        Then("^property details get saved successfully", () -> {
            String acknowledgementMessage = pageStore.get(PropertyAcknowledgementPage.class).getAcknowledgementMessage();
            Assert.assertTrue(acknowledgementMessage.contains("Property Data Saved Successfully"));
            String applicationNumber = pageStore.get(PropertyAcknowledgementPage.class).getApplicationNumber();
            scenarioContext.setApplicationNumber(applicationNumber);
        });
        And("^current user closes acknowledgement$", () -> {
            pageStore.get(PropertyAcknowledgementPage.class).close();
        });
    }
}
