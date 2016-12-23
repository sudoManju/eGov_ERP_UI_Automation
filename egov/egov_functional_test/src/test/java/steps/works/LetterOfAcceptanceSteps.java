package steps.works;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import pages.DashboardPage;
import pages.works.LetterOfAcceptancePage;
import steps.BaseSteps;

/**
 * Created by manjunatha-lap on 22/12/2016.
 */
public class LetterOfAcceptanceSteps extends BaseSteps implements En
{
    public LetterOfAcceptanceSteps()
    {
        And("^he choose to create letter of acceptance$", () -> {
           pageStore.get(DashboardPage.class).chooseToCreateLOA();
        });
        And("^he enters the mandatory details$", () -> {
          pageStore.get(LetterOfAcceptancePage.class).enterLOAdetails();
        });
        Then("^he save the file and view the LOA pdf$", () -> {
          pageStore.get(LetterOfAcceptancePage.class).saveAndClose();
        });
    }
}
