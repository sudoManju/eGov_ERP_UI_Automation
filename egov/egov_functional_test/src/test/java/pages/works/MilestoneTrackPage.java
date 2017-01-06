package pages.works;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.ISODateTimeFormat;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import javax.print.DocFlavor;
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

    public void search() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(new Date());

        waitForElementToBeClickable(adminSanctionDateTextBox,driver);
        adminSanctionDateTextBox.sendKeys(date, Keys.TAB);

        waitForElementToBeVisible(searchButton,driver);
        searchButton.click();
    }

    public void select() {
        waitForElementToBeVisible(searchTableForCreate,driver);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        waitForElementToBeVisible(radioButton,driver);
        jsClick(radioButton,driver);

    }

    public void createMilestone(){
        waitForElementToBeVisible(createMilestoneButton,driver);
        createMilestoneButton.click();
    }

    public String getLoaNumber() {
        waitForElementToBeVisible(loaNumberBox,driver);
        String loaNumber = loaNumberBox.getText();
        System.out.println("\n Number: "+loaNumber);
        return loaNumber;
    }

    public void enterMilestoneDetails() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(new Date());
        Date dt = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 30);
        String date1 = sdf.format(c.getTime());
        c.add(Calendar.DATE, 1);
        String date2 = sdf.format(c.getTime());
        c.add(Calendar.DATE, 30);
        String date3 = sdf.format(c.getTime());

        waitForElementToBeVisible(milestoneStageTbl,driver);
        WebElement requiredRow = milestoneStageTbl.findElements(By.tagName("tr")).get(0);

        WebElement stageDescription = requiredRow.findElements(By.tagName("td")).get(1).findElement(By.name("activities[0].description"));
        stageDescription.sendKeys("Stage 1");

        WebElement stagePercentage = requiredRow.findElements(By.tagName("td")).get(2).findElement(By.name("activities[0].percentage"));
        stagePercentage.sendKeys("50");

        WebElement stageScheduleStartDate = requiredRow.findElements(By.tagName("td")).get(3).findElement(By.name("activities[0].scheduleStartDate"));
        stageScheduleStartDate.sendKeys(date);
        stageScheduleStartDate.clear();
        stageScheduleStartDate.sendKeys(date);

        WebElement stageScheduleEndDate = requiredRow.findElements(By.tagName("td")).get(4).findElement(By.name("activities[0].scheduleEndDate"));
        stageScheduleEndDate.sendKeys(date1);
        stageScheduleEndDate.clear();
        stageScheduleEndDate.sendKeys(date1);

        waitForElementToBeClickable(addRowButton,driver);
        addRowButton.click();

        WebElement requiredRow1 = milestoneStageTbl.findElements(By.tagName("tr")).get(1);

        WebElement stageOrderNo1 = driver.findElement(By.xpath("(//*[@id='stageOrderNo'])[2]"));
        stageOrderNo1.sendKeys("2");

        WebElement stageDescription1 = requiredRow1.findElements(By.tagName("td")).get(1).findElement(By.name("activities[1].description"));
        stageDescription1.sendKeys("Stage 2");

        WebElement stagePercentage1 = requiredRow1.findElements(By.tagName("td")).get(2).findElement(By.name("activities[1].percentage"));
        stagePercentage1.sendKeys("50");

        WebElement stageScheduleStartDate1 = requiredRow1.findElements(By.tagName("td")).get(3).findElement(By.name("activities[1].scheduleStartDate"));
        stageScheduleStartDate1.sendKeys(date2);
        stageScheduleStartDate1.clear();
        stageScheduleStartDate1.sendKeys(date2);

        WebElement stageScheduleEndDate1 = requiredRow1.findElements(By.tagName("td")).get(4).findElement(By.name("activities[1].scheduleEndDate"));
        stageScheduleEndDate1.sendKeys(date3);
        stageScheduleEndDate1.clear();
        stageScheduleEndDate1.sendKeys(date3);

    }

    public void save() {
        waitForElementToBeClickable(saveButton,driver);
        saveButton.click();
    }

    public String successMessage(){
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

    public void searchUsingLoa(String number) {
        waitForElementToBeVisible(loaNumberTextBox,driver);
        loaNumberTextBox.sendKeys(number);

        waitForElementToBeClickable(searchButton,driver);
        searchButton.click();
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

        waitForElementToBeVisible(status1,driver);
        new Select(status1).selectByVisibleText("COMPLETED");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement completionDateBox1 = element1.findElement(By.name("trackMilestone[0].activities[0].completionDate"));
        completionDateBox1.sendKeys(element2.getText(),Keys.TAB , Keys.ARROW_DOWN);
        WebElement status2 = element1.findElement(By.name("trackMilestone[0].activities[1].status"));

        waitForElementToBeVisible(status2,driver);
        new Select(status2).selectByVisibleText("COMPLETED");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dt = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 62);
        String date1 = sdf.format(c.getTime());


        WebElement completionDateBox2 = element1.findElement(By.name("trackMilestone[0].activities[1].completionDate"));
        completionDateBox2.sendKeys(date1);
        completionDateBox2.sendKeys(Keys.TAB);

        WebElement reasonForDelayTextBox = element1.findElement(By.name("trackMilestone[0].activities[1].remarks"));
        reasonForDelayTextBox.sendKeys("testing");
    }


    public void createContractorBill() {
        waitForElementToBeClickable(createContractorBillButton,driver);
        createContractorBillButton.click();
    }

    public void enterContractorBillDetails() {
        String num1 = String.valueOf(Calendar.getInstance().get(Calendar.MILLISECOND));
        String num = String.valueOf(Calendar.getInstance().get(Calendar.SECOND));
        String transactionRefNo = num1+num;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(new Date());
        Date dt = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE,62);
        String date1 = sdf.format(c.getTime());

        waitForElementToBeVisible(billTypeBox,driver);
        new Select(billTypeBox).selectByVisibleText("Final Bill");

        waitForElementToBeClickable(mbRefNoTextBox,driver);
        mbRefNoTextBox.sendKeys("MB"+transactionRefNo);

        waitForElementToBeClickable(fromPageNoTextBox,driver);
        fromPageNoTextBox.sendKeys("1");

        waitForElementToBeClickable(toPageNoTextBox,driver);
        toPageNoTextBox.sendKeys("10");

        waitForElementToBeClickable(mbDateTextBox,driver);
        mbDateTextBox.sendKeys(date, Keys.TAB);

        waitForElementToBeClickable(completionDateTextBox,driver);
        completionDateTextBox.sendKeys(date1, Keys.TAB);

        waitForElementToBeClickable(debitAmountTextBox,driver);
        debitAmountTextBox.sendKeys("1000");

    }

    public String forwardToDEEContractorBill() {
        waitForElementToBeClickable(forwardButton,driver);
        forwardButton.click();

        waitForElementToBeVisible(creationMsg1,driver);
        String text = creationMsg1.getText();

        String billNumber = text.split("\\ ")[2];
        System.out.println("\n"+billNumber);

        return billNumber;
    }

    public String successMessage1(){
        waitForElementToBeVisible(creationMsg1,driver);
        String msg = creationMsg1.getText();

        System.out.println(msg);
        return msg;
    }


    public void approve() {
        waitForElementToBeVisible(approvalCommentBox,driver);
        approvalCommentBox.sendKeys("Approved");

        waitForElementToBeClickable(approveButton,driver);
        approveButton.click();
    }
}
