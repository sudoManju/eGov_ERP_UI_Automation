package steps.grievances;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
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
//           scenarioContext.setCRN(CRN);
            scenarioContext.setApplicationNumber(CRN);
//           Assert.assertNotNull(scenarioContext.getCRN());
           Assert.assertNotNull(scenarioContext.getApplicationNumber());

        });
        And("^current user sign out$", () -> {
            pageStore.get(GrievancesPage.class).signOut();
        });
        And("^he mark status as (\\w+)$", (String status) -> {
            scenarioContext.setActualMessage(pageStore.get(GrievancesPage.class).officialMarkStatus(status));

    });
        And("^official copies CRN and closes the acknowledgement$", () -> {
            String CRN= pageStore.get(GrievancesPage.class).getCRNByOfficial();
//            scenarioContext.setCRN(CRN);
            scenarioContext.setApplicationNumber(CRN);
        });
        And("^official create grievance$", () -> {
           scenarioContext.setActualMessage(pageStore.get(GrievancesPage.class).createInOfficial());
        });
        And("^citizen create grievance$", () -> {
            scenarioContext.setActualMessage(pageStore.get(GrievancesPage.class).createInCitizen());
        });
        And("^he marks the staus as processing$", () -> {
            pageStore.get(GrievancesPage.class).getProcessingStatus();
        });
        And("^he search complaint in his Inbox$", () -> {
//           pageStore.get(GrievancesPage.class).searchInCitizenInbox(scenarioContext.getCRN());
           pageStore.get(GrievancesPage.class).searchInCitizenInbox(scenarioContext.getApplicationNumber());
        });
        And("^he (.*) the complaint$", (String complaintStatus) -> {
            pageStore.get(GrievancesPage.class).withdrawComplaint(complaintStatus);
        });

    }
}
