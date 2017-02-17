package pages.lcms;

import entities.lcms.CreateLegalCase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.security.Key;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;

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

    @FindBy(id = "lcNumber")
    private WebElement lcNumberTextBox;

    @FindBy(id = "legalcaseReportSearch")
    private WebElement legalcaseReportSearchButton;

    @FindBy(id = "additionconn")
    private WebElement additionalActionSelect;

    @FindBy(id = "buttonSubmit")
    private WebElement updateButton;

    @FindBy(id = "createnewinterimorder")
    private WebElement createInterimOrderButton;

    @FindBy(id = "createnewhearings")
    private WebElement createHearingsButton;

    @FindBy(id = "hearingDate")
    private WebElement hearingDateTextBox;

    @FindBy(id = "purposeofHearings")
    private WebElement purposeOfHearingsTextBox;

    @FindBy(css = "div[role='alert']")
    private WebElement successMessage1;

    @FindBy(linkText = "Edit")
    private WebElement editLinkText;

    @FindBy(id = "interimOrder")
    private WebElement interimOrderTypeSelect;

    @FindBy(id = "ioDate")
    private WebElement interimOrderDate;

    @FindBy(id = "mpNumber")
    private WebElement interimOrderMPNumberTextBox;

    @FindBy(id = "notes")
    private WebElement interimOrderNotesTextArea;

    @FindBy(id = "save")
    private WebElement saveButton1;

    @FindBy(id = "orderDate")
    private WebElement judgmentOrderDate;

    @FindBy(id = "sentToDeptOn")
    private WebElement judgmentDateSentToZone;

    @FindBy(id = "judgmentType")
    private WebElement judgmentTypeSelect;

    @FindBy(id = "judgmentDetails")
    private WebElement judgmentDetailsTextArea;

    @FindBy(id = "dateOfCompliance")
    private WebElement dateOfComplianceTextBox;

    @FindBy(id = "complianceReport")
    private WebElement complianceReportTextArea;

    @FindBy(id = "IsCompliedNo")
    private WebElement judgmentCompiledNo;

    @FindBy(id = "IsCompliedInProgress")
    private WebElement judgmentInProgress;

    @FindBy(id = "details")
    private WebElement inProgressDetailsTextArea;

    @FindBy(id = "appeal[0].srNumber")
    private WebElement appealNumberTextBox;

    @FindBy(id = "appeal0.appealFiledOn")
    private WebElement appealedDate;

    @FindBy(id = "appeal0.appealFiledBy")
    private WebElement appealedFilledByTextBox;

    @FindBy(id = "contempt[0].caNumber")
    private WebElement contemptCANumberTextBox;

    @FindBy(id = "contempt0.receivingDate")
    private WebElement contemptReceivedDate;

    @FindBy(id = "implementationFailure")
    private WebElement implementationFailureSelect;

    @FindBy(name = "isStatusExcluded")
    private WebElement excludeJudgmentImplementationCheckBox;

    @FindBy(id = "disposalDate")
    private WebElement closeDisposalDate;

    @FindBy(id = "disposalDetails")
    private WebElement closeDisposalDetailsTextArea;

    private String message = null;
    private String caseFileNo = null;

    public LegalCaseManagementPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void enterLegalCaseDetails(CreateLegalCase createLegalCase){

        webDriver.manage().window().maximize();

        waitForElementToBeClickable(courtTypeSelect ,webDriver);
        new Select(courtTypeSelect).selectByVisibleText(createLegalCase.getTypeOfCourt());

        waitForElementToBeClickable(petitionTypeSelect ,webDriver);
        petitionTypeSelect.click();
        new Select(petitionTypeSelect).selectByVisibleText(createLegalCase.getPetitionType());

        waitForElementToBeClickable(courtNameSelect ,webDriver);
        new Select(courtNameSelect).selectByVisibleText(createLegalCase.getCourtName());

        waitForElementToBeClickable(caseTypeSelect ,webDriver);
        new Select(caseTypeSelect).selectByVisibleText(createLegalCase.getCaseCategory());

        waitForElementToBeClickable(caseNumberTextBox , webDriver);
        enterText(caseNumberTextBox , get6DigitRandomInt());

        waitForElementToBeClickable(yearSelect , webDriver);
        new Select(yearSelect).selectByVisibleText("2017");

        waitForElementToBeClickable(caseDate , webDriver);
        enterText(caseDate , getPreviousDate());
        caseDate.sendKeys(Keys.TAB);

        waitForElementToBeClickable(caseTitleTextBox , webDriver);
        enterText(caseTitleTextBox , "Testing");

        waitForElementToBeClickable(prayerTextBox , webDriver);
        enterText(prayerTextBox , "Tester");

        waitForElementToBeClickable(noticeDate , webDriver);
        enterText(noticeDate , getPreviousDate());
        noticeDate.sendKeys(Keys.TAB);

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
        waitForElementToBeClickable(lcNumberTextBox , webDriver);
        enterText(lcNumberTextBox , caseFileNumber);

        waitForElementToBeClickable(excludeJudgmentImplementationCheckBox , webDriver);
        excludeJudgmentImplementationCheckBox.click();

        waitForElementToBeClickable(legalcaseReportSearchButton , webDriver);
        legalcaseReportSearchButton.click();
    }

    public void clickOnCorrespondingAction(String action){

        switch (action){

            case "editLegalCase" :

                waitForElementToBeClickable(additionalActionSelect , webDriver);
                new Select(additionalActionSelect).selectByVisibleText("Edit legalCase");
                switchToNewlyOpenedWindow(webDriver);

                enterEditLegalCaseDetails();

                break;

            case "hearings" :

                waitForElementToBeClickable(additionalActionSelect , webDriver);
                new Select(additionalActionSelect).selectByVisibleText("Hearings");
                switchToNewlyOpenedWindow(webDriver);

                clickOnCreateHearings();

                break;

            case "editHearings" :

                waitForElementToBeClickable(additionalActionSelect , webDriver);
                new Select(additionalActionSelect).selectByVisibleText("Hearings");
                switchToNewlyOpenedWindow(webDriver);

                clickOnEditHearings();

                break;

            case "interimOrder" :

                waitForElementToBeClickable(additionalActionSelect , webDriver);
                new Select(additionalActionSelect).selectByVisibleText("Interim Order");
                switchToNewlyOpenedWindow(webDriver);

                clickOnCreateInterimOrder();

                break;

            case "editInterim" :

                waitForElementToBeClickable(additionalActionSelect , webDriver);
                new Select(additionalActionSelect).selectByVisibleText("Interim Order");
                switchToNewlyOpenedWindow(webDriver);

                clickOnEditInterimOrder();

                break;

            case "judgment" :

                waitForElementToBeClickable(additionalActionSelect , webDriver);
                new Select(additionalActionSelect).selectByVisibleText("Judgment");
                switchToNewlyOpenedWindow(webDriver);

                enterJudgmentDetails();

                break;

            case "editJudgment" :

                waitForElementToBeClickable(additionalActionSelect , webDriver);
                new Select(additionalActionSelect).selectByVisibleText("Edit Judgment");
                switchToNewlyOpenedWindow(webDriver);

                enterEditJudgmentDetails();

                break;

            case "judgmentImplementation" :

                waitForElementToBeClickable(additionalActionSelect , webDriver);
                new Select(additionalActionSelect).selectByVisibleText("Judgment Implementation");
                switchToNewlyOpenedWindow(webDriver);

                break;

            case "editJudgmentImplementation" :

                waitForElementToBeClickable(additionalActionSelect , webDriver);
                new Select(additionalActionSelect).selectByVisibleText("Edit Judgment Implementation");
                switchToNewlyOpenedWindow(webDriver);

                break;

            case "closeCase"  :

                waitForElementToBeClickable(additionalActionSelect , webDriver);
                new Select(additionalActionSelect).selectByVisibleText("Close Case");
                switchToNewlyOpenedWindow(webDriver);

                enterCloseCaseDetails();
                break;
        }
    }

    private void enterEditLegalCaseDetails(){

        waitForElementToBeClickable(caseTitleTextBox , webDriver);
        enterText(caseTitleTextBox , "Tester");

        waitForElementToBeClickable(petitonerNameTextBox , webDriver);
        enterText(petitonerNameTextBox , "Good Tester");

        waitForElementToBeClickable(updateButton , webDriver);
        updateButton.click();

        switchToNewlyOpenedWindow(webDriver);
    }

    private void clickOnCreateHearings(){

        waitForElementToBeClickable(createHearingsButton , webDriver);
        createHearingsButton.click();

        switchToNewlyOpenedWindow(webDriver);

        waitForElementToBeClickable(hearingDateTextBox , webDriver);
        enterText(hearingDateTextBox , getPreviousDate());
        hearingDateTextBox.sendKeys(Keys.TAB);

//        waitForElementToBeClickable(purposeOfHearingsTextBox, webDriver);
//        enterText(purposeOfHearingsTextBox , getPreviousDate());

        waitForElementToBeClickable(purposeOfHearingsTextBox , webDriver);
        enterText(purposeOfHearingsTextBox ,"Normal Use");

        waitForElementToBeClickable(saveButton , webDriver);
        saveButton.click();

        switchToNewlyOpenedWindow(webDriver);
    }

    public String closeCreatedOrUpdatedPage(){
        waitForElementToBeClickable(successMessage1, webDriver);
        message = successMessage1.getText();

        waitForElementToBeClickable(closeButtonWithLinkText , webDriver);
        closeButtonWithLinkText.click();

        switchToPreviouslyOpenedWindow(webDriver);
        return message;
    }

    private void clickOnEditHearings(){
        waitForElementToBeClickable(editLinkText , webDriver);
        editLinkText.click();

        switchToNewlyOpenedWindow(webDriver);

        waitForElementToBeClickable(purposeOfHearingsTextBox, webDriver);
        enterText(purposeOfHearingsTextBox, "General Purpose");

        waitForElementToBeClickable(saveButton , webDriver);
        saveButton.click();
    }

    private void clickOnCreateInterimOrder(){

        waitForElementToBeClickable(createInterimOrderButton , webDriver);
        createInterimOrderButton.click();

        switchToNewlyOpenedWindow(webDriver);

        waitForElementToBeVisible(interimOrderTypeSelect , webDriver);
        new Select(interimOrderTypeSelect).selectByVisibleText("Interim order");

        waitForElementToBeClickable(interimOrderDate , webDriver);
        enterText(interimOrderDate , getPreviousDate());
        interimOrderDate.sendKeys(Keys.TAB);

        waitForElementToBeClickable(interimOrderMPNumberTextBox , webDriver);
        enterText(interimOrderMPNumberTextBox , get6DigitRandomInt());

        waitForElementToBeClickable(interimOrderNotesTextArea,webDriver);
        enterText(interimOrderNotesTextArea ,"Creation Of Interim Order");

        waitForElementToBeClickable(saveButton1, webDriver);
        saveButton1.click();

        switchToNewlyOpenedWindow(webDriver);
    }

    private void clickOnEditInterimOrder(){

        waitForElementToBeClickable(editLinkText , webDriver);
        editLinkText.click();

        switchToNewlyOpenedWindow(webDriver);

        waitForElementToBeClickable(interimOrderNotesTextArea,webDriver);
        enterText(interimOrderNotesTextArea , "Editing Interim Order");

        waitForElementToBeClickable(updateButton , webDriver);
        updateButton.click();
    }

    private void enterJudgmentDetails(){

        waitForElementToBeClickable(judgmentOrderDate , webDriver);
        enterText(judgmentOrderDate ,getPreviousDate());
        judgmentOrderDate.sendKeys(Keys.TAB);

        waitForElementToBeClickable(judgmentDateSentToZone , webDriver);
        enterText(judgmentDateSentToZone,getPreviousDate());
        judgmentDateSentToZone.sendKeys(Keys.TAB);

        waitForElementToBeClickable(judgmentTypeSelect ,webDriver);
        new Select(judgmentTypeSelect).selectByVisibleText("Enquiry");

        waitForElementToBeClickable(judgmentDetailsTextArea , webDriver);
        enterText(judgmentDetailsTextArea ,"Judgment Is Under Process");

        waitForElementToBeClickable(saveButton1 , webDriver);
        saveButton1.click();

        switchToNewlyOpenedWindow(webDriver);
    }

    private void enterEditJudgmentDetails(){

        new Select(judgmentTypeSelect).selectByVisibleText("Allowed");

        waitForElementToBeClickable(judgmentDetailsTextArea , webDriver);
        enterText(judgmentDetailsTextArea , "Judgment Is Allowed");

        waitForElementToBeClickable(updateButton , webDriver);
        updateButton.click();

        switchToNewlyOpenedWindow(webDriver);
    }

    public void enterJudgmentImplementationDetails(String mode){

        switch (mode){

            case "Yes" :

                waitForElementToBeClickable(dateOfComplianceTextBox ,webDriver);
                enterText(dateOfComplianceTextBox ,getPreviousDate());
                dateOfComplianceTextBox.sendKeys(Keys.TAB);

                waitForElementToBeClickable(complianceReportTextArea, webDriver);
                enterText(complianceReportTextArea,"Judgment Implementation");

                waitForElementToBeClickable(saveButton , webDriver);
                saveButton.click();

                break;

            case "No_Appeal"  :

                waitForElementToBeClickable(judgmentCompiledNo ,webDriver);
                jsClick(judgmentCompiledNo , webDriver);

                waitForElementToBeClickable(implementationFailureSelect ,webDriver);
                new Select(implementationFailureSelect).selectByVisibleText("Appeal");

                waitForElementToBeClickable(appealNumberTextBox , webDriver);
                enterText(appealNumberTextBox,get6DigitRandomInt());

                waitForElementToBeClickable(appealedDate , webDriver);
                enterText(appealedDate,getPreviousDate());
                appealedDate.sendKeys(Keys.TAB);

                waitForElementToBeClickable(appealedFilledByTextBox , webDriver);
                enterText(appealedFilledByTextBox ,"Tester");

                waitForElementToBeClickable(saveButton , webDriver);
                saveButton.click();

                break;

            case "No_Contempt" :

                waitForElementToBeClickable(judgmentCompiledNo , webDriver);
                jsClick(judgmentCompiledNo , webDriver);

                waitForElementToBeClickable(implementationFailureSelect , webDriver);
                new Select(implementationFailureSelect).selectByVisibleText("Contempt");

                waitForElementToBeClickable(contemptCANumberTextBox , webDriver);
                enterText(contemptCANumberTextBox,get6DigitRandomInt());

                waitForElementToBeClickable(contemptReceivedDate , webDriver);
                enterText(contemptReceivedDate,getPreviousDate());
                contemptReceivedDate.sendKeys(Keys.TAB);

                waitForElementToBeClickable(saveButton , webDriver);
                saveButton.click();

                break;


            case "InProgress" :

                jsClick(judgmentInProgress ,webDriver);

                waitForElementToBeClickable(inProgressDetailsTextArea , webDriver);
                enterText(inProgressDetailsTextArea,"Details Of InProgress");

                waitForElementToBeClickable(saveButton , webDriver);
                saveButton.click();

                break;

            // From here we enter the edit details of judgment implementation
            case "edit_Yes" :
                waitForElementToBeClickable(complianceReportTextArea, webDriver);
                enterText(complianceReportTextArea ,"Judgment Implementation Edited");

                waitForElementToBeClickable(saveButton , webDriver);
                saveButton.click();

                break;

            case "edit_No_Appeal"  :

                waitForElementToBeClickable(appealedFilledByTextBox , webDriver);
                enterText(appealedFilledByTextBox , "Tester1");

                waitForElementToBeClickable(saveButton , webDriver);
                saveButton.click();

                break;

            case "edit_No_Contempt" :

                waitForElementToBeClickable(contemptCANumberTextBox , webDriver);
                enterText(contemptCANumberTextBox,get6DigitRandomInt());

                waitForElementToBeClickable(saveButton , webDriver);
                saveButton.click();

                break;

            case "edit_InProgress" :

                waitForElementToBeClickable(inProgressDetailsTextArea , webDriver);
                enterText(inProgressDetailsTextArea , "Edited details Of InProgress");

                waitForElementToBeClickable(saveButton , webDriver);
                saveButton.click();

                break;
        }

        switchToNewlyOpenedWindow(webDriver);
    }

    private void enterCloseCaseDetails(){

        waitForElementToBeClickable(closeDisposalDate ,webDriver);
        enterText(closeDisposalDate,getPreviousDate());
        closeDisposalDate.sendKeys(Keys.TAB);

        waitForElementToBeClickable(closeDisposalDetailsTextArea ,webDriver);
        enterText(closeDisposalDetailsTextArea,"Case is Closed");

        waitForElementToBeClickable(saveButton ,webDriver);
        saveButton.click();
    }
}
