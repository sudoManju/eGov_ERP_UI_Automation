package pages.financial;

import entities.financial.FinancialExpenseBillDetails;
import entities.ptis.ApprovalDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ExpenseDetailsPage extends BasePage {

    private WebDriver webDriver;

    @FindBy(id = "fund")
    private WebElement expenseFund;

    @FindBy(id = "department")
    private WebElement expenseDepartment;

    @FindBy(id = "function")
    private WebElement expenseFunction;

    @FindBy(id = "billSubType")
    private WebElement expenseBillSubType;

    @FindBy(id = "payTo")
    private WebElement expensePayTo;

    @FindBy(id = "tempDebitDetails[0].debitGlcode")
    private WebElement expenseAccountCodeDebit;

    @FindBy(className = "tt-highlight")
    private WebElement expenseBillAccountCodeDropdown;

    @FindBy(id = "tempDebitDetails[0].debitamount")
    private WebElement expenseDebitAmount;

    @FindBy(id = "tempCreditDetails[0].creditGlcode")
    private WebElement expenseAccountCodeCredit;

    @FindBy(id = "tempCreditDetails[0].creditamount")
    private WebElement expenseCreditAmount;

    @FindBy(id = "netPayableAccountCode")
    private WebElement expenseNetPayable;

    @FindBy(css = "input[id='netPayableAmount'][type='text']")
    private WebElement expenseNetAmount;

    @FindBy(id = "populateAccountDetails")
    private WebElement expensePopulate;

    @FindBy(id = "approvalDepartment")
    private WebElement expenseApprovalDepartment;

    @FindBy(id = "approvalDesignation")
    private WebElement expenseApprovalDesignation;

    @FindBy(id = "approvalPosition")
    private WebElement expenseApprovalPosition;

    @FindBy(id = "Forward")
    private WebElement forwardButton;

    @FindBy(id = "expType")
    private WebElement billType;

    @FindBy(id = "billNumber")
    private WebElement billNumberTextBox;

    @FindBy(css = ".buttonsubmit")
    private WebElement submitButton;

    @FindBy(id = "button2")
    private WebElement closeButton;

    @FindBy(className = "actionMessage")
    private WebElement forwardMessage;

    public ExpenseDetailsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void createNewExpenseBill(FinancialExpenseBillDetails financialExpenseBillDetails){
        waitForElementToBeVisible(expenseFund , webDriver);
        new Select(expenseFund).selectByVisibleText(financialExpenseBillDetails.getExpenseFund());

        waitForElementToBeVisible(expenseDepartment , webDriver);
        new Select(expenseDepartment).selectByVisibleText(financialExpenseBillDetails.getExpenseDeparment());

        waitForElementToBeClickable(expenseFunction , webDriver);
        expenseFunction.sendKeys(financialExpenseBillDetails.getExpenseFunction());
        waitForElementToBeVisible( webDriver.findElement(By.className("tt-dropdown-menu")),webDriver);
//        WebElement dropdown = webDriver.findElement(By.xpath(".//*[@id='expensebillheader']/div/div[7]/div[1]/span/span/div"));
        WebElement dropdown = webDriver.findElement(By.className("tt-dataset-0"));
        waitForElementToBeVisible(dropdown,webDriver);
        waitForElementToBeClickable(dropdown,webDriver);
        dropdown.click();

        waitForElementToBeClickable(expenseBillSubType , webDriver);
        new Select(expenseBillSubType).selectByVisibleText(financialExpenseBillDetails.getExpenseBillSubType());

        waitForElementToBeClickable(expensePayTo ,webDriver);
        expensePayTo.sendKeys("tester");

        waitForElementToBeClickable(expenseAccountCodeDebit ,webDriver);
        expenseAccountCodeDebit.sendKeys(financialExpenseBillDetails.getExpenseAccountCodeDebit());

        waitForElementToBeVisible( expenseBillAccountCodeDropdown,webDriver);
        expenseBillAccountCodeDropdown.click();

        waitForElementToBeClickable(expenseDebitAmount ,webDriver);
        expenseDebitAmount.sendKeys(financialExpenseBillDetails.getExpenseDebitAmount());

        waitForElementToBeClickable(expenseAccountCodeCredit , webDriver);
        expenseAccountCodeCredit.sendKeys(financialExpenseBillDetails.getExpenseAccountCodeCredit());

        waitForElementToBeVisible( expenseBillAccountCodeDropdown,webDriver);
        expenseBillAccountCodeDropdown.click();

        waitForElementToBeClickable(expenseCreditAmount ,webDriver);
        expenseCreditAmount.sendKeys(financialExpenseBillDetails.getExpenseCreditAmount());

        List<WebElement> element1 = expenseNetPayable.findElements(By.tagName("option"));
        waitForElementToBeClickable(element1.get(1), webDriver);
        element1.get(1).click();

        waitForElementToBeClickable(expenseNetAmount ,webDriver);
        expenseNetAmount.sendKeys(financialExpenseBillDetails.getExpenseNetAmount());

        waitForElementToBeClickable(expensePopulate , webDriver);
        expensePopulate.click();
    }

    public String enterExpenseApprovalDetails(ApprovalDetails approvalDetails){

        waitForElementToBeClickable(expenseApprovalDepartment ,webDriver);
        new Select(expenseApprovalDepartment).selectByVisibleText(approvalDetails.getApproverDepartment());

        for(int i = 0 ; i <= 10 ; i++) {
            if (!webDriver.findElement(By.id("approvalDesignation")).getText().equalsIgnoreCase(approvalDetails.getApproverDesignation())) {
                try {
                    waitForElementToBeClickable(expenseApprovalDesignation, webDriver);
                    new Select(expenseApprovalDesignation).selectByVisibleText(approvalDetails.getApproverDesignation());
                } catch (StaleElementReferenceException e) {
                    WebElement element = webDriver.findElement(By.id("approvalDesignation"));
                    new Select(element).selectByVisibleText(approvalDetails.getApproverDesignation());
                }
            }
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        waitForElementToBeVisible(expenseApprovalPosition , webDriver);
        waitForElementToBeClickable(expenseApprovalPosition , webDriver);
        Select approverPos = new Select(expenseApprovalPosition);
        String userName = approverPos.getOptions().get(1).getText();

        waitForElementToBeClickable(approverPos.getOptions().get(1) ,webDriver);
        approverPos.getOptions().get(1).click();

        waitForElementToBeClickable(forwardButton ,webDriver);
        forwardButton.click();

        switchToNewlyOpenedWindow(webDriver);
        return userName;
    }

    public void filterCreateVoucherBill(String applicationNumber){
        new Select(billType).selectByVisibleText("Expense");
        billNumberTextBox.sendKeys(applicationNumber);

        waitForElementToBeClickable(submitButton , webDriver);
        submitButton.click();

        getExpenseVoucherRow(applicationNumber).click();
        switchToNewlyOpenedWindow(webDriver);
    }

    private WebElement getExpenseVoucherRow(String applicationNumber){
        WebElement element = webDriver.findElement(By.className("tablebottom"));
        List<WebElement> elements = element.findElements(By.className("setborder"));

        for (WebElement applicationRow : elements) {
            if (applicationRow.findElements(By.tagName("td")).get(1).getText().contains(applicationNumber))
                return applicationRow.findElements(By.tagName("td")).get(1);
        }
        throw new RuntimeException("No application row found for -- " + applicationNumber);
    }

    public String closesExpenseVoucherPage(){

        waitForElementToBeVisible(forwardMessage , webDriver);
        String message = forwardMessage.getText();

        waitForElementToBeClickable(closeButton ,webDriver);
        closeButton.click();;

        switchToPreviouslyOpenedWindow(webDriver);
        return message;
    }
}
