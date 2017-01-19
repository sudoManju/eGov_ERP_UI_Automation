package pages.ptis;

import entities.ptis.RegistrationDetails;
import entities.tradeLicense.TradeDetails;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

/**
 * Created by bimal on 18/1/17.
 */
public class TransferDetailsPage extends BasePage {

    private WebDriver webdriver;

    public TransferDetailsPage (WebDriver webdriver) {
        this.webdriver = webdriver;
    }

    @FindBy (id = "REGISTERED TRANSFER")
    private WebElement registrationAlreadyDoneButton;

    @FindBy(id = "seller")
    private WebElement sellerExecutantNameTextBox;

    @FindBy (id = "buyer")
    private WebElement buyerClaimantNameTextBox;

    @FindBy (id = "doorNo")
    private WebElement doorNoTextBox;

    @FindBy (id = "address")
    private WebElement propertyAddressTextBox;

    @FindBy (id = "mobileNumber")
    private WebElement transferMobileNumber;

    public void chooseRegistrationAlreadyDone() {
        waitForElementToBeClickable(registrationAlreadyDoneButton, webdriver);
        registrationAlreadyDoneButton.click();
    }


    public void enterRegistrationDetails(RegistrationDetails registrationDetails) {
        waitForElementToBeClickable(transferMobileNumber, webdriver);
        transferMobileNumber.sendKeys("2299087661");
        transferMobileNumber.sendKeys(Keys.TAB);
        waitForElementToBeClickable(sellerExecutantNameTextBox, webdriver);
        enterText(sellerExecutantNameTextBox, registrationDetails.getSellerExecutantName());
        enterText(buyerClaimantNameTextBox, registrationDetails.getBuyerClaimantName());
        enterText(doorNoTextBox, registrationDetails.getDoorNo());
        enterText(propertyAddressTextBox, registrationDetails.getPropertyAddress());
//        enterText(registeredPlotArea, registrationDetails.getRegisteredPlotArea);
//        enterText(registeredPlinthArea, registrationDetails.getRegisteredPlinthArea);
//        enterText(eastBoundary, registrationDetails.getEastBoundary);
//        enterText(westBoundary, registrationDetails.getWestBoundary);
//        enterText(northBoundary, registrationDetails.getNorthBoundary);
//        enterText(southBoundary, registrationDetails.getSouthBoundary);
//        enterText(sROName, registrationDetails.getSROName);



    }


}
