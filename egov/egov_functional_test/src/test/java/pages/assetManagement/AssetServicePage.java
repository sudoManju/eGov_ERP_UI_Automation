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

    // Land Category Details
    @FindBy(css = "input[name='Land Register Number']")
    private WebElement landRegisterNumberTextBox;

    @FindBy(css = "select[name='OSR Land']")
    private WebElement osrLandSelectBox;

    @FindBy(css = "select[name='Is it Fenced']")
    private WebElement isItFencedSelectBox;

    @FindBy(css = "select[name='Land Type']")
    private WebElement landTypeSelectBox;

    @FindBy(css = "select[name='Unit of Measurement']")
    private WebElement unitOfMeasurementSelectBox;

    @FindBy(css = "[name='Government order number']")
    private WebElement governmentOrderNumberTextBox;

    @FindBy(css = "[name='Collector Order Number']")
    private WebElement collectorOrderNumberTextBox;

    @FindBy(css = "[name='Council Resolution Number']")
    private WebElement councilResolutionNumberTextBox;

    @FindBy(css = "[name='Award Number']")
    private WebElement awardNubmerTextBox;

    // Category Market Details
    @FindBy(css = "[name='Market Name']")
    private WebElement marketNameTextBox;

    // Category Kalyana Mandapam Details
    @FindBy(css = "[name='Kalyana Mandapam Name']")
    private WebElement kalyanaMandapamNameTextBox;

    // Category Parking Space Details
    @FindBy(css = "[name='Parking space Name']")
    private WebElement parkingSpaceNameTextBox;

    // Category Slaughter details
    @FindBy(css = "[name='Slaughter House Name']")
    private WebElement slaughterHouseNameTextBox;

    // Category Usufruct Details
    @FindBy(css = "[name='Usufruct Name']")
    private WebElement ussfructNameTextBox;

    // Category Fish Tank Details
    @FindBy(css = "[name='Fish Tank Name']")
    private WebElement fishTankNameTextBox;

    // Category Park Details
    @FindBy(css = "[name='Park Name']")
    private WebElement parkNameTextBox;

    // Category Community
    @FindBy(css = "[name='Community toilet complex Name']")
    private WebElement communityToiletComplexNameTextBox;

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
//        enterText(webDriver.findElement(By.cssSelector("input[type='text'][name='Shop Details']")), "Testing", webDriver);
    }

    public void enterAssetStatusDetails(String assetStatus) {
        selectFromDropDown(statusSelectBox, assetStatus, webDriver);
        clickOnButton(createAssetButton, webDriver);
        switchToNewlyOpenedWindow(webDriver);
    }

    public void enterCategoryDetails(String categoryDetails) {
        switch (categoryDetails) {

            case "land":

                enterText(landRegisterNumberTextBox, "LReg_"+get6DigitRandomInt(), webDriver);
                selectFromDropDown(osrLandSelectBox, "Yes", webDriver);
                selectFromDropDown(isItFencedSelectBox, "Yes", webDriver);
                selectFromDropDown(landTypeSelectBox, "Hold", webDriver);
                selectFromDropDown(unitOfMeasurementSelectBox, "sq. ft.", webDriver);
                enterText(governmentOrderNumberTextBox, "GOV_"+get6DigitRandomInt(), webDriver);
                enterText(collectorOrderNumberTextBox, "CO_"+get6DigitRandomInt(), webDriver);
                enterText(councilResolutionNumberTextBox, "CRO_"+get6DigitRandomInt(), webDriver);
                enterText(awardNubmerTextBox, "A_"+get6DigitRandomInt(), webDriver);
                break;

            case "market":
                enterText(marketNameTextBox, "abcd", webDriver);
                break;

            case "kalyanaMandapam":
                enterText(kalyanaMandapamNameTextBox, "abcd", webDriver);
                break;

            case "parkingSpace":
                enterText(parkingSpaceNameTextBox, "abcd", webDriver);
                break;

            case "slaughterHouse":
                enterText(slaughterHouseNameTextBox, "abcd", webDriver);
                break;

            case "ussfruct":
                enterText(ussfructNameTextBox, "abcd", webDriver);
                break;

            case "fishTank":
                enterText(fishTankNameTextBox, "abcd", webDriver);
                break;

            case "park":
                enterText(parkNameTextBox, "abcd", webDriver);
                break;

            case "community":
                enterText(communityToiletComplexNameTextBox, "abcd", webDriver);
                break;

        }
    }
}
