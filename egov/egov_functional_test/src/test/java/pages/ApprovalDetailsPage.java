package pages;

import entities.ApprovalDetailsNew;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

public class ApprovalDetailsPage extends BasePage {
    private WebDriver webDriver;

    @FindBy(id = "approvalDepartment")
    private WebElement approvalDepartmentSelect;

    @FindBy(id= "approvalDesignation")
    private WebElement approvalDesignationSelect;

    @FindBy(id= "approvalPosition")
    private WebElement approvalPositionSelect;

    @FindBy(id= "inc_messge")
    private WebElement incMessageTextBox;

    @FindBy(xpath = ".//*[@id='complaintUpdate']/div[6]/div/button[1]")
    private WebElement grievanceSubmit;

    @FindBy(linkText = "Close")
    private WebElement closeButton;

    @FindBy(id = "approverComments")
    private WebElement approverRemarkTextBox;

    @FindBy(id = "Approve")
    private WebElement approveButton;

    @FindBy(css = "textarea[name='approvalComent']")
    private WebElement approvalCommentsTextBox;

    @FindBy(css = "textarea[name='approverComments']")
    private WebElement approverCommentsTextBox;

    @FindBy(id = "approverDepartment")
    private WebElement approverDepartmentSelection;

    @FindBy(id = "approverDesignation")
    private WebElement approverDesignationSelection;

    @FindBy(id = "approverPositionId")
    private WebElement approverSelection;

    @FindBy(id = "Forward")
    private WebElement forwardButton;

    @FindBy (id = "approvalDepartment")
    private WebElement approvalDepartmentSelection;

    @FindBy (id = "approvalDesignation")
    private WebElement approvalDesignationSelection;

    public ApprovalDetailsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void enterApprovalDetailsForGrievances(ApprovalDetailsNew approvalDetails) {

        new Select(approvalDepartmentSelect).selectByVisibleText(approvalDetails.getApproverDepartment());
        new Select(approvalDesignationSelect).selectByVisibleText(approvalDetails.getApproverDesignation());
        new Select(approvalPositionSelect).selectByVisibleText(approvalDetails.getApprover());
        enterText(incMessageTextBox, approvalDetails.getApproverRemarks(), webDriver);
    }

    public void enterApproverDetails(ApprovalDetailsNew approvalDetailsNew) {

        selectFromDropDown(approverDepartmentSelection , approvalDetailsNew.getApproverDepartment() ,webDriver);
        await().atMost(10, SECONDS).until(() -> new Select(approverDesignationSelection).getOptions().size() > 1);

        selectFromDropDown(approverDesignationSelection , approvalDetailsNew.getApproverDesignation() ,webDriver);
        await().atMost(10, SECONDS).until(() -> new Select(approverSelection).getOptions().size() > 1);

        selectFromDropDown(approverSelection , approvalDetailsNew.getApprover() ,webDriver);

        if(approverCommentsTextBox.isDisplayed()) {
            enterText(approverCommentsTextBox, approvalDetailsNew.getApproverRemarks(), webDriver);
        }

//        forward();
//
//        switchToNewlyOpenedWindow(webDriver);
    }

    public void enterApprovalDetails(ApprovalDetailsNew approvalDetailsNew) {

        selectFromDropDown(approvalDepartmentSelection, approvalDetailsNew.getApproverDepartment(), webDriver);
        await().atMost(10, SECONDS).until(() -> new Select(approvalDesignationSelection).getOptions().size() > 1);

        selectFromDropDown(approvalDesignationSelection, approvalDetailsNew.getApproverDesignation(), webDriver);
        await().atMost(10, SECONDS).until(() -> new Select(approvalPositionSelect).getOptions().size() > 1);

        selectFromDropDown(approvalPositionSelect, approvalDetailsNew.getApprover(), webDriver);

        if(approverCommentsTextBox.isDisplayed()) {
            enterText(approvalCommentsTextBox, approvalDetailsNew.getApproverRemarks(), webDriver);
        }

//        forward();
//
//        switchToNewlyOpenedWindow(webDriver);
    }


    public void forward() {
        clickOnButton(forwardButton , webDriver);
    }

    public void createGrievance() {
        grievanceSubmit.click();
        closeButton.click();
        switchToPreviouslyOpenedWindow(webDriver);
    }
}
