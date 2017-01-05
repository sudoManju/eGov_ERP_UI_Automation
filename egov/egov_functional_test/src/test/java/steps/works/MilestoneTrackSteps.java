package steps.works;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import pages.DashboardPage;
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
        And("^he save the file and close$", () -> {
            pageStore.get(MilestoneTrackPage.class).save();

            String actualMsg = pageStore.get(MilestoneTrackPage.class).successMessage();
            scenarioContext.setActualMessage(actualMsg);

            pageStore.get(MilestoneTrackPage.class).close();
        });
        And("^he chooses to track milestone$", () -> {
           pageStore.get(DashboardPage.class).chooseToTrackMileStone();
        });
        And("^he search application using loa number$", () -> {
            pageStore.get(MilestoneTrackPage.class).searchUsingLoa(scenarioContext.getLoaNumber());
        });
        And("^he select the application$", () -> {
            pageStore.get(MilestoneTrackPage.class).selectApplication();
        });
        And("^he enters the milestone details$", () -> {
            pageStore.get(MilestoneTrackPage.class).enterTrackMilestoneDetails();
        });
    }
}