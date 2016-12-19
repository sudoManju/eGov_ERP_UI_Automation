package pages.wcms;

import entities.ptis.*;
import entities.wcms.EnclosedDocument;
import entities.wcms.FieldInspectionDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.util.List;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by vinaykumar on 9/12/16.
 */
public class WaterChargeManagementPage extends BasePage {

    private WebDriver webDriver;

    @FindBy(id = "propertyIdentifier")
    private WebElement waterConnectionAssesmentNumberTextBox;

    @FindBy(id = "waterSource")
    private WebElement waterSourceTypeSelectBox;

    @FindBy(id = "connectionType")
    private WebElement connectionTypeSelectBox;

    @FindBy(id = "propertyType")
    private WebElement propertyTypeSelectBox;

    @FindBy(id = "connectionCategorie")
    private WebElement categorySelectBox;

    @FindBy(id = "usageType")
    private WebElement usageTypeSelectBox;

    @FindBy(id = "pipeSize")
    private WebElement hscPipeSizeSelectBox;

    @FindBy(id = "sumpCapacity")
    private WebElement sumpCapacityTextBox;

    @FindBy(id = "numberOfPerson")
    private WebElement noOfPersonsTextBox;

    @FindBy(id = "applicationDocs0documentNumber")
    private WebElement documentNo1TextBox;

    @FindBy(id = "applicationDocs0documentDate")
    private WebElement documentDate1TextBox;

    @FindBy(id = "applicationDocs1documentNumber")
    private  WebElement documentNo2TextBox;

    @FindBy(id ="applicationDocs1documentDate")
    private WebElement documentDate2TextBox;

    @FindBy(id ="applicationDocs3documentNumber")
    private WebElement documentNo3TextBox;

    @FindBy(id = "applicationDocs3documentDate")
    private WebElement documentDate3TextBox;

    @FindBy(id = "file0id")
    private WebElement browse1Button;

    @FindBy(id = "file1id")
    private WebElement browse2Button;

    @FindBy(id = "file3id")
    private WebElement browse3Button;

    @FindBy(id = "approvalDepartment")
    private WebElement approvalWaterDepartment;

    @FindBy(id = "approvalDesignation")
    private WebElement approvalWaterDesignation;

    @FindBy(id = "approvalPosition")
    private WebElement approvalWaterPosition;

    @FindBy(id = "approvalComent")
    private WebElement approvalWaterComment;

    @FindBy(id = "Forward")
    private WebElement additionalForwardButton;

    @FindBy(id = "connectionReason")
    private WebElement reasonForNewConnection;

    @FindBy(id = "estimationDetails0itemDescription")
    private WebElement fieldInspectionMaterial;

    @FindBy(id = "estimationDetails0quantity")
    private WebElement fieldInspectionQuantity;

    @FindBy(id = "estimationDetails0unitOfMeasurement")
    private WebElement fieldInspectionMeasureUnit;

    @FindBy(id = "estimationDetails0unitRate")
    private WebElement fieldInspectionRate;

    @FindBy(id = "existingPipeline")
    private WebElement fieldInspectionExistingPipeline;

    @FindBy(id = "pipelineDistance")
    private WebElement fieldInspectionPipelineDistance;

    @FindBy(id = "estimationCharges")
    private WebElement fieldInspectionEstimationCharges;

    @FindBy(id = "Submit")
    private WebElement fieldInspectionSubmitButton;

    @FindBy(id = "Generate Estimation Notice")
    private WebElement generateEstimationNoticeButton;

    @FindBy(id = "moduleName")
    private WebElement searchApplicationService;

    @FindBy(id = "applicationType")
    private WebElement searchApplicationType;

    @FindBy(id = "searchapplication")
    private WebElement searchApplicationButton;

    @FindBy(id = "payBtn")
    private WebElement collectFeesButton;

    @FindBy(id = "totalamounttobepaid")
    private WebElement totalAmount;

    @FindBy(id = "instrHeaderCash.instrumentAmount")
    private WebElement amountToBePaidTextBox;

    @FindBy(id = "button2")
    private WebElement payButton;

    @FindBy(id = "buttonClose")
    private WebElement closeReceiptButton;

    @FindBy(id = "temporary")
    private WebElement temporaryRadioButton;

    @FindBy(id = "permanent")
    private WebElement permanentRadioButton;

    @FindBy(id = "closeconnectionreason")
    private WebElement closureConnectionReason;

    @FindBy(id = "official_inbox")
    private WebElement officialInboxTable;

    @FindBy(id = "Forward")
    private WebElement forwardButton;

