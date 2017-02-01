package pages.collections;

import entities.collections.PaymentMethod;
import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by soumyaghosh on 01/12/16.
 */
public class MiscellaneousPage extends BasePage{
    private WebDriver driver;
    @FindBy(id ="paidBy")
    private WebElement paidByTextBox;

    @FindBy(id = "referenceDesc")
    private WebElement narrationTextBox;

    @FindBy(id = "payeeAddress")
    private WebElement payeeAddressTextBox;

    @FindBy(id = "serviceCategoryid")
    private WebElement serviceCategoryDropDown;

    @FindBy(id = "serviceId")
    private WebElement serviceTypeIDropDown;

    @FindBy(css = "input[type='text'][id='billCreditDetailslist[0].creditAmountDetail']")
    private  WebElement receiptHeadsAmount;

    @FindBy(css = "input[type='text'][name='instrumentProxyList[0].instrumentNumber']")
    private WebElement chequeNumberTextBox;

    @FindBy(css = "input[type='radio'][id='chequeradiobutton']")
    private WebElement chequeModeRadioButton;

    @FindBy(css = "input[type='text'][id='instrumentDate']")
    private WebElement chequeDateTextBox;

    @FindBy(css = "input[type='text'][name='instrumentProxyList[0].bankId.name']")
    private WebElement bankNameTextBox;

    @FindBy(xpath = ".//*[@id='instrumentChequeAmount']")
    private WebElement amountTextBox;

    @FindBy(css = "input[type='submit'][value='Pay']")
    private WebElement payButton;

    @FindBy(css = "input[type='radio'][id='ddradiobutton']")
    private WebElement ddModeRadioButton;

    @FindBy(css = "input[value = 'Submit All Collections'][type='submit']")
    private WebElement submitAllCollectionsButton;

    @FindBy(css = "input[type = 'button'][value = 'Close']")
    private WebElement closeButton;

    @FindBy(css = "input[value = 'Approve All Collections'][type = 'submit']")
    private WebElement approveAllCollectionsButton;

    @FindBy(id = "serviceClass")
    private WebElement classificationBox;

    @FindBy(id = "serviceType")
    private WebElement serviceTypeTextBox;

    @FindBy(css = "input[id='fromDate'][type='text']")
    private WebElement fromDateTextBox;

    @FindBy(css = "input[id='toDate'][type='text']")
    private WebElement toDateTextBox;

    @FindBy(css = "input[value = 'Search'][type='submit']")
    private WebElement searchButton;

    @FindBy(css = "input[value = 'Cancel Receipt'][type = 'button']")
    private WebElement cancelReceiptButton;

    @FindBy(id = "reasonForCancellation")
    private WebElement reasonForCancellationTextBox;

    @FindBy(id = "bankBranchMaster")
    private WebElement bankNameBox;

    @FindBy(id = "accountNumberId")
    private WebElement bankAccountNumberBox;

    @FindBy(id = "paymentMode")
    private WebElement paymentModeBox;

    @FindBy(xpath = ".//*[@id='receiptIds']")
    private WebElement receiptCheckBox;

    @FindBy(css = "input[id='remittanceDate'][type='text']")
    private WebElement remitDateTextBox;

    @FindBy(css = "input[value='Remit to Bank'][type='submit']")
    private WebElement remitToBankButton;

    @FindBy(className = "mainheading")
    private WebElement successMessageTextOfRemittance;

    @FindBy(css = "input[id='bankradiobutton'][type='radio']")
    private WebElement directBankRadioButton;

    @FindBy(id = "bankBranchMaster")
    private WebElement bankNameDropBox;

    @FindBy(id = "accountNumberMaster")
    private WebElement accountNumberDropBox;

    @FindBy(css = "input[id='bankChallanDate'][type='text']")
    private WebElement challanDateTextBox;

    @FindBy(css = "input[id='instrHeaderBank.transactionNumber'][type='text']")
    private WebElement referenceNumberTextBox;

    @FindBy(css = "input[id='instrHeaderBank.instrumentAmount'][type='text']")
    private WebElement directBankAmountTextBox;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String date = sdf.format(new Date());

