package pages.ptis;


import entities.ptis.HearingDetails;
import entities.ptis.RevisionPetitionDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;


/**
 * Created by bimal on 25/1/17.
 */
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
}
