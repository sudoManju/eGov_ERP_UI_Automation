package pages.ptis;

import entities.ptis.HearingDetails;
import entities.ptis.RevisionPetitionDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

public class RevisionPetitionPage extends BasePage {

    private WebDriver webDriver;

    public RevisionPetitionPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @FindBy(id = "assessmentNum")
    private WebElement revisionPetitiontextBox;

    @FindBy(id = "assessmentform_search")
    private WebElement rpSearchButton;

    @FindBy(id = "header_2")
    private WebElement revisionPetitionHeader;

    @FindBy(id = "details")
    private WebElement revisionPetitionDetailTextBox;

    @FindBy(id = "plannedHearingDtId")
    private WebElement hearingDateTextBox;

    @FindBy(id = "hearingTime")
    private WebElement hearingTimeSelection;

    @FindBy(id = "hearingVenue")
    private WebElement venueTextBox;

    @FindBy(id = "Forward")
    private WebElement forwardButton;

    @FindBy(id = "approverComments")
    private WebElement approveRemarkHearingTextBox;

    @FindBy(id = "reasonForModify")
    private WebElement reasonForModificationDropDown;

    @FindBy(id = "inspectionRemarks")
    private WebElement inspectionTextBox;

    @FindBy(id = "Approve")
    private WebElement approveRpbutton;

    @FindBy(id = "Print Endoresement")
    private WebElement printEndoresementNoticeButton;

    @FindBy(id = "buttonClose")
    private WebElement PrintCloseButton;

    @FindBy(id = "Print Special Notice")
    private WebElement printSpecialNotice;

    public void revisionPetitionSearchScreen(String Rpscreen) {
        revisionPetitiontextBox.sendKeys(Rpscreen);
        rpSearchButton.click();
    }
    public void chooseRevisionPetitionHeader() {
        waitForElementToBeClickable(revisionPetitionHeader, webDriver);
        revisionPetitionHeader.click();
    }
    public void revisionPetitionBlock(RevisionPetitionDetails revisionPetitionDetails) {
        enterText(revisionPetitionDetailTextBox, revisionPetitionDetails.getRevisionPetitionDetail());
    }
    public void enterHearingDetails(HearingDetails hearingDetails) {
        enterText(hearingDateTextBox,hearingDetails.getHearingDate());
        new Select(hearingTimeSelection).selectByVisibleText(hearingDetails.getHearingTime());
        enterText(venueTextBox, hearingDetails.getVenue());
        forwardButton.click();
       }
    public void enterApproverRemarks() {
        waitForElementToBeClickable(approveRemarkHearingTextBox, webDriver);
        approveRemarkHearingTextBox.sendKeys("ApproverRemarkOfRP");
    }
    public void selectReasonForModification() {
        new Select(reasonForModificationDropDown).selectByIndex(1);
    }
    public void enterInspectionDetails() {
        waitForElementToBeClickable(inspectionTextBox, webDriver);
        inspectionTextBox.sendKeys("Inspection Details of property");
    }
    public void rpApprove() {
        waitForElementToBeClickable(approveRpbutton, webDriver);
        approveRpbutton.click();
    }
    public void clickPrintEndoresementNotice() {
        waitForElementToBeClickable(printEndoresementNoticeButton, webDriver);
        printEndoresementNoticeButton.click();
        waitForElementToBeClickable(PrintCloseButton, webDriver);
        PrintCloseButton.click();
        switchToPreviouslyOpenedWindow(webDriver);
    }
    public void clickOnPrintSpecialNotice() {
        waitForElementToBeClickable(printSpecialNotice, webDriver);
        printSpecialNotice.click();
        switchToNewlyOpenedWindow(webDriver);
        webDriver.close();
        switchToPreviouslyOpenedWindow(webDriver);
    }
}
