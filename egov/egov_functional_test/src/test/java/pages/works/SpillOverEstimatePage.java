package pages.works;


import entities.works.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static com.jayway.awaitility.Awaitility.waitAtMost;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by manjunatha-lap on 14/12/2016.
 */
public class SpillOverEstimatePage extends BasePage
{
    private WebDriver webDriver;

    @FindBy(xpath = ".//*[@id='lineEstimateDate']")
    private WebElement date;

    @FindBy(id = "reference")
    private WebElement reference;

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

    @FindBy(css = "input[id ='isWorkOrderCreated'][type = 'checkbox']")
    private WebElement workOrderCreatedCheckBox;

    @FindBy(css = "input[id ='isBillsCreated'][type = 'checkbox']")
    private WebElement isBillCreatedCheckBox;

    @FindBy(id = "adminSanctionNumber")
    private WebElement adminSanctionNumberTextBox;

    @FindBy(id = "adminSanctionDate")
    private WebElement adminSanctionDateBox;

    @FindBy(id = "adminSanctionBy")
    private WebElement adminSanctionAuthorityTextBox;

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

    @FindBy(xpath = ".//*[@id='main']/div/div/div/div/div")
     private WebElement creationMsg;

    @FindBy(id ="tempLineEstimateDetails0.nameOfWork")
     private WebElement estimateNameOfWorkBox;

    @FindBy(id ="tempLineEstimateDetails0.estimateAmount")
     private WebElement estimateEstimateAmountBox;

    @FindBy(id = "tempLineEstimateDetails0.uom")
     private WebElement estimateUOMBox;

    @FindBy(id = "approvalDepartment")
    private WebElement approverDepartment;

    @FindBy(id = "approvalDesignation")
    private WebElement approverDesignation;

    @FindBy(id = "approvalPosition")
    private WebElement approver;

    @FindBy(id = "approvalComent")
    private WebElement approverComment;

    @FindBy(css = "input[id='Forward'][type='submit']")
    private WebElement forwardButton;

    @FindBy(css = "input[id='Submit'][type='submit']")
     private WebElement submitButton;

    @FindBy(css = "input[id='Approve'][type = 'submit']")
     private WebElement approveButton;

    @FindBy(id = "inboxsearch")
     private WebElement searchBox;

    public SpillOverEstimatePage(WebDriver webDriver){this.webDriver = webDriver;}

    public void enterEstimateHeaderDetails(EstimateHeaderDetails estimateHeaderDetails) {
        String check = date.getAttribute("maxlength");
        if(check.equals("10")) {
            enterDate(date, estimateHeaderDetails.getDate(), webDriver);
        }

        enterText(reference, estimateHeaderDetails.getRequirementNumber(), webDriver);

        enterText(wardInput, estimateHeaderDetails.getElectionWard(), webDriver);

        WebElement dropdown = webDriver.findElement(By.className("tt-dropdown-menu"));
        clickOnButton(dropdown, webDriver);

        selectFromDropDown(location, estimateHeaderDetails.getLocation(), webDriver);

        selectFromDropDown(workCategory, estimateHeaderDetails.getWorkCategory(), webDriver);

        selectFromDropDown(beneficiary, estimateHeaderDetails.getBeneficiary(), webDriver);

        selectFromDropDown(natureOfWork, estimateHeaderDetails.getNatureOfWork(), webDriver);

        selectFromDropDown(typeofwork, estimateHeaderDetails.getTypeOfWork(), webDriver);

        waitForElementToBeClickable(subTypeOfWork,webDriver);
        subTypeOfWork.click();
        subTypeOfWork.click();
        selectFromDropDown(subTypeOfWork, estimateHeaderDetails.getSubTypeOfWork(), webDriver);

        selectFromDropDown(modeOfEntrustment, estimateHeaderDetails.getModeOfEntrustment(), webDriver);
    }

    public void enterFinancialDetails(FinancialDetails financialDetails) {

        selectFromDropDown(fundBox, financialDetails.getFund(), webDriver);

        selectFromDropDown(functionBox, financialDetails.getFunction(), webDriver);

        selectFromDropDown(budgetHeadBox, financialDetails.getBudgetHead(), webDriver);
    }

    public void enterWorkDetails(WorkDetails workDetails) {

        clickOnButton(workOrderCreatedCheckBox, webDriver);

        clickOnButton(isBillCreatedCheckBox, webDriver);

        enterText(nameOfWorkTextBox,workDetails.getNameOfWork(), webDriver);

        String abstractIdNumber = (workDetails.getAbstractEstimateNumber() + get6DigitRandomInt()) ;
        enterText(absEstimateNumTextBox,abstractIdNumber, webDriver);

        enterText(estimateAmountTextBox,workDetails.getEstimatedAmount(), webDriver);

        String workIdNumber = (workDetails.getWorkIdentificationNumber() + get6DigitRandomInt());
        enterText(WINTextBox,workIdNumber, webDriver);

        enterText(actualAmountTextBox,workDetails.getActualEstimateAmount(), webDriver);

       if(workDetails.getBillsCreated().equals(Boolean.TRUE)) {
           enterText(grossAmountTextBox, workDetails.getGrossAmountBilled(), webDriver);
       }
    }

//    private void selectWorksIfCreated(WebElement element, Boolean hasCreated) {
//        if (hasCreated && !element.isSelected())
//            element.click();
//    }

