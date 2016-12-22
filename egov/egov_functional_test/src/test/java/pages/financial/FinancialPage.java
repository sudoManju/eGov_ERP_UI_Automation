package pages.financial;

import entities.ptis.ApprovalDetails;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.util.List;
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
    private WebElement creditAmount2;

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

    @FindBy(xpath = ".//*[@id='egov_yui_add_image']")
    private List<WebElement> addList;

    @FindBy(css = "'div[class~='bootbox-alert'] button[class^='btn']'")
    private WebElement okButton;

    @FindBy(id = "button2")
    private WebElement closeButton;

    @FindBy(id = "official_inbox")
    private WebElement officialInboxTable;

    @FindBy(id = "Approve")
    private WebElement approveButton;

    public FinancialPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void enterJournalVoucherDetails(String voucherType , String accountCode){

        new Select(voucherSubType).selectByVisibleText(voucherType);
        new Select(fundId).selectByVisibleText("Municipal Fund");
        new Select(voucherDepartment).selectByVisibleText("PUBLIC HEALTH AND SANITATION");
        new Select(voucherFunction).selectByVisibleText("Public Health");

        accountCode1.sendKeys(accountCode.split("\\_")[0],  Keys.TAB);
        enterText(debitAmount1 , "100");

        accountCode2.sendKeys(accountCode.split("\\_")[1] , Keys.TAB);
        enterText(creditAmount2 , "100");

        new Select(ledgerAccount1).selectByVisibleText(accountCode.split("\\_")[0]);
        new Select(ledgerType1).selectByVisibleText("contractor");
        ledgerCode1.sendKeys("KMC001");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ledgerCode1.sendKeys(Keys.ENTER);
        ledgerAmount1.sendKeys("100");

        addList.get(2).click();

        new Select(ledgerAccount2).selectByVisibleText(accountCode.split("\\_")[1]);
        new Select(ledgerType2).selectByVisibleText("contractor");
        ledgerCode2.sendKeys("KMC001");

        try {
            TimeUnit.SECONDS.sleep(2);
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
        forwardButton.click();
    }

    public String getVoucherNumber(){

        webDriver.switchTo().activeElement();
        WebDriverWait webDriverWait = new WebDriverWait(webDriver,10);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[class~='bootbox-alert'] div[class^='bootbox-body']")));
        WebElement voucherNumber = webDriver.findElement(By.cssSelector("div[class~='bootbox-alert'] div[class^='bootbox-body']"));
        String number = voucherNumber.getText();

        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[class~='bootbox-alert'] button[class^='btn']")));
        WebElement element = webDriver.findElement(By.cssSelector("div[class~='bootbox-alert'] button[class^='btn']"));
        element.click();

        closeButton.click();
        await().atMost(5, SECONDS).until(() -> webDriver.getWindowHandles().size() == 1);
        for (String winHandle : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(winHandle);
        }
        return number.split("\\ ")[1];
    }

    public void openVoucher(String voucherNumber){
        WebElement element = getVoucherRow(voucherNumber);
        element.click();
        switchToNewlyOpenedWindow(webDriver);
    }

    private WebElement getVoucherRow(String voucherNumber) {
        waitForElementToBeVisible(webDriver.findElement(By.id("worklist")), webDriver);
        waitForElementToBeVisible(officialInboxTable, webDriver);

        await().atMost(10, SECONDS).until(() -> officialInboxTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size() > 1);
        List<WebElement> voucherRows = officialInboxTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));

        for (WebElement voucherRow : voucherRows) {
            if (voucherRow.findElements(By.tagName("td")).get(4).getText().contains(voucherNumber))
                return voucherRow;
        }
        throw new RuntimeException("No voucher row found for -- " + voucherNumber);
    }

    public void approvalPage(){
        waitForElementToBeClickable(approveButton , webDriver);
        approveButton.click();
    }

    public void closePage(){
        switchToNewlyOpenedWindow(webDriver);
        List<WebElement> closeElements = webDriver.findElements(By.className("button"));
        closeElements.get(1).click();

        await().atMost(5, SECONDS).until(() -> webDriver.getWindowHandles().size() == 1);
        for (String winHandle : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(winHandle);
        }
    }
}
