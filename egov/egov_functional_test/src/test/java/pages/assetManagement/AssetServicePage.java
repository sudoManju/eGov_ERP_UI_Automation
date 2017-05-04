package pages.assetManagement;

import entities.assetManagement.assetService.HeaderDetails;
import entities.assetManagement.assetService.LocationDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class AssetServicePage extends BasePage {

    // Header Details Locators
    @FindBy(id = "department")
    private WebElement departmentSelectBox;

    @FindBy(id = "assetCategory")
    private WebElement assetCategorySelectBox;

    @FindBy(id = "dateOfCreation")
    private WebElement dateOfCreationTextBox;

    @FindBy(id = "description")
    private WebElement descriptionTextBox;

    @FindBy(id = "name")
    private WebElement assetNameTextBox;

    @FindBy(id = "modeOfAcquisition")
    private WebElement modeOfAcquisitionSelectBox;

    // Location Details Locators
    @FindBy(id = "locality")
    private WebElement localitySelectBox;

    @FindBy(id = "revenueWard")
    private WebElement revenueWardSelectBox;

    @FindBy(id = "block")
    private WebElement blockNumberSelectBox;

    @FindBy(id = "street")
    private WebElement streetSelectBox;

    @FindBy(id = "electionWard")
    private WebElement electionWardNumberSelectBox;

    @FindBy(id = "doorNo")
    private WebElement doorNoTextBox;

    @FindBy(id = "zone")
    private WebElement zoneNumberSelectBox;

    @FindBy(id = "pinCode")
    private WebElement pinCodeTextBox;

    // Asset Status Details Locators
    @FindBy(id = "status")
    private WebElement statusSelectBox;

    @FindBy(css = "button[type='submit']")
    private WebElement createAssetButton;

    private WebDriver webDriver;

    public AssetServicePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void enterHeaderDetails(HeaderDetails headerDetails) {
        selectFromDropDown(departmentSelectBox, headerDetails.getDepartment(), webDriver);
        selectFromDropDown(assetCategorySelectBox, headerDetails.getAssetCategory(), webDriver);
        enterText(assetNameTextBox, "Tester", webDriver);
        selectFromDropDown(modeOfAcquisitionSelectBox, headerDetails.getModeOfAcquisition(), webDriver);
    }

    public void enterLocationDetails(LocationDetails locationDetails) {
        selectFromDropDown(localitySelectBox, locationDetails.getLocality(), webDriver);
//        enterText(webDriver.findElement(By.cssSelector("input[id='description']")), "Description", webDriver);
        enterText(webDriver.findElement(By.cssSelector("input[type='text'][name='Shop Details']")),"Testing",webDriver);
    }

    public void enterAssetStatusDetails(String assetStatus) {
        selectFromDropDown(statusSelectBox, assetStatus, webDriver);
        clickOnButton(createAssetButton, webDriver);
        switchToNewlyOpenedWindow(webDriver);
    }
}
