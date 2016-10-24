import cucumber.api.PendingException;
import cucumber.api.java8.En;
import pages.PropertyAcknowledgementPage;
import steps.BaseSteps;

public class PropertyAcknowledgementSteps extends BaseSteps implements En {
    public PropertyAcknowledgementSteps() {
        Then("^the property gets registered$", () -> {
            pageStore.get(PropertyAcknowledgementPage.class).getAcknowledgementMessage();
        });
    }
}
