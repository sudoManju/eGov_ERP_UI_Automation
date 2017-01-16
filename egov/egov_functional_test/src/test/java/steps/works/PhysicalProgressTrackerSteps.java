package steps.works;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java8.En;
import pages.works.PhysicalProgressTrackerPage;
import steps.BaseSteps;

/**
 * Created by manjunatha-lap on 16/01/2017.
 */
public class PhysicalProgressTrackerSteps extends BaseSteps implements En{

    public PhysicalProgressTrackerSteps()
    {
        And("^he search for estimate in estimate search result$", () -> {
            pageStore.get(PhysicalProgressTrackerPage.class).searchEstimate();
        });
//        And("^he upload the estimate photos for physical progress track$", () -> {
//           pageStore.get(PhysicalProgressTrackerPage.class).uploadEstimatePhotos();
//        });
    }
}
