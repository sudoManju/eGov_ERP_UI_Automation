package pages.employeeManagement.employeeCreation;

import entities.employeeManagement.createEmployee.AssignmentDetails;
import org.openqa.selenium.By;
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

    @FindBy(css = ".btn.btn-primary")
    private WebElement addOrEditButton;

    @FindBy(id = "addEmployee")
    private WebElement submitButton;

    private WebDriver webDriver;

    public AssignmentDetailsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void enterAssignmentDetails(AssignmentDetails assignmentDetails) {

        jsClick(webDriver.findElement(By.cssSelector("a[href='#assignmentDetails']")), webDriver);
        jsClick(addImageButton, webDriver);
        if (assignmentDetails.getIsPrimary().equalsIgnoreCase("Yes")) {
            clickOnButton(isPrimaryTrueRadio, webDriver);
        } else {
            clickOnButton(isPrimaryFalseRadio, webDriver);
        }

        enterDate(fromDateTextBox, getCurrentDate(), webDriver);
        enterDate(toDateTextBox, getCurrentDate(), webDriver);
        selectFromDropDown(departmentSelectBox, assignmentDetails.getDepartment(), webDriver);
        selectFromDropDown(designationSelectBox, assignmentDetails.getDesignation(), webDriver);
        clickOnButton(positionSelectBox, webDriver);
        selectFromDropDown(positionSelectBox, positionSelectBox.findElements(By.tagName("option")).get(1).getText(), webDriver);

        clickOnButton(addOrEditButton, webDriver);
    }
}
