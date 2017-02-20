package pages.financial;

import entities.financial.FinancialBankDetails;
import entities.ptis.ApprovalDetails;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

public class FinancialPage extends BasePage {

    private WebDriver webDriver;

    @FindBy(id = "fundId")
    private WebElement fundId;

    @FindBy(id = "totalcramount")
    private WebElement totalCreditAmount;

    @FindBy(id = "approverDepartment")
    private WebElement approverDepartment;

    @FindBy(id = "approverDesignation")
    private WebElement approverDesignation;

    @FindBy(id = "approverPositionId")
    private WebElement approverPosition;

    @FindBy(id = "Forward")
    private WebElement forwardButton;

    @FindBy(css = "'div[class~='bootbox-alert'] button[class^='btn']'")
    private WebElement okButton;

    @FindBy(id = "button2")
    private WebElement closeButton;

    @FindBy(id = "Approve")
    private WebElement approveButton;

    @FindBy(id = "searchBtn")
    private WebElement billSearch;

    @FindBy(id = "search")
    private WebElement remittanceBillSearch;

    @FindBy(className = "actionMessage")
    private WebElement forwardMessage;

    @FindBy(id = "voucherTypeBean.partyName")
    private WebElement voucherPartyName;

    @FindBy(css = "input[type='text'][id='voucherDate']")
    private WebElement voucherDate;

    @FindBy(id = "Create And Approve")
    private WebElement createAndApprove;

    @FindBy(name = "contingentList[0].isSelected")
    private WebElement firstVoucher;

    @FindBy(name = "contingentList[1].isSelected")
    private WebElement secondVoucher;

    @FindBy(id = "generatePayment")
    private WebElement generatePayment;

    @FindBy(linkText = "Bill Details")
    private WebElement billDetails;

    @FindBy(linkText = "Payment Details")
    private WebElement paymentDetails;

    @FindBy(name = "billList[0].billVoucherId")
    private WebElement voucherNumberToVerify;

    @FindBy(id = "bankbranch")
    private WebElement bankBranch;

    @FindBy(id = "bankaccount")
    private WebElement bankAccount;

    @FindBy(id = "bank")
    private WebElement bankBranch1;

    @FindBy(id = "billSubType")
    private WebElement expenseBillSubType;

    @FindBy(id = "subLedgerType")
    private WebElement subLedgerType;

    @FindBy(css = ".panel-title.text-center")
    private WebElement expenseCreatedMessage;

    @FindBy(id = "glCode")
    private WebElement glCode;

    @FindBy(id = "Search")
    private WebElement modifyAndSearch;

    @FindBy(id = "chartOfAccounts_accountDetailTypeList")
    private WebElement accountDetailType;

    @FindBy(id = "recoveryId")
    private WebElement recoveryId;

    @FindBy(id = "genPayment")
    private WebElement remittancePayment;

    @FindBy(css = "input[type='text'][id='vouchernumber']")
    private WebElement BPVNumber;

    @FindBy(name = "voucherNumber")
    private WebElement voucherNumberSearch;

    @FindBy(id = "bank_branch")
    private WebElement bankBranch2;

    @FindBy(id = "isSelected0")
    private WebElement firstBill;

    @FindBy(id = "expSelectAll")
    private WebElement selectAllBillsFromExpense;

    @FindBy(name = "chequeAssignmentList[0].chequeNumber")
    private WebElement checkAssignmentNumberBox;

    @FindBy(id = "assignChequeBtn")
    private WebElement assignChequeButton;

    @FindBy(css = ".form-control.datepicker")
    private WebElement rtgsDate;

    @FindBy(id = "departmentid")
    private WebElement remittanceAssignmentDepartment;

    @FindBy(css = "input[type='text'][name='chequeNo']")
    private WebElement remittanceChequeAssignmentNumber;

    @FindBy(name = "chequeDt")
    private WebElement remittanceChequeDate;

    @FindBy(name = "inFavourOf")
    private WebElement remittanceFavour;

    @FindBy(id = "closeButton")
    private WebElement bankCloseButton;

    @FindBy(id = "cbilltab")
    private WebElement expenseBillTable;

    @FindBy(id = "voucherTypeBean.billNum")
    private WebElement billNumberFromPreviewScreen;

    @FindBy(css = "input[type='button'][value='Close']")
    private WebElement closeButtonWithCSS;

    @FindBy(css = ".buttonsubmit")
    private WebElement submitButton;

    @FindBy(id = "Create And Approve")
    private WebElement createAndApproveButton;

