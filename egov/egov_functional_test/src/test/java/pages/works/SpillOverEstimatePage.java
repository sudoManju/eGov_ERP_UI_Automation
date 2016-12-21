package pages.works;


import entities.works.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by manjunatha-lap on 14/12/2016.
 */
public class SpillOverEstimatePage extends BasePage
{
    private WebDriver webDriver;

    @FindBy(id = "lineEstimateDate")
    private WebElement date;

    @FindBy(id = "subject")
    private WebElement subject;

    @FindBy(id = "reference")
    private WebElement reference;

    @FindBy(id = "description")
    private WebElement description;

    @FindBy(id = "wardInput")
    private WebElement wardInput;

    @FindBy(id = "locationBoundary")
    private WebElement location;

    @FindBy(id = "workCategory")
    private WebElement workCategory;

    @FindBy(id = "beneficiary")
    private WebElement beneficiary;

    @FindBy(id = "natureOfWork")
    private WebElement natureOfWork;

    @FindBy(id = "typeofwork")
    private WebElement typeofwork;

    @FindBy(id = "subTypeOfWork")
    private WebElement subTypeOfWork;

    @FindBy(id = "modeOfAllotment")
    private WebElement modeOfEntrustment;

    @FindBy(className = "datepicker-days")
    private WebElement dateTable;

    @FindBy(id = "fund")
    private WebElement fundBox;

    @FindBy(id = "function")
    private WebElement functionBox;

    @FindBy(id = "budgetHead")
    private WebElement budgetHeadBox;

    @FindBy(id =  "lineEstimateDetails0.nameOfWork")
    private WebElement nameOfWorkTextBox;

    @FindBy(id = "estimateNumber0")
    private WebElement absEstimateNumTextBox;

    @FindBy(id = "estimateAmount0")
    private WebElement estimateAmountTextBox;

    @FindBy(id = "lineEstimateDetails0.projectCode.code")
    private WebElement WINTextBox;

    @FindBy(id = "actualEstimateAmount0")
    private WebElement actualAmountTextBox;

    @FindBy(id = "grossAmountBilled0")
    private WebElement grossAmountTextBox;

    @FindBy(id = "quantity0")
    private WebElement quantityTextBox;

    @FindBy(id = "lineEstimateDetails0.uom")
    private WebElement UOMBox;

    @FindBy(id = "quantity")
    private WebElement expectedOutcomeTextBox;

    @FindBy(css = "input[id ='isWorkOrderCreated'][type = 'checkbox']")
    private WebElement workOrderCreatedCheckBox;

    @FindBy(css = "input[id ='isBillsCreated'][type = 'checkbox']")
    private WebElement isBillCreatedCheckBox;

    @FindBy(id = "adminSanctionNumber")
    private WebElement adminSanctionNumberTextBox;

    @FindBy(id = "adminSanctionDate")
    private WebElement adminSanctionDateBox;

    @FindBy(id ="technicalSanctionNumber")
    private WebElement technicalSanctionNumberTextBox;

    @FindBy(id = "technicalSanctionDate")
    private WebElement technicalSanctionDateTextBox;

    @FindBy(id = "designation")
    private WebElement designationBox;

    @FindBy(css = "input[id ='Save'][type ='submit']")
    private WebElement saveButton;

    @FindBy(linkText = "Close")
    private WebElement closeButton;


    String transactionRefNo = String.valueOf(Calendar.getInstance().get(Calendar.MILLISECOND));

    public void enterEstimateHeaderDetails(EstimateHeaderDetails estimateHeaderDetails) {
        waitForElementToBeClickable(date, webDriver);
        date.click();
        enterText(date,estimateHeaderDetails.getDate());
        date.sendKeys(Keys.TAB);

        waitForElementToBeClickable(subject, webDriver);
        enterText(subject, estimateHeaderDetails.getSubject());

        waitForElementToBeClickable(reference, webDriver);
        enterText(reference, estimateHeaderDetails.getRequirementNumber());

        waitForElementToBeClickable(description, webDriver);
        enterText(description, estimateHeaderDetails.getDescription());

        waitForElementToBeClickable(wardInput, webDriver);
        wardInput.sendKeys(estimateHeaderDetails.getElectionWard());

        waitForElementToBeVisible( webDriver.findElement(By.className("tt-dropdown-menu")),webDriver);
        WebElement dropdown = webDriver.findElement(By.className("tt-dropdown-menu"));
        dropdown.click();

        waitForElementToBeClickable(location,webDriver);
        new Select(location).selectByVisibleText(estimateHeaderDetails.getLocation());

        waitForElementToBeClickable(workCategory,webDriver);
        new Select(workCategory).selectByVisibleText(estimateHeaderDetails.getWorkCategory());

        waitForElementToBeClickable(beneficiary,webDriver);
        new Select(beneficiary).selectByVisibleText(estimateHeaderDetails.getBeneficiary());

        waitForElementToBeClickable(natureOfWork,webDriver);
        new Select(natureOfWork).selectByVisibleText(estimateHeaderDetails.getNatureOfWork());

        waitForElementToBeClickable(typeofwork,webDriver);
        new Select(typeofwork).selectByVisibleText(estimateHeaderDetails.getTypeOfWork());

        waitForElementToBeClickable(subTypeOfWork,webDriver);
        subTypeOfWork.click();
        subTypeOfWork.click();
        new Select(subTypeOfWork).selectByVisibleText(estimateHeaderDetails.getSubTypeOfWork());

        waitForElementToBeClickable(modeOfEntrustment,webDriver);
        new Select(modeOfEntrustment).selectByVisibleText(estimateHeaderDetails.getModeOfEntrustment());

    }

