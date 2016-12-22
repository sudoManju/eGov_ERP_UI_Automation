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

        And("^user will enter the journal voucher details as (\\w+) & (\\w+)$", (String voucherType , String accountCode ) -> {
            pageStore.get(FinancialPage.class).enterJournalVoucherDetails(voucherType , accountCode);
        });

        And("^user will enter the approval details as (\\w+)$", (String approveOfficer) -> {
            ApprovalDetails approvalDetails = new ExcelReader(ptisTestDataFileName).getApprovalDetails(approveOfficer);
            pageStore.get(FinancialPage.class).enterFinanceApprovalDetails(approvalDetails);
        });

        Then("^the officer will click on the voucher number$", (String voucherNumber) -> {
            pageStore.get(FinancialPage.class).openVoucher(voucherNumber);
        });

        And("^officer will approve and transfer to (\\w+)$", () -> {
        });
    }
}
