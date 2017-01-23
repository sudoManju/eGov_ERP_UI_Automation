package steps.financial;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import entities.financial.FinancialBankDetails;
import entities.financial.FinancialExpenseBillDetails;
import entities.financial.FinancialJournalVoucherDetails;
import entities.ptis.ApprovalDetails;
import entities.wcms.FieldInspectionDetails;
import org.junit.Assert;
import pages.DashboardPage;
import pages.financial.FinancialPage;
import steps.BaseSteps;
import utils.ExcelReader;

import java.text.ParseException;

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
            try {
                String userName = pageStore.get(FinancialPage.class).enterFinanceApprovalDetails(approvalDetails);
                scenarioContext.setUser(userName.split("\\ ")[0]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        And("^officer will get successful voucher created and closes it$", () -> {
            String voucherNumber = pageStore.get(FinancialPage.class).getVoucherNumber();
            scenarioContext.setVoucherNumber(voucherNumber.split("\\ ")[1]);
//            scenarioContext.setUser(voucherNumber.split("\\ ")[9]);
            scenarioContext.setActualMessage(voucherNumber);

            String profileName = pageStore.get(DashboardPage.class).getProfileName();
            if(profileName.contains(scenarioContext.getUser().split("\\ ")[0])){
                scenarioContext.setPreviousUser("yes");
            }
//            if(userName.contains(voucherNumber.split("\\ ")[9])){
//                scenarioContext.setPreviousUser("yes");
//            }
        });

        Then("^the officer will click on the voucher number$", () -> {
            if(scenarioContext.getPreviousUser().equalsIgnoreCase("yes")){
                pageStore.get(FinancialPage.class).openVoucherFromDrafts(scenarioContext.getVoucherNumber());
                scenarioContext.setPreviousUser("no");
            }
            else {
                pageStore.get(FinancialPage.class).openVoucher(scenarioContext.getVoucherNumber());
            }
        });

        And("^officer will closes the acknowledgement page$", () -> {
            String actualMessage = pageStore.get(FinancialPage.class).closePage();
            scenarioContext.setActualMessage(actualMessage);
//            if(!actualMessage.split("\\ ")[4].equalsIgnoreCase("approved")) {
//                scenarioContext.setUser(actualMessage.split("\\ ")[5] + actualMessage.split("\\ ")[6]);
//            }
            if(scenarioContext.getIsRemittance() == 1) {
                scenarioContext.setVoucherNumber(actualMessage.split("\\n")[0].split("\\ ")[7] + "-CASH");
                scenarioContext.setIsRemittance(0);
            }
        });

        And("^officer click on approval of the voucher$", () -> {
            pageStore.get(FinancialPage.class).approvalPage();
        });

        Then("^officer will modify the results depending upon the fund and date as (\\w+)$", (String date) -> {
            pageStore.get(FinancialPage.class).billSearch(date);
        });

        And("^officer will act upon the above voucher$", () -> {
            pageStore.get(FinancialPage.class).actOnAboveVoucher();
        });

        And("^officer will verify the voucher number$", () -> {
            String voucher = pageStore.get(FinancialPage.class).verifyVoucher();
            Assert.assertEquals(voucher , scenarioContext.getVoucherNumber());
        });

        And("^officer will enter the bank details$", () -> {
            String bankDetails = "SBI";
            FinancialBankDetails financialBankDetails = new ExcelReader(financialTestDataFileName).getFinancialBankDetails(bankDetails);
            pageStore.get(FinancialPage.class).billPayment(financialBankDetails);
        });

        And("^officer will enter the remittance bank details$", () -> {
            String bankDetails = "SBI1";
            FinancialBankDetails financialBankDetails = new ExcelReader(financialTestDataFileName).getFinancialBankDetails(bankDetails);
            pageStore.get(FinancialPage.class).billRemittancePayment(financialBankDetails);
            scenarioContext.setIsRemittance(1);

        });

        And("^officer will the expense bill details as (\\w+)$", (String expenseBill) -> {
            FinancialExpenseBillDetails financialBill = new ExcelReader(financialTestDataFileName).getFinancialExpenseBillDetails(expenseBill);
            pageStore.get(FinancialPage.class).createNewExpenseBill(financialBill);
        });

        And("^officer will enter the expense approval details as (\\w+)$", (String approveOfficer) -> {
            ApprovalDetails approvalDetails = new ExcelReader(ptisTestDataFileName).getApprovalDetails(approveOfficer);
            String userName = pageStore.get(FinancialPage.class).enterExpenseApprovalDetails(approvalDetails);
            scenarioContext.setUser(userName.split("\\ ")[0]);
        });

        And("^officer will closes the expense acknowledgement page$", () -> {
            String expenseBillNumber = pageStore.get(FinancialPage.class).closesTheExpensePage();
            scenarioContext.setVoucherNumber(expenseBillNumber.split("\\ ")[2]);
            scenarioContext.setActualMessage(expenseBillNumber.split("\\ ")[3]);

            String profileName = pageStore.get(DashboardPage.class).getProfileName();
            if(profileName.contains(scenarioContext.getUser().split("\\ ")[0])){
                scenarioContext.setPreviousUser("yes");
            }
        });

        And("^officer will closes the successfull payment page$", () -> {
            String billNUmber = pageStore.get(FinancialPage.class).closesSuccessfullPaymentPage();
            scenarioContext.setVoucherNumber(billNUmber);
        });

        And("^officer will get successful BAN NUMBER created and closes it$", () -> {
            String voucherNumber = pageStore.get(FinancialPage.class).getVoucherNumber();
            scenarioContext.setVoucherNumber(voucherNumber.split("\\ ")[1]);
            scenarioContext.setActualMessage(voucherNumber.split("\\:")[1]);
            String profileName = pageStore.get(DashboardPage.class).getProfileName();
            if(profileName.contains(scenarioContext.getUser().split("\\ ")[0])){
                scenarioContext.setPreviousUser("yes");
            }
        });

        And("^user will enter the account code to modify as (\\w+)$", (String glCode) -> {
            pageStore.get(FinancialPage.class).enterAccountCodeToModify(glCode);
        });

        And("^user will map the account code to particular$", () -> {
            pageStore.get(FinancialPage.class).toModifyTheGLCodeAccount();
        });

        And("^officer will enter the remittance details as (\\w+)$", (String voucher) -> {
            FinancialJournalVoucherDetails financialJournalVoucherDetails = new ExcelReader(financialTestDataFileName).getJournalVoucherDetails(voucher);
            pageStore.get(FinancialPage.class).enterRemittanceVoucherDetails(financialJournalVoucherDetails);
        });

        And("^officer will search for remittance bills$", () -> {
            pageStore.get(FinancialPage.class).searchRemittanceBill();
            pageStore.get(FinancialPage.class).selectRemittanceBIll(scenarioContext.getVoucherNumber());
        });

        And("^officer will filter the bill according to the type$", () -> {
            pageStore.get(FinancialPage.class).filterCreateVoucherBill(scenarioContext.getVoucherNumber());
//            pageStore.get(FinancialPage.class).filterCreateVoucherBill("PHS/EJV/3456/2016-17");
        });

        And("^officer will set the new expense voucher number and closes it$", () -> {
            String expenseVoucherMessage = pageStore.get(FinancialPage.class).closesExpenseVoucherPage();
            scenarioContext.setVoucherNumber(expenseVoucherMessage.split("\\ ")[4].split("\\.")[0]);
            scenarioContext.setActualMessage(expenseVoucherMessage);
        });
    }
}
