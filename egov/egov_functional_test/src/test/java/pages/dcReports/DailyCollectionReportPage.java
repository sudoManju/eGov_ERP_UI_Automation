package pages.dcReports;

import entities.dcReports.PTReport;
import entities.dcReports.VLTReport;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by vinaykumar on 11/12/16.
 */
public class DailyCollectionReportPage extends BasePage {

    private WebDriver webDriver;

    @FindBy(id = "fromDate")
    private WebElement vltFromDate;

    @FindBy(id = "toDate")
    private WebElement vltToDate;

    @FindBy(id = "dailyCollectionReportSearchVLT")
    private WebElement vltReportSearch;

    @FindBy(name = "fromDate")
    private WebElement ptFromDate;

    @FindBy(id = "toDate")
    private WebElement ptToDate;

    @FindBy(id = "dailyCollectionReportSearch")
    private WebElement ptReportSearch;

    @FindBy(linkText = "Close")
    private WebElement dcCloseButton;

    public void enterVLTReportDetails(VLTReport vltReport){

        //waitForElementToBeClickable(vltFromDate, webDriver);
        enterText(vltFromDate, vltReport.getFromDate());

        //waitForElementToBeClickable(vltToDate, webDriver);
        enterText(vltToDate, vltReport.getToDate());

        //waitForElementToBeClickable(vltReportSearch, webDriver);
        vltReportSearch.click();
    }

    public void enterPTReportDetails(PTReport ptReport){

        enterText(ptFromDate, ptReport.getFromDate());
        enterText(ptToDate, ptReport.getToDate());
        ptReportSearch.click();
    }

}
