package pages.Grievances;

import entities.grievances.CreateComplaintDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

/**
 * Created by tester1 on 1/23/2017.
 */
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

    @FindBy(id = "address")
    private WebElement citizenAddressTextBox;

    public GrievancesPage (WebDriver webDriver) {this.webDriver= webDriver;}

    public void openCreateGrievancePage() {
        waitForElementToBeClickable(registerComplaintLink, webDriver);
        registerComplaintLink.click();
        switchToNewlyOpenedWindow(webDriver);

    }

    public void enterCitizenContactDetails(CreateComplaintDetails createComplaintDetails) {
    waitForElementToBeClickable(citizenNameTextBox, webDriver);
    enterText(citizenNameTextBox, createComplaintDetails.getCitizenName());
    enterText(mobNoTextBox, createComplaintDetails.getcitizenMobNo());
    enterText(emailIdTextBox, createComplaintDetails.getEmailId());
    enterText(citizenAddressTextBox,createComplaintDetails.getcitizenAddress());

    }
}
