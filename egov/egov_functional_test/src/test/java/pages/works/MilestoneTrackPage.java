package pages.works;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.ISODateTimeFormat;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import javax.print.DocFlavor;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by karthik on 26/12/16.
 */
public class MilestoneTrackPage extends BasePage {

    private WebDriver driver;

    @FindBy(xpath = ".//*[@id='btnsearch']")
    private WebElement searchButton;

    @FindBy(id = "resultTable_wrapper")
    private WebElement searchTableForCreate;

    @FindBy(id = "createMilestone")
    private WebElement createMilestoneButton;

    @FindBy(xpath = ".//*[@id='milestone']/div[1]/div/div[1]/div/div/div[2]/div[5]/div[2]/a")
    private WebElement loaNumberBox;

    @FindBy(id = "milestoneDetailsTbl")
    private WebElement milestoneStageTbl;

    @FindBy(id = "addRowBtn")
    private WebElement addRowButton;

    @FindBy(className = "datepicker-days")
    private WebElement calenderForm;

    @FindBy(xpath = ".//*[@id='resultTable']/tbody/tr[1]/td[1]/input")
    private WebElement radioButton;

    @FindBy(xpath = ".//*[@id='save']")
    private WebElement saveButton;

    @FindBy(id = "successMessage")
    private WebElement creationMsg;

    @FindBy(xpath = ".//*[@id='page-container']/div/div[1]/div[1]")
    private WebElement creationMsg1;

    @FindBy(css = "input[value='Close'][type='submit']")
    private WebElement closeButton;

    @FindBy(css = "input[id='workOrderNumber'][type='text']")
    private WebElement loaNumberTextBox;

    @FindBy(id = "btntrackmilestone")
    private WebElement trackMilestoneButton;

    @FindBy(css = "input[id = 'adminSanctionFromDate'][type = 'text']")
    private WebElement adminSanctionDateTextBox;

    @FindBy(id = "btncreateloa")
    private WebElement createContractorBillButton;

    @FindBy(id = "billtype")
    private WebElement billTypeBox;

    @FindBy(id = "mbRefNo")
    private WebElement mbRefNoTextBox;

    @FindBy(id = "fromPageNo")
    private WebElement fromPageNoTextBox;

    @FindBy(id = "toPageNo")
    private WebElement toPageNoTextBox;

    @FindBy(css = "input[id='mbDate'][type='text']")
    private WebElement mbDateTextBox;

    @FindBy(css = "input[id='workCompletionDate'][type='text']")
    private WebElement completionDateTextBox;

    @FindBy(css = "input[id='debitamount'][type='text']")
    private WebElement debitAmountTextBox;

    @FindBy(css = "input[id='Forward'][type='submit']")
    private WebElement forwardButton;

    @FindBy(id = "official_inbox")
    private WebElement workListTable;

    @FindBy(id = "approvalComent")
    private WebElement approvalCommentBox;

    @FindBy(css = "input[value='Approve'][type='submit']")
    private WebElement approveButton;

    public MilestoneTrackPage(WebDriver driver) {this.driver = driver;}

