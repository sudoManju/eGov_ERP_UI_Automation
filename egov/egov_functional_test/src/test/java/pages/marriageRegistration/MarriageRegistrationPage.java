package pages.marriageRegistration;

import entities.marriageRegistration.MarriageRegistrationInformation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

public class MarriageRegistrationPage extends BasePage {
    public WebDriver driver;

    @FindBy(id = "select-registrationunit")
    private WebElement registrationUnit;

    @FindBy(id = "txt-street")
    private List<WebElement> street;

    @FindBy(id = "select-locality")
    private WebElement locality;

    @FindBy(id = "txt-locality")
    private List<WebElement> localityTxt;

    @FindBy(id = "txt-city")
    private List<WebElement> city;

    @FindBy(id = "txt-dateOfMarriage")
    private WebElement dateOfMarriage;

    @FindBy(id = "select-venue")
    private WebElement venueOfMarriage;

    @FindBy(id = "txt-placeofmrg")
    private WebElement placeOfMarriage;

    @FindBy(css = "input[type='file'][id='marriage-photo']")
    private WebElement marriagePhoto;

    @FindBy(id = "husband.name.firstName")
    private WebElement brideFullName;

    @FindBy(id = "txt-lastName")
    private List<WebElement> lastName;

    @FindBy(id = "txt-parentsName")
    private WebElement parentsName;

    @FindBy(css = "input[type='file'][id='husband-photo']")
    private WebElement photoUpload;

    @FindBy(css = "input[type='file'][id='wife-photo']")
    private WebElement wifePhotoUpload;

    @FindBy(xpath = ".//*[@id='settingstab']/li[2]/a")
    private WebElement witnessInfoLink;

    @FindBy(id = "witnesses0.name.firstName")
    private WebElement witnessName1;

    @FindBy(id = "witnesses1.name.firstName")
    private WebElement witnessName2;

    @FindBy(id = "witnesses2.name.firstName")
    private WebElement witnessName3;

    @FindBy(id = "witnesses3.name.firstName")
    private WebElement witnessName4;

    @FindBy(xpath = ".//*[@id='settingstab']/li[3]/a")
    private WebElement checkListLink;

    @FindBy(id = "file2id")
    private WebElement memorandumofMarriage;

    @FindBy(id = "indvcommonhusbandfile7id")
    private WebElement birthCertificateForBridegroom;

    @FindBy(id = "indvcommonwifefile7id")
    private WebElement birthCertificateForBride;

    @FindBy(id = "addressproofhusbandfile3id")
    private WebElement proofofResidenceForBridegroom;

    @FindBy(id = "addressproofwifefile3id")
    private WebElement proofofResidenceForBride;

    @FindBy(xpath = ".//*[@id='registrationsuccess-form']/div/div[2]/div")
    private WebElement creationMessage;

    @FindBy(css = "input[type='button'][value='Close']")
    private WebElement closeButton;

    @FindBy(css = "input[id='inboxsearch'][type='text']")
    private WebElement inboxSearchTextBox;

    @FindBy(id = "Approve")
    private WebElement approveButton;

    @FindBy(id = "txt-serialNo")
    private WebElement marriageSerialNum;

    @FindBy(xpath = ".//*[@id='txt-pageNo']")
    private WebElement marriagePageNum;

    @FindBy(id = "dataEntrySubmit")
    private WebElement dataEntrySubmitButton;

    @FindBy(id = "file1id")
    private WebElement memorandumOfMarriage;

    @FindBy(className = "today active day")
    private WebElement todayDatePicker;

    @FindBy(id = "applicationNo")
    private WebElement applicationNumberTextBox;

    @FindBy(id = "btnregistrationsearch")
    private WebElement searchRegistrationButton;

    @FindBy(className = "dropchange")
    private WebElement actionDropDown;

    @FindBy(xpath = ".//*[@id='registration_table']/tbody/tr/td[10]/button")
    private WebElement editButton;

    @FindBy(xpath = ".//*[@id='form-updateregistration']/div[2]/div/button")
    private WebElement updateButton;

    @FindBy(id = "applicationNo")
    private WebElement applicationNo;