    public MiscellaneousPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterMiscellaneousDetails() {

        paidByTextBox.sendKeys("Bimal Kumar");
        narrationTextBox.sendKeys("Narration");
        payeeAddressTextBox.sendKeys("Bangalore");
        new Select(serviceCategoryDropDown).selectByVisibleText("Entry Fees");

        waitForElementToBePresent(By.id("serviceId"),driver);
        waitForElementToBeClickable(serviceTypeIDropDown,driver);
        serviceTypeIDropDown.click();
        serviceTypeIDropDown.click();
        new Select(serviceTypeIDropDown).selectByIndex(1);

        for (int i = 0; i < 4; i++) {
            if (receiptHeadsAmount.isDisplayed())
                try {
                    receiptHeadsAmount.click();
                    receiptHeadsAmount.clear();
                    receiptHeadsAmount.sendKeys("655");
                } catch (StaleElementReferenceException e) {
                   WebElement element1 = driver.findElement(By.cssSelector("input[type='text'][id='billCreditDetailslist[0].creditAmountDetail']"));
                    element1.click();
                    element1.clear();
                    element1.sendKeys("655");
                }
          }
    }

    public void enterPaymentDetails(PaymentMethod paymentmethod, String mode) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(new Date());

        switch (mode){

            case "cash":

                break;

            case "cheque":

                waitForElementToBeClickable(chequeModeRadioButton,driver);
                chequeModeRadioButton.click();

                waitForElementToBeVisible(chequeNumberTextBox,driver);
                chequeNumberTextBox.sendKeys(paymentmethod.getChequeNumber());

                waitForElementToBeClickable(chequeDateTextBox,driver);
                chequeDateTextBox.sendKeys(date);

                waitForElementToBeClickable(bankNameTextBox,driver);
                bankNameTextBox.sendKeys(paymentmethod.getBankName());
                await().atMost(10, SECONDS).until(() -> driver.findElement(By.id("bankcodescontainer"))
                        .findElements(By.cssSelector("ul li"))
                        .get(0).click());

                waitForElementToBeClickable(amountTextBox,driver);
                amountTextBox.sendKeys("655");

                break;

            case "dd":

               waitForElementToBeClickable(ddModeRadioButton,driver);
               ddModeRadioButton.click();

                waitForElementToBeVisible(chequeNumberTextBox,driver);
                chequeNumberTextBox.sendKeys(paymentmethod.getChequeNumber());

                waitForElementToBeClickable(chequeDateTextBox,driver);
                chequeDateTextBox.sendKeys(date);

                waitForElementToBeClickable(bankNameTextBox,driver);
                bankNameTextBox.sendKeys(paymentmethod.getBankName());
                await().atMost(10, SECONDS).until(() -> driver.findElement(By.id("bankcodescontainer"))
                        .findElements(By.cssSelector("ul li"))
                        .get(0).click());

                waitForElementToBeClickable(amountTextBox,driver);
                amountTextBox.sendKeys("655");

                break;

            case "directBank":

              waitForElementToBeClickable(directBankRadioButton,driver);
              directBankRadioButton.click();

              waitForElementToBeVisible(referenceNumberTextBox,driver);
              referenceNumberTextBox.sendKeys(paymentmethod.getChequeNumber());

              waitForElementToBeClickable(challanDateTextBox,driver);
              challanDateTextBox.sendKeys(date);

              waitForElementToBeClickable(bankNameDropBox,driver);
              new Select(bankNameDropBox).selectByVisibleText(paymentmethod.getBankName());

              waitForElementToBeClickable(accountNumberDropBox,driver);
              new Select(accountNumberDropBox).selectByVisibleText(paymentmethod.getAccountNumber());

              waitForElementToBeClickable(directBankAmountTextBox,driver);
              directBankAmountTextBox.sendKeys("655");

              break;
        }

