package pages.Grievances;

import entities.grievances.CreateComplaintDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.util.List;

/**
 * Created by tester1 on 1/23/2017.
 */
public class GrievancesPage extends BasePage {
    private WebDriver webDriver;

    @FindBy(xpath = "html/body/div[1]/div/div[1]/div[2]/div[2]/a/div[2]")
    private WebElement registerComplaintLink;

    @FindBy(id = "f-name")
    private WebElement citizenNameTextBox;

    @FindBy(id = "mob-no")
    private WebElement mobNoTextBox;

    @FindBy(id = "email")
    private WebElement emailIdTextBox;


    @FindBy(id = "complaintTypeCategory")
    private WebElement complaintTypeCategorySelect;

    @FindBy(id = "complaintType")
    private WebElement complaintTypeSelect;

    @FindBy(id = "doc")
    private WebElement grievanceDetailsText;

    @FindBy(id = "location")
    private WebElement grievanceLocationText;

    @FindBy(id = "landmarkDetails")
    private WebElement locationLandmarkText;

    @FindBy(id = "create-griev")
    private WebElement createGrievanceButton;

    @FindBy(xpath = ".//*[@id='complaintform']/div[4]/div/button")
    private WebElement createGrievanceOfficialButton;

    @FindBy(linkText = "New Request")
    private WebElement newRequestLink;

    @FindBy(xpath = ".//*[@id='section-newrequest-1']/div[2]/header/div/a")
    private WebElement registerComplaint;

    @FindBy(id = "ctn_no")
    private WebElement CRNNumber;

    @FindBy(css = ".dropdown-toggle")
    private WebElement profileLink;

    @FindBy(linkText = "Sign out")
    private WebElement signOutLink;

    @FindBy(id = "status")
    private WebElement selectStatus;

    @FindBy(id = "inc_messge")
    private WebElement incMessageBox;


    @FindBy(xpath = "html/body/div[1]/div/div[1]/div/div/div[1]/div/strong")
    private WebElement acknMsg;

    @FindBy(linkText = "Close")
    private WebElement closeButton;

    @FindBy(id = "receivingMode1")
    private WebElement receivingModeRadio;

    @FindBy(xpath = "html/body/div[3]/header/div/ul/li[2]/a")
    private WebElement draftButton;

    @FindBy(id = "status")
    private WebElement statusSelect;

    @FindBy(xpath = ".//*[@id='inbox-template']/div[1]/div[1]/input")
    private WebElement searchCitizenInbox;

    @FindBy(xpath = ".//*[@id='inbox-template']/div[2]/section/div[1]/header/div[2]/a/u")
    private WebElement complaintLink;

    @FindBy(css = "button[type=submit]")
    private WebElement submitButton;



    public GrievancesPage (WebDriver webDriver) {this.webDriver= webDriver;}

    public void openCreateGrievancePage() {
//        waitForElementToBeClickable(registerComplaintLink, webDriver);
//        registerComplaintLink.click();
        clickOnButton(registerComplaintLink,webDriver);
        switchToNewlyOpenedWindow(webDriver);

    }

    public void enterCitizenContactDetails(CreateComplaintDetails createComplaintDetails) {
//    waitForElementToBeClickable(receivingModeRadio, webDriver);
//    receivingModeRadio.click();
    clickOnButton(receivingModeRadio,webDriver);
    enterText(citizenNameTextBox, createComplaintDetails.getCitizenName(), webDriver);
    enterText(mobNoTextBox, createComplaintDetails.getcitizenMobNo(),webDriver);
    enterText(emailIdTextBox, createComplaintDetails.getEmailId(), webDriver);
    }

    public void enterGrievanceDetails(CreateComplaintDetails createComplaintDetails) {
//    new Select(complaintTypeCategorySelect).selectByVisibleText(createComplaintDetails.getGrievanceCategory());
    selectFromDropDown(complaintTypeCategorySelect,createComplaintDetails.getGrievanceCategory(), webDriver);
//    new Select(complaintTypeSelect).selectByVisibleText(createComplaintDetails.getGrievanceType());
    selectFromDropDown(complaintTypeSelect,createComplaintDetails.getGrievanceType(), webDriver);
    enterText(grievanceDetailsText, createComplaintDetails.getGrievanceDetails(),webDriver);
    enterText(grievanceLocationText, createComplaintDetails.getGrievanceLocation(), webDriver);
    WebElement dropdown = webDriver.findElement(By.className("tt-highlight"));
    dropdown.click();
    grievanceLocationText.sendKeys(Keys.TAB);
    enterText(locationLandmarkText, createComplaintDetails.getLocationLandmark(), webDriver);

    }

