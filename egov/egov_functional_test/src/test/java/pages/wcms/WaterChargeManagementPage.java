package pages.wcms;

import entities.ptis.*;
import entities.wcms.EnclosedDocument;
import entities.wcms.FieldInspectionDetails;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

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

    @FindBy(css = "input[type='submit'][id='button2']")
    private WebElement button2;

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

    @FindBy(id = "Generate Reconnection Ack")
    private WebElement generateReConnectionAcknowledgement;

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

    @FindBy(name = "applicationNumber")
    private WebElement applicationSearchBox;

    @FindBy(linkText = "Close")
    private WebElement additionalCloseButton;

    @FindBy(id = "app-appcodo")
    private WebElement consumerNumberTextBox;

    @FindBy(id = "submitButtonId")
    private WebElement consumerSearchButton;

    @FindBy(id = "reConnectionReason")
    private WebElement reConnectionReason;

    @FindBy(id = "editDCB")
    private WebElement addEditDCB;

    @FindBy(id = "actualAmount")
    private WebElement dcbActualAmount;

    @FindBy(id = "actualCollection")
    private WebElement dcbActualCollection;

    @FindBy(id = "submitButtonId")
    private WebElement dcbSubmit;

    @FindBy(id = "chequeradiobutton")
    private WebElement chequeModeOfPaymentRadio;

    @FindBy(id = "instrumentChequeNumber")
    private WebElement chequeNumberTextBox;

    @FindBy(id = "instrumentDate")
    private WebElement chequeDateTextBox;

    @FindBy(id = "bankName")
    private WebElement bankNameInput;

    @FindBy(css = "input[id='instrumentChequeAmount'][type='text']")
    private WebElement payAmountBoxForCheque;

    @FindBy(id = "ddradiobutton")
    private WebElement ddModeofPaymentRadio;

    @FindBy(name = "consumerCode")
    private WebElement onlineConsumerCode;

    @FindBy(id = "cashradiobutton")
    private WebElement cashRadio;

    @FindBy(id = "searchapprvedapplication")
    private WebElement onlineSearchApplication;

    @FindBy(css = ".btn.btn-xs.btn-secondary.collect-hoardingWiseFee")
    private WebElement onlinePayButton;

    @FindBy(className = "justbold")
    private List<WebElement> totalOnlineAmount;

    @FindBy(id = "paymentAmount")
    private WebElement totalOnlineAmountToBePaid;

    @FindBy(name = "radioButton1")
    private WebElement axisBankRadio;

    @FindBy(id = "checkbox")
    private WebElement termsAndConditionsCheckBox;

    @FindBy(xpath = "html/body/center/table[6]/tbody/tr[3]/td/table/tbody/tr/td[3]/a/img")
    private WebElement masterCardImage;

    @FindBy(id = "CardNumber")
    private WebElement cardNumber;

    @FindBy(id = "CardMonth")
    private WebElement cardMonth;

    @FindBy(id = "CardYear")
    private WebElement cardYear;

    @FindBy(id = "Securecode")
    private WebElement cvvNumber;

    @FindBy(id = "Paybutton")
    private WebElement onlineCardPaymentButton;

    @FindBy(id = "paymentInfo")
    private WebElement onlinePaymentSuccessMessage;

    @FindBy(id = "btnGenerateReceipt")
    private WebElement onlineGenerateReceipt;

    @FindBy(id = "consumerCode")
    private WebElement reConnectionConsumerCode;

    @FindBy(id = "aplicationSearchResults")
    private WebElement applicationSearchTable;

    @FindBy(css = ".panel-title.text-center")
    private WebElement forwardMessage;

    private WebElement appRow1;

    private String message = null;

    SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");

    public WaterChargeManagementPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void enterWaterConnectionAssessmentNumber(String number){
        waitForElementToBeClickable(waterConnectionAssesmentNumberTextBox, webDriver);
        enterText(waterConnectionAssesmentNumberTextBox, number);
    }

    public void enterConnectionInfo(ConnectionInfo connectionInfo){

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
        enterText(documentDate1TextBox, currentDate.format(new Date()));

        waitForElementToBeClickable(documentDate2TextBox, webDriver);
        enterText(documentDate2TextBox, currentDate.format(new Date()));

        waitForElementToBeClickable(documentDate3TextBox, webDriver);
        enterText(documentDate3TextBox, currentDate.format(new Date()));

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
        waitForElementToBeVisible(generateEstimationNoticeButton , webDriver);
        waitForElementToBeClickable(generateEstimationNoticeButton , webDriver);
        jsClick(generateEstimationNoticeButton , webDriver);
        switchToNewlyOpenedWindow(webDriver);
        webDriver.close();
        switchToPreviouslyOpenedWindow(webDriver);
    }

    public void searchWaterConnectionApplications(String connectionType , String applicationNumber){

        waitForElementToBeClickable(searchApplicationService , webDriver);
        new Select(searchApplicationService).selectByVisibleText("Water Tax");
        waitForElementToBeClickable(searchApplicationType , webDriver);
        new Select(searchApplicationType).selectByVisibleText(connectionType.replaceAll("_"," "));

        waitForElementToBeClickable(applicationSearchBox , webDriver);
        applicationSearchBox.sendKeys(applicationNumber);

        waitForElementToBeClickable(searchApplicationDate , webDriver);
        searchApplicationDate.sendKeys(currentDate.format(new Date()) , Keys.TAB);

        waitForElementToBeClickable(searchApplicationButton , webDriver);
        searchApplicationButton.click();
    }

    public void clickOnCollectCharges(){
        waitForElementToBeClickable(collectFeesButton, webDriver);
        collectFeesButton.click();
        switchToNewlyOpenedWindow(webDriver);
    }

    public void toReceiveAmount(){
        waitForElementToBeClickable(cashRadio , webDriver);
        jsClick(cashRadio ,webDriver);
        String amount = totalAmount.getAttribute("value");

        amountToBePaidTextBox.sendKeys(amount.split("\\.")[0]);

        waitForElementToBeClickable(button2 , webDriver);
        jsClick(button2, webDriver);
        switchToNewlyOpenedWindow(webDriver);
    }

    public void closeSuccessfulPaymentReceiptPage(){
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

    public String enterDetailsOfClosureConnection(String closureType){

        WebElement acknowledgementNumber = webDriver.findElement(By.id("applicationNumber"));
        String number = acknowledgementNumber.getText();

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
        return number;
    }

    public void openApplicationFromInbox(String consumerNumber ){
        getApplicationRow(consumerNumber).click();
        switchToNewlyOpenedWindow(webDriver);
    }

    private WebElement getApplicationRow(String applicationNumber){
        waitForElementToBeVisible(webDriver.findElement(By.id("worklist")), webDriver);
        waitForElementToBeVisible(officialInboxTable, webDriver);

        await().atMost(20, SECONDS).until(() -> officialInboxTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size() > 1);
        List<WebElement> applicationRows = officialInboxTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        for (WebElement applicationRow : applicationRows) {
            if (applicationRow.findElements(By.tagName("td")).get(4).getText().contains(applicationNumber))
                return applicationRow;
        }
        throw new RuntimeException("No application row found for -- " + applicationNumber);
    }

    public void forward() {
        waitForElementToBeClickable(forwardButton , webDriver);
        forwardButton.click();
        switchToNewlyOpenedWindow(webDriver);
    }

    public String getAcknowledgementMessage(){
        waitForElementToBeVisible(forwardMessage , webDriver);
        message = forwardMessage.getText();
        return message;
    }

    public void closeAcknowledgementPage(){
        jsClick(closeSearchApplication , webDriver);
        switchToPreviouslyOpenedWindow(webDriver);
    }

    public void commissionerApprove(){

        waitForElementToBeClickable(sanctionNumber , webDriver);
        sanctionNumber.sendKeys("12345");
        waitForElementToBeClickable(commissionerApprove , webDriver);
        jsClick(commissionerApprove ,webDriver);

        switchToNewlyOpenedWindow(webDriver);
        closeAcknowledgementPage();
    }

    public void commissionerDigitalSignature(){
        waitForElementToBeClickable(digitalSignature , webDriver);
        digitalSignature.click();

        switchToNewlyOpenedWindow(webDriver);
        closeAcknowledgementPage();
    }

    public void generateWorkOrder(){

        waitForElementToBeClickable(generateWorkOrder , webDriver);
        jsClick(generateWorkOrder , webDriver);

        switchToNewlyOpenedWindow(webDriver);
        webDriver.close();
        switchToPreviouslyOpenedWindow(webDriver);
    }

    public String executeTap(){

        WebElement applicationNumber = webDriver.findElement(By.xpath(".//*[@id='page-container']/div[1]/div[2]/div[2]/div[4]"));
        waitForElementToBeVisible(applicationNumber , webDriver);
        String number = applicationNumber.getText();

        waitForElementToBeClickable(executeTap , webDriver);
        executeTap.click();

        switchToNewlyOpenedWindow(webDriver);
        closeAcknowledgementPage();
        return number;
    }

    public void commissionerClosureApprove(){

        waitForElementToBeClickable(commissionerApprove , webDriver);
        commissionerApprove.click();

        switchToNewlyOpenedWindow(webDriver);
        closeAcknowledgementPage();
    }

    public void toGenerateAcknowledgement(){

        waitForElementToBeClickable(generateAcknowledgement , webDriver);
        generateAcknowledgement.click();

        switchToNewlyOpenedWindow(webDriver);
        webDriver.close();

        switchToPreviouslyOpenedWindow(webDriver);
    }

    public void changeOfUseConnectionInfo(ConnectionInfo connectionInfo){
        new Select(propertyTypeSelectBox).selectByVisibleText(connectionInfo.getPropertyType());
        for(int i = 0 ; i <= 10 ; i++) {
            if (!webDriver.findElement(By.id("pipeSize")).getText().equalsIgnoreCase(connectionInfo.getHscPipeSize())){
                try {
            waitForElementToBeVisible(hscPipeSizeSelectBox , webDriver);
            new Select(hscPipeSizeSelectBox).selectByVisibleText(connectionInfo.getHscPipeSize());
                }catch (StaleElementReferenceException e){
            WebElement element = webDriver.findElement(By.id("pipeSize"));
            waitForElementToBeVisible(element , webDriver);
            new Select(element).selectByVisibleText(connectionInfo.getHscPipeSize());
                }
            }
        }
        enterText(sumpCapacityTextBox, connectionInfo.getSumpCapacity());
        enterText(noOfPersonsTextBox, connectionInfo.getNoOfPersons());
        new Select(usageTypeSelectBox).selectByVisibleText(connectionInfo.getUsageType());
        enterText(reasonForNewConnection, connectionInfo.getReasonForAdditionalConnection());
    }

    public void enterWaterDataEntryDetails(ApplicantInfo applicantInfo , String assessmentNumber){
//        WebElement connectionType2 = webDriver.findElement(By.id("applicationType2"));
//        connectionType2.click();
        waitForElementToBeClickable(waterConnectionAssesmentNumberTextBox, webDriver);
        enterText(waterConnectionAssesmentNumberTextBox, assessmentNumber);
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
        String message = successMessage.getText();

        webDriver.close();
        switchToPreviouslyOpenedWindow(webDriver);

        return message;
    }

    public String findAdditionalApplicationNumber(){
        waitForElementToBeClickable(additionalApplicationNumber, webDriver);
        String number = additionalApplicationNumber.getText();

        waitForElementToBeClickable(additionalCloseButton, webDriver);
        jsClick(additionalCloseButton, webDriver);

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

    public String enterReConnectionDetails(){
        WebElement acknowledgementNumber = webDriver.findElement(By.id("applicationNumber"));
        String number = acknowledgementNumber.getText();
        waitForElementToBeClickable(reConnectionReason , webDriver);
        reConnectionReason.sendKeys("Required Again");
        return number;
    }

    public void toGenerateReConnectionAcknowledgement(){

        waitForElementToBeClickable(generateReConnectionAcknowledgement , webDriver);
        generateReConnectionAcknowledgement.click();

        switchToNewlyOpenedWindow(webDriver);
        webDriver.close();

        switchToPreviouslyOpenedWindow(webDriver);
    }

    public void clickOnAddEditDCB(){
        waitForElementToBeClickable(addEditDCB , webDriver);
        addEditDCB.click();
        switchToNewlyOpenedWindow(webDriver);
    }

    public void enterDetailsOfDCB(){

        waitForElementToBeClickable(dcbActualAmount , webDriver);
        enterText(dcbActualAmount , "100");

        waitForElementToBeClickable(dcbActualCollection , webDriver);
        enterText(dcbActualCollection , "100");

        waitForElementToBeClickable(dcbSubmit , webDriver);
        dcbSubmit.click();
        switchToNewlyOpenedWindow(webDriver);
    }

    public String closesDCBPage(){
        WebElement element = webDriver.findElement(By.xpath("html/body/div[1]/div/table/tbody/tr[1]/td/strong"));
        waitForElementToBeVisible(element , webDriver);
        message = element.getText();

        waitForElementToBeClickable(button2, webDriver);
        button2.click();
        switchToPreviouslyOpenedWindow(webDriver);
        return message;
    }

    public void collectWaterCharges(){

        waitForElementToBeClickable(collectFeesButton , webDriver);
        collectFeesButton.click();

        switchToNewlyOpenedWindow(webDriver);
    }

    public void paymentWithMode(String mode){

        waitForElementToBeVisible(totalAmount, webDriver);
        String amount = totalAmount.getAttribute("value");
        String actualAmount = amount.split("\\.")[0];

        switch (mode){

            case "cash" :

                waitForElementToBeClickable(cashRadio , webDriver);
                jsClick(cashRadio ,webDriver);

                waitForElementToBeClickable(amountToBePaidTextBox,webDriver);
                amountToBePaidTextBox.sendKeys(actualAmount);

                break;

            case "cheque" :

                waitForElementToBeClickable(chequeModeOfPaymentRadio,webDriver);
                jsClick(chequeModeOfPaymentRadio,webDriver);

                waitForElementToBeVisible(chequeNumberTextBox,webDriver);
                chequeNumberTextBox.sendKeys("123456");
                waitForElementToBeClickable(chequeDateTextBox,webDriver);
                chequeDateTextBox.sendKeys("02/01/2017");
                waitForElementToBeClickable(bankNameInput,webDriver);
                bankNameInput.sendKeys("102");

                webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                await().atMost(10, SECONDS).until(() -> webDriver.findElement(By.id("bankcodescontainer"))
                        .findElements(By.cssSelector("ul li"))
                        .get(0).click());

                bankNameInput.sendKeys(Keys.TAB);

                waitForElementToBeClickable(payAmountBoxForCheque,webDriver);
                payAmountBoxForCheque.sendKeys(actualAmount);

                break;

            case "dd" :

                waitForElementToBeClickable(ddModeofPaymentRadio,webDriver);
                jsClick(ddModeofPaymentRadio,webDriver);

                waitForElementToBeVisible(chequeNumberTextBox,webDriver);
                chequeNumberTextBox.sendKeys("123456");
                waitForElementToBeClickable(chequeDateTextBox,webDriver);
                chequeDateTextBox.sendKeys("02/01/2017");
                waitForElementToBeClickable(bankNameInput,webDriver);
                bankNameInput.sendKeys("102");

                webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                await().atMost(10, SECONDS).until(() -> webDriver.findElement(By.id("bankcodescontainer"))
                        .findElements(By.cssSelector("ul li"))
                        .get(0).click());

                waitForElementToBeClickable(payAmountBoxForCheque,webDriver);
                payAmountBoxForCheque.sendKeys(actualAmount);

                break;

        }
        jsClick(button2, webDriver);
        switchToNewlyOpenedWindow(webDriver);
    }

    public void onlinePaymentLink(){
        webDriver.navigate().to("http://kurnool-uat.egovernments.org/wtms/search/waterSearch/");
    }

    public void enterOnlineConsumerNumber(String consumerNumber){
        waitForElementToBeClickable(onlineConsumerCode , webDriver);
        onlineConsumerCode.sendKeys(consumerNumber);

        waitForElementToBeClickable(onlineSearchApplication , webDriver);
        onlineSearchApplication.click();
    }

    public void clickOnOnlinePayButton(){

        waitForElementToBeClickable(onlinePayButton , webDriver);
        onlinePayButton.click();
    }

    public void selectBankDetails(){

        waitForElementToBeVisible(totalOnlineAmount.get(1) , webDriver);
        String amount = totalOnlineAmount.get(1).getText();

        waitForElementToBeClickable(totalOnlineAmountToBePaid , webDriver);
        totalOnlineAmountToBePaid.sendKeys(amount.split("\\.")[0]);

        waitForElementToBeClickable(axisBankRadio , webDriver);
        jsClick(axisBankRadio ,webDriver);

        waitForElementToBeClickable(termsAndConditionsCheckBox , webDriver);
        termsAndConditionsCheckBox.click();

        waitForElementToBeClickable(button2, webDriver);
        button2.click();

        waitForElementToBeClickable(masterCardImage , webDriver);
        masterCardImage.click();
    }

    public void enterCardDetails(){

        waitForElementToBeClickable(cardNumber , webDriver);
        cardNumber.sendKeys("5123456789012346");

        waitForElementToBeClickable(cardMonth , webDriver);
        cardMonth.sendKeys("05");

        waitForElementToBeClickable(cardYear ,webDriver);
        cardYear.sendKeys("17");

        waitForElementToBeClickable(cvvNumber , webDriver);
        cvvNumber.sendKeys("123");

        waitForElementToBeClickable(onlineCardPaymentButton , webDriver);
        onlineCardPaymentButton.click();
    }

    public String onlinePaymentSuccess(){

        waitForElementToBeVisible(onlinePaymentSuccessMessage , webDriver);
        message = onlinePaymentSuccessMessage.getText();
        return message;
    }

    public void onlineGenerateReceipt(){
        waitForElementToBeClickable(onlineGenerateReceipt , webDriver);
        onlineGenerateReceipt.click();

        waitForElementToBeClickable(closeReceiptButton , webDriver);
        if(closeReceiptButton.isDisplayed()){
            webDriver.close();
        }
    }

    public void closeCollectChargesReceipt(){
        waitForElementToBeClickable(closeReceiptButton , webDriver);
        closeReceiptButton.click();

        switchToPreviouslyOpenedWindow(webDriver);
    }

    public void openSearchApplication(String applicationNumber) {
        appRow1 = getSearchApplicationRowFor(applicationNumber);
        waitForElementToBeClickable(appRow1 , webDriver);
        jsClick(appRow1 , webDriver);
        switchToNewlyOpenedWindow(webDriver);
    }

    private WebElement getSearchApplicationRowFor(String applicationNumber) {

        List<WebElement> applicationRows = applicationSearchTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));

        for (WebElement applicationRow1 : applicationRows) {
            if (applicationRow1.findElements(By.tagName("td")).get(1).getText().contains(applicationNumber))
                return applicationRow1;
        }

        throw new RuntimeException("No application row found for -- " + applicationNumber);
    }
}
