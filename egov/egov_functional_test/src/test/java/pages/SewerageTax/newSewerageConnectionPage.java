package pages.SewerageTax;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by karthik on 27/1/17.
 */
public class newSewerageConnectionPage extends BasePage {

    private WebDriver driver;

    @FindBy(css = "input[id='propertyIdentifier'][type='text']")
    private WebElement PTAssessmentNumberTextBox;

    @FindBy(id = "propertyType")
    private WebElement propertyTypeDropBox;

    @FindBy(css = "input[id='noOfClosetsResidential'][type='text']")
    private WebElement noOfClosetsTextBox;

    @FindBy(css = "input[id='appDetailsDocument0documentNumber'][type='text']")
    private WebElement documentNumberTextBox;

    @FindBy(css = "input[id='appDetailsDocument0documentDate'][type='text']")
    private WebElement documentDateTextBox;

    @FindBy(css = "input[id='file0id'][type='file']")
    private WebElement chooseFileButton;

    @FindBy(id = "Forward")
    private WebElement forwardButton;

    @FindBy(xpath = ".//*[@id='sewarageConnectionSuccess']/div/div/div/span[1]")
    private WebElement applicationNumberText;

    @FindBy(xpath = ".//*[@id='sewarageConnectionSuccess']/div/div/div/span[2]")
    private WebElement successMessageForSewerageConnectionText;

    @FindBy(xpath = ".//*[@id='sewarageConnectionSuccess']/div/div/div/span")
    private WebElement successMessageForSewerageConnectionText1;

    @FindBy(xpath = ".//*[@id='sewarageChangeClosetsSuccess']/div/div/div/span[1]")
    private WebElement applicationNumberTextForChange;

    @FindBy(xpath = ".//*[@id='sewarageChangeClosetsSuccess']/div/div/div/span[2]")
    private WebElement getSuccessMessageForChangeSewerageConnectionText;

    @FindBy(xpath = ".//*[@id='sewarageChangeClosetsSuccess']/div/div/div/span")
    private WebElement successMessageForChangeSewerageConnectionText1;


    @FindBy(linkText = "Close")
    private WebElement closeLink;

    @FindBy(css = "input[id='consumerNumber'][type='text']")
    private WebElement applicationNumberTextBox;

    @FindBy(id = "searchSewerageapplication")
    private WebElement searchButton;

    @FindBy(id = "aplicationSearchResults")
    private WebElement searchResultsTable;

    @FindBy(id = "totalamounttobepaid")
    private WebElement amountToBePaidText;

    @FindBy(id = "instrHeaderCash.instrumentAmount")
    private WebElement amountToBePaidTextBox;

    @FindBy(css = "input[value='Pay'][type='submit']")
    private WebElement payButton;

    @FindBy(css = "input[value='Close'][type='button']")
    private WebElement closeButton;

    @FindBy(css = "li[role='presentation'] a[data-now='New%20Sewerage%20Connection']")
    private WebElement newSewerageConnectionLink;

    @FindBy(css = "li[role='presentation'] a[data-now='Change%20In%20Closets']")
    private WebElement changeInClosetsLink;

    @FindBy(css = "li[role='presentation'] a[data-now='Close%20Sewerage%20Connection']")
    private WebElement closeSewerageConnectionLink;

    @FindBy(id = "official_inbox")
    private WebElement inboxTable;

    @FindBy(id = "approvalComent")
    private WebElement approverCommentTextBox;

    @FindBy(id = "Approve")
    private WebElement approveButton;

    @FindBy(id = "Generate Estimation Notice")
    private WebElement generateEstimationNoticeButton;

    @FindBy(css = "input[id='inboxsearch'][type='text']")
    private WebElement inboxSearchTextBox;

    @FindBy(linkText = "Generate Work Order")
    private WebElement generateWorkOrderLink;

    @FindBy(id = "Execute Connection")
    private WebElement executeConnectionButton;

    @FindBy(id = "closeConnectionReason")
    private WebElement closeConnectionRemarksTextBox;

    @FindBy(xpath = ".//*[@id='sewarageCloseConnectionSuccess']/div/div/div/span[1]")
    private WebElement getApplicationNumberTextForClosure;