    @FindBy(xpath = ".//*[@id='registration_table']/tbody/tr[1]/td[10]/button")
    private WebElement reIssueCertificateLink;

    @FindBy(id = "txt-firstName")
    private WebElement firstName;

    @FindBy(id = "txt-residenceAddress")
    private WebElement residenceAddress;

    @FindBy(id = "txt-officeAddress")
    private WebElement officeAddress;

    @FindBy(id = "txt-phoneNo")
    private WebElement phoneNo;

    @FindBy(id = "marriageRegistrationType")
    private WebElement marriageRegistrationType;

    @FindBy(id = "official_inbox_wrapper")
    private WebElement workListTable;

    @FindBy(id = "button2")
    private WebElement closeButton2;

    @FindBy(id = "applicationNum")
    private WebElement marriageRegApplNum;

    @FindBy(xpath = ".//*[@id='registrationNum']")
    private WebElement marriageRegNum;

    @FindBy(id = "ageproofhusbandfile0id")
    private WebElement ageproofHusband;

    @FindBy(id = "ageproofwifefile0id")
    private WebElement ageproofWife;

    public MarriageRegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterApplicantsInformation(MarriageRegistrationInformation marriageRegistrationInformation) {

        Boolean isPresent = driver.findElements(By.id("applicationNum")).size() > 0;

        if(isPresent) {
            waitForElementToBeClickable(marriageRegApplNum, driver);
            marriageRegApplNum.sendKeys("A" + get6DigitRandomInt());
            waitForElementToBeClickable(marriageRegNum, driver);
            marriageRegNum.sendKeys(get6DigitRandomInt());
        }
        waitForElementToBeClickable(registrationUnit, driver);
        registrationUnit.sendKeys(marriageRegistrationInformation.getRegistrationUnit());
        waitForElementToBeClickable(street.get(0), driver);
        street.get(0).sendKeys(marriageRegistrationInformation.getStreet());
        waitForElementToBeClickable(locality, driver);
        locality.sendKeys(marriageRegistrationInformation.getLocality());
        waitForElementToBeClickable(city.get(0), driver);
        city.get(0).sendKeys(marriageRegistrationInformation.getCity());
        waitForElementToBeClickable(dateOfMarriage, driver);
        dateOfMarriage.clear();
        dateOfMarriage.sendKeys(getPastDate(35));

        waitForElementToBeClickable(venueOfMarriage, driver);
        venueOfMarriage.sendKeys(marriageRegistrationInformation.getVenueOfMarriage());
        waitForElementToBeClickable(placeOfMarriage, driver);
        placeOfMarriage.sendKeys(marriageRegistrationInformation.getPlaceOfMarriage());
        marriagePhoto.sendKeys(System.getProperty("user.dir") + "/src/test/resources/logo.jpg");
    }


