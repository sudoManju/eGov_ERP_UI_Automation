package steps.financial;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import entities.ptis.ApprovalDetails;
import pages.financial.FinancialPage;
import pages.wcms.WaterChargeManagementPage;
import steps.BaseSteps;
import utils.ExcelReader;

/**
 * Created by vinaykumar on 20/12/16.
 */
public class FinancialSteps extends BaseSteps implements En {

    public FinancialSteps() {

        And("^officer will enter the journal voucher details as (\\w+) & (\\w+)$", (String voucherType , String accountCode ) -> {
            pageStore.get(FinancialPage.class).enterJournalVoucherDetails(voucherType , accountCode);
        });

        And("^officer will enter the approval details as (\\w+)$", (String approveOfficer) -> {
            ApprovalDetails approvalDetails = new ExcelReader(ptisTestDataFileName).getApprovalDetails(approveOfficer);
            pageStore.get(FinancialPage.class).enterFinanceApprovalDetails(approvalDetails);
        });

        And("^officer will get the voucher number and closes it$", () -> {
            String voucherNumber = pageStore.get(FinancialPage.class).getVoucherNumber();
            scenarioContext.setVoucherNumber(voucherNumber);
        });

        Then("^the officer will click on the voucher number$", () -> {
            pageStore.get(FinancialPage.class).openVoucher(scenarioContext.getVoucherNumber());
        });

        And("^officer will closes the acknowledgement page$", () -> {
            pageStore.get(FinancialPage.class).closePage();
        });

        And("^officer click on approval of the voucher$", () -> {
            pageStore.get(FinancialPage.class).approvalPage();
        });
    }
}
