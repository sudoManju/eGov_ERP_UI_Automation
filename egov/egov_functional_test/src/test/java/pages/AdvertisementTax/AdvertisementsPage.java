package pages.AdvertisementTax;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

/**
 * Created by karthik on 11/1/17.
 */
public class AdvertisementsPage extends BasePage {

    private WebDriver driver;

    @FindBy(id = "category")
    private WebElement categoryBox;

    @FindBy(id = "subCategory")
    private WebElement subCategoryBox;

    @FindBy(id = "rateClass")
    private WebElement classBox;

    @FindBy(id = "revenueInspector")
    private WebElement revenueInspectorBox;

    @FindBy(css = "input[id='advertisement.type2'][type='radio']")
    private WebElement structureTypeRadioButton;

    @FindBy(id = "propertyType")
    private WebElement propertyTypeBox;



    public AdvertisementsPage(WebDriver driver){
       this.driver = driver;
    }

    public void enterAdvertisementDetails() {
        waitForElementToBeVisible(categoryBox,driver);
        new Select(categoryBox).selectByVisibleText("Hoardings");

        waitForElementToBeClickable(subCategoryBox,driver);
        new Select(subCategoryBox).selectByVisibleText("Foot over Bridges");

        waitForElementToBeClickable(classBox,driver);
        new Select(classBox).selectByVisibleText("A");

        waitForElementToBeClickable(revenueInspectorBox,driver);
        new Select(revenueInspectorBox).selectByVisibleText("N Rajesh");

        waitForElementToBeClickable(structureTypeRadioButton,driver);
        structureTypeRadioButton.click();

        waitForElementToBeClickable(propertyTypeBox,driver);
        new Select(propertyTypeBox).selectByVisibleText("GOVERNMENT");
    }
}
