package pages.councilManagement;

import entities.councilManagement.CreatePreambleDetails;
import entities.ptis.ApprovalDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static java.awt.SystemColor.window;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by tester1 on 1/4/2017.
 */
public class CouncilManagementPage extends BasePage {
    private WebDriver webDriver;

    @FindBy (id = "department")
    private WebElement preambleDepartment;

    @FindBy (id = "sanctionAmount")
    private WebElement sanctionAmount;

    @FindBy (id = "gistOfPreamble")
    private WebElement gistOfPreamble;

    @FindBy (id = "attachments")
    private WebElement attachment;

    @FindBy (id = "wards")
    private WebElement wards;

    @FindBy (id = "Forward")
    private WebElement forwardButton;

    @FindBy (id = "Approve")
    private WebElement approve;

    @FindBy (id = "preambleNumber")
    private WebElement preambleNumberTextBox;

    @FindBy (id = "btnsearch")
    private WebElement searchbutton;

    @FindBy (id = "committeeType")
    private WebElement committeeTypeSelect;

    @FindBy (id = "btnsave")
    private WebElement saveButton;

    @FindBy (id = "agendaNumber")
    private WebElement agendaNumberTextBox;

    @FindBy (id = "meetingDate")
    private WebElement meetingDateText;

    @FindBy (id = "meetingTime")
    private WebElement meetingTimeSelect;

    @FindBy (id = "meetingLocation")
    private  WebElement meetingLocationText;

    @FindBy (id = "buttonSubmit")
    private WebElement createButton;

    @FindBy (linkText = "Close")
    private WebElement CloseButton;

    @FindBy (id = "meetingNumber")
    private WebElement meetingNumberText;

    @FindBy (id = "committeechk")
    private WebElement committeechkCheckBox;

    @FindBy (id = "finalizeAttendanceBtn")
    private WebElement finalizeAttendanceBtn;

    @FindBy (xpath = ".//*[@id='showModal']")
    private WebElement resolutionComment;

    @FindBy (id = "resolutionStatus")
    private WebElement resolutionStatusSelect;

    @FindBy (id = "buttonFinalSubmit")
    private WebElement resolutionPDFgenerationButton;

    @FindBy(xpath = ".//*[@id='textarea-updatedcontent']")
    private WebElement textEntry;

    @FindBy (xpath = ".//*[@id='textarea-btnupdate']")
    private WebElement updateButton;



    public CouncilManagementPage(WebDriver webDriver){this.webDriver=webDriver;}

    public void enterCreatePreambleDetails(CreatePreambleDetails createPreambleDetails) {
        new Select(preambleDepartment).selectByVisibleText(createPreambleDetails.getPreambleDepartment());
        enterText(sanctionAmount, createPreambleDetails.getAmount());
        enterText(gistOfPreamble, createPreambleDetails.getGistOfPreamble());
        attachment.sendKeys(System.getProperty("user.dir") + "/src/test/resources/PTISTestData.xlsx");
        Select sel=new Select(wards);
        for(int i=1;i<3;i++)
        {
            sel.selectByIndex(i);
        }
    }



    public String getPreambleNumber() {
        List<WebElement> elements=webDriver.findElements(By.cssSelector(".col-sm-3.add-margin.view-content"));
        return elements.get(0).getText();
    }

    public String getStatus() {
        List<WebElement> elements=webDriver.findElements(By.cssSelector(".col-sm-3.add-margin.view-content"));
        String ele=elements.get(1).getText();
        CloseButton.click();
        switchToPreviouslyOpenedWindow(webDriver);
        return ele;
    }

    public String approve() {
        approve.click();
        List<WebElement> elements=webDriver.findElements(By.cssSelector(".col-sm-3.add-margin.view-content"));
        String ele=elements.get(1).getText();
        CloseButton.click();
        switchToPreviouslyOpenedWindow(webDriver);
        return ele;
    }


    public void enterCreateAgenda(CreatePreambleDetails createPreambleDetails) {
        searchbutton.click();
        List<WebElement> ele = webDriver.findElements(By.cssSelector(".btn.btn-xs.btn-secondary.add"));
        jsClick(ele.get(0) , webDriver);
        new Select(committeeTypeSelect).selectByVisibleText(createPreambleDetails.getCommitteeType());
        jsClick(saveButton, webDriver);
    }

    public void enterCreateAgendaDetails(String preambleNumber) {
        waitForElementToBeClickable(preambleNumberTextBox, webDriver);
        enterText(preambleNumberTextBox, preambleNumber);
    }

