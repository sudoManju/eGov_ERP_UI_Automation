package pages.financial;

import entities.ptis.ApprovalDetails;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;

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
    private WebElement ledgerAccount1;

    @FindBy(id = "subLedgerlist[1].glcode.id")
    private WebElement ledgerAccount2;

    @FindBy(id = "subLedgerlist[0].detailType.id")
    private WebElement ledgerType1;

    @FindBy(id = "subLedgerlist[1].detailType.id")
    private WebElement ledgerType2;

    @FindBy(id = "subLedgerlist[0].detailCode")
    private WebElement ledgerCode1;

    @FindBy(id = "subLedgerlist[1].detailCode")
    private WebElement ledgerCode2;

    @FindBy(id = "approverDepartment")
    private WebElement approverDepartment;

    @FindBy(id = "approverDesignation")
    private WebElement approverDesignation;

    @FindBy(id = "approverPositionId")
    private WebElement approverPosition;

    @FindBy(id = "Forward")
    private WebElement forwardButton;

    @FindBy(id = "subLedgerlist[0].amount")
    private WebElement ledgerAmount1;

    @FindBy(id = "subLedgerlist[1].amount")
    private WebElement ledgerAmount2;

    @FindBy(xpath = "/egi/resources/erp2/images/add.png")
    private WebElement ledgerAdd;

    public void enterJournalVoucherDetails(){

        new Select(voucherSubType).selectByVisibleText("General");
        new Select(fundId).selectByVisibleText("Municipal Fund");
        new Select(voucherDepartment).selectByVisibleText("ACCOUNTS");
        new Select(voucherFunction).selectByVisibleText("12th Finance Commission");

        accountCode1.sendKeys("2101001" , Keys.TAB);
        enterText(debitAmount1 , "100");

        accountCode2.sendKeys("3501001" , Keys.TAB);
        enterText(creditAmount , "100");

        new Select(ledgerAccount1).selectByVisibleText("2101001");
        new Select(ledgerType1).selectByVisibleText("contractor");
        ledgerCode1.sendKeys("KMC001");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ledgerCode1.sendKeys(Keys.ENTER);
        ledgerAmount1.sendKeys("100");

        ledgerAdd.click();

        new Select(ledgerAccount2).selectByVisibleText("3501001");
        new Select(ledgerType2).selectByVisibleText("contractor");
        ledgerCode2.sendKeys("KMC001");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ledgerCode2.sendKeys(Keys.ENTER);
        ledgerAmount2.sendKeys("100");
    }

    public void enterFinanceApprovalDetails(ApprovalDetails approvalDetails){

        new Select(approverDepartment).selectByVisibleText(approvalDetails.getApproverDepartment());
        new Select(approverDesignation).selectByVisibleText(approvalDetails.getApproverDesignation());
        new Select(approverPosition).selectByVisibleText(approvalDetails.getApprover());

    }

}