    public SpillOverEstimatePage(WebDriver webDriver){this.webDriver = webDriver;}

    public void enterFinancialDetails(FinancialDetails financialDetails) {

        waitForElementToBeClickable(fundBox,webDriver);
        new Select(fundBox).selectByVisibleText(financialDetails.getFund());

        waitForElementToBeClickable(functionBox,webDriver);
        new Select(functionBox).selectByVisibleText(financialDetails.getFunction());

        waitForElementToBeClickable(budgetHeadBox,webDriver);
        new Select(budgetHeadBox).selectByVisibleText(financialDetails.getBudgetHead());
    }

    public void enterWorkDetails(WorkDetails workDetails) {

        waitForElementToBeClickable(workOrderCreatedCheckBox,webDriver);
        selectWorksIfCreated(workOrderCreatedCheckBox, workDetails.getWorksorderCreated());

        waitForElementToBeClickable(isBillCreatedCheckBox,webDriver);
        selectWorksIfCreated(isBillCreatedCheckBox, workDetails.getBillsCreated());

        waitForElementToBeClickable(nameOfWorkTextBox,webDriver);
        enterText(nameOfWorkTextBox,workDetails.getNameOfWork());

        waitForElementToBeClickable(absEstimateNumTextBox,webDriver);
        String abstractIdNumber = (workDetails.getAbstractEstimateNumber() + transactionRefNo) ;
        enterText(absEstimateNumTextBox,abstractIdNumber);

        waitForElementToBeClickable(estimateAmountTextBox,webDriver);
        enterText(estimateAmountTextBox,workDetails.getEstimatedAmount());

        waitForElementToBeClickable(WINTextBox,webDriver);
        String workIdNumber = (workDetails.getWorkIdentificationNumber() + transactionRefNo );
        enterText(WINTextBox,workIdNumber);

        waitForElementToBeClickable(actualAmountTextBox,webDriver);
        enterText(actualAmountTextBox,workDetails.getActualEstimateAmount());

        waitForElementToBeClickable(grossAmountTextBox,webDriver);
        enterText(grossAmountTextBox,workDetails.getGrossAmountBilled());

        waitForElementToBeClickable(quantityTextBox,webDriver);
        enterText(quantityTextBox,workDetails.getQuantity());

        waitForElementToBeClickable(UOMBox,webDriver);
        new Select(UOMBox).selectByVisibleText(workDetails.getUom());

        waitForElementToBeClickable(expectedOutcomeTextBox,webDriver);
        enterText(expectedOutcomeTextBox,workDetails.getExpectedOutcome());
    }

    private void selectWorksIfCreated(WebElement element, Boolean hasCreated) {
        if (hasCreated && !element.isSelected())
            element.click();
    }

    public void enterAdminSanctionDetails(AdminSanctionDetails adminSanctionDetails) {

        waitForElementToBeClickable(adminSanctionNumberTextBox,webDriver);
        String adminSanctionId = (adminSanctionDetails.getAdministrationSanctionNumber() + transactionRefNo);
        enterText(adminSanctionNumberTextBox,adminSanctionId);

        waitForElementToBeClickable(adminSanctionDateBox,webDriver);
        adminSanctionDateBox.click();
        enterText(adminSanctionDateBox,adminSanctionDetails.getAdminSanctionDate());
        adminSanctionDateBox.sendKeys(Keys.TAB);
    }

    public void enterTechnicalSanctionDetails(TechnicalSanctionDetails technicalSanctionDetails) {

        waitForElementToBeClickable(technicalSanctionNumberTextBox,webDriver);
        String technicalSanctionId = (technicalSanctionDetails.getTechnicalSanctionNumber() + transactionRefNo);
        enterText(technicalSanctionNumberTextBox, technicalSanctionId);

        waitForElementToBeClickable(technicalSanctionDateTextBox,webDriver);
        enterText(technicalSanctionDateTextBox,technicalSanctionDetails.getTechnicalSanctionDate());
        technicalSanctionDateTextBox.sendKeys(Keys.TAB);

        waitForElementToBeClickable(designationBox,webDriver);
        new Select(designationBox).selectByVisibleText(technicalSanctionDetails.getTechnicalSanctionAuthority());

    }

    public void saveAndClose() {

        waitForElementToBeVisible(saveButton, webDriver);
        saveButton.click();

        waitForElementToBeVisible(closeButton, webDriver);
        closeButton.click();

        await().atMost(5, SECONDS).until(() -> webDriver.getWindowHandles().size() == 1);
        for (String winHandle : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(winHandle);
        }
    }
}
