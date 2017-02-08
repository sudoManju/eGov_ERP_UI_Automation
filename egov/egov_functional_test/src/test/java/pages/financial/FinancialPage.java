package pages.financial;

import entities.financial.FinancialBankDetails;
import entities.financial.FinancialExpenseBillDetails;
import entities.financial.FinancialJournalVoucherDetails;
import entities.ptis.ApprovalDetails;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;


/**
 * Created by vinaykumar on 20/12/16.
 */
public class FinancialPage extends BasePage {

    private WebDriver webDriver;

    @FindBy(id = "vType")
    private WebElement voucherSubType;

    @FindBy(id = "fundId")
    private WebElement fundId;

    @FindBy(id = "vouchermis.departmentid")
    private WebElement voucherDepartment;

    @FindBy(id = "vouchermis.function")
    private WebElement voucherFunction;

    @FindBy(id = "billDetailslist[0].glcodeDetail")
    private WebElement accountCode1;

    @FindBy(id = "billDetailslist[1].glcodeDetail")
    private WebElement accountCode2;

    @FindBy(id = "billDetailslist[2].glcodeDetail")
    private WebElement accountCode3;

    @FindBy(id = "billDetailslist[0].debitAmountDetail")
    private WebElement debitAmount1;

    @FindBy(id = "billDetailslist[1].creditAmountDetail")
    private WebElement creditAmount2;

    @FindBy(id = "billDetailslist[2].creditAmountDetail")
    private WebElement creditAmount3;

    @FindBy(id = "totalcramount")
    private WebElement totalCreditAmount;

    @FindBy(id = "subLedgerlist[0].glcode.id")
    private WebElement ledgerAccount1;

    @FindBy(id = "subLedgerlist[1].glcode.id")
    private WebElement ledgerAccount2;

    @FindBy(id = "subLedgerlist[0].detailType.id")
    private WebElement ledgerType1;

    @FindBy(id = "subLedgerlist[1].detailType.id")
    private WebElement ledgerType2;

    @FindBy(id = "subLedgerlist[0].detailCode")
    private WebElement ledgerCode1;

    @FindBy(id = "subLedgerlist[1].detailCode")
    private WebElement ledgerCode2;

    @FindBy(id = "approverDepartment")
    private WebElement approverDepartment;

    @FindBy(id = "approverDesignation")
    private WebElement approverDesignation;

    @FindBy(id = "approverPositionId")
    private WebElement approverPosition;

    @FindBy(id = "Forward")
    private WebElement forwardButton;

    @FindBy(id = "subLedgerlist[0].amount")
    private WebElement ledgerAmount1;

    @FindBy(id = "subLedgerlist[1].amount")
    private WebElement ledgerAmount2;

    @FindBy(xpath = ".//*[@id='egov_yui_add_image']")
    private List<WebElement> addList;

    @FindBy(css = "'div[class~='bootbox-alert'] button[class^='btn']'")
    private WebElement okButton;

    @FindBy(id = "button2")
    private WebElement closeButton;

    @FindBy(id = "official_inbox")
    private WebElement officialInboxTable;

    @FindBy(className = "yui-dt-data")
    private WebElement remittanceBillTable;

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

    @FindBy(id = "voucherDate")
    private WebElement voucherDate;

    @FindBy(id = "Create And Approve")
    private WebElement createAndApprove;

    @FindBy(linkText = "Expense Bill")
    private WebElement expenseBillSearch;

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

    @FindBy(id = "fromDate")
    private WebElement billFromDate;

    @FindBy(id = "toDate")
    private WebElement billToDate;

    @FindBy(id = "bankbranch")
    private WebElement bankBranch;

    @FindBy(id = "bankaccount")
    private WebElement bankAccount;

    @FindBy(id = "bank")
    private WebElement bankBranch1;

    @FindBy(id = "fund")
    private WebElement expenseFund;

    @FindBy(id = "department")
    private WebElement expenseDepartment;

    @FindBy(id = "function")
    private WebElement expenseFunction;

    @FindBy(id = "billSubType")
    private WebElement expenseBillSubType;

