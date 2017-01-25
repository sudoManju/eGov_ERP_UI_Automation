package steps.grievances;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import entities.grievances.CreateComplaintDetails;
import org.junit.Assert;
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
        And("^he choose to enter grievance details as (\\w+)$", (String grievanceDetails) -> {
            CreateComplaintDetails createComplaintDetails= new ExcelReader(grievanceTestDataFileName).getGrievanceDetails(grievanceDetails);
            pageStore.get(GrievancesPage.class).enterGrievanceDetails(createComplaintDetails);
        });
        When("^he choose to register complaint with his login$", () -> {
            pageStore.get(GrievancesPage.class).getRegisterComplaintPage();
        });
        And("^he copies CRN and closes the acknowledgement$", () -> {
           String CRN= pageStore.get(GrievancesPage.class).getCRN();
           scenarioContext.setCRN(CRN);
        });
        And("^current user sign out$", () -> {
            pageStore.get(GrievancesPage.class).signOut();
        });
        And("^he resolves the issue and mark status as completed$", () -> {
            scenarioContext.setActualMessage(pageStore.get(GrievancesPage.class).officialMarkStatus());

    });


    }
}
