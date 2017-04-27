package pages.employeeManagement;

import entities.employeeManagement.EmployeeDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class EmployeeDetailsPage extends BasePage {

    private WebDriver driver;

    @FindBy(id = "user.name")
    private WebElement employeeNameTextBox;

    @FindBy(id = "code")
    private WebElement employeeCodeTextBox;

    @FindBy(id = "employeeType")
    private WebElement employeeTypeBox;

    @FindBy(id = "employeeStatus")
    private WebElement employeeStatusBox;

    @FindBy(id = "group")
    private WebElement employeeGroupBox;

    @FindBy(id = "user.dob")
    private WebElement dobTextBox;

    @FindBy(css = "input[id='user.gender'][value='MALE']")
    private WebElement maleRadioButton;

    @FindBy(css = "input[id='user.gender'][value='FEMALE']")
    private WebElement femaleRadioButton;

    @FindBy(id = "maritalStatus")
    private WebElement maritalStatusBox;

    @FindBy(id = "user.userName")
    private WebElement userNameTextBox;

    @FindBy(css = "input[id='user.active'][value='true']")
    private WebElement userActiveYesButton;

    @FindBy(css = "input[id='user.active'][value='false']")
    private WebElement userActiveNoButton;

    @FindBy(id = "user.mobileNumber")
    private WebElement mobileNumberTextBox;

    @FindBy(id = "user.emailId")
    private WebElement emailIdTextBox;

    @FindBy(id = "user.fatherOrHusbandName")
    private WebElement fatherOrHusbandName;

    @FindBy(id = "placeOfBirth")
    private WebElement birthPlaceTextBox;

    @FindBy(id = "user.bloodGroup")
    private WebElement userBloodGroupBox;

    @FindBy(id = "motherTounge")
    private WebElement motherToungeBox;

    @FindBy(id = "religion")
    private WebElement religionBox;

    @FindBy(id = "community")
    private WebElement communityBox;

    @FindBy(id = "category")
    private WebElement categoryBox;

    @FindBy(css = "input[id='physicallyDisabled'][value='true']")
    private WebElement physicallyDisabledYesButton;

    @FindBy(css = "input[id='physicallyDisabled'][value='false']")
    private WebElement physicallyDisabledNoButton;

    @FindBy(css = "input[id='medicalReportProduced'][value='true']")
    private WebElement medicalReportAvailbleYesButton;

    @FindBy(css = "input[id='medicalReportProduced'][value='false']")
    private WebElement medicalReportAvailbleNoButton;

    @FindBy(id = "user.identificationMark")
    private WebElement identificationMarkTextBox;

    @FindBy(id = "user.pan")
    private WebElement panNumberTextBox;

    @FindBy(id = "passportNo")
    private WebElement passportNoTextBox;

    @FindBy(id = "gpfNo")
    private WebElement gpfNoTextBox;

    @FindBy(id = "user.aadhaarNumber")
    private WebElement aadhaarNumberTextBox;

    @FindBy(id = "bank")
    private WebElement bankBox;

    @FindBy(id = "bankBranch")
    private WebElement bankBranchBox;

    @FindBy(id = "bankAccount")
    private WebElement bankAccountNumberTextBox;

    @FindBy(id = "user.altContactNumber")
    private WebElement altContactNumberTextBox;

    @FindBy(id = "user.permanentAddress")
    private WebElement permanentAddressTextBox;

    @FindBy(id = "user.permanentCity")
    private WebElement permanentCityTextBox;

    @FindBy(id = "user.permanentPincode")
    private WebElement permanentPincodeTextBox;

    @FindBy(id = "user.correspondenceAddress")
    private WebElement correspondenceAddressTextBox;

    @FindBy(id = "user.correspondenceCity")
    private WebElement correspondenceCityTextBox;

    @FindBy(id = "user.correspondencePincode")
    private WebElement correspondencePincodeTextBox;

    @FindBy(id = "languagesKnown")
    private WebElement languagesKnownBox;

    @FindBy(id = "recruitmentMode")
    private WebElement recruitmentModeBox;

    @FindBy(id = "recruitmentType")
    private WebElement recruitmentTypeBox;

    @FindBy(id = "recruitmentQuota")
    private WebElement recruitmentQuotaBox;

    @FindBy(id = "dateOfAppointment")
    private WebElement dateOfAppointmentTextBox;

    @FindBy(id = "dateOfJoining")
    private WebElement dateOfJoiningTextBox;

    @FindBy(id = "retirementAge")
    private WebElement retirementAgeTextBox;

    @FindBy(id = "dateOfRetirement")
    private WebElement dateOfRetirementTextBox;

    @FindBy(id = "dateOfTermination")
    private WebElement dateOfTerminationTextBox;

    @FindBy(id = "dateOfResignation")
    private WebElement dateOfResignationTextBox;

    @FindBy(id = "user.photo")
    private WebElement choosePhoto;

    @FindBy(id = "user.signature")
    private WebElement chooseSignature;

    @FindBy(id = "documents")
    private WebElement chooseDocuments;

    public EmployeeDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmployeeDetails(EmployeeDetails employeeDetails) {
        enterText(employeeNameTextBox, "TestUser_" + get6DigitRandomInt(), driver);
        enterText(employeeCodeTextBox, "EMP_" + get6DigitRandomInt(), driver);
        selectFromDropDown(employeeTypeBox, employeeDetails.getEmployeeType(), driver);
        selectFromDropDown(employeeStatusBox, employeeDetails.getStatus(), driver);
        enterText(dobTextBox, employeeDetails.getDateOfBirth(), driver);
        if (employeeDetails.getGender().equals("Male")) {
            clickOnButton(maleRadioButton, driver);
        } else {
            clickOnButton(femaleRadioButton, driver);
        }
        selectFromDropDown(maritalStatusBox, employeeDetails.getMaritalStatus(), driver);
        enterText(userNameTextBox, employeeDetails.getUserName(), driver);
        if (employeeDetails.getIsUserActive().equals("Yes")) {
            clickOnButton(userActiveYesButton, driver);
        } else {
            clickOnButton(userActiveNoButton, driver);
        }
        enterText(mobileNumberTextBox, employeeDetails.getMobileNumber(), driver);
        enterText(passportNoTextBox, "IND" + get6DigitRandomInt(), driver);
        enterText(gpfNoTextBox, get6DigitRandomInt(), driver);
        enterText(panNumberTextBox, "ABCDE" + Integer.toString(Integer.parseInt(get6DigitRandomInt()) / 100) + "F", driver);
        enterText(permanentAddressTextBox, employeeDetails.getPermanentAddress(), driver);
        enterText(permanentCityTextBox, employeeDetails.getPermanentCity(), driver);
        enterText(permanentPincodeTextBox, employeeDetails.getPermanentPincode(), driver);
        enterText(dateOfAppointmentTextBox, employeeDetails.getDateOfAppointment(), driver);
    }
}
