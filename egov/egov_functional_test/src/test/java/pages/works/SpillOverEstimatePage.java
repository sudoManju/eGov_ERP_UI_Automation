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

    @FindBy(css = "li[role='presentation'] a[data-now^='Estimate']")
    private WebElement estimateLink;

    @FindBy(id = "official_inbox_wrapper")
    private WebElement workListTable;

    @FindBy(css = "input[id='Submit'][type='submit']")
     private WebElement submitButton;

    @FindBy(css = "input[id='Approve'][type = 'submit']")
     private WebElement approveButton;

    @FindBy(id = "inboxsearch")
     private WebElement searchBox;

    String num1 = String.valueOf(Calendar.getInstance().get(Calendar.MILLISECOND));
    String num = String.valueOf(Calendar.getInstance().get(Calendar.SECOND));
    String transactionRefNo = num1+num;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate localDate = LocalDate.now();


    public void enterEstimateHeaderDetails(EstimateHeaderDetails estimateHeaderDetails) {
        String check = date.getAttribute("maxlength");
        if(check.equals("10")) {
            waitForElementToBeClickable(date, webDriver);
            date.click();
            enterText(date, estimateHeaderDetails.getDate());
            date.sendKeys(Keys.TAB);
        }

        waitForElementToBeClickable(reference, webDriver);
        reference.sendKeys(estimateHeaderDetails.getRequirementNumber());

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

//        JavascriptExecutor js;
//        if (webDriver instanceof JavascriptExecutor) {
//            js = (JavascriptExecutor)webDriver;
//        } else {
//            throw new IllegalStateException("This driver cannot run JavaScript.");
//        }
//
//        WebElement element = (WebElement)js.executeScript("$('#isWorkOrderCreated').trigger('click');")

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

       if(workDetails.getBillsCreated().equals(Boolean.TRUE)) {
           waitForElementToBeClickable(grossAmountTextBox, webDriver);
           enterText(grossAmountTextBox, workDetails.getGrossAmountBilled());
       }
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
        enterText(adminSanctionDateBox,dtf.format(localDate));
        adminSanctionDateBox.sendKeys(Keys.TAB);

        waitForElementToBeClickable(adminSanctionAuthorityTextBox,webDriver);
        adminSanctionAuthorityTextBox.sendKeys(adminSanctionDetails.getAdminSanctionAuthority());

    }

    public void enterTechnicalSanctionDetails(TechnicalSanctionDetails technicalSanctionDetails) {

        waitForElementToBeClickable(technicalSanctionNumberTextBox,webDriver);
        String technicalSanctionId = (technicalSanctionDetails.getTechnicalSanctionNumber() + transactionRefNo);
        enterText(technicalSanctionNumberTextBox, technicalSanctionId);

        waitForElementToBeClickable(technicalSanctionDateTextBox,webDriver);
        enterText(technicalSanctionDateTextBox,dtf.format(localDate));
        technicalSanctionDateTextBox.sendKeys(Keys.TAB);

        waitForElementToBeClickable(designationBox,webDriver);
        new Select(designationBox).selectByVisibleText(technicalSanctionDetails.getTechnicalSanctionAuthority());

    }

    public void saveAndClose() {

        waitForElementToBeVisible(saveButton, webDriver);
        saveButton.click();
    }

    public void enterWorkDetailsforestimate(WorkDetails workDetails) {
        waitForElementToBeClickable(estimateNameOfWorkBox,webDriver);
        enterText(estimateNameOfWorkBox,workDetails.getNameOfWork());

        waitForElementToBeClickable(estimateEstimateAmountBox,webDriver);
        enterText(estimateEstimateAmountBox,workDetails.getEstimatedAmount());
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

     waitForElementToBeClickable(approverComment,webDriver);
     enterText(approverComment,approverDetails.getApproverComment());
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

    public void close() {
        waitForElementToBeVisible(closeButton, webDriver);
        closeButton.click();

        switchToPreviouslyOpenedWindow(webDriver);
    }

    public void openApplication(String estimateNumber) {

        await().atMost(20, SECONDS).until(() -> workListTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size() > 0);
        List<WebElement> totalRows = workListTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        System.out.println("\n" + totalRows.size());
        for (WebElement applicationRow : totalRows) {
            if (applicationRow.findElements(By.tagName("td")).get(4).getText().contains(estimateNumber))
                applicationRow.click();
                break;
        }
        switchToNewlyOpenedWindow(webDriver);
    }

    public void submit() {
        waitForElementToBeClickable(submitButton, webDriver);
        submitButton.click();
    }

    public void adminSanctionNumber() {
        waitForElementToBeClickable(adminSanctionNumberTextBox,webDriver);
        adminSanctionNumberTextBox.sendKeys("ASN"+transactionRefNo);
    }


    public void detailsForApprove() {
        waitForElementToBeVisible(estimateAmountTextBox,webDriver);
        String amount = estimateAmountTextBox.getText();
        String actualAmount = amount.split("\\.")[0];
        waitForElementToBeClickable(actualAmountTextBox,webDriver);
        actualAmountTextBox.sendKeys(actualAmount);

        waitForElementToBeClickable(technicalSanctionNumberTextBox,webDriver);
        technicalSanctionNumberTextBox.sendKeys("TSN"+transactionRefNo);
        waitForElementToBeClickable(technicalSanctionDateTextBox,webDriver);
        technicalSanctionDateTextBox.sendKeys(dtf.format(localDate));
        technicalSanctionDateTextBox.sendKeys(Keys.TAB);

        approverComment.sendKeys("Approved");
    }

    public void approve() {
        waitForElementToBeClickable(approveButton,webDriver);
        approveButton.click();
    }
}