package steps.financial;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import entities.financial.FinancialJournalVoucherDetails;
import entities.ptis.ApprovalDetails;
import entities.wcms.FieldInspectionDetails;
import org.junit.Assert;
import pages.financial.FinancialPage;
import steps.BaseSteps;
import utils.ExcelReader;

/**
 * Created by vinaykumar on 20/12/16.
 */
public class FinancialSteps extends BaseSteps implements En {

    public FinancialSteps() {

        And("^officer will enter the journal voucher details as (\\w+)$", (String voucher) -> {
            FinancialJournalVoucherDetails financialJournalVoucherDetails = new ExcelReader(financialTestDataFileName).getJournalVoucherDetails(voucher);
            pageStore.get(FinancialPage.class).enterJournalVoucherDetails(financialJournalVoucherDetails);
        });

        And("^officer will enter the approval details as (\\w+)$", (String approveOfficer) -> {
            ApprovalDetails approvalDetails = new ExcelReader(ptisTestDataFileName).getApprovalDetails(approveOfficer);
            pageStore.get(FinancialPage.class).enterFinanceApprovalDetails(approvalDetails);
        });

        And("^officer will get successful voucher created and closes it \"([^\"]*)\"$", (String expectedMessage) -> {
            String voucherNumber = pageStore.get(FinancialPage.class).getVoucherNumber();
            scenarioContext.setVoucherNumber(voucherNumber.split("\\ ")[1]);
            Assert.assertEquals(voucherNumber.split("\\ ")[2], expectedMessage );
        });

        Then("^the officer will click on the voucher number$", () -> {
            pageStore.get(FinancialPage.class).openVoucher(scenarioContext.getVoucherNumber());
        });

        And("^officer will closes the acknowledgement page \"([^\"]*)\"$", (String expectedMessage) -> {
            String actualMessage = pageStore.get(FinancialPage.class).closePage();

            if(expectedMessage.equals("forwarded")){
            Assert.assertEquals(actualMessage.split("\\ ")[3] , expectedMessage);
            }
            else {
                Assert.assertEquals(actualMessage.split("\\ ")[4] , expectedMessage);
            }
        });

        And("^officer click on approval of the voucher$", () -> {
            pageStore.get(FinancialPage.class).approvalPage();
        });
        Then("^officer will modify the results depending upon the fund$", () -> {
            pageStore.get(FinancialPage.class).billPayments();
        });
    }
}
