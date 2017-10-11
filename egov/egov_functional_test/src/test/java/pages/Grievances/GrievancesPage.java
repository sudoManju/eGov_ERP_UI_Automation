package pages.Grievances;

import entities.grievances.CreateComplaintDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;


public class GrievancesPage extends BasePage {
    private WebDriver webDriver;

    @FindBy(xpath = "html/body/div[1]/div/div[1]/div[2]/div[2]/a/div[2]")
    private WebElement registerComplaintLink;

    @FindBy(id = "f-name")
    private WebElement citizenNameTextBox;

    @FindBy(id = "mob-no")
    private WebElement mobNoTextBox;

    @FindBy(id = "email")
    private WebElement emailIdTextBox;

    @FindBy(id = "complaintTypeCategory")
    private WebElement complaintTypeCategorySelect;

    @FindBy(id = "complaintType")
    private WebElement complaintTypeSelect;

    @FindBy(id = "complaintTypeName")
    private WebElement complaintTypeTextBox;

    @FindBy(id = "doc")
    private WebElement grievanceDetailsText;

    @FindBy(id = "location")
    private WebElement grievanceLocationText;

    @FindBy(id = "landmarkDetails")
    private WebElement locationLandmarkText;

    @FindBy(css = "button[class='btn btn-primary']")
    private WebElement createGrievanceButton;

    @FindBy(xpath = ".//*[@id='main']/div[1]/div/div/div[1]/div/strong")
    private WebElement successMsg;

    @FindBy(linkText = "Grievance Redressal")
    private WebElement newRequestLink;

    @FindBy(linkText = "Register Grievance")
    private WebElement registerComplaint;

    @FindBy(id = "ctn_no")
    private WebElement CRNNumber;

    @FindBy(id = "status")
    private WebElement selectStatus;

    @FindBy(id = "inc_messge")
    private WebElement incMessageBox;

    @FindBy(xpath = "html/body/div[1]/div/div[1]/div/div/div[1]/div/strong")
    private WebElement acknMsg;

    @FindBy(linkText = "Close")
    private WebElement closeButton;

    @FindBy(id = "receivingMode1")
    private WebElement receivingModeRadio;

    @FindBy(xpath = "html/body/div[3]/header/div/ul/li[2]/a")
    private WebElement draftButton;

    @FindBy(id = "status")
    private WebElement statusSelect;

    @FindBy(xpath = ".//*[@id='inbox-template']/div[1]/div[1]/input")
    private WebElement searchCitizenInbox;

    @FindBy(xpath = ".//*[@id='tabelPortal']/tbody[2]/tr[1]/td[2]")
    private WebElement complaintLink;

    @FindBy(css = "button[type=submit]")
    private WebElement submitButton;

    @FindBy(id = "ct-location")
    private WebElement locationTextBox;

    @FindBy(id = "searchComplaints")
    private WebElement searchComplaints;

    @FindBy(id = "ct-ctno")
    private WebElement searchByAppNumTextBox;

    @FindBy(id = "when_date")
    private WebElement searchComplaintByDateBox;

    @FindBy(id = "toggle-searchcomp")
    private WebElement advanceSearchButton;

    @FindBy(id = "closeComplaints")
    private WebElement closeButton1;

    public GrievancesPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void openCreateGrievancePage() {
        clickOnButton(registerComplaintLink, webDriver);
        switchToNewlyOpenedWindow(webDriver);
    }

    public void enterCitizenContactDetails(CreateComplaintDetails createComplaintDetails) {
        clickOnButton(receivingModeRadio, webDriver);
        enterText(citizenNameTextBox, createComplaintDetails.getCitizenName(), webDriver);
        enterText(mobNoTextBox, createComplaintDetails.getcitizenMobNo(), webDriver);
//        enterText(emailIdTextBox, createComplaintDetails.getEmailId(), webDriver);
    }

    public String enterGrievanceDetails(CreateComplaintDetails createComplaintDetails, String user) {
        if (user.equals("citizen")) {
            enterText(complaintTypeTextBox, createComplaintDetails.getGrievanceType(), webDriver);
            await().atMost(10, TimeUnit.SECONDS).until(() -> webDriver.findElements(By.cssSelector("[class='tt-dropdown-menu'] div span div p strong")).size() == 1);
            WebElement dropdown = webDriver.findElement(By.cssSelector("[class='tt-dropdown-menu'] div span div p strong"));
            dropdown.click();
        } else {
            selectFromDropDown(complaintTypeCategorySelect, createComplaintDetails.getGrievanceCategory(), webDriver);
            selectFromDropDown(complaintTypeSelect, createComplaintDetails.getGrievanceType(), webDriver);
        }
        enterText(grievanceDetailsText, createComplaintDetails.getGrievanceDetails(), webDriver);
        enterText(grievanceLocationText, createComplaintDetails.getGrievanceLocation(), webDriver);
        await().atMost(10, TimeUnit.SECONDS).until(() -> webDriver.findElements(By.cssSelector("[class='tt-dropdown-menu'] div span div p strong")).size() == 1);
        WebElement dropdown = webDriver.findElement(By.cssSelector("[class='tt-dropdown-menu'] div span div p strong"));
        dropdown.click();
        enterText(locationLandmarkText, createComplaintDetails.getLocationLandmark(), webDriver);
        clickOnButton(createGrievanceButton, webDriver);
        return successMsg.getText();
    }

