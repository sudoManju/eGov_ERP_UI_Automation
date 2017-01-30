package pages.SewerageTax;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
        documentDateTextBox.sendKeys("27/01/2017");

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

    public String getSuccessMessage1() {
        waitForElementToBeVisible(successMessageForSewerageConnectionText1,driver);
        return successMessageForSewerageConnectionText1.getText();
    }

    public String getApplicatioNumber() {
        waitForElementToBeVisible(applicationNumberText,driver);

        String num1 = applicationNumberText.getText().split("\\ ")[5].substring(1,14);
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

        List<WebElement> totalRows = inboxTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
//        System.out.println("\n "+ totalRows.size());

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

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.close();
        switchToPreviouslyOpenedWindow(driver);
    }

    public void searchForApplicationInbox(String num) {
        waitForElementToBeVisible(inboxSearchTextBox,driver);
        inboxSearchTextBox.sendKeys(num);
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
}
