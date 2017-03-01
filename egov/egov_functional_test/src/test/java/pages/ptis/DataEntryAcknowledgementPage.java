package pages.ptis;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

public class DataEntryAcknowledgementPage extends BasePage {

    private WebDriver driver;

    @FindBy(css = "input[value='Close'][type='button']")
    private WebElement closeButton;

    @FindBy(linkText = "Close")
    private WebElement closeLink;

    @FindBy(xpath = ".//*[@id='createProperty-createDataEntry']/div/table/tbody/tr[1]/td")
    private WebElement dataEntryAcknowledgement;

    @FindBy(id = "editDCB")
    private WebElement editDCBButton;

    @FindBy(name = "demandDetailBeanList[0].installment.id")
    private WebElement installmentDropBox;

    @FindBy(name = "demandDetailBeanList[0].actualAmount")
    private WebElement generalTaxTextBox;

    @FindBy(name = "demandDetailBeanList[1].actualAmount")
    private WebElement libraryCessTextBox;

    @FindBy(name = "demandDetailBeanList[2].actualAmount")
    private WebElement educationCessTextBox;

    @FindBy(name = "demandDetailBeanList[3].actualAmount")
    private WebElement unauthorizedPenaltyTextBox;

    @FindBy(id = "remarks")
    private WebElement remarksTextArea;

    @FindBy(id = "addDemand-update_Update")
    private WebElement updateButton;

    public DataEntryAcknowledgementPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getdataentryAcknowledgementMessage() {
        return driver.findElement(By.xpath("//table/tbody/tr/td")).getText();
    }

    public String getAssessmentNumber() {
        waitForElementToBeVisible(dataEntryAcknowledgement, driver);
        String genericAssessmentNumber = dataEntryAcknowledgement.getText().replaceAll("[^0-9]", "");
        return genericAssessmentNumber;
    }

    public void close() {
        clickOnButton(closeButton, driver);
        await().atMost(5, SECONDS).until(() -> driver.getWindowHandles().size() == 1);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    public void geteditDCB() {
        jsClick(editDCBButton, driver);
    }

    public void enterAddDemandDetails() {
        selectFromDropDown(installmentDropBox, "2016-2017-2", driver);
        enterText(generalTaxTextBox, "300", driver);
        enterText(libraryCessTextBox, "200", driver);
        enterText(educationCessTextBox, "100", driver);
        enterText(unauthorizedPenaltyTextBox, "100", driver);
        enterText(remarksTextArea, "Added 2016-17-2 Demand", driver);
        clickOnButton(updateButton, driver);
    }
}
