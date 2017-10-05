package pages.employeeManagement.position;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class PositionPage extends BasePage {

    @FindBy(id = "position_dept")
    private WebElement positionDepartmentSelectBox;

    @FindBy(id = "position_desig")
    private WebElement positionDesignationSelectBox;

    @FindBy(id = "position_name")
    private WebElement positionNameTextBox;

    @FindBy(id = "active")
    private WebElement activeCheckBox;

    @FindBy(xpath = ".//*[@id='addPosition']/div[2]/div/button")
    private WebElement positionSubmitButton;

    @FindBy(xpath = "//*[text()='Close']")
    private WebElement positionCloseButton;

    private WebDriver webDriver;

    public PositionPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void enterPositionDetails() {
        selectFromDropDown(positionDepartmentSelectBox, "Department", webDriver);
        selectFromDropDown(positionDesignationSelectBox, "Designation", webDriver);
        enterText(positionNameTextBox, "Name", webDriver);
        clickOnButton(positionSubmitButton, webDriver);
    }

    public void selectPositionDetails(String departmentID, String designationID, String positionID) {
        selectFromDropDown(positionDepartmentSelectBox, departmentID, webDriver);
        selectFromDropDown(positionDesignationSelectBox, designationID, webDriver);
        enterText(positionNameTextBox, positionID, webDriver);
    }

    public void selectPositionDetails() {
        clickOnButton(positionSubmitButton, webDriver);
        clickOnButton(positionCloseButton, webDriver);
        switchToPreviouslyOpenedWindow(webDriver);
    }
}
