package pages.marriageRegistration;

import entities.marriageRegistration.MarriageRegistrationInformation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

/**
 * Created by manjunatha-lap on 24/01/2017.
 */
public class MarriageRegistrationPage extends BasePage {
    public WebDriver driver;

    @FindBy(id = "select-registrationunit")
    private WebElement registrationUnit;

    @FindBy(id = "txt-street")
    private WebElement street;

    @FindBy(id = "select-locality")
    private WebElement locality;

    @FindBy(id = "txt-city")
    private WebElement city;

    @FindBy(id = "txt-dateOfMarriage")
    private WebElement dateOfMarriage;

    @FindBy(id = "select-venue")
    private  WebElement venueOfMarriage;

    @FindBy(id = "txt-placeofmrg")
    private WebElement placeOfMarriage;

    @FindBy(css = "input[type='file'][id='marriage-photo']")
    private WebElement marriagePhoto;

    @FindBy(id = "husband.name.firstName")
    private WebElement brideFullName;

    @FindBy(id = "txt-lastName")
    private WebElement brideLastName;

    @FindBy(id = "txt-parentsName")
    private WebElement parentsName;

    @FindBy(css = "input[type='file'][id='husband-photo']")
    private WebElement photoUpload;

    @FindBy(id = "husband.religion")
    private WebElement religion;

    @FindBy(id = "husband.ageInYearsAsOnMarriage")
    private WebElement ageInYearsAsOnMarriage;

    @FindBy(id = "husband.ageInMonthsAsOnMarriage")
    private WebElement ageInMonthsAsOnMarriage;

    @FindBy(id = "husband.maritalStatus")
    private WebElement maritalStatus;

    @FindBy(id = "husband.contactInfo.residenceAddress")
    private WebElement residenceAddress;

    @FindBy(id = "husband.contactInfo.officeAddress")
    private WebElement officeAddress;

    @FindBy(id = "husband.contactInfo.mobileNo")
    private WebElement mobileNo;

    @FindBy(id = "husband.occupation")
    private WebElement occupation;

    @FindBy(id = "husband.qualification")
    private WebElement qualification;

    @FindBy(id = "husband.nationality")
    private WebElement nationality;

    public  MarriageRegistrationPage(WebDriver driver){ this.driver = driver;}

    public void enterApplicantsInformation(MarriageRegistrationInformation marriageRegistrationInformation) {
        waitForElementToBeClickable(registrationUnit, driver);
        registrationUnit.sendKeys(marriageRegistrationInformation.getRegistrationUnit());
        waitForElementToBeClickable(street, driver);
        street.sendKeys(marriageRegistrationInformation.getStreet());
        waitForElementToBeClickable(locality, driver);
        locality.sendKeys(marriageRegistrationInformation.getLocality());
        waitForElementToBeClickable(city, driver);
        city.sendKeys(marriageRegistrationInformation.getCity());
        waitForElementToBeClickable(placeOfMarriage, driver);
        placeOfMarriage.sendKeys(marriageRegistrationInformation.getPlaceOfMarriage());
        marriagePhoto.sendKeys(System.getProperty("user.dir")+"\\src\\test\\resources\\logo.jpg");
//        waitForElementToBeClickable(brideFullName, driver);
//        brideFullName.sendKeys(marriageRegistrationInformation.getFullName());
//        waitForElementToBeClickable(brideLastName, driver);
//        brideLastName.sendKeys("N");
//        photoUpload.sendKeys(System.getProperty("user.dir")+"\\src\\test\\resources\\logo.jpg");
//        waitForElementToBeClickable(parentsName, driver);
//        parentsName.sendKeys(marriageRegistrationInformation.getFathersMothersName());
//        waitForElementToBeClickable(religion, driver);
//        religion.sendKeys(marriageRegistrationInformation.getReligion());
//        waitForElementToBeClickable(ageInYearsAsOnMarriage, driver);
//        ageInYearsAsOnMarriage.sendKeys("32");
//        waitForElementToBeClickable(ageInMonthsAsOnMarriage, driver);
//        ageInMonthsAsOnMarriage.sendKeys("0");
//        waitForElementToBeClickable(maritalStatus, driver);
//        maritalStatus.sendKeys(marriageRegistrationInformation.getStatusAtTheTimeMarriage());
//        waitForElementToBeClickable(residenceAddress, driver);
//        residenceAddress.sendKeys(marriageRegistrationInformation.getResidenceAddress());
//        waitForElementToBeClickable(street, driver);
//        street.sendKeys(marriageRegistrationInformation.getStreet());
//        waitForElementToBeClickable(locality, driver);
//        locality.sendKeys(marriageRegistrationInformation.getLocality());
//        waitForElementToBeClickable(city, driver);
//        city.sendKeys(marriageRegistrationInformation.getCity());

    }
}
