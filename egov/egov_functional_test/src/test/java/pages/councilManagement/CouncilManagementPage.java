package pages.councilManagement;

import entities.councilManagement.CreatePreambleDetails;
import entities.ptis.ApprovalDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static java.awt.SystemColor.window;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by tester1 on 1/4/2017.
 */
public class CouncilManagementPage extends BasePage {
    private WebDriver webDriver;

    @FindBy (id = "department")
    private WebElement preambleDepartment;

    @FindBy (id = "sanctionAmount")
    private WebElement sanctionAmount;

    @FindBy (id = "gistOfPreamble")
    private WebElement gistOfPreamble;

    @FindBy (id = "attachments")
    private WebElement attachment;

    @FindBy (id = "wards")
    private WebElement wards;

    @FindBy (id = "approvalDepartment")
    private WebElement approverDepartmentSelection;

    @FindBy (id = "approvalDesignation")
    private WebElement approverDesignationSelection;

    @FindBy (id = "approvalPosition")
    private WebElement approverSelection;

    @FindBy (id = "Forward")
    private WebElement forwardButton;

    @FindBy (id = "Approve")
    private WebElement approve;

    public CouncilManagementPage(WebDriver webDriver){this.webDriver=webDriver;}

    public void enterCreatePreambleDetails(CreatePreambleDetails createPreambleDetails) {
        new Select(preambleDepartment).selectByVisibleText(createPreambleDetails.getPreambleDepartment());
        enterText(sanctionAmount, createPreambleDetails.getAmount());
        enterText(gistOfPreamble, createPreambleDetails.getGistOfPreamble());
        attachment.sendKeys(System.getProperty("user.dir") + "/src/test/resources/PTISTestData.xlsx");
        Select sel=new Select(wards);
        List<WebElement> selval=sel.getOptions();
        for(int i=0;i<selval.size();i++)
        {
            sel.selectByIndex(i);
        }
    }

    public void enterApproverDetails(ApprovalDetails approvalDetails) {
        new Select(approverDepartmentSelection).selectByVisibleText(approvalDetails.getApproverDepartment());
        await().atMost(10, SECONDS).until(() -> new Select(approverDesignationSelection).getOptions().size() > 1);
        new Select(approverDesignationSelection).selectByVisibleText(approvalDetails.getApproverDesignation());
        await().atMost(10, SECONDS).until(() -> new Select(approverSelection).getOptions().size() > 1);
        new Select(approverSelection).selectByVisibleText(approvalDetails.getApprover());
        forwardButton.click();
    }

    public String getPreambleNumber() {
        List<WebElement> elements=webDriver.findElements(By.cssSelector(".col-sm-3.add-margin.view-content"));
        return elements.get(0).getText();
    }

    public String getStatus() {
        List<WebElement> elements=webDriver.findElements(By.cssSelector(".col-sm-3.add-margin.view-content"));
        String ele=elements.get(1).getText();
        webDriver.close();
        switchToPreviouslyOpenedWindow(webDriver);
        return ele;
    }

    public String approve() {
        approve.click();
        List<WebElement> elements=webDriver.findElements(By.cssSelector(".col-sm-3.add-margin.view-content"));
        String ele=elements.get(1).getText();
        webDriver.close();
        switchToPreviouslyOpenedWindow(webDriver);
        return ele;
    }
}
