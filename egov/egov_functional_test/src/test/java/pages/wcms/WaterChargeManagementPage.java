package pages.wcms;

import entities.ptis.*;
import entities.wcms.EnclosedDocument;
import entities.wcms.FieldInspectionDetails;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @FindBy(linkText = "Close")
    private WebElement closeSearchApplication;

    @FindBy(id = "approvalNumber")
    private WebElement sanctionNumber;

    @FindBy(id = "Approve")
    private WebElement commissionerApprove;

    @FindBy(id = "Sign")
    private WebElement digitalSignature;

    @FindBy(id = "Generate WorkOrder")
    private WebElement generateWorkOrder;

    @FindBy(id = "Execute Tap")
    private WebElement executeTap;

    @FindBy(id = "Generate Acknowledgement")
    private WebElement generateAcknowledgement;

    @FindBy(id = "monthlyFee")
    private WebElement monthlyFees;

    @FindBy(id = "existingConnection.donationCharges")
    private WebElement donationCharges;

    @FindBy(id = "Create")
    private WebElement createDataEntryScreen;

    @FindBy(id = "consumerCodeData")
    private WebElement hscNumber;

    @FindBy(id = "executionDate")
    private WebElement dataEntryExecutionDate;

    @FindBy(name = "fromDate")
    private WebElement searchApplicationDate;

    @FindBy(id = "applicationNumber")
    private WebElement additionalApplicationNumber;

    @FindBy(linkText = "Close")
    private WebElement additionalCloseButton;

    @FindBy(id = "app-appcodo")
    private WebElement consumerNumberTextBox;

    @FindBy(id = "submitButtonId")
    private WebElement consumerSearchButton;


    public WaterChargeManagementPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void enterWaterConectionAssessmentNumber(String number){
        waitForElementToBeClickable(waterConnectionAssesmentNumberTextBox, webDriver);
        enterText(waterConnectionAssesmentNumberTextBox, number);
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
        browse1Button.sendKeys(System.getProperty("user.dir") + "/src/test/resources/PTISTestData.xlsx");

        waitForElementToBeClickable(browse2Button, webDriver);
        browse2Button.sendKeys(System.getProperty("user.dir") + "/src/test/resources/PTISTestData.xlsx");

        waitForElementToBeClickable(browse3Button, webDriver);
        browse3Button.sendKeys(System.getProperty("user.dir") + "/src/test/resources/PTISTestData.xlsx");

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

        waitForElementToBeClickable(fieldInspectionSubmitButton, webDriver);
        fieldInspectionSubmitButton.click();

        switchToNewlyOpenedWindow(webDriver);
    }

    public void clickOnGenerateNotice(){

        waitForElementToBeClickable(generateEstimationNoticeButton , webDriver);
        generateEstimationNoticeButton.click();
        switchToNewlyOpenedWindow(webDriver);
        webDriver.close();
        switchToPreviouslyOpenedWindow(webDriver);
    }

    public void searchWaterConnectionApplications(String connectionType){
        waitForElementToBeClickable(searchApplicationService , webDriver);
        new Select(searchApplicationService).selectByVisibleText("Water Tax");
        waitForElementToBeClickable(searchApplicationType , webDriver);
        new Select(searchApplicationType).selectByVisibleText(connectionType.replaceAll("_"," "));

        waitForElementToBeClickable(searchApplicationDate , webDriver);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        searchApplicationDate.sendKeys(sdf.format(new Date()) , Keys.TAB);

        waitForElementToBeClickable(searchApplicationButton , webDriver);
        searchApplicationButton.click();
    }

    public String clickOnCollectCharges(){
        WebElement applicationNumber = webDriver.findElement(By.xpath(".//*[@id='page-container']/div[1]/div[2]/div[2]/div[4]"));
        waitForElementToBeVisible(applicationNumber , webDriver);
        String number = applicationNumber.getText();
        waitForElementToBeClickable(collectFeesButton, webDriver);
        collectFeesButton.click();
        switchToNewlyOpenedWindow(webDriver);
        return number;
    }

    public void toReceiveAmount(){
        waitForElementToBeClickable(totalAmount , webDriver);
        waitForElementToBeClickable(amountToBePaidTextBox , webDriver);
        String amount = totalAmount.getAttribute("value");

        amountToBePaidTextBox.sendKeys(amount.split("\\.")[0]);

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement element = webDriver.findElement(By.id("button2"));
        jsClick(element , webDriver);
//        JavascriptExecutor executor = (JavascriptExecutor)webDriver;
//        executor.executeScript("arguments[0].click();", element);
        switchToNewlyOpenedWindow(webDriver);
    }

    public void closeReceipt(){
        waitForElementToBeClickable(closeReceiptButton , webDriver);
        closeReceiptButton.click();
    }
    public void closeSearchApplicationPage(){

        for (String winHandle : webDriver.getWindowHandles()) {
            String title = webDriver.switchTo().window(winHandle).getCurrentUrl();
            if(title.equals("http://kurnool-uat.egovernments.org/wtms/elastic/appSearch/")){
                break;
            }
        }
        waitForElementToBeClickable(closeSearchApplication , webDriver);
        closeSearchApplication.click();

        switchToPreviouslyOpenedWindow(webDriver);
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

    private WebElement getApplicationRow(String consumerNumber){
        waitForElementToBeVisible(webDriver.findElement(By.id("worklist")), webDriver);
        waitForElementToBeVisible(officialInboxTable, webDriver);

        await().atMost(10, SECONDS).until(() -> officialInboxTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size() > 1);
        List<WebElement> applicationRows = officialInboxTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        for (WebElement applicationRow : applicationRows) {
            if (applicationRow.findElements(By.tagName("td")).get(4).getText().contains(consumerNumber))
                return applicationRow;
        }
        throw new RuntimeException("No application row found for -- " + consumerNumber);
    }

    public void forward() {
        forwardButton.click();
    }

    public void closePage(){
        closeSearchApplication.click();
        switchToPreviouslyOpenedWindow(webDriver);
    }

    public void commissionerApprove(){

        waitForElementToBeClickable(sanctionNumber , webDriver);
        sanctionNumber.sendKeys("12345");
        waitForElementToBeClickable(commissionerApprove , webDriver);
        commissionerApprove.click();

        switchToNewlyOpenedWindow(webDriver);
        closePage();
    }

    public void commissionerSignature(){
        waitForElementToBeClickable(digitalSignature , webDriver);
        digitalSignature.click();

        switchToNewlyOpenedWindow(webDriver);
        closePage();
    }

    public void generateWorkOrder(){

        waitForElementToBeClickable(generateWorkOrder , webDriver);
        generateWorkOrder.click();

        switchToNewlyOpenedWindow(webDriver);
        webDriver.close();
        switchToPreviouslyOpenedWindow(webDriver);
    }

    public void executeTap(){

        waitForElementToBeClickable(executeTap , webDriver);
        executeTap.click();

        switchToNewlyOpenedWindow(webDriver);
        closePage();
    }

    public void commissionerClosureApprove(){

        waitForElementToBeClickable(commissionerApprove , webDriver);
        commissionerApprove.click();

        switchToNewlyOpenedWindow(webDriver);
        closePage();
    }

    public void toGenerateAcknowledgement(){

        waitForElementToBeClickable(generateAcknowledgement , webDriver);
        generateAcknowledgement.click();

        switchToNewlyOpenedWindow(webDriver);
        webDriver.close();

        switchToPreviouslyOpenedWindow(webDriver);
    }

    public void changeOfUseWaterConnectionInfo(ConnectionInfo connectionInfo){
        new Select(propertyTypeSelectBox).selectByVisibleText(connectionInfo.getPropertyType());
        new Select(hscPipeSizeSelectBox).selectByVisibleText(connectionInfo.getHscPipeSize());
        enterText(sumpCapacityTextBox, connectionInfo.getSumpCapacity());
        enterText(noOfPersonsTextBox, connectionInfo.getNoOfPersons());
        new Select(usageTypeSelectBox).selectByVisibleText(connectionInfo.getUsageType());
        enterText(reasonForNewConnection, connectionInfo.getReasonForAdditionalConnection());
    }

    public void enterWaterDataEntryDetails(ApplicantInfo applicantInfo){
        WebElement connectionType2 = webDriver.findElement(By.id("applicationType2"));
        connectionType2.click();
        waitForElementToBeClickable(waterConnectionAssesmentNumberTextBox, webDriver);
        enterText(waterConnectionAssesmentNumberTextBox, applicantInfo.getPtAssessmentNumber());
        enterText(hscNumber , applicantInfo.getHscNumber());
        enterText(dataEntryExecutionDate , applicantInfo.getConnectionDate());
    }

    public void estimationFeeDetails(){

        enterText(monthlyFees ,"1000");
        enterText(donationCharges , "100");

        createDataEntryScreen.click();
        switchToNewlyOpenedWindow(webDriver);
    }

    public String closesTheDataEntryPage(){

        WebElement successMessage = webDriver.findElement(By.cssSelector(".main-content>table>tbody>tr>td>strong"));

        webDriver.close();
        switchToPreviouslyOpenedWindow(webDriver);

        return successMessage.getText();
    }

    public String findAdditionalApplicationNumber(){
        waitForElementToBeClickable(additionalApplicationNumber, webDriver);
        String number = additionalApplicationNumber.getText();
        waitForElementToBeClickable(additionalCloseButton, webDriver);
        additionalCloseButton.click();

        switchToPreviouslyOpenedWindow(webDriver);

        return number;
    }


    public void enterConsumerNumber(String consumerNumber){

        waitForElementToBeClickable(consumerNumberTextBox, webDriver);
        enterText(consumerNumberTextBox, consumerNumber);

        waitForElementToBeClickable(consumerSearchButton, webDriver);
        consumerSearchButton.click();
        switchToNewlyOpenedWindow(webDriver);
    }
}
