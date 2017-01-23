package steps.grievances;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import entities.grievances.CreateComplaintDetails;
import pages.Grievances.GrievancesPage;
import steps.BaseSteps;
import utils.ExcelReader;

/**
 * Created by tester1 on 1/23/2017.
 */
public class GrievancesSteps extends BaseSteps implements En {
    public GrievancesSteps() {
        When ("^he choose to register a complaint$", () -> {
            pageStore.get(GrievancesPage.class).openCreateGrievancePage();
        });
        And("^he choose to enter contact information as (\\w+)$", (String contactInfo) -> {
           CreateComplaintDetails createComplaintDetails=new ExcelReader(grievanceTestDataFileName).getCitizenContactDetails(contactInfo);
           pageStore.get(GrievancesPage.class).enterCitizenContactDetails(createComplaintDetails);
        });

    }
}