    public WaterChargeManagementPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void enterWaterConectionAssessmentNumber(ApplicantInfo applicantInfo){
        waitForElementToBeClickable(waterConnectionAssesmentNumberTextBox, webDriver);
        enterText(waterConnectionAssesmentNumberTextBox, applicantInfo.getPtAssessmentNumber());
    }

    public void enterNewWaterConnectionInfo(ConnectionInfo connectionInfo){

        waitForElementToBeClickable(waterSourceTypeSelectBox, webDriver);
        new Select(waterSourceTypeSelectBox).selectByVisibleText(connectionInfo.getWaterSourceType());

        new Select(connectionTypeSelectBox).selectByVisibleText(connectionInfo.getConnectionType());

        new Select(propertyTypeSelectBox).selectByVisibleText(connectionInfo.getPropertyType());

        new Select(categorySelectBox).selectByVisibleText(connectionInfo.getCategory());

        new Select(usageTypeSelectBox).selectByVisibleText(connectionInfo.getUsageType());

        new Select(hscPipeSizeSelectBox).selectByVisibleText(connectionInfo.getHscPipeSize());

        enterText(sumpCapacityTextBox, connectionInfo.getSumpCapacity());

        enterText(noOfPersonsTextBox, connectionInfo.getNoOfPersons());

    }

    public void enterDocumentInfo(EnclosedDocument enclosedDocument){

        waitForElementToBeClickable(documentNo1TextBox, webDriver);
        enterText(documentNo1TextBox, enclosedDocument.getDocumentN01());

        waitForElementToBeClickable(documentNo2TextBox, webDriver);
        enterText(documentNo2TextBox, enclosedDocument.getDocumentN02());

        waitForElementToBeClickable(documentNo3TextBox, webDriver);
        enterText(documentNo3TextBox, enclosedDocument.getDocumentN03());

        waitForElementToBeClickable(documentDate1TextBox, webDriver);
        enterText(documentDate1TextBox, enclosedDocument.getDocumentDate1());

        waitForElementToBeClickable(documentDate2TextBox, webDriver);
        enterText(documentDate2TextBox, enclosedDocument.getDocumentDate2());

        waitForElementToBeClickable(documentDate3TextBox, webDriver);
        enterText(documentDate3TextBox, enclosedDocument.getDocumentDate3());

        waitForElementToBeClickable(browse1Button, webDriver);
        browse1Button.sendKeys("/home/vinaykumar/State Bank of India.pdf");

        waitForElementToBeClickable(browse2Button, webDriver);
        browse2Button.sendKeys("/home/vinaykumar/State Bank of India.pdf");

        waitForElementToBeClickable(browse3Button, webDriver);
        browse3Button.sendKeys("/home/vinaykumar/State Bank of India.pdf");

    }

    public void enterWaterApprovalDetails(ApprovalDetails approvalDetails){

        waitForElementToBeClickable(approvalWaterDepartment, webDriver);
        new Select(approvalWaterDepartment).selectByVisibleText(approvalDetails.getApproverDepartment());

        waitForElementToBeClickable(approvalWaterDesignation, webDriver);
        new Select(approvalWaterDesignation).selectByVisibleText(approvalDetails.getApproverDesignation());

        waitForElementToBeClickable(approvalWaterPosition, webDriver);
        new Select(approvalWaterPosition).selectByVisibleText(approvalDetails.getApprover());

        waitForElementToBeClickable(additionalForwardButton, webDriver);
        additionalForwardButton.click();
        switchToNewlyOpenedWindow(webDriver);

    }

    public void enterAdditionalWaterConnectionInfo(ConnectionInfo connectionInfo){

        waitForElementToBeClickable(waterSourceTypeSelectBox, webDriver);
        new Select(waterSourceTypeSelectBox).selectByVisibleText(connectionInfo.getWaterSourceType());

        new Select(connectionTypeSelectBox).selectByVisibleText(connectionInfo.getConnectionType());

        new Select(propertyTypeSelectBox).selectByVisibleText(connectionInfo.getPropertyType());

        new Select(categorySelectBox).selectByVisibleText(connectionInfo.getCategory());

        new Select(usageTypeSelectBox).selectByVisibleText(connectionInfo.getUsageType());

        new Select(hscPipeSizeSelectBox).selectByVisibleText(connectionInfo.getHscPipeSize());

        enterText(sumpCapacityTextBox, connectionInfo.getSumpCapacity());

        enterText(noOfPersonsTextBox, connectionInfo.getNoOfPersons());
        enterText(reasonForNewConnection, connectionInfo.getReasonForAdditionalConnection());
    }