    @FindBy(css = ".btn.btn-primary")
    private WebElement okButton1;

    @FindBy(id = "selectAll")
    private WebElement selectAllBillsFromRemittanceRecovery;

    @FindBy(id = "selectall")
    private WebElement selectAllBillsfromAssignmentPage;

    private List<WebElement> voucherRows;

    private String userName = "";

    public FinancialPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String enterFinanceApprovalDetails(ApprovalDetails approvalDetails) throws ParseException {

        maximizeBrowserWindow(webDriver);

        waitForElementToBeClickable(approverDepartment ,webDriver);
        new Select(approverDepartment).selectByVisibleText(approvalDetails.getApproverDepartment());

        waitForElementToBeClickable(approverDesignation ,webDriver);
        new Select(approverDesignation).selectByVisibleText(approvalDetails.getApproverDesignation());

        waitForElementToBeClickable(approverPosition  ,webDriver);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
                e.printStackTrace();
        }

        Select approverPos = new Select(approverPosition);
        userName = approverPos.getOptions().get(1).getText().split("\\ ")[0];
        approverPos.getOptions().get(1).click();

        clickOnForwardButton();

        isAlertOpened();
        return userName;
    }

    private void clickOnCloseButton(){
        waitForElementToBeClickable(closeButton ,webDriver);
        closeButton.click();
    }

    public void approvalPage(){
        waitForElementToBeClickable(approveButton , webDriver);
        approveButton.click();
    }

    public String closePage(){
        switchToNewlyOpenedWindow(webDriver);

        String message = getForwardMessage();

        List<WebElement> closeElements = webDriver.findElements(By.className("button"));
        waitForElementToBeClickable(closeElements.get(1) ,webDriver);
        closeElements.get(1).click();

        switchToPreviouslyOpenedWindow(webDriver);
        return message;
    }

    public String verifyVoucher(){

        waitForElementToBeClickable(billDetails ,webDriver);
        billDetails.click();

        waitForElementToBeClickable(voucherNumberToVerify , webDriver);
        String voucherNumber = voucherNumberToVerify.getText();

        waitForElementToBeClickable(paymentDetails ,webDriver);
        paymentDetails.click();

        return voucherNumber;
    }

    public void billPayment(FinancialBankDetails financialBankDetails){

        maximizeBrowserWindow(webDriver);
        enterBankBranchDetails(financialBankDetails);
        enterBankAccountDetails(financialBankDetails);
    }

    private void enterBankBranchDetails(FinancialBankDetails financialBankDetails){
        waitForElementToBeClickable(bankBranch ,webDriver);
        bankBranch.click();
        new Select(bankBranch).selectByVisibleText(financialBankDetails.getBankName());
    }

    private void enterBankBranchDetails1(FinancialBankDetails financialBankDetails){
        waitForElementToBeClickable(bankBranch1 ,webDriver);
        bankBranch1.click();
        new Select(bankBranch1).selectByVisibleText(financialBankDetails.getBankName());
    }

    private void enterBankAccountDetails(FinancialBankDetails financialBankDetails){
        waitForElementToBeClickable(bankAccount , webDriver);
        bankAccount.click();
        new Select(bankAccount).selectByVisibleText(financialBankDetails.getAccountNumber());
    }

    public void billRemittancePayment(FinancialBankDetails financialBankDetails){
        maximizeBrowserWindow(webDriver);
        enterBankBranchDetails1(financialBankDetails);
        enterBankAccountDetails(financialBankDetails);
    }

    private void clickOnForwardButton(){
        waitForElementToBeClickable(forwardButton ,webDriver);
        forwardButton.click();
    }

    public String closesTheExpensePage(){
        waitForElementToBeVisible(expenseCreatedMessage ,webDriver);
        String message = expenseCreatedMessage.getText();

        clickOnCloseButton();

        switchToPreviouslyOpenedWindow(webDriver);

        return message;
    }

    public String closesSuccessfulPaymentPage(){
        switchToNewlyOpenedWindow(webDriver);

        WebElement element = webDriver.findElement(By.xpath(".//*[@id='payment']/div[1]/table/tbody/tr/td/div/table/tbody/tr/td/div/div[1]/span/table/tbody/tr[5]/td[3]"));
        waitForElementToBeVisible(element ,webDriver);
        String billNumber = element.getText();

        clickOnCloseButton();

        switchToPreviouslyOpenedWindow(webDriver);
        return billNumber;
    }

    public void enterAccountCodeToModify(String code){
        waitForElementToBeClickable(glCode , webDriver);
        enterText(glCode, code);

        waitForElementToBeClickable(modifyAndSearch , webDriver);
        modifyAndSearch.click();

        switchToNewlyOpenedWindow(webDriver);
    }

    public void toModifyTheGLCodeAccount(){

        waitForElementToBeClickable(accountDetailType ,webDriver);
        List<WebElement> element = accountDetailType.findElements(By.tagName("option"));
        if(!element.get(5).isSelected()){
            element.get(5).click();
        }

        clickOnSubmitButton();

        switchToNewlyOpenedWindow(webDriver);

        waitForElementToBeClickable(closeButtonWithCSS , webDriver);
        closeButtonWithCSS.click();

        switchToPreviouslyOpenedWindow(webDriver);
    }

    private void clickOnSubmitButton(){
        waitForElementToBeClickable(submitButton , webDriver);
        submitButton.click();
    }

    public void searchRemittanceBill(){
        waitForElementToBeClickable(recoveryId , webDriver);
        new Select(recoveryId).selectByVisibleText("3502002-GPF –Employees on Deputation");

        waitForElementToBeClickable(fundId ,webDriver);
        new Select(fundId).selectByVisibleText("Municipal Fund");

        waitForElementToBeClickable(remittanceBillSearch , webDriver);
        remittanceBillSearch.click();
    }

    private String getForwardMessage(){
        waitForElementToBeVisible(forwardMessage , webDriver);
        return forwardMessage.getText();
    }

    public void chequeAssignmentBillSearch(String number){

        if(!number.contains("-CASH")) {
            waitForElementToBeClickable(voucherNumberSearch, webDriver);
            voucherNumberSearch.sendKeys(number);
        }
        else {
            waitForElementToBeClickable(voucherNumberSearch, webDriver);
            voucherNumberSearch.sendKeys(number.split("\\-")[0]);
        }

        enterBankDetailsWithFund();
        clickOnBillSearch();

        switchToNewlyOpenedWindow(webDriver);
    }

    private void enterBankDetailsWithFund(){

        waitForElementToBeClickable(fundId ,webDriver);
        new Select(fundId).selectByVisibleText("Municipal Fund");

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);

        waitForElementToBeClickable(bankBranch2 ,webDriver);
        bankBranch2.click();
        new Select(bankBranch2).selectByVisibleText("ANDHRA BANK Andhra Bank RTC Busstand");

        waitForElementToBeClickable(bankAccount ,webDriver);
        bankAccount.click();
        new Select(bankAccount).selectByVisibleText("4502110--110710100009664--ANDHRA BANK");
    }

    private void clickOnBillSearch(){
        waitForElementToBeClickable(billSearch, webDriver);
        billSearch.click();
    }

    public void chequeAssignmentBillSearch(){

        enterBankDetailsWithFund();
        clickOnBillSearch();
        switchToNewlyOpenedWindow(webDriver);
    }

    public void toFillChequeAssignmentDetails(String assignment){

        switch (assignment){

            case "cheque" :

                waitForElementToBeClickable(firstBill, webDriver);
                firstBill.click();

                waitForElementToBeClickable(checkAssignmentNumberBox, webDriver);
                checkAssignmentNumberBox.sendKeys(get6DigitRandomInt());

                break;

            case "RTGS" :

                waitForElementToBeClickable(firstBill, webDriver);
                firstBill.click();

                waitForElementToBeClickable(rtgsDate , webDriver);
                rtgsDate.sendKeys(getCurrentDate() , Keys.TAB);

                break;

            case "remittance" :

                waitForElementToBeClickable(firstBill, webDriver);
                firstBill.click();

                waitForElementToBeVisible(remittanceAssignmentDepartment ,webDriver);
                new Select(remittanceAssignmentDepartment).selectByVisibleText("ACCOUNTS");

                waitForElementToBeClickable(remittanceChequeAssignmentNumber , webDriver);
                remittanceChequeAssignmentNumber.sendKeys(get6DigitRandomInt());

                waitForElementToBeClickable(remittanceChequeDate , webDriver);
                remittanceChequeDate.sendKeys(getCurrentDate() , Keys.TAB);

                waitForElementToBeClickable(remittanceFavour , webDriver);
                remittanceFavour.sendKeys("Testing");

                break;
        }

        waitForElementToBeClickable(assignChequeButton, webDriver);
        assignChequeButton.click();
    }

    public String closeAssignmentSuccessPage(){
        String message = getForwardMessage();

        clickOnSubmitButton();
        switchToPreviouslyOpenedWindow(webDriver);
        return message;
    }

    private void clickOnBankCloseButton(){
        waitForElementToBeClickable(bankCloseButton, webDriver);
        bankCloseButton.click();
    }

    public String closesSuccessfulTransferCreationPage(){

        String message = getForwardMessage();

        clickOnBankCloseButton();
        switchToPreviouslyOpenedWindow(webDriver);
        return message;
    }

    public void clickOnCreateAndApprove(){
        waitForElementToBeClickable(createAndApproveButton , webDriver);
        createAndApproveButton.click();

        // This method is required on bank details page if there is any less balance
        // in bank it will open the alert and accepts it and it is not required for other screens
        isAlertOpened();

        switchToNewlyOpenedWindow(webDriver);
    }

    private void isAlertOpened(){
        try
        {
            webDriver.switchTo().alert().accept();
        }
        catch (NoAlertPresentException Ex)
        {
            System.out.println(" ");
        }
    }

    public String createAndApproveSuccessPage() {

        switchToNewlyOpenedWindow(webDriver);

        WebDriverWait webDriverWait = new WebDriverWait(webDriver,10);

        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[class~='bootbox-alert'] div[class^='bootbox-body']")));
        WebElement voucherNumber = webDriver.findElement(By.cssSelector("div[class~='bootbox-alert'] div[class^='bootbox-body']"));
        String message = voucherNumber.getText();

        waitForElementToBeClickable(okButton1 , webDriver);
        okButton1.click();

        clickOnCloseButton();

        switchToPreviouslyOpenedWindow(webDriver);
        return message;
    }

    public void selectMultipleRemittanceBill() {

        if(webDriver.findElement(By.id("listRemitBean[0].chkremit")).isDisplayed()) {
            waitForElementToBeClickable(selectAllBillsFromRemittanceRecovery, webDriver);
            selectAllBillsFromRemittanceRecovery.click();
        }
        else {
            throw new RuntimeException("No Remittance Recovery Bills found");
        }

        clickOnRemittancePaymentButton();
        switchToNewlyOpenedWindow(webDriver);
    }

    private void clickOnRemittancePaymentButton(){
        waitForElementToBeClickable(remittancePayment ,webDriver);
        remittancePayment.click();
    }

    public void toFillMultipleChequeAssignmentDetails(String assignmentMode) {

        switch (assignmentMode){

            case "cheque" :

                if(firstBill.isDisplayed()){
                    waitForElementToBeClickable(selectAllBillsfromAssignmentPage  ,webDriver);
                    selectAllBillsfromAssignmentPage.click();
                }
                else {
                    throw new RuntimeException("No Bills are found");
                }
                WebElement paymentTable = webDriver.findElement(By.id("paymentTable"));
                List<WebElement> chequeRows = paymentTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));

                for (int i = 0 ; i < (chequeRows.size() -1) ; i++){
                    WebElement element = webDriver.findElement(By.name("chequeAssignmentList["+i+"].chequeNumber"));
                    waitForElementToBeClickable(element ,webDriver);
                    element.sendKeys(get6DigitRandomInt());
                }

                break;

            case "RTGS" :

                if(firstBill.isDisplayed()){
                    waitForElementToBeClickable(selectAllBillsfromAssignmentPage  ,webDriver);
                    selectAllBillsfromAssignmentPage.click();
                }
                else {
                    throw new RuntimeException("No Bills are found");
                }

                waitForElementToBeClickable(rtgsDate , webDriver);
                rtgsDate.sendKeys(getCurrentDate() , Keys.TAB);

                break;

            case "remittance" :

                if(firstBill.isDisplayed()){
                    waitForElementToBeClickable(selectAllBillsfromAssignmentPage  ,webDriver);
                    selectAllBillsfromAssignmentPage.click();
                }
                else {
                    throw new RuntimeException("No Bills are found");
                }

                waitForElementToBeVisible(remittanceAssignmentDepartment , webDriver);
                new Select(remittanceAssignmentDepartment).selectByVisibleText("ACCOUNTS");

                waitForElementToBeClickable(remittanceChequeAssignmentNumber , webDriver);
                remittanceChequeAssignmentNumber.sendKeys(get6DigitRandomInt());

                waitForElementToBeClickable(remittanceChequeDate , webDriver);
                remittanceChequeDate.sendKeys(getCurrentDate() , Keys.TAB);

                waitForElementToBeClickable(remittanceFavour , webDriver);
                remittanceFavour.sendKeys("Testing");

                break;
        }

        waitForElementToBeClickable(assignChequeButton, webDriver);
        assignChequeButton.click();
    }
}
