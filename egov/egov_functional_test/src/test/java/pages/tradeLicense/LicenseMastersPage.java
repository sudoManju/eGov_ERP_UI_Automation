package pages.tradeLicense;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

/**
 * Created by tester1 on 6/13/2017.
 */
public class LicenseMastersPage extends BasePage {
    private WebDriver webDriver;

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "code")
    private WebElement codeField;

    @FindBy(css = "button[class='btn btn-primary'][type='submit']")
    private WebElement saveButton;

    @FindBy(linkText = "Close")
    private WebElement closeButton;

    @FindBy(id = "category")
    private WebElement categorySelect;

    @FindBy(id = "licenseSubCategoryDetails[0].feeType")
    private WebElement feeTypeDropDown;

    @FindBy(id = "licenseSubCategoryDetails[0].rateType")
    private WebElement rateTypeDropDown;

    @FindBy(id = "licenseSubCategoryDetails[0].uom")
    private WebElement UOMDropDown;

    @FindBy(id = "licenseAppType")
    private WebElement licenseAppType;

    @FindBy(id = "natureOfBusiness")
    private WebElement natureOfBusiness;

    @FindBy(id = "licenseCategory")
    private WebElement licenseCategorySelect;

    @FindBy(id = "select2-subCategory-container")
    private WebElement licenseSubCategorySelect;

    @FindBy(id = "feeType")
    private WebElement licenseFeeType;

    @FindBy(css = "input[class='select2-search__field']")
    private WebElement searchBox;

    @FindBy(id = "select2-subCategory-container")
    private WebElement tradeSubCategoryDropBox;

    @FindBy(id = "financialYear")
    private WebElement financialYear;

    public LicenseMastersPage(WebDriver webDriver){this.webDriver =webDriver;}

    public void masterNameCode(String name, String code) {
        enterText(nameField,name,webDriver);
        enterText(codeField,code,webDriver);
    }

    public String createLicenseMaster() {
        clickOnButton(saveButton,webDriver);
        return webDriver.findElement(By.cssSelector(".panel-title")).getText();
    }

    public void closeViewPage() {
        clickOnButton(closeButton,webDriver);
        switchToPreviouslyOpenedWindow(webDriver);
    }

    public void selectCategory(String licenseCategory) {
        selectFromDropDown(categorySelect,licenseCategory,webDriver);
    }

    public void enterDetails(String feeType, String rateType, String uom) {
        selectFromDropDown(feeTypeDropDown, feeType,webDriver);
        selectFromDropDown(rateTypeDropDown,rateType,webDriver);
        selectFromDropDown(UOMDropDown, uom, webDriver);
    }

    public void UOMActive() {
        clickOnButton(webDriver.findElement(By.id("active")),webDriver);
    }

    public void closeSubCategoryViewPage() {
        clickOnButton(webDriver.findElement(By.xpath(".//*[@id='page-content']/div[2]/div/button")),webDriver);
        switchToPreviouslyOpenedWindow(webDriver);
    }

    public void enterFeeMatrixDetails(String category, String licenseSubCategory) {
        selectFromDropDown(licenseAppType,"New",webDriver);
        selectFromDropDown(natureOfBusiness, "Permanent",webDriver);
        selectFromDropDown(licenseCategorySelect, category,webDriver);

        try {
            clickOnButton(tradeSubCategoryDropBox, webDriver);
        } catch (StaleElementReferenceException e) {
            WebElement element = webDriver.findElement(By.id("select2-subCategory-container"));
            clickOnButton(element, webDriver);
        }
        waitForElementToBeVisible(searchBox, webDriver);
        searchBox.sendKeys(licenseSubCategory);
        WebElement element = webDriver.findElement(By.xpath(".//*[@id='select2-subCategory-results']/li[1]"));
        clickOnButton(element, webDriver);

        if (tradeSubCategoryDropBox.getText().isEmpty()) {
            WebElement element2 = webDriver.findElement(By.id("select2-subCategory-container"));
            clickOnButton(element2, webDriver);
            waitForElementToBeVisible(searchBox, webDriver);
            searchBox.sendKeys(licenseSubCategory);
            WebElement element1 = webDriver.findElement(By.xpath(".//*[@id='select2-subCategory-results']/li[1]"));
            clickOnButton(element1, webDriver);
        }
        selectFromDropDown(licenseFeeType,"License Fee",webDriver);
        selectFromDropDown(financialYear,"2017-18",webDriver);
    }
}
