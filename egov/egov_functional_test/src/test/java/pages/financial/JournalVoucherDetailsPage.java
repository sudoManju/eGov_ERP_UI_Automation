package pages.financial;

import entities.financial.FinancialJournalVoucherDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class JournalVoucherDetailsPage extends BasePage {

    private WebDriver webDriver;

    @FindBy(id = "vType")
    private WebElement voucherSubType;

    @FindBy(id = "voucherTypeBean.partyName")
    private WebElement voucherPartyName;

    @FindBy(id = "fundId")
    private WebElement fundId;

    @FindBy(id = "vouchermis.departmentid")
    private WebElement voucherDepartment;

    @FindBy(id = "vouchermis.function")
    private WebElement voucherFunction;

    @FindBy(id = "billDetailslist[0].glcodeDetail")
    private WebElement accountCode1;

    @FindBy(className = "yui-ac-highlight")
    private WebElement accountCodeDropdown;

    @FindBy(id = "billDetailslist[0].debitAmountDetail")
    private WebElement debitAmount1;

    @FindBy(id = "billDetailslist[1].glcodeDetail")
    private WebElement accountCode2;

    @FindBy(id = "billDetailslist[1].creditAmountDetail")
    private WebElement creditAmount2;

    @FindBy(xpath = ".//*[@id='egov_yui_add_image']")
    private List<WebElement> addList;

    @FindBy(id = "billDetailslist[2].glcodeDetail")
    private WebElement accountCode3;

    @FindBy(id = "billDetailslist[2].creditAmountDetail")
    private WebElement creditAmount3;

    @FindBy(id = "subLedgerlist[0].glcode.id")
    private WebElement ledgerAccount1;

    @FindBy(id = "subLedgerlist[0].detailType.id")
    private WebElement ledgerType1;

    @FindBy(id = "subLedgerlist[0].detailCode")
    private WebElement ledgerCode1;

    @FindBy(id = "subLedgerlist[0].amount")
    private WebElement ledgerAmount1;

    @FindBy(id = "subLedgerlist[1].glcode.id")
    private WebElement ledgerAccount2;

    @FindBy(id = "subLedgerlist[1].detailType.id")
    private WebElement ledgerType2;

    @FindBy(id = "subLedgerlist[1].detailCode")
    private WebElement ledgerCode2;

    @FindBy(id = "subLedgerlist[1].amount")
    private WebElement ledgerAmount2;

    @FindBy(id = "button2")
    private WebElement closeButton;

    public JournalVoucherDetailsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void enterJournalVoucherDetails(FinancialJournalVoucherDetails financialJournalVoucherDetails , String isSubLedgerPresent){

        enterVoucherDetails(financialJournalVoucherDetails);

        enterVoucherAccountDetails(financialJournalVoucherDetails);

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        enterVoucherSubLedgerDetails(financialJournalVoucherDetails , isSubLedgerPresent);
    }

    private void enterVoucherDetails(FinancialJournalVoucherDetails financialJournalVoucherDetails){

        waitForElementToBeClickable(voucherSubType , webDriver);
        new Select(voucherSubType).selectByVisibleText(financialJournalVoucherDetails.getVoucherType());

        if(!financialJournalVoucherDetails.getVoucherType().equals("General")){
            waitForElementToBeClickable(voucherPartyName , webDriver);
            voucherPartyName.sendKeys("voucher");
        }

        waitForElementToBeClickable(fundId , webDriver);
        new Select(fundId).selectByVisibleText(financialJournalVoucherDetails.getFundId());

        waitForElementToBeClickable(voucherDepartment ,webDriver);
        new Select(voucherDepartment).selectByVisibleText(financialJournalVoucherDetails.getDepartment());

        waitForElementToBeVisible(voucherFunction ,webDriver);
        new Select(voucherFunction).selectByVisibleText(financialJournalVoucherDetails.getFunction());
    }

    private void enterVoucherAccountDetails(FinancialJournalVoucherDetails financialJournalVoucherDetails){

        waitForElementToBeVisible(accountCode1, webDriver);
        waitForElementToBeClickable(accountCode1, webDriver);
        accountCode1.sendKeys(financialJournalVoucherDetails.getAccountCode1());

        waitForElementToBeVisible(accountCodeDropdown, webDriver);
        waitForElementToBeClickable(accountCodeDropdown, webDriver);
        accountCodeDropdown.click();

        waitForElementToBeClickable(debitAmount1 , webDriver);
        enterText(debitAmount1 , financialJournalVoucherDetails.getDebitAmount1());

        waitForElementToBeClickable(accountCode2 , webDriver);
        accountCode2.sendKeys(financialJournalVoucherDetails.getAccountCode2());

        waitForElementToBeClickable(accountCodeDropdown ,webDriver);
        accountCodeDropdown.click();

        waitForElementToBeClickable(creditAmount2 ,webDriver);
        enterText(creditAmount2 ,financialJournalVoucherDetails.getCreditAmount2());

        if(!financialJournalVoucherDetails.getAccountCode3().isEmpty()){

            waitForElementToBeClickable(addList.get(1) ,webDriver);
            addList.get(1).click();

            waitForElementToBeClickable(accountCode3 ,webDriver);
            accountCode3.sendKeys(financialJournalVoucherDetails.getAccountCode3());

            waitForElementToBeClickable(accountCodeDropdown ,webDriver);
            accountCodeDropdown.click();

            waitForElementToBeClickable(creditAmount3 ,webDriver);
            enterText(creditAmount3 , financialJournalVoucherDetails.getCreditAmount3());
        }
    }

    private void enterVoucherSubLedgerDetails(FinancialJournalVoucherDetails financialJournalVoucherDetails, String isSubLedgerPresent){

        if(isSubLedgerPresent.contains("yes")) {

            waitForElementToBePresent(By.className("yui-dt-dropdown"), webDriver);
            List<WebElement> webElementList = ledgerAccount1.findElements(By.tagName("option"));

            waitForElementToBeVisible(ledgerAccount1 , webDriver);
            new Select(ledgerAccount1).selectByVisibleText(webElementList.get(1).getText());

            waitForElementToBeClickable(ledgerType1 ,webDriver);
            new Select(ledgerType1).selectByVisibleText(financialJournalVoucherDetails.getLedgerType1());

            waitForElementToBeClickable(ledgerCode1 , webDriver);
            ledgerCode1.sendKeys(financialJournalVoucherDetails.getLedgerCode1());

            waitForElementToBeClickable(accountCodeDropdown, webDriver);
            accountCodeDropdown.click();

            waitForElementToBeClickable(ledgerAmount1 ,webDriver);
            ledgerAmount1.sendKeys(financialJournalVoucherDetails.getLedgerAmount1());

            if (webElementList.size() > 2) {

                if (addList.get(3).isDisplayed()){
                    waitForElementToBeClickable(addList.get(3) ,webDriver);
                    addList.get(3).click();
                }
                else {
                    waitForElementToBeClickable(addList.get(2) ,webDriver);
                    addList.get(2).click();
                }

                waitForElementToBeClickable(ledgerAccount2 ,webDriver);
                ledgerAccount2.click();
                new Select(ledgerAccount2).selectByVisibleText(webElementList.get(2).getText());

                waitForElementToBeVisible(ledgerType2 ,webDriver);
                new Select(ledgerType2).selectByVisibleText(financialJournalVoucherDetails.getLedgerType2());

                waitForElementToBeClickable(ledgerCode2 ,webDriver);
                ledgerCode2.sendKeys(financialJournalVoucherDetails.getLedgerCode2());

                waitForElementToBeClickable(accountCodeDropdown, webDriver);
                accountCodeDropdown.click();

                waitForElementToBeClickable(ledgerAmount2 ,webDriver);
                ledgerAmount2.sendKeys(financialJournalVoucherDetails.getLedgerAmount2());
            }
        }
    }

    public String getVoucherNumber(){

        switchToNewlyOpenedWindow(webDriver);

        WebDriverWait webDriverWait = new WebDriverWait(webDriver,10);

        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[class~='bootbox-alert'] div[class^='bootbox-body']")));
        WebElement voucherNumber = webDriver.findElement(By.cssSelector("div[class~='bootbox-alert'] div[class^='bootbox-body']"));
        String number = voucherNumber.getText();

        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[class~='bootbox-alert'] button[class^='btn']")));
        WebElement element = webDriver.findElement(By.cssSelector("div[class~='bootbox-alert'] button[class^='btn']"));
        element.click();

        waitForElementToBeClickable(closeButton ,webDriver);
        closeButton.click();

        switchToPreviouslyOpenedWindow(webDriver);

        return number;
    }

}