    public void enterAdminSanctionDetails(AdminSanctionDetails adminSanctionDetails) {

        String adminSanctionId = (adminSanctionDetails.getAdministrationSanctionNumber() + get6DigitRandomInt());
        enterText(adminSanctionNumberTextBox,adminSanctionId, webDriver);

        adminSanctionDateBox.click();
        enterDate(adminSanctionDateBox, getCurrentDate(), webDriver);

        waitForElementToBeClickable(adminSanctionAuthorityTextBox,webDriver);
        adminSanctionAuthorityTextBox.sendKeys(adminSanctionDetails.getAdminSanctionAuthority());

    }

    public void enterTechnicalSanctionDetails(TechnicalSanctionDetails technicalSanctionDetails) {
        String technicalSanctionId = (technicalSanctionDetails.getTechnicalSanctionNumber() + get6DigitRandomInt());
        enterText(technicalSanctionNumberTextBox, technicalSanctionId, webDriver);
        enterDate(technicalSanctionDateTextBox, getCurrentDate(), webDriver);
        selectFromDropDown(designationBox, technicalSanctionDetails.getTechnicalSanctionAuthority(), webDriver);
    }

    public void enterWorkDetailsforestimate(WorkDetails workDetails) {
        enterText(estimateNameOfWorkBox,workDetails.getNameOfWork(), webDriver);
        enterText(estimateEstimateAmountBox,workDetails.getEstimatedAmount(), webDriver);
    }

    public void enterApproverDetails(ApproverDetails approverDetails) {
     waitForElementToBeClickable(approverDepartment,webDriver);
     new Select(approverDepartment).selectByVisibleText(approverDetails.getApproverDepartment());

     for (int i=0;i<4;i++) {
         try {
               waitForElementToBeClickable(approverDesignation, webDriver);
               approverDesignation.click();
               new Select(approverDesignation).selectByVisibleText(approverDetails.getApproverDesignation());
           } catch (StaleElementReferenceException e) {
               WebElement element1 = webDriver.findElement(By.id("approvalDesignation"));
               waitForElementToBeClickable(element1, webDriver);
               element1.click();
               new Select(element1).selectByVisibleText(approverDetails.getApprover());
           }
     }

   for (int i=0;i<4;i++) {
            try {
                waitForElementToBeClickable(approver, webDriver);
                approver.click();
                new Select(approver).selectByVisibleText(approverDetails.getApprover());
            } catch (StaleElementReferenceException e) {
                WebElement element2 = webDriver.findElement(By.id("approvalPosition"));
                waitForElementToBeClickable(element2, webDriver);
                element2.click();
                new Select(element2).selectByVisibleText(approverDetails.getApprover());
            }
    }

     enterText(approverComment,approverDetails.getApproverComment(), webDriver);
    }

    public String forwardToDEE() {
     waitForElementToBeClickable(forwardButton,webDriver);
     forwardButton.click();

        waitForElementToBeVisible(creationMsg,webDriver);
        String Msg = creationMsg.getText();
        String number =  Msg.substring(Msg.lastIndexOf(" ")+1);
        String num = number.substring(0, number.length()-1);

        System.out.println(num);

         return num;
    }

    public String successMessage(){
        waitForElementToBeVisible(creationMsg,webDriver);
        String msg =creationMsg.getText();
        System.out.println(msg);
        return msg;
    }

    public void adminSanctionNumber() {
        waitForElementToBeClickable(adminSanctionNumberTextBox,webDriver);
        adminSanctionNumberTextBox.sendKeys("ASN"+get6DigitRandomInt());
    }

    public void detailsForApprove() {
        waitForElementToBeVisible(estimateAmountTextBox,webDriver);
        String amount = estimateAmountTextBox.getText();
        String actualAmount = amount.split("\\.")[0];
        waitForElementToBeClickable(actualAmountTextBox,webDriver);
        actualAmountTextBox.sendKeys(actualAmount);

        waitForElementToBeClickable(technicalSanctionNumberTextBox,webDriver);
        technicalSanctionNumberTextBox.sendKeys("TSN"+get6DigitRandomInt());
        waitForElementToBeClickable(technicalSanctionDateTextBox,webDriver);
        technicalSanctionDateTextBox.sendKeys(getCurrentDate());
        technicalSanctionDateTextBox.sendKeys(Keys.TAB);
        approverComment.sendKeys("Approved");
    }

    public void saveAndClose() {
        waitForElementToBeVisible(saveButton, webDriver);
        saveButton.click();
    }

    public void approve() {
        waitForElementToBeClickable(approveButton,webDriver);
        approveButton.click();
    }

    public void submit() {
        waitForElementToBeClickable(submitButton, webDriver);
        submitButton.click();
    }

    public void close() {
        waitForElementToBeVisible(closeButton, webDriver);
        closeButton.click();
        switchToPreviouslyOpenedWindow(webDriver);
    }
}