package pages.works;


import entities.works.EstimateHeaderDetails;
import entities.works.FinancialDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
}
