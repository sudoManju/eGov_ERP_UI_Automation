package steps.works;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import pages.works.MilestoneTrackPage;
import steps.BaseSteps;

/**
 * Created by karthik on 26/12/16.
 */
public class MilestoneTrackSteps extends BaseSteps implements En {
    public MilestoneTrackSteps() {
        And("^he search and select the required file$", () -> {
            pageStore.get(MilestoneTrackPage.class).search();

            pageStore.get(MilestoneTrackPage.class).select();
        });
        And("^he stores the loa number and enters details$", () -> {
            String number = pageStore.get(MilestoneTrackPage.class).getLoaNumber();
            scenarioContext.setLoaNumber(number);

            pageStore.get(MilestoneTrackPage.class).enterMilestoneDetails();
        });
    }
}