    @FindBy(id = "subLedgerType")
    private WebElement subLedgerType;

    @FindBy(id = "payTo")
    private WebElement expensePayTo;

    @FindBy(id = "tempDebitDetails[0].debitGlcode")
    private WebElement expenseAccountCodeDebit;

    @FindBy(id = "tempCreditDetails[0].creditGlcode")
    private WebElement expenseAccountCodeCredit;

    @FindBy(id = "tempDebitDetails[0].debitamount")
    private WebElement expenseDebitAmount;

    @FindBy(id = "tempCreditDetails[0].creditamount")
    private WebElement expenseCreditAmount;

    @FindBy(id = "netPayableAccountCode")
    private WebElement expenseNetPayable;

    @FindBy(css = "input[id='netPayableAmount'][type='text']")
    private WebElement expenseNetAmount;

    @FindBy(id = "populateAccountDetails")
    private WebElement expensePopulate;

    @FindBy(id = "approvalDepartment")
    private WebElement expenseApprovalDepartment;

    @FindBy(id = "approvalDesignation")
    private WebElement expenseApprovalDesignation;

    @FindBy(id = "approvalPosition")
    private WebElement expenseApprovalPosition;

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

    @FindBy(id = "expType")
    private WebElement billType;

    @FindBy(css = "li[class='dropdown'] a[data-original-title ='Drafts']")
    private WebElement draftsLink;

    @FindBy(id = "official_drafts")
    private WebElement officialDraftsTable;

    @FindBy(id = "paymentModecheque")
    private WebElement paymentModeCheque;

    @FindBy(id = "paymentModecash")
    private WebElement paymentModeCash;

    @FindBy(id = "paymentModertgs")
    private WebElement paymentModeRTGS;

    @FindBy(name = "voucherNumber")
    private WebElement voucherNumberSearch;

    @FindBy(id = "bank_branch")
    private WebElement bankBranch2;

    @FindBy(id = "isSelected0")
    private WebElement chequeAssignmentBill;

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

    @FindBy(id = "bankId")
    private WebElement bankPaymentId;

    @FindBy(id = "accountNumber")
    private WebElement accountNumber;

    @FindBy(id = "amount")
    private WebElement amountTextBox;

    @FindBy(id = "modeOfPaymentcash")
    private WebElement bankPaymentCash;

    @FindBy(id = "modeOfPaymentrtgs")
    private WebElement bankPaymentRTGS;

    @FindBy(id = "commonBean.documentNumber")
    private WebElement documentNumber;

    @FindBy(id = "documentDate")
    private WebElement documentDate;

    @FindBy(id = "paidTo")
    private WebElement paidToCustomer;

    @FindBy(id = "closeButton")
    private WebElement bankCloseButton;

    @FindBy(id = "fromBankId")
    private WebElement fromBankId;

    @FindBy(id = "fromAccountNumber")
    private WebElement fromAccountNumber;

    @FindBy(id = "toFundId")
    private WebElement toFundId;

    @FindBy(id = "toBankId")
    private WebElement toBankId;

    @FindBy(id = "toAccountNumber")
    private WebElement toAccountNumber;

    @FindBy(id = "chequeNum")
    private WebElement referenceNumber;

    @FindBy(id = "Save_New")
    private WebElement saveButton;

    @FindBy(id = "cbilltab")
    private WebElement expenseBillTable;

    @FindBy(className = "yui-ac-highlight")
    private WebElement accountCodeDropdown;

    @FindBy(id = "voucherTypeBean.billNum")
    private WebElement billNumberFromPreviewScreen;

    @FindBy(css = "input[type='button'][value='Close']")
    private WebElement closeButtonWithCSS;

    @FindBy(id = "billNumber")
    private WebElement billNumberTextBox;

    @FindBy(className = "tt-highlight")
    private WebElement expenseBillAccountCodeDropdown;

    @FindBy(css = ".buttonsubmit")
    private WebElement submitButton;

    private List<WebElement> voucherRows;

    private String juneDate = "00";