    public void enterFieldInspectionInfo(FieldInspectionDetails fieldInspectionDetails){
        waitForElementToBeClickable(fieldInspectionMaterial, webDriver);
        enterText(fieldInspectionMaterial , fieldInspectionDetails.getMaterial());

        waitForElementToBeClickable(fieldInspectionQuantity, webDriver);
        enterText(fieldInspectionQuantity , fieldInspectionDetails.getQuantity());

        waitForElementToBeClickable(fieldInspectionMeasureUnit, webDriver);
        enterText(fieldInspectionMeasureUnit , fieldInspectionDetails.getUnitOfMeasurement());

        waitForElementToBeClickable(fieldInspectionRate, webDriver);
        enterText(fieldInspectionRate , fieldInspectionDetails.getRate());

        waitForElementToBeClickable(fieldInspectionExistingPipeline, webDriver);
        enterText(fieldInspectionExistingPipeline , fieldInspectionDetails.getExistingDistributionPipeline());

        waitForElementToBeClickable(fieldInspectionPipelineDistance, webDriver);
        enterText(fieldInspectionPipelineDistance , fieldInspectionDetails.getPipelineToHomeDistance());

        waitForElementToBeClickable(fieldInspectionEstimationCharges, webDriver);
        enterText(fieldInspectionEstimationCharges , fieldInspectionDetails.getEstimationCharges());

        waitForElementToBeClickable(fieldInspectionSubmitButton, webDriver);
        fieldInspectionSubmitButton.click();

        switchToNewlyOpenedWindow(webDriver);
    }

    public void clickOnGenerateNotice(){

        waitForElementToBeClickable(generateEstimationNoticeButton , webDriver);
        generateEstimationNoticeButton.click();
        switchToNewlyOpenedWindow(webDriver);
        webDriver.close();
        await().atMost(5, SECONDS).until(() -> webDriver.getWindowHandles().size() == 1);
        for (String winHandle : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(winHandle);
        }
    }

    public void searchWaterConnectionApplications(String connectionType){
        waitForElementToBeClickable(searchApplicationService , webDriver);
        new Select(searchApplicationService).selectByVisibleText("Water Tax");
        waitForElementToBeClickable(searchApplicationType , webDriver);
        new Select(searchApplicationType).selectByVisibleText(connectionType.replaceAll("_"," "));
        waitForElementToBeClickable(searchApplicationButton , webDriver);
        searchApplicationButton.click();

    }

    public void clickOnCollectCharges(){

        waitForElementToBeClickable(collectFeesButton, webDriver);
        collectFeesButton.click();
        switchToNewlyOpenedWindow(webDriver);

    }

    public void toReceiveAmount(){
        waitForElementToBeClickable(totalAmount , webDriver);
        waitForElementToBeClickable(amountToBePaidTextBox , webDriver);
        String amount = totalAmount.getText();
        System.out.println("=========================" +amount);;
        amountToBePaidTextBox = totalAmount;
        amountToBePaidTextBox.sendKeys(amountToBePaidTextBox.getText());


        waitForElementToBeClickable(payButton , webDriver);
        payButton.click();
        switchToNewlyOpenedWindow(webDriver);
    }

    public void closeReceipt(){
        waitForElementToBeClickable(closeReceiptButton , webDriver);
        closeReceiptButton.click();
        await().atMost(5, SECONDS).until(() -> webDriver.getWindowHandles().size() == 1);
        for (String winHandle : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(winHandle);
        }
    }

    public void enterDetailsOfClosureConnection(String closureType){

        if(closureType.equalsIgnoreCase("Temporary")){
            waitForElementToBeClickable(temporaryRadioButton , webDriver);
            jsClick(temporaryRadioButton ,webDriver);
        }
        else {
            waitForElementToBeClickable(permanentRadioButton , webDriver);
            jsClick(permanentRadioButton ,webDriver);
        }
        waitForElementToBeClickable(closureConnectionReason , webDriver);
        closureConnectionReason.sendKeys("Not Required");
    }

    public void selectApplication(String consumerNumber ){
        getApplicationRow(consumerNumber).click();
        switchToNewlyOpenedWindow(webDriver);
    }

    public WebElement getApplicationRow(String consumerNumber){
        waitForElementToBeVisible(webDriver.findElement(By.id("worklist")), webDriver);
        waitForElementToBeVisible(officialInboxTable, webDriver);

        await().atMost(10, SECONDS).until(() -> officialInboxTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size() > 1);
        List<WebElement> applicationRows = officialInboxTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        System.out.println("total number of rows -- " + applicationRows.size());
        for (WebElement applicationRow : applicationRows) {
            if (applicationRow.findElements(By.tagName("td")).get(4).getText().contains(consumerNumber))
                return applicationRow;
        }
        throw new RuntimeException("No application row found for -- " + consumerNumber);
    }

    public void forward() {
        forwardButton.click();
    }
}