    public void enterBrideGroomInformation(MarriageRegistrationInformation marriageRegistrationInformation, String name) {
        WebElement fullName = driver.findElement(By.id(name + ".name.firstName"));
        fullName.sendKeys(marriageRegistrationInformation.getFullName());

        waitForElementToBeClickable(lastName.get(0), driver);
        lastName.get(0).sendKeys("N");
        waitForElementToBeClickable(lastName.get(1), driver);
        lastName.get(1).sendKeys("P");
        photoUpload.sendKeys(System.getProperty("user.dir") + "/src/test/resources/logo.jpg");

        WebElement parentsNamegb = driver.findElement(By.name(name + ".parentsName"));
        parentsNamegb.sendKeys(marriageRegistrationInformation.getFathersMothersName());

        WebElement religion = driver.findElement((By.id(name + ".religion")));
        religion.sendKeys(marriageRegistrationInformation.getReligion());

        WebElement ageInYears = driver.findElement(By.id(name + ".ageInYearsAsOnMarriage"));
        ageInYears.sendKeys("30");
        WebElement ageInMonth = driver.findElement(By.id(name + ".ageInMonthsAsOnMarriage"));
        ageInMonth.sendKeys("8");

        WebElement martialStatus = driver.findElement(By.id(name + ".maritalStatus"));
        martialStatus.sendKeys(marriageRegistrationInformation.getStatusAtTheTimeMarriage());

        WebElement residenceAddress = driver.findElement(By.id(name + ".contactInfo.residenceAddress"));
        residenceAddress.sendKeys(marriageRegistrationInformation.getResidenceAddress());
        WebElement officeAddress = driver.findElement(By.id(name + ".contactInfo.officeAddress"));
        officeAddress.sendKeys(marriageRegistrationInformation.getOfficeAddress());

        WebElement localitygb = driver.findElement(By.name(name + ".locality"));
        localitygb.sendKeys(marriageRegistrationInformation.getLocality());

        WebElement streetbg = driver.findElement(By.name(name + ".street"));
        streetbg.sendKeys(marriageRegistrationInformation.getStreet());

        WebElement citygb = driver.findElement(By.name(name + ".city"));
        citygb.sendKeys(marriageRegistrationInformation.getCity());

        WebElement mobileNogb = driver.findElement(By.id(name + ".contactInfo.mobileNo"));
        mobileNogb.sendKeys(marriageRegistrationInformation.getPhoneNo());

        WebElement occupationgb = driver.findElement(By.id(name + ".occupation"));
        occupationgb.sendKeys(marriageRegistrationInformation.getOccupation());

        WebElement qualificationgb = driver.findElement(By.id(name + ".qualification"));
        qualificationgb.sendKeys(marriageRegistrationInformation.getEducationQualification());

        wifePhotoUpload.sendKeys(System.getProperty("user.dir") + "/src/test/resources/logo.jpg");
    }

    public void entersWitnessesInformation() {
        waitForElementToBeClickable(witnessInfoLink, driver);
        jsClick(witnessInfoLink, driver);

        for (int i = 0; i <= 3; i++) {
            int res = 0;
            int sum = i + res;

            WebElement witnessName = driver.findElement(By.id("witnesses" + sum + ".name.firstName"));
            witnessName.sendKeys("Witness Names");
            WebElement witnessLastName = driver.findElement(By.id("witnesses" + sum + ".name.lastName"));
            witnessLastName.sendKeys("Witness Last Names");
            WebElement relativeName = driver.findElement(By.id("witnesses[" + sum + "].relativeName"));
            relativeName.sendKeys("Relative Name");
            WebElement witnessAge = driver.findElement(By.id("witnesses[" + sum + "].age"));
            witnessAge.sendKeys("35");
            WebElement residenceAddress = driver.findElement(By.id("witnesses[" + sum + "].contactInfo.residenceAddress"));
            residenceAddress.sendKeys("Residence Address");
        }
    }

    public void enterChecklist() {
        waitForElementToBeClickable(checkListLink, driver);
        jsClick(checkListLink, driver);

        memorandumofMarriage.sendKeys(System.getProperty("user.dir") + "/src/test/resources/logo.jpg");
        birthCertificateForBridegroom.sendKeys(System.getProperty("user.dir") + "/src/test/resources/logo.jpg");
        birthCertificateForBride.sendKeys(System.getProperty("user.dir") + "/src/test/resources/logo.jpg");
        proofofResidenceForBridegroom.sendKeys(System.getProperty("user.dir") + "/src/test/resources/logo.jpg");
        proofofResidenceForBride.sendKeys(System.getProperty("user.dir") + "/src/test/resources/logo.jpg");
        ageproofHusband.sendKeys(System.getProperty("user.dir") + "/src/test/resources/logo.jpg");
        ageproofWife.sendKeys(System.getProperty("user.dir") + "/src/test/resources/logo.jpg");
    }

    public String getApplicationNumber() {
        String msg = creationMessage.getText();
        String number = msg.split("\\s")[6];
        return number;
    }

    public String getSuccessMessage() {
        return creationMessage.getText();
    }

    public void close() {
        waitForElementToBeClickable(closeButton, driver);
        closeButton.click();
        for (String winHandle : driver.getWindowHandles()) {
            if (driver.switchTo().window(winHandle).getCurrentUrl().equals(getEnvironmentURL()+"/mrs/registration/reissuecertificate")) {
                break;
            }
        }
        WebElement closeButton1 = driver.findElement(By.xpath(".//*[@id='page-content']/div/div[3]/div/a"));
        waitForElementToBeClickable(closeButton1, driver);
        closeButton1.click();
        switchToPreviouslyOpenedWindow(driver);
    }