    public FinancialPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void enterJournalVoucherDetails(FinancialJournalVoucherDetails financialJournalVoucherDetails){

        if(financialJournalVoucherDetails.getDate().split("\\/")[1].equals("06")){
            juneDate = "06";
        }

        voucherDate.clear();
        voucherDate.sendKeys(financialJournalVoucherDetails.getDate() , Keys.TAB);
        new Select(voucherSubType).selectByVisibleText(financialJournalVoucherDetails.getVoucherType());
        if(!financialJournalVoucherDetails.getVoucherType().equals("General")){
            waitForElementToBeClickable(voucherPartyName , webDriver);
            voucherPartyName.sendKeys("voucher");
        }

        new Select(fundId).selectByVisibleText("Municipal Fund");
        new Select(voucherDepartment).selectByVisibleText(financialJournalVoucherDetails.getDepartment());
        new Select(voucherFunction).selectByVisibleText(financialJournalVoucherDetails.getFunction());

        waitForElementToBeVisible(accountCode1, webDriver);
        waitForElementToBeClickable(accountCode1, webDriver);
        accountCode1.sendKeys(financialJournalVoucherDetails.getAccountCode1());

        waitForElementToBeVisible(accountCodeDropdown, webDriver);
        waitForElementToBeClickable(accountCodeDropdown, webDriver);
        accountCodeDropdown.click();
        enterText(debitAmount1 , "100");

        accountCode2.sendKeys(financialJournalVoucherDetails.getAccountCode2());
        accountCodeDropdown.click();
        enterText(creditAmount2 , "100");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        waitForElementToBePresent(By.className("yui-dt-dropdown") , webDriver);
        List<WebElement> webElementList = ledgerAccount1.findElements(By.tagName("option"));

        new Select(ledgerAccount1).selectByVisibleText(webElementList.get(1).getText());
        new Select(ledgerType1).selectByVisibleText("contractor");
        ledgerCode1.sendKeys("KMC001");

        waitForElementToBeClickable(accountCodeDropdown , webDriver);
        accountCodeDropdown.click();
        ledgerAmount1.sendKeys("100");

        if(webElementList.size()>2){
            addList.get(2).click();

            ledgerAccount2.click();
            new Select(ledgerAccount2).selectByVisibleText(webElementList.get(2).getText());
            new Select(ledgerType2).selectByVisibleText("contractor");
            ledgerCode2.sendKeys("KMC001");

            waitForElementToBeClickable(accountCodeDropdown , webDriver);
            accountCodeDropdown.click();
            ledgerAmount2.sendKeys("100");
        }
    }

    public String enterFinanceApprovalDetails(ApprovalDetails approvalDetails) throws ParseException {

        webDriver.manage().window().maximize();
        String userName = "";
        if(juneDate.contains("06")){
            createAndApprove.click();
            juneDate = "00";
        }
        else{
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

        forwardButton.click();
        }

        return userName;
    }

    public String getVoucherNumber(){

        switchToNewlyOpenedWindow(webDriver);

        WebDriverWait webDriverWait = new WebDriverWait(webDriver,10);

        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[class~='bootbox-alert'] div[class^='bootbox-body']")));
        WebElement voucherNumber = webDriver.findElement(By.cssSelector("div[class~='bootbox-alert'] div[class^='bootbox-body']"));
        String number = voucherNumber.getText();

        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[class~='bootbox-alert'] button[class^='btn']")));
        WebElement element = webDriver.findElement(By.cssSelector("div[class~='bootbox-alert'] button[class^='btn']"));
        element.click();

        closeButton.click();

        switchToPreviouslyOpenedWindow(webDriver);

        return number;
    }

    public void openVoucherFromInboxOrDrafts(String voucherNumber){
        WebElement element = getVoucherRow(voucherNumber);
        element.click();
        switchToNewlyOpenedWindow(webDriver);
    }

    private WebElement getVoucherRow(String voucherNumber){

        try{
            waitForElementToBeVisible(officialInboxTable, webDriver);

            await().atMost(20, SECONDS).until(() -> officialInboxTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size() > 1);
            voucherRows = officialInboxTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));

