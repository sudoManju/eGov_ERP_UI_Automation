package pages.collections;

import entities.collections.ChallanHeaderDetails;
import entities.collections.ChequeDetails;
import entities.ptis.ApprovalDetails;
import org.openqa.selenium.*;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

public class CollectionsPage extends BasePage {

    private WebDriver driver;

    @FindBy(id = "chequeradiobutton")
    private WebElement chequeModeofPaymentRadio;


    @FindBy(id = "instrumentChequeNumber")
    private WebElement chequeNumberTextBox;

    @FindBy(id = "instrumentDate")
    private WebElement chequeDateTextBox;

    @FindBy(id = "bankName")
    private WebElement bankNameInput;

    @FindBy(id = "billDetailslist[0].creditAmountDetail")
    private WebElement challanAmountTextBox;

    @FindBy(id = "paidBy")
    private WebElement paidByTextBox;

    @FindBy(id = "totalamounttobepaid")
    private WebElement amountToBePaidLabel;

    @FindBy(id = "challanDate")
    private WebElement challanDateTextBox;

    @FindBy(id = "payeeName")
    private WebElement payeeNameTextBox;

    @FindBy(id = "payeeAddress")
    private WebElement payeeAddressTextBox;

    @FindBy(id = "referenceDesc")
    private WebElement narrationTextBox;

    @FindBy(id = "serviceCategoryId")
    private WebElement serviceCategoryBox;

    @FindBy(id = "serviceId")
    private  WebElement serviceTypeBox;

    @FindBy(id = "functionId")
    private WebElement functionTab;

    @FindBy(id = "instrumentChequeAmount")
    private WebElement amountTextBox;

    @FindBy(id = "approverDeptId")
    private WebElement approverDeptBox;

    @FindBy(id = "designationId")
    private WebElement approverDesignationBox;

    @FindBy(id = "positionUser")
    private WebElement approverBox;

    @FindBy(id = "CHALLAN_NEW")
    private WebElement createChallanButton;

    @FindBy(id = "totalcramount")
    private WebElement totalAmount;

    @FindBy(id = "CHALLAN_VALIDATE")
    private WebElement validateChallan;

    @FindBy(id = "challanNumber")
    private WebElement challanNumberTextBox;

    @FindBy(id = "totalamounttobepaid")
    private WebElement amountToBePaid;

    @FindBy(id = "instrHeaderCash.instrumentAmount")
    private WebElement amountToBePaidBox;

    @FindBy(id = "button2")
    private WebElement payButton;

    @FindBy(id ="input[value='Approve All Collections'][type='submit']")
    private WebElement approveCollectionButton;

    public CollectionsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterChequeDetails(ChequeDetails chequeDetails) {
        waitForElementToBeClickable(chequeModeofPaymentRadio, driver);
        jsClick(chequeModeofPaymentRadio, driver);
//        chequeModeofPaymentRadio.click();
        chequeNumberTextBox.sendKeys(chequeDetails.getChequeNumber());
        chequeDateTextBox.sendKeys(chequeDetails.getChequeDate());
        chequeDateTextBox.sendKeys(Keys.TAB);
        bankNameInput.sendKeys(chequeDetails.getBankName());

        await().atMost(10, SECONDS).until(() -> driver.findElement(By.id("bankcodescontainer"))
                .findElements(By.cssSelector("ul li"))
                .get(0).click());

//        amountTextBox.sendKeys(amountToBePaidLabel.getText());
        paidByTextBox.sendKeys(chequeDetails.getPaidBy());
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        jse.executeScript(String.format("document.getElementById('instrumentChequeAmount').value = '%s';", amountToBePaidLabel.getText()));


        enterText(amountTextBox, amountToBePaidLabel.getAttribute("value").split("\\.")[0]);
        payButton.click();
    }

    public void enterChallanHeader(ChallanHeaderDetails challanHeaderDetails) {

        waitForElementToBeClickable(challanDateTextBox, driver);
        challanDateTextBox.clear();
        challanDateTextBox.sendKeys(challanHeaderDetails.getDate());
        waitForElementToBeClickable(payeeNameTextBox, driver);
        payeeNameTextBox.sendKeys(challanHeaderDetails.getPayeeName());
        waitForElementToBeClickable(payeeAddressTextBox, driver);
        payeeAddressTextBox.sendKeys(challanHeaderDetails.getPayeeAddress());
        waitForElementToBeClickable(narrationTextBox, driver);
        narrationTextBox.sendKeys(challanHeaderDetails.getNarration());

        new Select(serviceCategoryBox).selectByVisibleText(challanHeaderDetails.getServiceCategory());
        new Select(serviceTypeBox).selectByVisibleText(challanHeaderDetails.getServiceType());
        serviceTypeBox.sendKeys(Keys.CONTROL + "t");
        functionTab.sendKeys(Keys.CONTROL + "t");

        //challanAmountTextBox.clear();
        challanAmountTextBox.sendKeys(Keys.CONTROL + "a");
        challanAmountTextBox.sendKeys(Keys.DELETE);
        challanAmountTextBox.sendKeys("500");

    }

    public void enterApprovalDetails(ApprovalDetails approverDetails) {

        waitForElementToBeClickable(approverDeptBox, driver);
        new Select(approverDeptBox).selectByVisibleText(approverDetails.getApproverDepartment());
        new Select(approverDesignationBox).selectByVisibleText(approverDetails.getApproverDesignation());
        new Select(approverBox).selectByVisibleText(approverDetails.getApprover());

        createChallanButton.click();
        //challanAmountTextBox.sendKeys("50");
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    }

    public void validateChallan() {
        waitForElementToBeClickable(validateChallan,driver);
        validateChallan.click();
    }

    public void enterChallanNumber(String number) {
        waitForElementToBeClickable(challanNumberTextBox, driver);
        challanNumberTextBox.sendKeys(number);

        challanNumberTextBox.sendKeys(Keys.TAB);
    }

    public void payAmount() {

        waitForElementToBeVisible(amountToBePaid, driver);
        String amount = amountToBePaid.getText();

        amountToBePaid.click();
        amountToBePaid.sendKeys(amount);

        payButton.click();
        switchToNewlyOpenedWindow(driver);
    }


}