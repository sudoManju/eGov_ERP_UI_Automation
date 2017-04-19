package pages.employeeManagement;

import entities.employeeManagement.AssignmentDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class AssignmentDetailsPage extends BasePage {

    @FindBy(css = ".glyphicon.glyphicon-plus")
    private WebElement addImageButton;

    @FindBy(css = "input[id='assignments.isPrimary'][value='true']")
    private WebElement isPrimaryTrueRadio;

    @FindBy(css = "input[id='assignments.isPrimary'][value='false']")
    private WebElement isPrimaryFalseRadio;

    @FindBy(css = "input[id='assignments.fromDate']")
    private WebElement fromDateTextBox;

    @FindBy(css = "input[id='assignments.toDate']")
    private WebElement toDateTextBox;

    @FindBy(css = "select[id='assignments.department']")
    private WebElement departmentSelectBox;

    @FindBy(css = "select[id='assignments.designation']")
    private WebElement designationSelectBox;

    @FindBy(css = "select[id='assignments.position']")
    private WebElement positionSelectBox;

    @FindBy(css = "select[id='assignments.grade']")
    private WebElement gradeSelectBox;

    @FindBy(css = "select[id='assignments.function']")
    private WebElement functionSelectBox;

    @FindBy(css = "select[id='assignments.functionary']")
    private WebElement functionarySelectBox;

    @FindBy(css = "select[id='assignments.fund']")
    private WebElement fundSelectBox;

    @FindBy(css = "input[id='assignments.hod'][value='true']")
    private WebElement isHODTrueRadioButton;

    @FindBy(css = "input[id='assignments.hod'][value='false']")
    private WebElement isHODFalseRadioButton;

    @FindBy(css = "input[id='assignments.govtOrderNumber']")
    private WebElement govtOrderNumberTextBox;

    @FindBy(css = "input[id='assignments.documents']")
    private WebElement attachFileButton;

    @FindBy(linkText = "Add/Edit")
    private WebElement addOrEditButton;

    @FindBy(id = "addEmployee")
    private WebElement submitButton;

    private WebDriver webDriver;

    public AssignmentDetailsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void enterassignmentDetails(AssignmentDetails assignmentDetails) {

        clickOnButton(addImageButton, webDriver);

        if (assignmentDetails.getIsPrimary()) {
            clickOnButton(isPrimaryTrueRadio, webDriver);
        } else {
            clickOnButton(isPrimaryFalseRadio, webDriver);
        }

        enterDate(fromDateTextBox, assignmentDetails.getFromDate(), webDriver);
        enterDate(toDateTextBox, assignmentDetails.getToDate(), webDriver);
        selectFromDropDown(departmentSelectBox, assignmentDetails.getDepartment(), webDriver);
        selectFromDropDown(designationSelectBox, assignmentDetails.getDesignation(), webDriver);
        selectFromDropDown(positionSelectBox, assignmentDetails.getPosition(), webDriver);

        if (assignmentDetails.getIsHOD()) {
            clickOnButton(isHODTrueRadioButton, webDriver);
        } else {
            clickOnButton(isHODFalseRadioButton, webDriver);
        }

        clickOnButton(addOrEditButton, webDriver);
        clickOnButton(submitButton, webDriver);
    }
}
