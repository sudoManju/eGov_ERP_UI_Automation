package pages.collections;

import com.gargoylesoftware.htmlunit.javascript.host.Window;
import entities.collections.ChallanHeaderDetails;
import entities.collections.ChequeDetails;
import entities.ptis.ApprovalDetails;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.*;
import pages.BasePage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

public class CollectionsPage extends BasePage {

    private WebDriver driver;

    @FindBy(id = "chequeradiobutton")
    private WebElement chequeModeofPaymentRadio;

    @FindBy(id = "ddradiobutton")
    private WebElement ddModeofPaymentRadio;

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

    @FindBy(id = "app-appcodo")
    private  WebElement consumerNumberTextBox;

    @FindBy(id = "submitButtonId")
    private WebElement submitButton;

    @FindBy(id = "payBtn")
    private WebElement collectWaterCharge;

    @FindBy(css = "input[id='instrumentChequeAmount'][type='text']")
    private WebElement payAmountBoxForCheque;

    @FindBy(css = "input[id='instrHeaderCash.instrumentAmount'][type='text']")
    private WebElement payAmountBoxForCash;

    @FindBy(id = "challan.challanNumber")
    private WebElement challanNumber;

    @FindBy(xpath = ".//*[@id='actionMessages']/ul/li/span")
    private WebElement creationMsg;

    @FindBy(xpath = ".//*[@id='buttonclose2']")
    private WebElement closeButton;

    @FindBy(id = "assessmentNum")
    private WebElement assessmentNumberField;

    @FindBy(id = "assessmentform_search")
    private WebElement assessmentFormSearchButton;

    @FindBy(id = "taxEnsureCheckbox")
    private WebElement onlinePageCheckBox;

    @FindBy(id = "PayTax")
    private WebElement payTaxButton;

    @FindBy(id = "updatePaytax")
    private WebElement updatePayTaxButton;
    
    @FindBy(css = ".btn.btn-xs.btn-secondary.collect-hoardingWiseFee")
    private WebElement onlinePayButton;

    @FindBy(className = "justbold")
    private List<WebElement> totalOnlineAmount;

    @FindBy(id = "paymentAmount")
    private WebElement totalOnlineAmountToBePaid;

    @FindBy(name = "radioButton1")
    private WebElement axisBankRadio;

    @FindBy(id = "checkbox")
    private WebElement termsAndConditionsCheckBox;

    @FindBy(xpath = "html/body/center/table[6]/tbody/tr[3]/td/table/tbody/tr/td[3]/a/img")
    private WebElement masterCardImage;

    @FindBy(id = "CardNumber")
    private WebElement cardNumber;

    @FindBy(id = "CardMonth")
    private WebElement cardMonth;

    @FindBy(id = "CardYear")
    private WebElement cardYear;

    @FindBy(id = "Securecode")
    private WebElement cvvNumber;

    @FindBy(id = "Paybutton")
    private WebElement onlineCardPaymentButton;

    public CollectionsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterChallanHeader(ChallanHeaderDetails challanHeaderDetails) {

        waitForElementToBeClickable(challanDateTextBox, driver);
        challanDateTextBox.clear();
        challanDateTextBox.sendKeys("02/01/2017");
        challanDateTextBox.sendKeys(Keys.TAB);
        waitForElementToBeClickable(payeeNameTextBox, driver);
        payeeNameTextBox.sendKeys(challanHeaderDetails.getPayeeName());
        waitForElementToBeClickable(payeeAddressTextBox, driver);
        payeeAddressTextBox.sendKeys(challanHeaderDetails.getPayeeAddress());
        waitForElementToBeClickable(narrationTextBox, driver);
        narrationTextBox.sendKeys(challanHeaderDetails.getNarration());

        new Select(serviceCategoryBox).selectByVisibleText(challanHeaderDetails.getServiceCategory());
        serviceTypeBox.click();
        serviceTypeBox.click();
        new Select(serviceTypeBox).selectByVisibleText(challanHeaderDetails.getServiceType());

        WebElement element = driver.findElement(By.id("billDetailslist[0].creditAmountDetail"));
        element.clear();
        element.sendKeys("500");
    }

    public void enterApprovalDetails(ApprovalDetails approverDetails) {

        waitForElementToBeClickable(approverDeptBox, driver);
        new Select(approverDeptBox).selectByVisibleText(approverDetails.getApproverDepartment());
        new Select(approverDesignationBox).selectByVisibleText(approverDetails.getApproverDesignation());

        waitForElementToBePresent(By.cssSelector("select[id='positionUser'] option[value='180']"),driver);
        new Select(approverBox).selectByVisibleText(approverDetails.getApprover());
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

    public void collectChargeFor(String consumerNumber) {
        consumerNumberTextBox.sendKeys(consumerNumber);
        submitButton.click();
    }

    public void collectCharge() {
        collectWaterCharge.click();
    }

    public String generateChallan() {
        waitForElementToBeClickable(createChallanButton,driver);
        createChallanButton.click();

        boolean isPresent = driver.findElements(By.xpath(".//*[@id='challan_error_area']")).size() > 0;

        if(isPresent){
            WebElement element = driver.findElement(By.id("billDetailslist[0].creditAmountDetail"));
            element.clear();
            element.sendKeys("500");

            waitForElementToBeClickable(createChallanButton,driver);
            createChallanButton.click();
        }

        waitForElementToBeVisible(challanNumber,driver);
        String number = challanNumber.getAttribute("value");

        return number;

    }

    public String successMessage() {

        waitForElementToBeVisible(creationMsg,driver);
        String msg = creationMsg.getText();
        System.out.println("\n"+msg);

        return msg;
    }

    public void close(){
        waitForElementToBeClickable(closeButton,driver);
        closeButton.click();

        switchToPreviouslyOpenedWindow(driver);
    }

    public void propertyTaxOnlinePaymentLink() {
        driver.navigate().to("http://kurnool-qa.egovernments.org/ptis/citizen/search/search-searchByAssessmentForm.action");
    }

    public void enerterAssessmentNumber(String assessmentNumber) {
        waitForElementToBeClickable(assessmentNumberField,driver);
        assessmentNumberField.sendKeys(assessmentNumber);
        assessmentFormSearchButton.click();
        onlinePageCheckBox.click();
        payTaxButton.click();
        updatePayTaxButton.click();
    }

    public void enterAmountAndPayOnline() {
        waitForElementToBeVisible(totalOnlineAmount.get(1) , driver);
        String amount = totalOnlineAmount.get(1).getText();

        waitForElementToBeClickable(totalOnlineAmountToBePaid , driver);
        totalOnlineAmountToBePaid.sendKeys(amount.split("\\.")[0]);

        waitForElementToBeClickable(axisBankRadio , driver);
        jsClick(axisBankRadio ,driver);

        waitForElementToBeClickable(termsAndConditionsCheckBox , driver);
        termsAndConditionsCheckBox.click();

        waitForElementToBeClickable(payButton , driver);
        payButton.click();

        waitForElementToBeClickable(masterCardImage , driver);
        masterCardImage.click();
    }

    public void enterCarddetailsAndPay() {
        waitForElementToBeClickable(cardNumber , driver);
        cardNumber.sendKeys("512345678912346");

        waitForElementToBeClickable(cardMonth , driver);
        cardMonth.sendKeys("04");

        waitForElementToBeClickable(cardYear ,driver);
        cardYear.sendKeys("17");

        waitForElementToBeClickable(cvvNumber , driver);
        cvvNumber.sendKeys("123");

        waitForElementToBeClickable(onlineCardPaymentButton , driver);
        onlineCardPaymentButton.click();
    }
}
