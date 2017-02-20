package pages.financial;

import entities.financial.DirectBankPaymentDetails;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

public class DirectBankPaymentDetailsPage extends BasePage {

    private WebDriver webDriver;

    @FindBy(id = "fundId")
    private WebElement fundId;

    @FindBy(id = "vouchermis.departmentid")
    private WebElement voucherDepartment;

    @FindBy(id = "vouchermis.function")
    private WebElement voucherFunction;

    @FindBy(id = "bankId")
    private WebElement bankPaymentId;

    @FindBy(id = "amount")
    private WebElement amountTextBox;

    @FindBy(id = "accountNumber")
    private WebElement accountNumber;

    @FindBy(id = "modeOfPaymentcash")
    private WebElement bankPaymentCash;

    @FindBy(id = "modeOfPaymentrtgs")
    private WebElement bankPaymentRTGS;

    @FindBy(id = "paidTo")
    private WebElement paidToCustomer;

    @FindBy(id = "commonBean.documentNumber")
    private WebElement documentNumber;

    @FindBy(id = "documentDate")
    private WebElement documentDate;

    @FindBy(id = "billDetailslist[0].glcodeDetail")
    private WebElement accountCode1;

    @FindBy(className = "yui-ac-highlight")
    private WebElement accountCodeDropdown;

    @FindBy(id = "billDetailslist[0].debitAmountDetail")
    private WebElement debitAmount1;

    @FindBy(id = "subLedgerlist[0].glcode.id")
    private WebElement ledgerAccount1;

    @FindBy(id = "subLedgerlist[0].detailType.id")
    private WebElement ledgerType1;

    @FindBy(id = "subLedgerlist[0].detailCode")
    private WebElement ledgerCode1;

    @FindBy(id = "subLedgerlist[0].amount")
    private WebElement ledgerAmount1;

    @FindBy(className = "actionMessage")
    private WebElement forwardMessage;

    @FindBy(id = "closeButton")
    private WebElement bankCloseButton;

    @FindBy(id = "button2")
    private WebElement closeButton;

    public DirectBankPaymentDetailsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void enterDirectBankPaymentDetails(DirectBankPaymentDetails directBankPaymentDetails , String mode){

        waitForElementToBeVisible(fundId ,webDriver);
        new Select(fundId).selectByVisibleText(directBankPaymentDetails.getFundId());

        waitForElementToBeVisible(voucherDepartment ,webDriver);
        new Select(voucherDepartment).selectByVisibleText(directBankPaymentDetails.getVoucherDepartment());

        waitForElementToBeVisible(voucherFunction ,webDriver);
        new Select(voucherFunction).selectByVisibleText(directBankPaymentDetails.getVoucherFunction());

        waitForElementToBeClickable(bankPaymentId ,webDriver);
        bankPaymentId.click();
        new Select(bankPaymentId).selectByVisibleText(directBankPaymentDetails.getBankBranch());

        waitForElementToBeClickable(amountTextBox , webDriver);
        amountTextBox.sendKeys(directBankPaymentDetails.getAmount());

        waitForElementToBeClickable(accountNumber ,webDriver);
        accountNumber.click();
        new Select(accountNumber).selectByVisibleText(directBankPaymentDetails.getAccountNumber());

        switch (mode){
            case "cheque" :
                break;

            case "cash" :
                waitForElementToBeClickable(bankPaymentCash , webDriver);
                bankPaymentCash.click();
                break;

            case "RTGS" :
                waitForElementToBeClickable(bankPaymentRTGS , webDriver);
                bankPaymentRTGS.click();
                break;
        }

        waitForElementToBeClickable(paidToCustomer ,webDriver);
        paidToCustomer.sendKeys("Tester");

        waitForElementToBeClickable(documentNumber ,webDriver);
        documentNumber.sendKeys(get6DigitRandomInt());

        waitForElementToBeClickable(documentDate ,webDriver);
        documentDate.sendKeys(getCurrentDate() , Keys.TAB);

        waitForElementToBeVisible(accountCode1 , webDriver);
        waitForElementToBeClickable(accountCode1, webDriver);
        accountCode1.sendKeys(directBankPaymentDetails.getAccountCode1());

        waitForElementToBeVisible(accountCodeDropdown , webDriver);
        waitForElementToBeClickable(accountCodeDropdown , webDriver);
        accountCodeDropdown.click();
        enterText(debitAmount1 , directBankPaymentDetails.getDebitAmount1());

        waitForElementToBeClickable(ledgerAccount1 ,webDriver);
        new Select(ledgerAccount1).selectByVisibleText(directBankPaymentDetails.getLedgerAccount1());

        waitForElementToBeClickable(ledgerType1 ,webDriver);
        new Select(ledgerType1).selectByVisibleText(directBankPaymentDetails.getLedgerType1());

        waitForElementToBeClickable(ledgerCode1 ,webDriver);
        ledgerCode1.sendKeys(directBankPaymentDetails.getLedgerCode1());

        waitForElementToBeVisible(accountCodeDropdown , webDriver);
        waitForElementToBeClickable(accountCodeDropdown , webDriver);
        accountCodeDropdown.click();

        waitForElementToBeClickable(ledgerAmount1 ,webDriver);
        ledgerAmount1.sendKeys(directBankPaymentDetails.getLedgerAmount1());
    }

    public String directBankSuccessPage(){
        switchToNewlyOpenedWindow(webDriver);

        waitForElementToBeVisible(forwardMessage , webDriver);
        String message = forwardMessage.getText();

        if(message.contains("Successful")) {
            waitForElementToBeClickable(bankCloseButton, webDriver);
            bankCloseButton.click();;
        }
        else {
            waitForElementToBeClickable(closeButton ,webDriver);
            closeButton.click();;
        }
        switchToPreviouslyOpenedWindow(webDriver);
        return message;
    }

}
