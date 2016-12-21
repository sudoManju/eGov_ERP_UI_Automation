package pages.financial;

import entities.ptis.ApprovalDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by vinaykumar on 20/12/16.
 */
public class FinancialPage extends BasePage {

    private WebDriver webDriver;

    @FindBy(id = "vType")
    private WebElement voucherSubType;

    @FindBy(id = "fundId")
    private WebElement fundId;

    @FindBy(id = "vouchermis.departmentid")
    private WebElement voucherDepartment;

    @FindBy(id = "vouchermis.function")
    private WebElement voucherFunction;

    @FindBy(id = "billDetailslist[0].glcodeDetail")
    private WebElement accountCode1;

    @FindBy(id = "billDetailslist[1].glcodeDetail")
    private WebElement accountCode2;

    @FindBy(id = "billDetailslist[0].debitAmountDetail")
    private WebElement debitAmount1;

    @FindBy(id = "billDetailslist[1].creditAmountDetail")
    private WebElement creditAmount;

    @FindBy(id = "totalcramount")
    private WebElement totalCreditAmount;

    @FindBy(id = "subLedgerlist[0].glcode.id")
    private WebElement ledgerAccount;

    @FindBy(id = "subLedgerlist[0].detailType.id")
    private WebElement ledgerType;

    @FindBy(id = "subLedgerlist[0].detailCode")
    private WebElement ledgerCode;

    @FindBy(id = "approverDepartment")
    private WebElement approverDepartment;

    @FindBy(id = "approverDesignation")
    private WebElement approverDesignation;

    @FindBy(id = "approverPositionId")
    private WebElement approverPosition;

    @FindBy(id = "Forward")
    private WebElement forwardButton;

    @FindBy(id = "subLedgerlist[0].detailKey")
    private WebElement ledgerName;

    @FindBy(id = "subLedgerlist[0].search")
    private WebElement ledgerSearch;

    @FindBy(linkText = "KMC001")
    private WebElement ledgerList1;

    public void enterJournalVoucherDetails(){

        new Select(voucherSubType).selectByVisibleText("General");
        new Select(fundId).selectByVisibleText("Municipal Fund");
        new Select(voucherDepartment).selectByVisibleText("ACCOUNTS");
        new Select(voucherFunction).selectByVisibleText("12th Finance Commission");

        enterText(accountCode1 , "2101001");
        accountCode1.sendKeys(Keys.TAB);
        enterText(debitAmount1 , "100");

        enterText(accountCode2 , "3501001");
        accountCode2.sendKeys(Keys.TAB);
        enterText(creditAmount , "100");
        totalCreditAmount.click();

        new Select(ledgerAccount).selectByVisibleText("2101001");
        new Select(ledgerType).selectByVisibleText("contractor");
        ledgerSearch.click();
        switchToNewlyOpenedWindow(webDriver);
        ledgerList1.click();
    }

    public void enterFinanceApprovalDetails(ApprovalDetails approvalDetails){

        for (String winHandle : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(winHandle);
        }

        new Select(approverDepartment).selectByVisibleText(approvalDetails.getApproverDepartment());
        new Select(approverDesignation).selectByVisibleText(approvalDetails.getApproverDesignation());
        new Select(approverPosition).selectByVisibleText(approvalDetails.getApprover());

    }

}
