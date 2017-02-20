package pages.financial;

import entities.financial.FinancialBankToBankDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

public class BankToBankDetailsPage extends BasePage {

    private WebDriver webDriver;

    @FindBy(id = "fundId")
    private WebElement fundId;

    @FindBy(id = "vouchermis.departmentid")
    private WebElement voucherDepartment;

    @FindBy(id = "vouchermis.function")
    private WebElement voucherFunction;

    @FindBy(id = "fromBankId")
    private WebElement fromBankId;

    @FindBy(id = "fromAccountNumber")
    private WebElement fromAccountNumber;

    @FindBy(id = "toFundId")
    private WebElement toFundId;

    @FindBy(id = "toBankId")
    private WebElement toBankId;

    @FindBy(id = "toAccountNumber")
    private WebElement toAccountNumber;

    @FindBy(id = "chequeNum")
    private WebElement referenceNumber;

    @FindBy(id = "amount")
    private WebElement amountTextBox;

    @FindBy(id = "Save_New")
    private WebElement saveButton;

    public BankToBankDetailsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void enterBankToBankDetails(FinancialBankToBankDetails financialBankToBankDetails){

        waitForElementToBeVisible(fundId,webDriver);
        new Select(fundId).selectByVisibleText(financialBankToBankDetails.getFundId());

        waitForElementToBeVisible(voucherDepartment ,webDriver);
        new Select(voucherDepartment).selectByVisibleText(financialBankToBankDetails.getVoucherDepartment());

        waitForElementToBeVisible(voucherFunction ,webDriver);
        new Select(voucherFunction).selectByVisibleText(financialBankToBankDetails.getVoucherFunction());

        waitForElementToBeClickable(fromBankId ,webDriver);
        fromBankId.click();
        new Select(fromBankId).selectByVisibleText(financialBankToBankDetails.getFromBank());

        waitForElementToBeClickable(fromAccountNumber ,webDriver);
        fromAccountNumber.click();
        new Select(fromAccountNumber).selectByVisibleText(financialBankToBankDetails.getFromAccountNumber());

        waitForElementToBeVisible(toFundId ,webDriver);
        new Select(toFundId).selectByVisibleText(financialBankToBankDetails.getToFundId());

        waitForElementToBeClickable(toBankId ,webDriver);
        toBankId.click();
        new Select(toBankId).selectByVisibleText(financialBankToBankDetails.getToBank());

        waitForElementToBeClickable(toAccountNumber ,webDriver);
        toAccountNumber.click();
        new Select(toAccountNumber).selectByVisibleText(financialBankToBankDetails.getToAccountNumber());

        waitForElementToBeClickable(referenceNumber , webDriver);
        referenceNumber.sendKeys(get6DigitRandomInt());

        waitForElementToBeClickable(amountTextBox , webDriver);
        amountTextBox.sendKeys(financialBankToBankDetails.getAmount());

        waitForElementToBeClickable(saveButton , webDriver);
        saveButton.click();

        switchToNewlyOpenedWindow(webDriver);
    }

}