    public void getRegisterComplaintPage() {
        clickOnButton(newRequestLink, webDriver);
        clickOnButton(registerComplaint, webDriver);
        switchToNewlyOpenedWindow(webDriver);
    }

    public String getCRN() {
        String CrnNum = CRNNumber.getText();
        clickOnButton(closeButton, webDriver);
        switchToPreviouslyOpenedWindow(webDriver);
        return CrnNum;
    }


    public String officialMarkStatus(String status) {
        selectFromDropDown(selectStatus, status, webDriver);
        enterText(incMessageBox, status, webDriver);
        clickOnButton(submitButton, webDriver);
        String success = webDriver.findElement(By.xpath(".//*[@id='main']/div[1]/div/div/div[1]/div/strong")).getText();
        clickOnButton(closeButton, webDriver);
        switchToPreviouslyOpenedWindow(webDriver);
        return success;
    }

    public void getProcessingStatus() {
        selectFromDropDown(statusSelect, "PROCESSING", webDriver);
    }

    public void searchInCitizenInbox(String crn) {
        webDriver.navigate().refresh();
        webDriver.findElement(By.xpath(".//*[@id='totalServicesAppliedDiv']/div/div[2]")).click();
//        enterText(searchCitizenInbox, crn, webDriver);
        clickOnButton(complaintLink, webDriver);
        switchToNewlyOpenedWindow(webDriver);
    }

    public void withdrawComplaint(String complaintStatus) {
//        selectFromDropDown(selectStatus, complaintStatus, webDriver);
        enterText(incMessageBox, complaintStatus, webDriver);
        webDriver.findElement(By.cssSelector("button[class='btn btn-primary'][type='submit'")).click();
        clickOnButton(closeButton, webDriver);
        switchToPreviouslyOpenedWindow(webDriver);
    }

    public void searchComplaint(String applicationNumber, String searchType) {
        webDriver.navigate().refresh();
        switch (searchType) {

            case "appNum":
                enterText(searchByAppNumTextBox, applicationNumber, webDriver);
                break;

            case "location":
                enterText(locationTextBox, "Election Ward No. 44", webDriver);
                break;

            case "today":
                selectFromDropDown(searchComplaintByDateBox, "Today", webDriver);
                break;

            case "allDates":
                selectFromDropDown(searchComplaintByDateBox, "All", webDriver);
                break;

            case "last7Days":
                selectFromDropDown(searchComplaintByDateBox, "In Last 7 days", webDriver);
                break;

            case "last30Days":
                selectFromDropDown(searchComplaintByDateBox, "In Last 30 days", webDriver);
                break;

            case "last90Days":
                selectFromDropDown(searchComplaintByDateBox, "In Last 90 days", webDriver);
                break;

            case "status":
                clickOnButton(advanceSearchButton, webDriver);
                selectFromDropDown(webDriver.findElement(By.name("complaintStatus")), "REGISTERED", webDriver);
                break;

        }
        clickOnButton(searchComplaints, webDriver);
//        clickOnButton(webDriver.findElement(By.cssSelector("button[type='reset']")),webDriver);
//        selectFromDropDown(webDriver.findElement(By.name("complaintDate")), "All", webDriver);
//        clickOnButton(searchComplaints, webDriver);
//        List<WebElement> numOfComplaints = webDriver.findElements(By.className("sorting_1"));
//        int complaintRow = checkComplaint(numOfComplaints, applicationNumber);
//        numOfComplaints.get(complaintRow).click();
        String e = "//*[text()='" + applicationNumber + "']";
        await().atMost(10, TimeUnit.SECONDS).until(() -> webDriver.findElements(By.xpath(e)).size() == 1);
        webDriver.findElement(By.xpath(e)).click();
        switchToNewlyOpenedWindow(webDriver);
        clickOnButton(webDriver.findElement(By.cssSelector("[type='button'][onclick]")), webDriver);
        List<String> webPages = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(webPages.get(1));
    }

    private int checkComplaint(List<WebElement> numOfComplaints, String applicationNumber) {
        int rowNumber = 0;
        boolean found = false;
        for (int i = 0; i < numOfComplaints.size(); i++) {
            if (webDriver.findElements(By.className("sorting_1")).get(i).getText().contains(applicationNumber)) {
                rowNumber = i;
                found = true;
            }
        }
        if (found)
            return rowNumber;
        else
            throw new RuntimeException("Element Not Found");

    }

    public void close() {
        clickOnButton(closeButton1, webDriver);
        switchToPreviouslyOpenedWindow(webDriver);
    }
}
