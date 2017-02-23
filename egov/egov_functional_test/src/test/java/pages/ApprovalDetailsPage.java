package pages;

import entities.ApprovalDetailsEntity;
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

    @FindBy(id = "approverComments")
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

    public void enterApprovalDetailsForGrievances(ApprovalDetailsEntity approvalDetails) {
    new Select(approvalDepartmentSelect).selectByVisibleText(approvalDetails.getApproverDepartment());
    new Select(approvalDesignationSelect).selectByVisibleText(approvalDetails.getApproverDesignation());
    new Select(approvalPositionSelect).selectByVisibleText(approvalDetails.getApprover());
    enterText(incMessageTextBox, approvalDetails.getApproverRemarks());
    }

    public void enterApprovalDetails(ApprovalDetailsEntity approvalDetails) {
        new Select(approverDepartmentSelection).selectByVisibleText(approvalDetails.getApproverDepartment());
        await().atMost(10, SECONDS).until(() -> new Select(approverDesignationSelection).getOptions().size() > 1);
        new Select(approverDesignationSelection).selectByVisibleText(approvalDetails.getApproverDesignation());
        await().atMost(10, SECONDS).until(() -> new Select(approverSelection).getOptions().size() > 1);
        new Select(approverSelection).selectByVisibleText(approvalDetails.getApprover());
        enterText(approverCommentsTextBox, approvalDetails.getApproverRemarks());
    }

    public void enterApproverDetails(ApprovalDetailsEntity approvalDetails) {
        new Select(approvalDepartmentSelection).selectByVisibleText(approvalDetails.getApproverDepartment());
        await().atMost(10, SECONDS).until(() -> new Select(approvalDepartmentSelection).getOptions().size() > 1);
        new Select(approvalDesignationSelection).selectByVisibleText(approvalDetails.getApproverDesignation());
        await().atMost(10, SECONDS).until(() -> new Select(approvalDesignationSelection).getOptions().size() > 1);
        new Select(approvalPositionSelect).selectByVisibleText(approvalDetails.getApprover());
    }



    public void forward() {
        forwardButton.click();
    }

    public void createGrievance() {
        grievanceSubmit.click();
        closeButton.click();
        switchToPreviouslyOpenedWindow(webDriver);
    }
}