    @FindBy(xpath = ".//*[@id='sewarageCloseConnectionSuccess']/div/div/div/span[2]")
    private WebElement getSuccessMessageForSewerageConnectionClosure;

    @FindBy(id = "Generate Close Connection Notice")
    private WebElement generateClosureNoticeButton;

    @FindBy(css = "input[id='shscNumber'][type='text']")
    private WebElement hscNumberTextBox;

    @FindBy(css = "input[id='executionDate'][type='text']")
    private WebElement executionDateTextBox;

    @FindBy(css = "input[id='demandDetailBeanList0actualAmount'][type='text']")
    private WebElement demandTextBox;

    @FindBy(css = "input[id='demandDetailBeanList0actualCollection'][type='text']")
    private WebElement collectionTextBox;

    @FindBy(id = "submit")
    private WebElement submitButton;

    String min = String.valueOf(Calendar.getInstance().get(Calendar.MILLISECOND));
    String hour = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
    String min1 = String.valueOf(Calendar.getInstance().get(Calendar.SECOND));

    public newSewerageConnectionPage(WebDriver driver) {
        this.driver = driver;
    }

    public void createNewConnection(String assessmentNumber) {
        waitForElementToBeVisible(PTAssessmentNumberTextBox,driver);
        PTAssessmentNumberTextBox.sendKeys(assessmentNumber);

        waitForElementToBeClickable(propertyTypeDropBox,driver);
        new Select(propertyTypeDropBox).selectByVisibleText("RESIDENTIAL");

        waitForElementToBeVisible(noOfClosetsTextBox,driver);
        noOfClosetsTextBox.sendKeys("3");

        waitForElementToBeClickable(documentNumberTextBox,driver);
        documentNumberTextBox.sendKeys("123");

        waitForElementToBeClickable(documentDateTextBox,driver);
        documentDateTextBox.sendKeys(getCurrentDate());

        waitForElementToBeClickable(chooseFileButton,driver);
        chooseFileButton.sendKeys(System.getProperty("user.dir") + "/src/test/resources/loginCredentials.txt");
    }

    public void forward() {
        waitForElementToBeClickable(forwardButton,driver);
        forwardButton.click();
    }

    public String getSuccessMessage() {
        waitForElementToBeVisible(successMessageForSewerageConnectionText,driver);
        return successMessageForSewerageConnectionText.getText();
    }

    public String getSuccessMessageForChangeSewerage() {
        waitForElementToBeVisible(getSuccessMessageForChangeSewerageConnectionText,driver);
        return getSuccessMessageForChangeSewerageConnectionText.getText();
    }

    public String getSuccessMessage1() {
        waitForElementToBeVisible(successMessageForSewerageConnectionText1,driver);
        return successMessageForSewerageConnectionText1.getText();
    }

    public String getApplicationNumberForLegacyCreation(){
        waitForElementToBeVisible(successMessageForSewerageConnectionText1,driver);

        String num1 = successMessageForSewerageConnectionText1.getText().split("\\ ")[5].substring(1,14);
        System.out.println("\n "+num1);

        return num1;
    }

    public String getSuccessMessage1ForChangeSewerage() {
        waitForElementToBeVisible(successMessageForChangeSewerageConnectionText1,driver);
        return successMessageForChangeSewerageConnectionText1.getText();
    }

    public String getApplicatioNumber() {
        waitForElementToBeVisible(applicationNumberText,driver);

        String num1 = applicationNumberText.getText().split("\\ ")[5].substring(1,14);
        System.out.println("\n "+num1);

        return num1;
    }

    public String getApplicatioNumberForChangeSewerage() {
        waitForElementToBeVisible(applicationNumberTextForChange,driver);

        String num1 = applicationNumberTextForChange.getText().split("\\ ")[5].substring(1,14);
        System.out.println("\n "+num1);

        return num1;
    }

    public void close() {
      waitForElementToBeClickable(closeLink,driver);
      closeLink.click();

      switchToPreviouslyOpenedWindow(driver);
    }

    public void searchForApplicationNumberToCollect(String number) {
        waitForElementToBeVisible(applicationNumberTextBox,driver);
        applicationNumberTextBox.sendKeys(number);

        waitForElementToBeClickable(searchButton,driver);
        searchButton.click();

        waitForElementToBeVisible(searchResultsTable,driver);
        WebElement dropDownAction = searchResultsTable.findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(8).findElement(By.className("actiondropdown"));
        waitForElementToBeClickable(dropDownAction,driver);
        new Select(dropDownAction).selectByVisibleText("Collect Fee");
        switchToNewlyOpenedWindow(driver);
    }