            for (WebElement voucherRow : voucherRows) {
                if (voucherRow.findElements(By.tagName("td")).get(4).getText().contains(voucherNumber))
                    return voucherRow;
            }
            throw new RuntimeException("No voucher row found in Inbox -- " + voucherNumber);
        }
        catch (Exception e){

            waitForElementToBeClickable(draftsLink , webDriver);
            draftsLink.click();

            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            waitForElementToBeVisible(officialDraftsTable, webDriver);
            await().atMost(20, SECONDS).until(() -> officialDraftsTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size() > 1);
            voucherRows = officialDraftsTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));

            for (WebElement voucherRow : voucherRows) {
                if (voucherRow.findElements(By.tagName("td")).get(4).getText().contains(voucherNumber))
                    return voucherRow;
            }
            throw new RuntimeException("No voucher row found in Inbox and Drafts -- " + voucherNumber);
        }
    }

    public void approvalPage(){
        waitForElementToBeClickable(approveButton , webDriver);
        approveButton.click();
    }

    public String closePage(){
        switchToNewlyOpenedWindow(webDriver);

        waitForElementToBeClickable(forwardMessage , webDriver);
        String forwardMessageText = forwardMessage.getText();

        List<WebElement> closeElements = webDriver.findElements(By.className("button"));
        closeElements.get(1).click();

        switchToPreviouslyOpenedWindow(webDriver);
        return forwardMessageText;
    }

    public void billSearch(String date){
        billFromDate.sendKeys(date.replaceAll("_" , "/"));
        billToDate.sendKeys(date.replaceAll("_" , "/"));
        new Select(fundId).selectByVisibleText("Municipal Fund");
        waitForElementToBeClickable(billSearch , webDriver);
        billSearch.click();

        switchToNewlyOpenedWindow(webDriver);
        expenseBillSearch.click();
    }

    public void actOnAboveVoucher(String paymentMode ,String voucherNumber){

        WebElement table = webDriver.findElement(By.xpath(".//*[@id='cbilltab']/span/table/tbody/tr[2]/td/div/table/tbody"));
        voucherRows = table.findElements(By.tagName("tr"));

        for(WebElement applicationRows : voucherRows.subList(1 , voucherRows.size())){
            if(applicationRows.findElements(By.tagName("td")).get(4).findElements(By.tagName("input")).get(0).getAttribute("value").contains(voucherNumber)){
                applicationRows.findElements(By.tagName("td")).get(0).findElement(By.cssSelector("input[type='checkbox']")).click();
                break;
            }

        }
        selectModeOfPayment(paymentMode);

        waitForElementToBeClickable(generatePayment , webDriver);
        generatePayment.click();
        switchToNewlyOpenedWindow(webDriver);
    }

    private void selectModeOfPayment(String mode){
        switch (mode){

            case "cheque" :

                waitForElementToBeClickable(paymentModeCheque , webDriver);
                jsClick(paymentModeCheque ,webDriver);
                break;

            case "cash" :

                waitForElementToBeClickable(paymentModeCash,webDriver);
                jsClick(paymentModeCash,webDriver);
                break;

            case "RTGS" :

                waitForElementToBeClickable(paymentModeRTGS,webDriver);
                jsClick(paymentModeRTGS,webDriver);
                break;
        }
    }

    public String verifyVoucher(){
        billDetails.click();
        waitForElementToBeClickable(voucherNumberToVerify , webDriver);
        String voucherNumber = voucherNumberToVerify.getText();

        paymentDetails.click();

        return voucherNumber;
    }

    public void billPayment(FinancialBankDetails financialBankDetails){
        bankBranch.click();
        new Select(bankBranch).selectByVisibleText(financialBankDetails.getBankName());
        waitForElementToBeClickable(bankAccount , webDriver);
        bankAccount.click();
        new Select(bankAccount).selectByVisibleText(financialBankDetails.getAccountNumber());
    }

    public void billRemittancePayment(FinancialBankDetails financialBankDetails){
        bankBranch1.click();
        new Select(bankBranch1).selectByVisibleText(financialBankDetails.getBankName());
        waitForElementToBeClickable(bankAccount , webDriver);
        bankAccount.click();
        new Select(bankAccount).selectByVisibleText(financialBankDetails.getAccountNumber());
    }

    public void createNewExpenseBill(FinancialExpenseBillDetails financialExpenseBillDetails){
        new Select(expenseFund).selectByVisibleText(financialExpenseBillDetails.getExpenseFund());
        new Select(expenseDepartment).selectByVisibleText(financialExpenseBillDetails.getExpenseDeparment());

        expenseFunction.sendKeys(financialExpenseBillDetails.getExpenseFunction());
        waitForElementToBeVisible( webDriver.findElement(By.className("tt-dropdown-menu")),webDriver);
        WebElement dropdown = webDriver.findElement(By.className("tt-dataset-0"));
        dropdown.click();

        waitForElementToBeClickable(expenseBillSubType , webDriver);
        new Select(expenseBillSubType).selectByVisibleText(financialExpenseBillDetails.getExpenseBillSubType());
//        new Select(subLedgerType).selectByVisibleText("contractor");
        expensePayTo.sendKeys("tester");

        expenseAccountCodeDebit.sendKeys(financialExpenseBillDetails.getExpenseAccountCodeDebit());
        waitForElementToBeVisible( expenseBillAccountCodeDropdown,webDriver);
        expenseBillAccountCodeDropdown.click();
        expenseDebitAmount.sendKeys("100");

        expenseAccountCodeCredit.sendKeys(financialExpenseBillDetails.getExpenseAccountCodeCredit());
        waitForElementToBeVisible( expenseBillAccountCodeDropdown,webDriver);
        expenseBillAccountCodeDropdown.click();
        expenseCreditAmount.sendKeys("90");

        List<WebElement> element1 = expenseNetPayable.findElements(By.tagName("option"));
        waitForElementToBeClickable(element1.get(1), webDriver);
        element1.get(1).click();

        expenseNetAmount.sendKeys("10");
        expensePopulate.click();
    }

    public String enterExpenseApprovalDetails(ApprovalDetails approvalDetails){

        waitForElementToBeClickable(expenseApprovalDepartment ,webDriver);
        new Select(expenseApprovalDepartment).selectByVisibleText(approvalDetails.getApproverDepartment());

        for(int i = 0 ; i <= 10 ; i++) {
            if (!webDriver.findElement(By.id("approvalDesignation")).getText().equalsIgnoreCase(approvalDetails.getApproverDesignation())) {
                try {
                    waitForElementToBeClickable(expenseApprovalDesignation, webDriver);
                    new Select(expenseApprovalDesignation).selectByVisibleText(approvalDetails.getApproverDesignation());
                } catch (StaleElementReferenceException e) {
                    WebElement element = webDriver.findElement(By.id("approvalDesignation"));
                    new Select(element).selectByVisibleText(approvalDetails.getApproverDesignation());
                }
            }
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        waitForElementToBeVisible(expenseApprovalPosition , webDriver);
        Select approverPos = new Select(expenseApprovalPosition);
        String userName = approverPos.getOptions().get(1).getText();
        approverPos.getOptions().get(1).click();

        forwardButton.click();

        switchToNewlyOpenedWindow(webDriver);
        return userName;
    }

    public String closesTheExpensePage(){
        String message = expenseCreatedMessage.getText();

        closeButton.click();
        switchToPreviouslyOpenedWindow(webDriver);

        return message;
    }

    public String closesSuccessfulPaymentPage(){
        switchToNewlyOpenedWindow(webDriver);

        WebElement element = webDriver.findElement(By.xpath(".//*[@id='payment']/div[1]/table/tbody/tr/td/div/table/tbody/tr/td/div/div[1]/span/table/tbody/tr[5]/td[3]"));
        String billNumber = element.getText();
        waitForElementToBeClickable(closeButton , webDriver);
        closeButton.click();

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
        List<WebElement> element = accountDetailType.findElements(By.tagName("option"));
        if(!element.get(5).isSelected()){
            element.get(5).click();
        }

        waitForElementToBeClickable(submitButton , webDriver);
        submitButton.click();
        switchToNewlyOpenedWindow(webDriver);

        waitForElementToBeClickable(closeButtonWithCSS , webDriver);
        closeButtonWithCSS.click();
        switchToPreviouslyOpenedWindow(webDriver);
    }

    public void enterRemittanceVoucherDetails(FinancialJournalVoucherDetails financialJournalVoucherDetails){

        if(financialJournalVoucherDetails.getDate().split("\\/")[1].equals("06")){
            juneDate = "06";
        }

        voucherDate.clear();
        voucherDate.sendKeys(financialJournalVoucherDetails.getDate() , Keys.TAB);
        new Select(voucherSubType).selectByVisibleText(financialJournalVoucherDetails.getVoucherType());
        if(!financialJournalVoucherDetails.getVoucherType().equals("General")){
            waitForElementToBeClickable(voucherPartyName , webDriver);
            voucherPartyName.sendKeys("voucher");
        }

        new Select(fundId).selectByVisibleText("Municipal Fund");
        new Select(voucherDepartment).selectByVisibleText(financialJournalVoucherDetails.getDepartment());
        new Select(voucherFunction).selectByVisibleText(financialJournalVoucherDetails.getFunction());

        waitForElementToBeVisible(accountCode1 ,webDriver);
        waitForElementToBeClickable(accountCode1 , webDriver);
        accountCode1.sendKeys(financialJournalVoucherDetails.getAccountCode1());

        waitForElementToBeClickable(accountCodeDropdown , webDriver);
        accountCodeDropdown.click();
        enterText(debitAmount1 , "1000");

        accountCode2.sendKeys(financialJournalVoucherDetails.getAccountCode2());
        accountCodeDropdown.click();
        enterText(creditAmount2 , "800");

        if(!financialJournalVoucherDetails.getAccountCode3().isEmpty()){
            addList.get(1).click();
            accountCode3.sendKeys(financialJournalVoucherDetails.getAccountCode3());
            accountCodeDropdown.click();
            enterText(creditAmount3 , "200");
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        waitForElementToBePresent(By.className("yui-dt-dropdown") , webDriver);
        List<WebElement> webElementList = ledgerAccount1.findElements(By.tagName("option"));

        new Select(ledgerAccount1).selectByVisibleText(webElementList.get(1).getText());
        new Select(ledgerType1).selectByVisibleText("contractor");
        ledgerCode1.sendKeys("KMC001");

        waitForElementToBeClickable(accountCodeDropdown , webDriver);
        accountCodeDropdown.click();
        ledgerAmount1.sendKeys("1000");

        if(webElementList.size()>2){
            addList.get(3).click();

            ledgerAccount2.click();
            new Select(ledgerAccount2).selectByVisibleText(webElementList.get(2).getText());
            new Select(ledgerType2).selectByVisibleText("Employee");
            ledgerCode2.sendKeys("946800");

            waitForElementToBeClickable(accountCodeDropdown , webDriver);
            accountCodeDropdown.click();
            ledgerAmount2.sendKeys("200");
        }
    }

    public void searchRemittanceBill(){
        new Select(recoveryId).selectByVisibleText("3502002-GPF –Employees on Deputation");
        new Select(fundId).selectByVisibleText("Municipal Fund");
        remittanceBillSearch.click();
    }

    public void selectRemittanceBIll(String remittanceBill){
        int rowNumber = Integer.parseInt(getRemittanceBill(remittanceBill).getText());
        WebElement element = webDriver.findElement(By.id("listRemitBean["+(rowNumber-1)+"].chkremit"));
        element.click();
        remittancePayment.click();
        switchToNewlyOpenedWindow(webDriver);
    }

    private WebElement getRemittanceBill(String applicationNumber) {

        await().atMost(20, SECONDS).until(() -> remittanceBillTable.findElements(By.tagName("tr")).size() > 1);
        List<WebElement> applicationRows = remittanceBillTable.findElements(By.tagName("tr"));
        System.out.println("total number of rows -- " + applicationRows.size());
        for (WebElement applicationRow : applicationRows) {
            if (applicationRow.findElements(By.tagName("td")).get(1).
                            findElement(By.className("yui-dt-liner")).findElement(By.tagName("label")).getText().contains(applicationNumber))
                return applicationRow.findElements(By.tagName("td")).get(0).findElement(By.className("yui-dt-liner"));
        }
        throw new RuntimeException("No application row found for -- " + applicationNumber);
    }

    public void filterCreateVoucherBill(String applicationNumber){
        new Select(billType).selectByVisibleText("Expense");
        billNumberTextBox.sendKeys(applicationNumber);

        waitForElementToBeClickable(submitButton , webDriver);
        submitButton.click();

        getExpenseVoucherRow(applicationNumber).click();
        switchToNewlyOpenedWindow(webDriver);
    }

    private WebElement getExpenseVoucherRow(String applicationNumber){
        WebElement element = webDriver.findElement(By.className("tablebottom"));
        List<WebElement> elements = element.findElements(By.className("setborder"));

        for (WebElement applicationRow : elements) {
            if (applicationRow.findElements(By.tagName("td")).get(1).getText().contains(applicationNumber))
                return applicationRow.findElements(By.tagName("td")).get(1);
        }
        throw new RuntimeException("No application row found for -- " + applicationNumber);
    }

    public String closesExpenseVoucherPage(){

        waitForElementToBeVisible(forwardMessage , webDriver);
        String mesage = forwardMessage.getText();
        closeButton.click();
        switchToPreviouslyOpenedWindow(webDriver);
        return mesage;
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

        new Select(fundId).selectByVisibleText("Municipal Fund");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
        bankBranch2.click();
        new Select(bankBranch2).selectByVisibleText("ANDHRA BANK Andhra Bank RTC Busstand");
        bankAccount.click();
        new Select(bankAccount).selectByVisibleText("4502110--110710100009664--ANDHRA BANK");

        waitForElementToBeClickable(billSearch, webDriver);
        billSearch.click();
        switchToNewlyOpenedWindow(webDriver);
    }

    public void toFillChequeAssignmentDetails(String assignment){

        switch (assignment){

            case "cheque" :

                waitForElementToBeClickable(chequeAssignmentBill, webDriver);
                chequeAssignmentBill.click();

                waitForElementToBeClickable(checkAssignmentNumberBox, webDriver);
                checkAssignmentNumberBox.sendKeys(get6DigitRandomInt());

                break;

            case "RTGS" :

                waitForElementToBeClickable(chequeAssignmentBill, webDriver);
                chequeAssignmentBill.click();

                waitForElementToBeClickable(rtgsDate , webDriver);
//                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//                Calendar calobj = Calendar.getInstance();
//                rtgsDate.sendKeys(df.format(calobj.getTime()));
                rtgsDate.sendKeys(getCurrentDate() , Keys.TAB);

                break;

            case "remittance" :

                waitForElementToBeClickable(chequeAssignmentBill, webDriver);
                chequeAssignmentBill.click();

                new Select(remittanceAssignmentDepartment).selectByVisibleText("ACCOUNTS");

                waitForElementToBeClickable(remittanceChequeAssignmentNumber , webDriver);
                remittanceChequeAssignmentNumber.sendKeys(get6DigitRandomInt());

                waitForElementToBeClickable(remittanceChequeDate , webDriver);
//                DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
//                Calendar calobj1 = Calendar.getInstance();
//                remittanceChequeDate.sendKeys(df1.format(calobj1.getTime()));
                remittanceChequeDate.sendKeys(getCurrentDate() , Keys.TAB);

                waitForElementToBeClickable(remittanceFavour , webDriver);
                remittanceFavour.sendKeys("Testing");

                break;

        }

        waitForElementToBeClickable(assignChequeButton, webDriver);
        assignChequeButton.click();
    }

    public String closeAssignmentSuccessPage(){
        waitForElementToBeVisible(forwardMessage , webDriver);
        String message = forwardMessage.getText();

        webDriver.findElement(By.cssSelector(".buttonsubmit")).click();
        switchToPreviouslyOpenedWindow(webDriver);
        return message;
    }

    public void enterDirectBankPaymentDetails(String mode){

        new Select(fundId).selectByVisibleText("Municipal Fund");
        new Select(voucherDepartment).selectByVisibleText("ENGINEERING");
        new Select(voucherFunction).selectByVisibleText("Water Supply");
        bankPaymentId.click();
        new Select(bankPaymentId).selectByVisibleText("ANDHRA BANK Andhra Bank RTC Busstand");
        waitForElementToBeClickable(amountTextBox , webDriver);
        amountTextBox.sendKeys("100");
        accountNumber.click();
        new Select(accountNumber).selectByVisibleText("4502110--110710100009664--ANDHRA BANK");

        switch (mode){
            case "cheque" :
                break;

            case "cash" :
                waitForElementToBeClickable(bankPaymentCash , webDriver);
                bankPaymentCash.click();
                break;

            case "RTGS" :
                waitForElementToBeClickable(bankPaymentRTGS , webDriver);
                bankPaymentRTGS.click();
                break;
        }

        waitForElementToBeClickable(paidToCustomer ,webDriver);
        paidToCustomer.sendKeys("Tester");

        waitForElementToBeClickable(documentNumber ,webDriver);
        documentNumber.sendKeys("123456");

        waitForElementToBeClickable(documentDate ,webDriver);
        DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calobj1 = Calendar.getInstance();
        documentDate.sendKeys(df1.format(calobj1.getTime()));

        waitForElementToBeVisible(accountCode1 , webDriver);
        waitForElementToBeClickable(accountCode1, webDriver);
        accountCode1.sendKeys("2101001");

        waitForElementToBeVisible(accountCodeDropdown , webDriver);
        waitForElementToBeClickable(accountCodeDropdown , webDriver);
        accountCodeDropdown.click();
        enterText(debitAmount1 , "100");

        new Select(ledgerAccount1).selectByVisibleText("2101001");
        new Select(ledgerType1).selectByVisibleText("contractor");
        ledgerCode1.sendKeys("KMC001");

        waitForElementToBeClickable(accountCodeDropdown , webDriver);
        accountCodeDropdown.click();
        ledgerAmount1.sendKeys("100");
    }

    public String directBankSuccessPage(){
        switchToNewlyOpenedWindow(webDriver);
        String message = forwardMessage.getText();
        if(message.contains("Successful")) {
            waitForElementToBeClickable(bankCloseButton, webDriver);
            bankCloseButton.click();
        }
        else {
            waitForElementToBeClickable(closeButton ,webDriver);
            closeButton.click();
        }
        switchToPreviouslyOpenedWindow(webDriver);
        return message;
    }

    public void enterBankToBankDetails(){

        new Select(fundId).selectByVisibleText("Municipal Fund");
        new Select(voucherDepartment).selectByVisibleText("ENGINEERING");
        new Select(voucherFunction).selectByVisibleText("Water Supply");
        fromBankId.click();
        new Select(fromBankId).selectByVisibleText("KOTAK MAHINDRA BANK Ucon Plaza Kurnool");
        fromAccountNumber.click();
        new Select(fromAccountNumber).selectByVisibleText("4502205--311010192115--KOTAK MAHINDRA BANK");
        new Select(toFundId).selectByVisibleText("Municipal Fund");
        toBankId.click();
        new Select(toBankId).selectByVisibleText("KOTAK MAHINDRA BANK Ucon Plaza Kurnool");
        toAccountNumber.click();
        new Select(toAccountNumber).selectByVisibleText("4502207--311010192123--KOTAK MAHINDRA BANK");

        waitForElementToBeClickable(referenceNumber , webDriver);
        referenceNumber.sendKeys(get6DigitRandomInt());

        waitForElementToBeClickable(amountTextBox , webDriver);
        amountTextBox.sendKeys("100");

        waitForElementToBeClickable(saveButton , webDriver);
        saveButton.click();
        switchToNewlyOpenedWindow(webDriver);
    }

    public String closesSuccessfulTransferCreationPage(){
        waitForElementToBeVisible(forwardMessage , webDriver);
        String message = forwardMessage.getText();

        waitForElementToBeClickable(bankCloseButton , webDriver);
        bankCloseButton.click();
        switchToPreviouslyOpenedWindow(webDriver);
        return message;
    }
}