        waitForElementToBeClickable(payButton,driver);
        payButton.click();
    }

    public void openAboveReceipt(String tableId) {

        WebElement table = driver.findElement(By.id(tableId));

        waitForElementToBeVisible(table,driver);

        await().atMost(10, SECONDS).until(() -> table.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size() > 1);
        List<WebElement> totalRows = table.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        System.out.println("\n"+totalRows.size());

        List<WebElement> requiredRows = new ArrayList<>();

        for(WebElement applicationRow : totalRows ){
            if(applicationRow.findElements(By.tagName("td")).get(4).getText().contains("Entry Fees")){
                requiredRows.add(applicationRow);
            }
        }
        requiredRows.get(0).click();

        switchToNewlyOpenedWindow(driver);
    }

    public String submitAllCollections() {
        waitForElementToBeVisible(submitAllCollectionsButton,driver);
        WebElement element = driver.findElement(By.cssSelector("input[value = 'Submit All Collections'][type='submit']"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);


        waitForElementToBePresent(By.className("subheadnew"),driver);
        String message = driver.findElement(By.className("subheadnew")).getText();
        return message;
    }

    public void close() {
        waitForElementToBeClickable(closeButton,driver);
        closeButton.click();

        switchToPreviouslyOpenedWindow(driver);
    }


    public String approveAllCollections() {
        waitForElementToBeVisible(approveAllCollectionsButton,driver);
        WebElement element = driver.findElement(By.cssSelector("input[value = 'Approve All Collections'][type = 'submit']"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);

        waitForElementToBePresent(By.className("subheadnew"),driver);
        String message = driver.findElement(By.className("subheadnew")).getText();
        return message;
    }

    public void selectRequiredReceipt() {

        List<WebElement> totalPages = driver.findElements(By.xpath(".//*[@id='searchReceipt-search']/div[4]/span/a"));

        if(totalPages.size()>0) {
             WebElement lastPageLink = driver.findElement(By.xpath(".//*[@id='searchReceipt-search']/div[4]/span/a["+(totalPages.size()-1)+"]"));
             waitForElementToBeVisible(lastPageLink,driver);
             JavascriptExecutor executor = (JavascriptExecutor)driver;
             executor.executeScript("arguments[0].click();", lastPageLink);
        }

      List<WebElement> totalRows = driver.findElements(By.xpath(".//*[@id='selectedReceipts']"));
      WebElement requiredRow = totalRows.get(totalRows.size()-1);
      waitForElementToBeVisible(requiredRow,driver);
      jsClick(requiredRow,driver);

      waitForElementToBeClickable(cancelReceiptButton,driver);
      cancelReceiptButton.click();
    }

    public void searchRequiredReceipt() {
        waitForElementToBeVisible(classificationBox,driver);
        new Select(classificationBox).selectByVisibleText("Miscelleneous Collection");

        waitForElementToBeClickable(serviceTypeTextBox,driver);
        waitForElementToBePresent(By.xpath(".//*[@id='serviceType']/option[2]"),driver);
        new Select(serviceTypeTextBox).selectByVisibleText("Monuments Entry Fees-MNMENTFEE");

        waitForElementToBeClickable(fromDateTextBox,driver);
        fromDateTextBox.sendKeys(date);

        waitForElementToBeClickable(searchButton,driver);
        searchButton.click();
    }

    public String cancelReceipt() {
        waitForElementToBeVisible(reasonForCancellationTextBox,driver);
        reasonForCancellationTextBox.sendKeys("Testing");

        waitForElementToBeClickable(cancelReceiptButton,driver);
        cancelReceiptButton.click();

        WebElement cancelReceiptSuccessMessage = driver.findElement(By.xpath(".//*[@id='searchReceipt']/table/tbody/tr[2]/td/font/b/div"));
        waitForElementToBeVisible(cancelReceiptSuccessMessage,driver);
        String message = cancelReceiptSuccessMessage.getText();

        return message;
    }

    public void enterBankDetails() {
        waitForElementToBeVisible(bankNameBox, driver);
        new Select(bankNameBox).selectByVisibleText("STATE BANK OF HYDERABAD-KMC Complex Kurnool");

        waitForElementToBeClickable(bankAccountNumberBox, driver);
        new Select(bankAccountNumberBox).selectByVisibleText("62007226282");

        waitForElementToBeClickable(paymentModeBox, driver);
        new Select(paymentModeBox).selectByVisibleText("cheque/dd");

        waitForElementToBeClickable(fromDateTextBox,driver);
        fromDateTextBox.sendKeys(date);

        waitForElementToBeClickable(toDateTextBox,driver);
        toDateTextBox.sendKeys(date);

        waitForElementToBeClickable(searchButton, driver);
        searchButton.click();

        waitForElementToBeVisible(receiptCheckBox,driver);
        jsClick(receiptCheckBox,driver);

        receiptCheckBox.sendKeys(Keys.TAB);

        List<WebElement> allDates=driver.findElements(By.xpath(".//div[@class='datepicker-days']//td"));

        for(WebElement ele:allDates)
        {
            String date=ele.getAttribute("class");

            if(date.equalsIgnoreCase("active day"))
            {
                jsClick(ele,driver);
                break;
            }

        }

        waitForElementToBeClickable(remitToBankButton,driver);
        jsClick(remitToBankButton,driver);
    }

    public String successMessageOfRemittance() {
        String message = successMessageTextOfRemittance.getText();
        return message;
    }
}
