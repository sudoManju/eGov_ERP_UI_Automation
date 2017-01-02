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
          String number = pageStore.get(LetterOfAcceptancePage.class).saveAndClose();
          scenarioContext.setWorkNumber(number);

          String actualMsg = pageStore.get(LetterOfAcceptancePage.class).successMessage();
          scenarioContext.setActualMessage(actualMsg);

          pageStore.get(LetterOfAcceptancePage.class).close();
        });
        And("^he choose to view Letter of Acceptance$", () -> {
            pageStore.get(DashboardPage.class).chooseToViewLOA();
        });
        And("^he search for LOA$", () -> {
            pageStore.get(LetterOfAcceptancePage.class).searchForLOA(scenarioContext.getWorkNumber());
        });
        And("^he choose to modify letter of acceptance$", () -> {
            pageStore.get(DashboardPage.class).chooseToModifyLOA();
        });
        And("^he search for LOA for modify$", () -> {
            pageStore.get(LetterOfAcceptancePage.class).searchForLOAModify(scenarioContext.getWorkNumber());

            String actualMsg = pageStore.get(LetterOfAcceptancePage.class).successMessage();
            scenarioContext.setActualMessage(actualMsg);

            pageStore.get(LetterOfAcceptancePage.class).close();
        });
        And("^he select the required application$", () -> {
           pageStore.get(LetterOfAcceptancePage.class).searchForApplication();
        });
    }
}
