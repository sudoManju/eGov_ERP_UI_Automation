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

import java.text.ParseException;
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

        waitForElementToBeClickable(accountCode1, webDriver);
        accountCode1.sendKeys(financialJournalVoucherDetails.getAccountCode1());

        WebElement dropdown = webDriver.findElement(By.className("yui-ac-highlight"));
        dropdown.click();
        enterText(debitAmount1 , "100");

        accountCode2.sendKeys(financialJournalVoucherDetails.getAccountCode2());
        WebElement dropdown1 = webDriver.findElement(By.className("yui-ac-highlight"));
        dropdown1.click();
        enterText(creditAmount2 , "100");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement element = webDriver.findElement(By.id("subLedgerlist[0].glcode.id"));
        waitForElementToBePresent(By.className("yui-dt-dropdown") , webDriver);
        List<WebElement> webElementList = element.findElements(By.tagName("option"));

        new Select(ledgerAccount1).selectByVisibleText(webElementList.get(1).getText());
        new Select(ledgerType1).selectByVisibleText("contractor");
        ledgerCode1.sendKeys("KMC001");

        WebElement kmcCLass = webDriver.findElement(By.className("yui-ac-highlight"));
        waitForElementToBeClickable(kmcCLass , webDriver);
        kmcCLass.click();
        ledgerAmount1.sendKeys("100");

        if(webElementList.size()>2){
            addList.get(2).click();

            ledgerAccount2.click();
            new Select(ledgerAccount2).selectByVisibleText(webElementList.get(1).getText());
            new Select(ledgerType2).selectByVisibleText("contractor");
            ledgerCode2.sendKeys("KMC001");

            WebElement kmcCLass1 = webDriver.findElement(By.className("yui-ac-highlight"));
            waitForElementToBeClickable(kmcCLass1 , webDriver);
            kmcCLass.click();
            ledgerAmount2.sendKeys("100");
        }
    }

    public void enterFinanceApprovalDetails(ApprovalDetails approvalDetails) throws ParseException {
        if(juneDate.contains("06")){
            createAndApprove.click();
            juneDate = "00";
        }
        else{
        waitForElementToBeClickable(approverDepartment ,webDriver);
        new Select(approverDepartment).selectByVisibleText(approvalDetails.getApproverDepartment());
        waitForElementToBeClickable(approverDesignation ,webDriver);
        new Select(approverDesignation).selectByVisibleText(approvalDetails.getApproverDesignation());
        waitForElementToBeVisible(approverPosition , webDriver);
        new Select(approverPosition).selectByVisibleText(approvalDetails.getApprover());
        forwardButton.click();
        }
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

    public void openVoucher(String voucherNumber){
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement element = getVoucherRow(voucherNumber);
        element.click();
        switchToNewlyOpenedWindow(webDriver);
    }

    private WebElement getVoucherRow(String voucherNumber) {
        waitForElementToBeVisible(webDriver.findElement(By.id("worklist")), webDriver);
        waitForElementToBeVisible(officialInboxTable, webDriver);

        await().atMost(10, SECONDS).until(() -> officialInboxTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size() > 1);
        List<WebElement> voucherRows = officialInboxTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));

        for (WebElement voucherRow : voucherRows) {
            if (voucherRow.findElements(By.tagName("td")).get(4).getText().contains(voucherNumber))
                return voucherRow;
        }
        throw new RuntimeException("No voucher row found for -- " + voucherNumber);
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

    public void actOnAboveVoucher(){
        waitForElementToBeClickable(firstVoucher , webDriver);
        if ( !firstVoucher.isSelected() )
        {
            firstVoucher.click();
        }
        waitForElementToBeClickable(generatePayment , webDriver);
        generatePayment.click();
        switchToNewlyOpenedWindow(webDriver);
    }

    public String verifyVoucher(){
        billDetails.click();
        waitForElementToBeClickable(voucherNumberToVerify , webDriver);
        String voucherNumber = voucherNumberToVerify.getText();

        paymentDetails.click();

        return voucherNumber;
    }

    public void billPayment(FinancialBankDetails financialBankDetails){
        new Select(bankBranch).selectByVisibleText(financialBankDetails.getBankName());
        waitForElementToBeClickable(bankAccount , webDriver);
        new Select(bankAccount).selectByVisibleText(financialBankDetails.getAccountNumber());
    }

    public void billRemittancePayment(FinancialBankDetails financialBankDetails){
        new Select(bankBranch1).selectByVisibleText(financialBankDetails.getBankName());
        waitForElementToBeClickable(bankAccount , webDriver);
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
        expensePayTo.sendKeys("tester");

        expenseAccountCodeDebit.sendKeys(financialExpenseBillDetails.getExpenseAccountCodeDebit());
        waitForElementToBeVisible( webDriver.findElement(By.className("tt-highlight")),webDriver);
        WebElement dropDown1 = webDriver.findElement(By.className("tt-highlight"));
        dropDown1.click();
        expenseDebitAmount.sendKeys("100");

        expenseAccountCodeCredit.sendKeys(financialExpenseBillDetails.getExpenseAccountCodeCredit());
        waitForElementToBeVisible( webDriver.findElement(By.className("tt-highlight")),webDriver);
        WebElement dropdown1 = webDriver.findElement(By.className("tt-highlight"));
        dropdown1.click();
        expenseCreditAmount.sendKeys("90");

        List<WebElement> element1 = expenseNetPayable.findElements(By.tagName("option"));
        waitForElementToBeClickable(element1.get(1), webDriver);
        element1.get(1).click();

        expenseNetAmount.sendKeys("10");
        expensePopulate.click();
    }

    public void enterExpenseApprovalDetails(ApprovalDetails approvalDetails){

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

        waitForElementToBeVisible(expenseApprovalPosition , webDriver);
        new Select(expenseApprovalPosition).selectByVisibleText(approvalDetails.getApprover());

        forwardButton.click();
        switchToNewlyOpenedWindow(webDriver);
    }

    public String closesTheExpensePage(){
        String message = expenseCreatedMessage.getText();

        closeButton.click();
        switchToPreviouslyOpenedWindow(webDriver);
        return message;
    }

    public String closesSuccessfullPaymentPage(){
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

        webDriver.findElement(By.cssSelector(".buttonsubmit")).click();
        switchToNewlyOpenedWindow(webDriver);
        webDriver.findElement(By.cssSelector("input[type='button'][value='Close']")).click();

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

        accountCode1.sendKeys(financialJournalVoucherDetails.getAccountCode1());
        WebElement dropdown = webDriver.findElement(By.className("yui-ac-highlight"));
        dropdown.click();
        enterText(debitAmount1 , "1000");

        accountCode2.sendKeys(financialJournalVoucherDetails.getAccountCode2());
        WebElement dropdown1 = webDriver.findElement(By.className("yui-ac-highlight"));
        dropdown1.click();
        enterText(creditAmount2 , "800");

        if(!financialJournalVoucherDetails.getAccountCode3().isEmpty()){
            addList.get(1).click();
            accountCode3.sendKeys(financialJournalVoucherDetails.getAccountCode3());
            WebElement dropdown2 = webDriver.findElement(By.className("yui-ac-highlight"));
            dropdown2.click();
            enterText(creditAmount3 , "200");
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement element = webDriver.findElement(By.id("subLedgerlist[0].glcode.id"));
        waitForElementToBePresent(By.className("yui-dt-dropdown") , webDriver);
        List<WebElement> webElementList = element.findElements(By.tagName("option"));

        new Select(ledgerAccount1).selectByVisibleText(webElementList.get(1).getText());
        new Select(ledgerType1).selectByVisibleText("contractor");
        ledgerCode1.sendKeys("KMC001");

        WebElement kmcCLass = webDriver.findElement(By.className("yui-ac-highlight"));
        waitForElementToBeClickable(kmcCLass , webDriver);
        kmcCLass.click();
        ledgerAmount1.sendKeys("1000");

        if(webElementList.size()>2){
            addList.get(3).click();

            ledgerAccount2.click();
            new Select(ledgerAccount2).selectByVisibleText(webElementList.get(2).getText());
            new Select(ledgerType2).selectByVisibleText("Employee");
            ledgerCode2.sendKeys("946800");

            WebElement kmcClass1 = webDriver.findElement(By.className("yui-ac-highlight"));
            waitForElementToBeClickable(kmcClass1 , webDriver);
            kmcClass1.click();
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

        await().atMost(10, SECONDS).until(() -> remittanceBillTable.findElements(By.tagName("tr")).size() > 1);
        List<WebElement> applicationRows = remittanceBillTable.findElements(By.tagName("tr"));
        System.out.println("total number of rows -- " + applicationRows.size());
        for (WebElement applicationRow : applicationRows) {
            if (applicationRow.findElements(By.tagName("td")).get(1).
                            findElement(By.className("yui-dt-liner")).findElement(By.tagName("label")).getText().contains(applicationNumber))
                return applicationRow.findElements(By.tagName("td")).get(0).findElement(By.className("yui-dt-liner"));
        }
        throw new RuntimeException("No application row found for -- " + applicationNumber);
    }
}