    public void getRegisterComplaintPage() {
//    newRequestLink.click();
    clickOnButton(newRequestLink, webDriver);
//    waitForElementToBeClickable(registerComplaint, webDriver);
//    registerComplaint.click();
    clickOnButton(registerComplaint, webDriver);
    switchToNewlyOpenedWindow(webDriver);
    }

    public String getCRN() {
//        waitForElementToBeVisible(CRNNumber, webDriver);
        String CrnNum=CRNNumber.getText();
//        closeButton.click();
        clickOnButton(closeButton, webDriver);
        switchToPreviouslyOpenedWindow(webDriver);
        return CrnNum;
    }

    public void signOut() {
//        waitForElementToBeClickable(profileLink,webDriver);
//        profileLink.click();
//        profileLink.click();
        clickOnButton(profileLink, webDriver);
        clickOnButton(profileLink, webDriver);
//        waitForElementToBeClickable(signOutLink, webDriver);
//        signOutLink.click();
        clickOnButton(signOutLink, webDriver);
    }

    public String officialMarkStatus(String status) {
//        new Select(selectStatus).selectByVisibleText(status);
        selectFromDropDown(selectStatus,status,webDriver);
        enterText(incMessageBox, status, webDriver);
//        submitButton.click();
        clickOnButton(submitButton, webDriver);
        String success=webDriver.findElement(By.xpath(".//*[@id='main']/div[1]/div/div/div[1]/div/strong")).getText();
//        closeButton.click();
        clickOnButton(closeButton, webDriver);
        switchToPreviouslyOpenedWindow(webDriver);
        return success;
    }

    public String getCRNByOfficial() {
//        waitForElementToBeVisible(CRNNumber, webDriver);
        String CrnNum=CRNNumber.getText();
//        closeButton.click();
        clickOnButton(closeButton,webDriver);
        switchToPreviouslyOpenedWindow(webDriver);
//        webDriver.navigate().refresh();
//        waitForElementToBeClickable(draftButton,webDriver);
//        draftButton.click();
        return CrnNum;
    }

    public String createInOfficial() {
//        createGrievanceOfficialButton.click();
        clickOnButton(createGrievanceOfficialButton,webDriver);
        switchToNewlyOpenedWindow(webDriver);
        WebElement element= webDriver.findElement(By.xpath(".//*[@id='main']/div[1]/div/div/div[1]/div/strong"));
        return element.getText();
    }

    public String createInCitizen() {
//        createGrievanceButton.click();
        clickOnButton(createGrievanceButton, webDriver);
        WebElement element= webDriver.findElement(By.xpath(".//*[@id='main']/div[1]/div/div/div[1]/div/strong"));
        return element.getText();
    }

    public void getProcessingStatus() {
        waitForElementToBeClickable(statusSelect, webDriver);
        selectFromDropDown(statusSelect, "PROCESSING", webDriver);
//        new Select(statusSelect).selectByVisibleText("PROCESSING");

    }

    public void searchInCitizenInbox(String crn) {
        webDriver.navigate().refresh();
//        waitForElementToBeClickable(searchCitizenInbox,webDriver);
        enterText(searchCitizenInbox,crn, webDriver);
//        waitForElementToBeClickable(complaintLink,webDriver);
//        complaintLink.click();
        clickOnButton(complaintLink, webDriver);
        switchToNewlyOpenedWindow(webDriver);
    }

    public void withdrawComplaint(String complaintStatus) {
//    waitForElementToBeClickable(selectStatus,webDriver);
    selectFromDropDown(selectStatus, complaintStatus, webDriver);
//    new Select(selectStatus).selectByVisibleText(complaintStatus);
    enterText(incMessageBox, complaintStatus, webDriver);
    clickOnButton(submitButton, webDriver);
//    submitButton.click();
    clickOnButton(closeButton, webDriver);
//    closeButton.click();
    switchToPreviouslyOpenedWindow(webDriver);
    }
}
