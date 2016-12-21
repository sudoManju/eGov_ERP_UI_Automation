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

        And("^user will enter the journal voucher details as (\\w+)$", (String voucherType) -> {
            String approveDetails = "accountOfficer";
            pageStore.get(FinancialPage.class).enterJournalVoucherDetails();

            ApprovalDetails approvalDetails = new ExcelReader(ptisTestDataFileName).getApprovalDetails(approveDetails);
            pageStore.get(FinancialPage.class).enterFinanceApprovalDetails(approvalDetails);;
        });
    }
}
