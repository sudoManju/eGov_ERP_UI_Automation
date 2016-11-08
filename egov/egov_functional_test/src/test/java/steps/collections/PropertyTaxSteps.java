package steps.collections;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import pages.collections.PropertyTaxPage;
import steps.BaseSteps;

public class PropertyTaxSteps extends BaseSteps implements En {
    public PropertyTaxSteps() {
        And("^he chooses to collect tax for \"([^\"]*)\"$", (String assessmentNumber) -> {
            pageStore.get((PropertyTaxPage.class)).collectTaxFor(assessmentNumber);
        });
        And("^he chooses to pay tax$", () -> {
            pageStore.get(PropertyTaxPage.class).payTax();
        });
    }
}
