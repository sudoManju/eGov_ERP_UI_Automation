package pages.lcms;

import entities.lcms.CreateLegalCase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

/**
 * Created by vinaykumar on 2/2/17.
 */
public class LegalCaseManagementPage extends BasePage {

    private WebDriver webDriver;

    @FindBy(id = "courtType")
    private WebElement courtTypeSelect;

    @FindBy(id = "petitionTypeMaster")
    private WebElement petitionTypeSelect;

    @FindBy(id = "courtMaster")
    private WebElement courtNameSelect;

    @FindBy(id = "caseTypeMaster")
    private WebElement caseTypeSelect;

    @FindBy(id = "caseNumber")
    private WebElement caseNumberTextBox;

    @FindBy(id = "wpYear")
    private WebElement yearSelect;

    @FindBy(id = "caseDate")
    private WebElement caseDate;

    @FindBy(id = "caseTitle")
    private WebElement caseTitleTextBox;

    @FindBy(id = "prayer")
    private WebElement prayerTextBox;

    @FindBy(id = "noticeDate")
    private WebElement noticeDate;

    @FindBy(id = "bipartisanPetitionerDetailsList[0].name")
    private WebElement petitonerNameTextBox;

    @FindBy(id = "bipartisanRespondentDetailsList[0].name")
    private WebElement respondentNameTextBox;

    @FindBy(id = "buttonid")
    private WebElement saveButton;

    @FindBy(css = ".panel-body.custom-form")
    private WebElement successMessage;

    @FindBy(linkText = "Close")
    private WebElement closeButtonWithLinkText;

    @FindBy(xpath = ".//*[@id='legalCaseForm']/div/div/div/div/div/div[3]/div[4]")
    private WebElement caseFileNumber;

    private String message = null;
    private String caseFileNo = null;

    public LegalCaseManagementPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void enterLegalCaseDetails(CreateLegalCase createLegalCase){

        new Select(courtTypeSelect).selectByVisibleText(createLegalCase.getTypeOfCourt());
        petitionTypeSelect.click();
        new Select(petitionTypeSelect).selectByVisibleText(createLegalCase.getPetitionType());
        new Select(courtNameSelect).selectByVisibleText(createLegalCase.getCourtName());
        new Select(caseTypeSelect).selectByVisibleText(createLegalCase.getCaseCategory());

        waitForElementToBeClickable(caseNumberTextBox , webDriver);
        enterText(caseNumberTextBox , get6DigitRandomInt());
        new Select(yearSelect).selectByVisibleText("2017");

        waitForElementToBeClickable(caseDate , webDriver);
        caseDate.sendKeys(getPreviousDate() , Keys.TAB);

        waitForElementToBeClickable(caseTitleTextBox , webDriver);
        enterText(caseTitleTextBox , "Testing");
        waitForElementToBeClickable(prayerTextBox , webDriver);
        enterText(prayerTextBox , "Tester");

        waitForElementToBeClickable(noticeDate , webDriver);
        noticeDate.sendKeys(getPreviousDate(), Keys.TAB);

        waitForElementToBeClickable(petitonerNameTextBox , webDriver);
        enterText(petitonerNameTextBox , createLegalCase.getPetitionerName());

        waitForElementToBeClickable(respondentNameTextBox , webDriver);
        enterText(respondentNameTextBox , createLegalCase.getRespondentName());

        waitForElementToBeClickable(saveButton , webDriver);
        saveButton.click();

        switchToNewlyOpenedWindow(webDriver);
    }

    public String closesAcknowledgementForm(){
        waitForElementToBeVisible(successMessage , webDriver);
        message = successMessage.findElements(By.tagName("div")).get(0).getText();

        waitForElementToBeVisible(caseFileNumber , webDriver);
        caseFileNo = caseFileNumber.getText();

        waitForElementToBeClickable(closeButtonWithLinkText , webDriver);
        closeButtonWithLinkText.click();

        switchToPreviouslyOpenedWindow(webDriver);

        return message+">"+caseFileNo;
    }

    public void searchCaseFile(String caseFileNumber){

    }

}
