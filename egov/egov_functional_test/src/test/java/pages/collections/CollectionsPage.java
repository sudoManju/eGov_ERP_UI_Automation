package pages.collections;

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
        enterText(bankNameInput, chequeDetails.getBankName());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        await().atMost(10, SECONDS).until(() -> driver.findElement(By.id("bankcodescontainer"))
                .findElements(By.cssSelector("ul li"))
                .get(0).click());

//        amountTextBox.sendKeys(amountToBePaidLabel.getText());
        paidByTextBox.sendKeys(chequeDetails.getPaidBy());
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        jse.executeScript(String.format("document.getElementById('instrumentChequeAmount').value = '%s';", amountToBePaidLabel.getText()));

//        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        enterText(amountTextBox, amountToBePaidLabel.getAttribute("value").split("\\.")[0]);
        payButton.click();

//        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

       for (int i=0;i<4;i++) {
           try {
               WebElement element = driver.findElement(By.id("billDetailslist[0].creditAmountDetail"));
               element.clear();
               element.sendKeys("500");
           } catch (StaleElementReferenceException e) {
               WebElement element = driver.findElement(By.id("billDetailslist[0].creditAmountDetail"));
               element.clear();
               element.sendKeys("500");
           }
       }
    }

    public void enterApprovalDetails(ApprovalDetails approverDetails) {

        waitForElementToBeClickable(approverDeptBox, driver);
        new Select(approverDeptBox).selectByVisibleText(approverDetails.getApproverDepartment());
        new Select(approverDesignationBox).selectByVisibleText(approverDetails.getApproverDesignation());
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    public void payAmount(String method) {

        waitForElementToBeVisible(amountToBePaid, driver);
        String amount = amountToBePaid.getAttribute("value");
        String actualAmount = amount.split("\\.")[0];
        System.out.println("\n"+actualAmount);

        switch (method){
            case "cash" :

                waitForElementToBeClickable(payAmountBoxForCash,driver);
                payAmountBoxForCash.sendKeys(actualAmount);

                break;

            case "cheque" :

                waitForElementToBeClickable(chequeModeofPaymentRadio,driver);
                jsClick(chequeModeofPaymentRadio,driver);
                waitForElementToBeVisible(chequeNumberTextBox,driver);
                chequeNumberTextBox.sendKeys("123456");
                waitForElementToBeClickable(chequeDateTextBox,driver);
                chequeDateTextBox.sendKeys("02/01/2017");
                waitForElementToBeClickable(bankNameInput,driver);
                bankNameInput.sendKeys("102");

                driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                await().atMost(10, SECONDS).until(() -> driver.findElement(By.id("bankcodescontainer"))
                        .findElements(By.cssSelector("ul li"))
                        .get(0).click());

                bankNameInput.sendKeys(Keys.TAB);

                waitForElementToBeClickable(payAmountBoxForCheque,driver);
                payAmountBoxForCheque.sendKeys(actualAmount);

                break;

            case "dd" :

                waitForElementToBeClickable(ddModeofPaymentRadio,driver);
                jsClick(ddModeofPaymentRadio,driver);

                waitForElementToBeVisible(chequeNumberTextBox,driver);
                chequeNumberTextBox.sendKeys("123456");
                waitForElementToBeClickable(chequeDateTextBox,driver);
                chequeDateTextBox.sendKeys("02/01/2017");
                waitForElementToBeClickable(bankNameInput,driver);
                bankNameInput.sendKeys("102");

                driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                await().atMost(10, SECONDS).until(() -> driver.findElement(By.id("bankcodescontainer"))
                        .findElements(By.cssSelector("ul li"))
                        .get(0).click());

                waitForElementToBeClickable(payAmountBoxForCheque,driver);
                payAmountBoxForCheque.sendKeys(actualAmount);

                break;

        }


        payButton.click();
        switchToNewlyOpenedWindow(driver);
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

        await().atMost(5, SECONDS).until(() -> driver.getWindowHandles().size() == 1);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }
}