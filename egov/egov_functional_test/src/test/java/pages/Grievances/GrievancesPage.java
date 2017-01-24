package pages.Grievances;

import cucumber.api.java.eo.Se;
import entities.grievances.CreateComplaintDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

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

    @FindBy(id = "address")
    private WebElement citizenAddressTextBox;

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

    @FindBy(id = "triggerFile")
    private WebElement uploadPhotoButton;

    @FindBy(linkText = "New Request")
    private WebElement newRequestLink;

    @FindBy(xpath = ".//*[@id='section-newrequest-1']/div[2]/header/div/a")
    private WebElement registerComplaint;

    @FindBy(id = "ctn_no")
    private WebElement CRNNumber;

    public GrievancesPage (WebDriver webDriver) {this.webDriver= webDriver;}

    public void openCreateGrievancePage() {
        waitForElementToBeClickable(registerComplaintLink, webDriver);
        registerComplaintLink.click();
        switchToNewlyOpenedWindow(webDriver);

    }

    public void enterCitizenContactDetails(CreateComplaintDetails createComplaintDetails) {
    waitForElementToBeClickable(citizenNameTextBox, webDriver);
    enterText(citizenNameTextBox, createComplaintDetails.getCitizenName());
    enterText(mobNoTextBox, createComplaintDetails.getcitizenMobNo());
    enterText(emailIdTextBox, createComplaintDetails.getEmailId());
    enterText(citizenAddressTextBox,createComplaintDetails.getcitizenAddress());

    }

    public void enterGrievanceDetails(CreateComplaintDetails createComplaintDetails) {
    new Select(complaintTypeCategorySelect).selectByVisibleText(createComplaintDetails.getGrievanceCategory());
    new Select(complaintTypeSelect).selectByVisibleText(createComplaintDetails.getGrievanceType());
    enterText(grievanceDetailsText, createComplaintDetails.getGrievanceDetails());
    uploadPhotoButton.sendKeys(System.getProperty("user.dir") + "/src/test/resources/Mosquito-Menace.jpg");
   // enterText(grievanceLocationText, createComplaintDetails.getGrievanceLocation());
      enterText(grievanceLocationText, "abbas nagar-m");
      WebElement dropdown = webDriver.findElement(By.className("tt-highlight"));
      dropdown.click();
    grievanceLocationText.sendKeys(Keys.TAB);
    enterText(locationLandmarkText, createComplaintDetails.getLocationLandmark());
    createGrievanceButton.click();

    }

    public void getRegisterComplaintPage() {
    newRequestLink.click();
    waitForElementToBeClickable(registerComplaint, webDriver);
    registerComplaint.click();
    switchToNewlyOpenedWindow(webDriver);
    }

    public String getCRN() {
        waitForElementToBeVisible(CRNNumber, webDriver);
        String CrnNum=CRNNumber.getText();
        webDriver.close();
        switchToPreviouslyOpenedWindow(webDriver);
        webDriver.close();
        return CrnNum;
    }

//    public void signOut() {
//        waitForElementToBeClickable(profileLink,driver);
//        profileLink.click();
//        waitForElementToBeClickable(signOutLink, driver);
//        signOutLink.click();
//    }
}
