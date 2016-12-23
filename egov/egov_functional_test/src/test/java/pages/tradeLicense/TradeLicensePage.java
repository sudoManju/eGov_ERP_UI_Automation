package pages.tradeLicense;

import entities.tradeLicense.TradeOwnerDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

/**
 * Created by bimal on 20/12/16.
 */
public class TradeLicensePage extends BasePage {
    private WebDriver webDriver;

    @FindBy(id = "adhaarId")
    private WebElement aadhaarNumberTextBox;

    public TradeLicensePage(WebDriver webDriver) {this.webDriver = webDriver;
    }
    public void entertradeOwnerDetails(TradeOwnerDetails tradeOwnerDetails) {
        waitForElementToBeClickable(aadhaarNumberTextBox , webDriver);

        enterText(aadhaarNumberTextBox, tradeOwnerDetails.getAadhaarNumber());

    }
}