    public void approve() {
        waitForElementToBeClickable(approveButton,driver);
        approveButton.click();
    }

    public String getRegistrationNumber(){
        String msg = creationMessage.getText();
        String number = msg.split("\\ ")[7];
        return number;
    }

    public void enterMarriageRegNum() {
        waitForElementToBeClickable(marriageSerialNum, driver);
        marriageSerialNum.sendKeys(get6DigitRandomInt());
        waitForElementToBeClickable(marriagePageNum, driver);
        marriagePageNum.sendKeys(get6DigitRandomInt());
    }

    public String isSuccesful() {
        waitForElementToBeClickable(dataEntrySubmitButton, driver);
        jsClick(dataEntrySubmitButton, driver);
        String message = creationMessage.getText();
         return  message;
    }

    public void searchForMarriageApplicationNumberToCollect(String applicationNumber, String type) {
        waitForElementToBeVisible(applicationNumberTextBox,driver);
        applicationNumberTextBox.sendKeys(applicationNumber);
        if(type.equals("reissue")) {
            waitForElementToBeClickable(marriageRegistrationType, driver);
            new Select(marriageRegistrationType).selectByVisibleText("REISSUE");
        }
        searchRegistrationButton.click();
    }

    public void clickOnCollectDropdown(){
            waitForElementToBeVisible(actionDropDown, driver);
            new Select(actionDropDown).selectByVisibleText("Collect Fee");
            switchToNewlyOpenedWindow(driver);
    }

    public void searchForApplicationToModify(String applicationNumber) {
        waitForElementToBeVisible(applicationNumberTextBox,driver);
        applicationNumberTextBox.sendKeys(applicationNumber);
        searchRegistrationButton.click();
    }

    public void clickOnEditButton() {
        waitForElementToBeClickable(editButton,driver);
        editButton.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void modifyAndUpdateMarriageApplication() {
        waitForElementToBeClickable(lastName.get(0), driver);
        lastName.get(0).sendKeys("KHAN");
        waitForElementToBeClickable(updateButton,driver);
        updateButton.click();

    }

    public String isSuccesfulForModification() {
        return creationMessage.getText();
    }

    public void closeMultipleWindows() {
        waitForElementToBeClickable(closeButton, driver);
        closeButton.click();
        for (String winHandle : driver.getWindowHandles()) {
            if (driver.switchTo().window(winHandle).getCurrentUrl().equals("http://kurnool-qa.egovernments.org/mrs/registration/searchApproved")) {
                break;
            }
        }
        driver.findElement(By.linkText("Close")).click();
        switchToPreviouslyOpenedWindow(driver);

    }
    public void searchMarriageApplication(String applicationNumber) {
        waitForElementToBeClickable(applicationNo, driver);
        applicationNo.sendKeys(applicationNumber);
        searchRegistrationButton.click();
    }

    public void selectsReIssueCertificate() {
        waitForElementToBeClickable(reIssueCertificateLink, driver);
        reIssueCertificateLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void entersMemorandumOfMarriage() {
        waitForElementToBeClickable(registrationUnit, driver);
        new Select(registrationUnit).selectByVisibleText("BTM");
        waitForElementToBeClickable(firstName, driver);
        firstName.sendKeys("AaZz");
        waitForElementToBeClickable(residenceAddress, driver);
        residenceAddress.sendKeys("Kurnool, Andra Pradesh");
        waitForElementToBeClickable(officeAddress, driver);
        officeAddress.sendKeys("Kurnool, Andra Pradesh");
        waitForElementToBeClickable(phoneNo, driver);
        phoneNo.sendKeys("9876511223");
    }

    public String getReIssueNumber() {
        String message = creationMessage.getText();
        return  message;
    }

    public void closeApplication() {
        waitForElementToBeClickable(closeButton2, driver);
        closeButton2.click();
        switchToPreviouslyOpenedWindow(driver);
    }
}
