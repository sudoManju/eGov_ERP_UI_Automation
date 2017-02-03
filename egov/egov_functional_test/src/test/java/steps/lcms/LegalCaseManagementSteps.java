package steps.lcms;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import entities.lcms.CreateLegalCase;
import pages.lcms.LegalCaseManagementPage;
import steps.BaseSteps;
import utils.ExcelReader;

/**
 * Created by vinaykumar on 2/2/17.
 */
public class LegalCaseManagementSteps extends BaseSteps implements En {

    public LegalCaseManagementSteps() {

        And("^user will enter the legal case details as (\\w+)$", (String legalCaseData) -> {

            CreateLegalCase createLegalCase = new ExcelReader(legalCaseTestDataFileName).getCreateLegalCaseDetails(legalCaseData);
            pageStore.get(LegalCaseManagementPage.class).enterLegalCaseDetails(createLegalCase);
        });

        And("^user closes the successful acknowledgement form$", () -> {
            String messageAndFileNumber = pageStore.get(LegalCaseManagementPage.class).closesAcknowledgementForm();
            scenarioContext.setActualMessage(messageAndFileNumber.split("\\>")[0]);
            scenarioContext.setCaseFileNumber(messageAndFileNumber.split("\\>")[1]);
        });

        And("^user will enter the case file number to search the file$", () -> {
//            pageStore.get(LegalCaseManagementPage.class).searchCaseFile("LC/1016/2016/000026");
            pageStore.get(LegalCaseManagementPage.class).searchCaseFile(scenarioContext.getCaseFileNumber());

        });

    }
}
