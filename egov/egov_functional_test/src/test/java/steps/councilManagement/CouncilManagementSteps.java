package steps.councilManagement;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import entities.councilManagement.CreatePreambleDetails;
import entities.ptis.ApprovalDetails;
import gherkin.lexer.Da;
import org.junit.Assert;
import pages.DashboardPage;
import pages.councilManagement.CouncilManagementPage;
import pages.ptis.PropertyDetailsPage;
import steps.BaseSteps;
import utils.ExcelReader;

/**
 * Created by tester1 on 1/4/2017.
 */
public class CouncilManagementSteps extends BaseSteps implements En {
    public CouncilManagementSteps() {
        And("^he enters create preamble details as (\\w+)$", (String createPreambleData) -> {
            CreatePreambleDetails createPreambleDetails= new ExcelReader(councilManagementTestDataFileName).getCreatePreambleDetails(createPreambleData);
            pageStore.get(CouncilManagementPage.class).enterCreatePreambleDetails(createPreambleDetails);
        });
        And("^he will enter the approval details as (\\w+)$", (String approvalDetailsDataId) -> {
            ApprovalDetails approvalDetails = new ExcelReader(ptisTestDataFileName).getApprovalDetails(approvalDetailsDataId);
            pageStore.get(CouncilManagementPage.class).enterApproverDetails(approvalDetails);
        });
        And("^he copies preamble number and closes the acknowledgement$", () -> {
            String PreambleNumber = pageStore.get(CouncilManagementPage.class).getPreambleNumber();
            scenarioContext.setPreambleNumber(PreambleNumber);
            String Status= pageStore.get(CouncilManagementPage.class).getStatus();
            Assert.assertTrue(Status.contains("CREATED"));
        });
        And("^he approves the preamble number$", () -> {
          String Status= pageStore.get(CouncilManagementPage.class).approve();
            scenarioContext.setActualMessage(Status);

        });

        And("^he choose to create agenda for the above preamble$", () -> {
            String preambleNumber;
            preambleNumber = scenarioContext.getPreambleNumber();
            pageStore.get(CouncilManagementPage.class).enterCreateAgendaDetails(preambleNumber);
        });
        And("^he enters create agenda details as (\\w+)$", (String createAgendaData) -> {
            CreatePreambleDetails createPreambleDetails=new ExcelReader(councilManagementTestDataFileName).getCreateAgendaDetails(createAgendaData);
            pageStore.get(CouncilManagementPage.class).enterCreateAgenda(createPreambleDetails);
        });
        And("^he copies agenda number and closes the acknowledgement$", () -> {
            String AgendaNumber = pageStore.get(CouncilManagementPage.class).getAgendaNumber();
            scenarioContext.setAgendaNumber(AgendaNumber);
            System.out.println("Agenda Number"+AgendaNumber);
        });


    }
}