    public void collectCharges() {
        waitForElementToBeVisible(amountToBePaidText,driver);
        String amount = amountToBePaidText.getAttribute("value");
        String actualAmount = amount.split("\\.")[0];

        waitForElementToBeClickable(amountToBePaidTextBox,driver);
        amountToBePaidTextBox.sendKeys(actualAmount);

        waitForElementToBeClickable(payButton,driver);
        jsClick(payButton,driver);
    }

    public void closeMultipleWindows(String s) {
        waitForElementToBeVisible(closeButton,driver);
        closeButton.click();

        for (String winHandle : driver.getWindowHandles()) {
            if(driver.switchTo().window(winHandle).getCurrentUrl().equals(s)){
                break;
            }
        }

        close();
    }

    public void SelectSewerageTax() {
         waitForElementToBeVisible(newSewerageConnectionLink,driver);
         newSewerageConnectionLink.click();
    }

    public void selectAboveApplication(String applicationNumber) {
        waitForElementToBeVisible(inboxTable,driver);
        waitForElementToBeClickable(inboxTable,driver);

        await().atMost(20, SECONDS).until(() -> inboxTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size() > 0);
        List<WebElement> totalRows = inboxTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));

        for (WebElement applicationRow : totalRows){
            if(applicationRow.findElements(By.tagName("td")).get(4).getText().contains(applicationNumber)){
                applicationRow.click();
                break;
            }
        }
        switchToNewlyOpenedWindow(driver);
    }


    public void approveTheApplication() {
        waitForElementToBeVisible(approverCommentTextBox,driver);
        approverCommentTextBox.sendKeys("Approved");

        waitForElementToBeVisible(approveButton,driver);
        waitForElementToBeClickable(approveButton,driver);
        approveButton.click();
    }

    public void generateEstimationNotice() {
        waitForElementToBeClickable(approverCommentTextBox,driver);
        approverCommentTextBox.sendKeys("Generated estimate notice");

        waitForElementToBeVisible(generateEstimationNoticeButton,driver);
        waitForElementToBeClickable(generateEstimationNoticeButton,driver);
        generateEstimationNoticeButton.click();

        await().atMost(3, SECONDS);

        driver.close();
        switchToPreviouslyOpenedWindow(driver);
    }



    public void generateWorkOrder(String num) {
        waitForElementToBeVisible(generateWorkOrderLink,driver);
        generateWorkOrderLink.click();

        switchToNewlyOpenedWindow(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.close();
        for (String winHandle : driver.getWindowHandles()) {
            if(driver.switchTo().window(winHandle).getCurrentUrl().equals("http://kurnool-uat.egovernments.org/stms/transactions/update/"+num)){
                break;
            }
        }
    }

    public void executeConnection() {
        waitForElementToBeVisible(executeConnectionButton,driver);
        waitForElementToBeClickable(executeConnectionButton,driver);
        executeConnectionButton.click();
    }

    public void searchForAboveSewerageConnection(String number) {
        waitForElementToBeVisible(applicationNumberTextBox,driver);
        applicationNumberTextBox.sendKeys(number);

        waitForElementToBeClickable(searchButton,driver);
        searchButton.click();

        waitForElementToBeVisible(searchResultsTable,driver);
        WebElement dropDownAction = searchResultsTable.findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(1).findElement(By.tagName("a"));
        String hscNumber = dropDownAction.getText();
//        waitForElementToBeClickable(dropDownAction,driver);
//        new Select(dropDownAction).selectByVisibleText("Change number of seats");
//        switchToNewlyOpenedWindow(driver);
//        System.out.println(driver.getTitle());
        driver.navigate().to("http://kurnool-uat.egovernments.org/stms/transactions/modifyConnection/"+hscNumber);
    }

    public void increseTheNumberOfClosets() {
        String url = driver.getCurrentUrl();
        System.out.println(url);
        waitForElementToBeVisible(noOfClosetsTextBox,driver);
        waitForElementToBeClickable(noOfClosetsTextBox,driver);
        noOfClosetsTextBox.clear();
        noOfClosetsTextBox.sendKeys("5");

        waitForElementToBeClickable(documentNumberTextBox,driver);
        documentNumberTextBox.sendKeys("123");

        waitForElementToBeClickable(documentDateTextBox,driver);
        documentDateTextBox.sendKeys(getCurrentDate());

        waitForElementToBeClickable(chooseFileButton,driver);
        chooseFileButton.sendKeys(System.getProperty("user.dir") + "/src/test/resources/loginCredentials.txt");
    }

    public void selectChangeInClosets() {
         waitForElementToBeClickable(changeInClosetsLink,driver);
        changeInClosetsLink.click();
    }

    public void searchForSewerageConnectionForClosure(String applicationNumber) {
        waitForElementToBeVisible(applicationNumberTextBox,driver);
        applicationNumberTextBox.sendKeys(applicationNumber);

        waitForElementToBeClickable(searchButton,driver);
        searchButton.click();

        waitForElementToBeVisible(searchResultsTable,driver);
        WebElement dropDownAction = searchResultsTable.findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(1).findElement(By.tagName("a"));
        String hscNumber = dropDownAction.getText();

        driver.navigate().to("http://kurnool-uat.egovernments.org/stms/transactions/closeConnection/"+hscNumber);
    }

    public void remarks() {
        waitForElementToBeVisible(closeConnectionRemarksTextBox,driver);
        closeConnectionRemarksTextBox.sendKeys("Testing...");
    }

    public void selectClosureConnection() {
        waitForElementToBeVisible(closeSewerageConnectionLink,driver);
        closeSewerageConnectionLink.click();
    }

    public String getApplicatioNumberForClosure() {
        waitForElementToBeVisible(getApplicationNumberTextForClosure,driver);

        String num1 = getApplicationNumberTextForClosure.getText().split("\\ ")[4].substring(1,14);
        System.out.println("\n "+num1);

        return num1;
    }

    public String getSuccessMessageForClosure() {
        waitForElementToBeVisible(getSuccessMessageForSewerageConnectionClosure,driver);
        return getSuccessMessageForSewerageConnectionClosure.getText();
    }

    public void generateClosureNotice() {
        waitForElementToBeVisible(generateClosureNoticeButton,driver);
        generateClosureNoticeButton.click();

        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        driver.close();
        switchToPreviouslyOpenedWindow(driver);
    }

    public void enterDetailsForLegacySewerageConnection(String assessmentNumber) {
        waitForElementToBeVisible(PTAssessmentNumberTextBox,driver);
        PTAssessmentNumberTextBox.sendKeys(assessmentNumber);

        waitForElementToBeClickable(hscNumberTextBox,driver);
        hscNumberTextBox.sendKeys("1016"+hour+hour+min1+min);

        waitForElementToBeClickable(executionDateTextBox,driver);
        executionDateTextBox.sendKeys(getPreviousDate());

        waitForElementToBeVisible(demandTextBox,driver);
        demandTextBox.sendKeys("1000");

        waitForElementToBeClickable(collectionTextBox,driver);
        collectionTextBox.sendKeys("0");

        waitForElementToBeClickable(propertyTypeDropBox,driver);
        new Select(propertyTypeDropBox).selectByVisibleText("RESIDENTIAL");

        waitForElementToBeVisible(noOfClosetsTextBox,driver);
        noOfClosetsTextBox.sendKeys("3");
    }

    public void submit() {
        waitForElementToBeClickable(submitButton,driver);
        submitButton.click();
    }

    public void searchAndGenerateDemandBill(String number) {
        waitForElementToBeVisible(applicationNumberTextBox,driver);
        applicationNumberTextBox.sendKeys(number);

        waitForElementToBeClickable(searchButton,driver);
        searchButton.click();

        waitForElementToBeVisible(searchResultsTable,driver);
        WebElement dropDownAction = searchResultsTable.findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(1).findElement(By.tagName("a"));
        String hscNumber = dropDownAction.getText();

        driver.navigate().to("http://kurnool-uat.egovernments.org/stms/reports/generate-sewerage-demand-bill/"+number+"/"+hscNumber);

        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);

        driver.close();

        switchToPreviouslyOpenedWindow(driver);
    }
}