    public String getAgendaNumber() {
        List<WebElement> elements= webDriver.findElements(By.cssSelector(".col-sm-3.add-margin.view-content"));
        String agendaNumber = elements.get(2).getText();
        CloseButton.click();
        switchToPreviouslyOpenedWindow(webDriver);
        return agendaNumber;
    }

    public void enterCreateMeetingDetails(String agendaNumber) {
        waitForElementToBeClickable(agendaNumberTextBox, webDriver);
        enterText(agendaNumberTextBox, agendaNumber);
        searchbutton.click();
        List<WebElement> elements= webDriver.findElements(By.cssSelector(".btn.btn-xs.btn-secondary.view"));
        waitForElementToBeClickable(elements.get(0),webDriver);
        jsClick(elements.get(0), webDriver);
        switchToNewlyOpenedWindow(webDriver);
    }

    public void enterCouncilMeetingDetails(CreatePreambleDetails createMeetingData) {
        waitForElementToBeClickable(meetingDateText, webDriver);
        enterText(meetingDateText, createMeetingData.getcouncilMeetingDate());
        new Select(meetingTimeSelect).selectByVisibleText(createMeetingData.getCouncilMeetingTime());
        enterText(meetingLocationText, createMeetingData.getCouncilMeetingPlace());
        jsClick(createButton, webDriver);
    }


    public String getMeetingNumber() {
        List<WebElement> elements = webDriver.findElements(By.cssSelector(".col-sm-3.add-margin.view-content"));
        String meetingNumber = elements.get(1).getText();
        System.out.println("meeting number "+meetingNumber);
        CloseButton.click();
        switchToNewlyOpenedWindow(webDriver);
        CloseButton.click();
        switchToPreviouslyOpenedWindow(webDriver);
        return meetingNumber;
    }

    public void enterMeetingNumber(String meetingNumber) {
        waitForElementToBeClickable(meetingNumberText, webDriver);
        enterText(meetingNumberText, meetingNumber);
        searchbutton.click();

    }

    public void enterAttendanceDetails() {
        List<WebElement> elements= webDriver.findElements(By.className("dropchange"));
        new Select(elements.get(0)).selectByVisibleText("Edit");
        switchToNewlyOpenedWindow(webDriver);
    }

    public void finalizeAttendance() {
        committeechkCheckBox.click();
        finalizeAttendanceBtn.click();
        webDriver.switchTo().activeElement();
        WebDriverWait webDriverWait = new WebDriverWait(webDriver,10);
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[class~='modal-footer'] button[data-bb-handler~='confirm']")));
        WebElement element=webDriver.findElement(By.cssSelector("div[class~='modal-footer'] button[data-bb-handler~='confirm']"));
        element.click();
        webDriver.close();
        switchToNewlyOpenedWindow(webDriver);
        webDriver.close();
        switchToPreviouslyOpenedWindow(webDriver);
    }

    public void searchMeetingNumber(String meetingNumber) {
        enterText(meetingNumberText, meetingNumber);
        searchbutton.click();
        List<WebElement> elements= webDriver.findElements(By.cssSelector(".btn.btn-xs.btn-secondary.view"));
        jsClick(elements.get(0),webDriver);
        switchToNewlyOpenedWindow(webDriver);

    }

    public void enterCouncilMOMDetails(CreatePreambleDetails councilMOMData) {
        waitForElementToBeClickable(resolutionStatusSelect, webDriver);
        resolutionComment.click();
        webDriver.switchTo().activeElement();
        waitForElementToBeVisible(textEntry, webDriver);
        enterText(textEntry, councilMOMData.getCouncilMOMResolution());
        waitForElementToBeVisible(textEntry, webDriver);
        updateButton.click();
        webDriver.switchTo().activeElement();
        new Select(resolutionStatusSelect).selectByVisibleText(councilMOMData.getCouncilMOMAction());
        waitForElementToBeVisible(resolutionPDFgenerationButton, webDriver);
        resolutionPDFgenerationButton.click();
        webDriver.switchTo().activeElement();
        WebDriverWait webDriverWait = new WebDriverWait(webDriver,10);
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("html/body/div[4]/div/div/div[2]/button[2]")));
        WebElement element=webDriver.findElement(By.xpath("html/body/div[4]/div/div/div[2]/button[2]"));
        element.click();
        switchToNewlyOpenedWindow(webDriver);
        webDriver.close();
        switchToNewlyOpenedWindow(webDriver);
        CloseButton.click();
        switchToPreviouslyOpenedWindow(webDriver);
    }
}
