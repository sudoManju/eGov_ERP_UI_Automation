package pages.works;


import entities.works.EstimateHeaderDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

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

    public void enterEstimateHeaderDetails(EstimateHeaderDetails estimateHeaderDetails) {
//        waitForElementToBeClickable(date, webDriver);
//        new Select(date).selectByVisibleText(estimateHeaderDetails.getDate());
        waitForElementToBeClickable(subject, webDriver);
        enterText(subject, estimateHeaderDetails.getSubject());
        waitForElementToBeClickable(reference, webDriver);
        enterText(reference, estimateHeaderDetails.getRequirementNumber());
        waitForElementToBeClickable(description, webDriver);
        enterText(description, estimateHeaderDetails.getDescription());
        waitForElementToBeClickable(wardInput, webDriver);
        enterText(wardInput, estimateHeaderDetails.getElectionWard());
        waitForElementToBeClickable(location, webDriver);
        new Select(location).selectByVisibleText(estimateHeaderDetails.getLocation());
    }

    public SpillOverEstimatePage(WebDriver webDriver){this.webDriver = webDriver;}
}