    public void search(String number) {
     driver.findElement(By.id("workOrderNumber")).sendKeys(number);
     clickOnButton(searchButton, driver);
    }
    public void select() {
        jsClick(radioButton,driver);
    }
    public void createMilestone(){
        clickOnButton(createMilestoneButton, driver);
    }
    public String getLoaNumber() {
        String loaNumber = getTextFromWeb(loaNumberBox, driver);
        return loaNumber;
    }
    public void enterMilestoneDetails() {
        WebElement requiredRow = milestoneStageTbl.findElements(By.tagName("tr")).get(0);
        WebElement stageDescription = requiredRow.findElements(By.tagName("td")).get(1).findElement(By.name("activities[0].description"));
        enterText(stageDescription, "Stage 1", driver);
        WebElement stagePercentage = requiredRow.findElements(By.tagName("td")).get(2).findElement(By.name("activities[0].percentage"));
        enterText(stagePercentage, "50", driver);
        WebElement stageScheduleStartDate = requiredRow.findElements(By.tagName("td")).get(3).findElement(By.name("activities[0].scheduleStartDate"));
        enterDate(stageScheduleStartDate, getCurrentDate(), driver);
        enterDate(stageScheduleStartDate, getCurrentDate(), driver);

        WebElement stageScheduleEndDate = requiredRow.findElements(By.tagName("td")).get(4).findElement(By.name("activities[0].scheduleEndDate"));
        enterDate(stageScheduleEndDate, getFutureDate(62), driver);
        enterText(stageScheduleEndDate, getFutureDate(62), driver);

        clickOnButton(addRowButton, driver);

        WebElement requiredRow1 = milestoneStageTbl.findElements(By.tagName("tr")).get(1);
        WebElement stageOrderNo1 = driver.findElement(By.xpath("(//*[@id='stageOrderNo'])[2]"));
        enterText(stageOrderNo1, "2", driver);
        WebElement stageDescription1 = requiredRow1.findElements(By.tagName("td")).get(1).findElement(By.name("activities[1].description"));
        enterText(stageDescription1, "Stage 2", driver);
        WebElement stagePercentage1 = requiredRow1.findElements(By.tagName("td")).get(2).findElement(By.name("activities[1].percentage"));
        enterText(stagePercentage1, "50", driver);
        WebElement stageScheduleStartDate1 = requiredRow1.findElements(By.tagName("td")).get(3).findElement(By.name("activities[1].scheduleStartDate"));
        enterDate(stageScheduleStartDate1, getFutureDate(62), driver);
        enterDate(stageScheduleStartDate1, getFutureDate(62), driver);

        WebElement stageScheduleEndDate1 = requiredRow1.findElements(By.tagName("td")).get(4).findElement(By.name("activities[1].scheduleEndDate"));
        enterText(stageScheduleEndDate1, getFutureDate(72), driver);
        enterText(stageScheduleEndDate1, getFutureDate(72), driver);
    }

    public void save() {
        clickOnButton(saveButton, driver);
    }

    public String successMessage(){
        String msg = getTextFromWeb(creationMsg, driver);
        return msg;
    }

    public void close(){
        clickOnButton(closeButton, driver);
        switchToPreviouslyOpenedWindow(driver);
    }

    public void searchUsingLoa(String number) {
        enterText(loaNumberTextBox, number, driver);
        clickOnButton(searchButton,driver);
    }

    public void selectApplication() {
        waitForElementToBeClickable(radioButton,driver);
        jsClick(radioButton,driver);

        waitForElementToBeClickable(trackMilestoneButton,driver);
        trackMilestoneButton.click();
    }

    public void enterTrackMilestoneDetails() {
        WebElement element1 = driver.findElement(By.id("tblmilestone"));
        WebElement status1 = element1.findElement(By.name("trackMilestone[0].activities[0].status"));
        WebElement element2 = element1.findElement(By.className("scheduleEndDate_0"));
        selectFromDropDown(status1,"COMPLETED", driver );
        WebElement completionDateBox1 = element1.findElement(By.name("trackMilestone[0].activities[0].completionDate"));
        enterText(completionDateBox1, element2.getText(), driver);
        WebElement status2 = element1.findElement(By.name("trackMilestone[0].activities[1].status"));
        selectFromDropDown(status2, "COMPLETED", driver);
        await().atMost(1, SECONDS);
        WebElement completionDateBox2 = element1.findElement(By.name("trackMilestone[0].activities[1].completionDate"));
        enterDate(completionDateBox2, getFutureDate(82), driver);
        WebElement reasonForDelayTextBox = element1.findElement(By.name("trackMilestone[0].activities[1].remarks"));
        enterText(reasonForDelayTextBox, "Delay", driver);
    }

    public void createContractorBill() {
        clickOnButton(createContractorBillButton, driver);
    }

    public void enterContractorBillDetails(String billType) {

        switch (billType){
           case "part":
               selectFromDropDown(billTypeBox, "Part Bill", driver);
               break;

           case "full":
               selectFromDropDown(billTypeBox, "Final Bill", driver);
               enterDate(completionDateTextBox, getFutureDate(62), driver);
               break;
        }

        enterText(mbRefNoTextBox, "MB"+get6DigitRandomInt(), driver);
        enterText(fromPageNoTextBox, "1", driver);
        enterText(toPageNoTextBox, "10", driver);
        enterDate(mbDateTextBox, getCurrentDate(), driver);
        enterText(debitAmountTextBox, "1000", driver);
    }

    public String forwardToDEEContractorBill() {
        clickOnButton(forwardButton, driver);
        String text = getTextFromWeb(creationMsg1, driver);
        String billNumber = text.split("\\ ")[2];
        return billNumber;
    }

    public String successMessage1(){
        String msg = getTextFromWeb(creationMsg1, driver);
        return msg;
    }

    public void approve() {
        enterText(approvalCommentBox, "Approved", driver);
        clickOnButton(approveButton, driver);
    }
}
