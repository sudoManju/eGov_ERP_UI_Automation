package excelDataFiles;

import builders.financial.*;
import entities.financial.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class FinanceDataReader extends ExcelReader {

    Sheet financialJournalVoucherSheet;
    Sheet financialBankDetailsSheet;
    Sheet financialExpenseBillDetailsSheet;
    Sheet directBankPaymentDetailsSheet;
    Sheet bankToBankTransferDetailsSheet;

    public FinanceDataReader(String testData) {
        super(testData);
        financialJournalVoucherSheet = workbook.getSheet("journalVoucherDetails");
        financialBankDetailsSheet = workbook.getSheet("financialBankDetails");
        financialExpenseBillDetailsSheet = workbook.getSheet("financialExpenseBillDetails");
        directBankPaymentDetailsSheet = workbook.getSheet("directBankPaymentDetails");
        bankToBankTransferDetailsSheet = workbook.getSheet("financialBankToBankDetails");
    }

    public FinancialJournalVoucherDetails getJournalVoucherDetails(String voucher){

        Row dataRow = readDataRow(financialJournalVoucherSheet, voucher);
        String voucherType = getCellData(financialJournalVoucherSheet, dataRow, "voucherType").getStringCellValue();
        String fundId = getCellData(financialJournalVoucherSheet, dataRow, "fund").getStringCellValue();
        String department = getCellData(financialJournalVoucherSheet, dataRow, "department").getStringCellValue();
        String function = getCellData(financialJournalVoucherSheet, dataRow, "function").getStringCellValue();

        Cell code1 = getCellData(financialJournalVoucherSheet, dataRow, "accountCode1");
        code1.setCellType(Cell.CELL_TYPE_STRING);
        String  accountCode1 = code1.getStringCellValue();

        Cell code2 = getCellData(financialJournalVoucherSheet, dataRow, "accountCode2");
        code2.setCellType(Cell.CELL_TYPE_STRING);
        String  accountCode2 = code2.getStringCellValue();

        Cell code3 = getCellData(financialJournalVoucherSheet, dataRow, "accountCode3");
        code3.setCellType(Cell.CELL_TYPE_STRING);
        String  accountCode3 = code3.getStringCellValue();

        Cell code4 = getCellData(financialJournalVoucherSheet, dataRow, "debitAmount1");
        code4.setCellType(Cell.CELL_TYPE_STRING);
        String  debitAmount1 = code4.getStringCellValue();

        Cell code5 = getCellData(financialJournalVoucherSheet, dataRow, "creditAmount2");
        code5.setCellType(Cell.CELL_TYPE_STRING);
        String  creditAmount2 = code5.getStringCellValue();

        Cell code6 = getCellData(financialJournalVoucherSheet, dataRow, "creditAmount3");
        code6.setCellType(Cell.CELL_TYPE_STRING);
        String  creditAmount3 = code6.getStringCellValue();

        Cell code7 = getCellData(financialJournalVoucherSheet, dataRow, "ledgerAmount1");
        code7.setCellType(Cell.CELL_TYPE_STRING);
        String  ledgerAmount1 = code7.getStringCellValue();

        Cell code8 = getCellData(financialJournalVoucherSheet, dataRow, "ledgerAmount2");
        code8.setCellType(Cell.CELL_TYPE_STRING);
        String  ledgerAmount2 = code8.getStringCellValue();

        String ledgerType1 = getCellData(financialJournalVoucherSheet, dataRow, "ledgerType1").getStringCellValue();
        String ledgerType2 = getCellData(financialJournalVoucherSheet, dataRow, "ledgerType2").getStringCellValue();
        String ledgerCode1 = getCellData(financialJournalVoucherSheet, dataRow, "ledgerCode1").getStringCellValue();

        Cell code9 = getCellData(financialJournalVoucherSheet, dataRow, "ledgerCode2");
        code9.setCellType(Cell.CELL_TYPE_STRING);
        String  ledgerCode2 = code9.getStringCellValue();

        return new FinancialJournalVoucherDetailsBuilder()
                .withVoucherType(voucherType)
                .withFundId(fundId)
                .withAccountCode1(accountCode1)
                .withAccountCode2(accountCode2)
                .withAccountCode3(accountCode3)
                .withDepartment(department)
                .withFunction(function)
                .withDebitAmount1(debitAmount1)
                .withCreditAmount2(creditAmount2)
                .withCreditAmount3(creditAmount3)
                .withLedgerAmount1(ledgerAmount1)
                .withLedgerAmount2(ledgerAmount2)
                .withLedgerType1(ledgerType1)
                .withLedgerType2(ledgerType2)
                .withLedgerCode1(ledgerCode1)
                .withLedgerCode2(ledgerCode2)
                .build();
    }

    public FinancialBankDetails getFinancialBankDetails(String bankDetails){
        Row dataRow = readDataRow(financialBankDetailsSheet, bankDetails);

        String bankName = getCellData(financialBankDetailsSheet, dataRow, "bankName").getStringCellValue();
        String accountNumber = getCellData(financialBankDetailsSheet, dataRow, "accountNumber").getStringCellValue();

        return new FinancialBankDetailsBuilder()
                .withBankName(bankName)
                .withAccountNumber(accountNumber)
                .build();
    }

    public FinancialExpenseBillDetails getFinancialExpenseBillDetails(String expenseBill){
        Row dataRow = readDataRow(financialExpenseBillDetailsSheet, expenseBill);

        String fund = getCellData(financialExpenseBillDetailsSheet, dataRow, "fund").getStringCellValue();
        String department = getCellData(financialExpenseBillDetailsSheet, dataRow, "department").getStringCellValue();
        String function = getCellData(financialExpenseBillDetailsSheet, dataRow, "function").getStringCellValue();
        String billSubType = getCellData(financialExpenseBillDetailsSheet, dataRow, "billSubType").getStringCellValue();

        Cell code1 = getCellData(financialExpenseBillDetailsSheet, dataRow, "accountCodeDebit");
        code1.setCellType(Cell.CELL_TYPE_STRING);
        String  accountCodeDebit = code1.getStringCellValue();

        Cell code2 = getCellData(financialExpenseBillDetailsSheet, dataRow, "accountCodeCredit");
        code2.setCellType(Cell.CELL_TYPE_STRING);
        String  accountCodeCredit = code2.getStringCellValue();

        Cell code3 = getCellData(financialExpenseBillDetailsSheet, dataRow, "expenseDebitAmount");
        code3.setCellType(Cell.CELL_TYPE_STRING);
        String  expenseDebitAmount = code3.getStringCellValue();

        Cell code4 = getCellData(financialExpenseBillDetailsSheet, dataRow, "expenseCreditAmount");
        code4.setCellType(Cell.CELL_TYPE_STRING);
        String  expenseCreditAmount = code4.getStringCellValue();

        Cell code5 = getCellData(financialExpenseBillDetailsSheet, dataRow, "expenseNetAmount");
        code5.setCellType(Cell.CELL_TYPE_STRING);
        String  expenseNetAmount = code5.getStringCellValue();

        return new FinancialExpenseBillDetailsBuilder()
                .withExpenseFund(fund)
                .withExpenseDepartment(department)
                .withExpenseFunction(function)
                .withBillSubType(billSubType)
                .withExpenseAccountDebit(accountCodeDebit)
                .withExpenseAccountCredit(accountCodeCredit)
                .withExpenseDebitAmount(expenseDebitAmount)
                .withExpenseCreditAmount(expenseCreditAmount)
                .withExpenseNetAmount(expenseNetAmount)
                .build();
    }

    public DirectBankPaymentDetails getDirectBankPaymentDetails(String directBankDetails) {

        Row dataRow = readDataRow(directBankPaymentDetailsSheet, directBankDetails);

        String fundId = getCellData(directBankPaymentDetailsSheet, dataRow, "fundId").getStringCellValue();
        String voucherDepartment = getCellData(directBankPaymentDetailsSheet, dataRow, "voucherDepartment").getStringCellValue();
        String voucherFunction = getCellData(directBankPaymentDetailsSheet, dataRow, "voucherFunction").getStringCellValue();
        String bankBranch = getCellData(directBankPaymentDetailsSheet, dataRow, "bankBranch").getStringCellValue();
        String accountNumber = getCellData(directBankPaymentDetailsSheet, dataRow, "accountNumber").getStringCellValue();
        String ledgerType1 = getCellData(directBankPaymentDetailsSheet, dataRow, "ledgerType1").getStringCellValue();
        String ledgerCode1 = getCellData(directBankPaymentDetailsSheet, dataRow, "ledgerCode1").getStringCellValue();

        Cell cell1 = getCellData(directBankPaymentDetailsSheet, dataRow, "amount");
        cell1.setCellType(Cell.CELL_TYPE_STRING);
        String amount = cell1.getStringCellValue();

        Cell cell2 = getCellData(directBankPaymentDetailsSheet, dataRow, "accountCode1");
        cell2.setCellType(Cell.CELL_TYPE_STRING);
        String accountCode1 = cell2.getStringCellValue();

        Cell cell3 = getCellData(directBankPaymentDetailsSheet, dataRow, "debitAmount1");
        cell3.setCellType(Cell.CELL_TYPE_STRING);
        String debitAmount1 = cell3.getStringCellValue();

        Cell cell4 = getCellData(directBankPaymentDetailsSheet, dataRow, "ledgerAccount1");
        cell4.setCellType(Cell.CELL_TYPE_STRING);
        String ledgerAccount1 = cell4.getStringCellValue();

        Cell cell5 = getCellData(directBankPaymentDetailsSheet, dataRow, "ledgerAmount1");
        cell5.setCellType(Cell.CELL_TYPE_STRING);
        String ledgerAmount1 = cell5.getStringCellValue();

        return new DirectBankPaymentDetailsBuilder()
                .withFundId(fundId)
                .withVoucherDepartment(voucherDepartment)
                .withVoucherFunction(voucherFunction)
                .withBankBranch(bankBranch)
                .withAccountNumber(accountNumber)
                .withLedgerType1(ledgerType1)
                .withLedgerCode1(ledgerCode1)
                .withAmount(amount)
                .withAccountCode1(accountCode1)
                .withDebitAmount1(debitAmount1)
                .withLedgerAccount1(ledgerAccount1)
                .withLedgerAmount1(ledgerAmount1)
                .build();
    }

    public FinancialBankToBankDetails getBankToBankTransferDetails(String bankDetails) {

        Row dataRow = readDataRow(bankToBankTransferDetailsSheet, bankDetails);

        String fundId = getCellData(bankToBankTransferDetailsSheet, dataRow, "fundId").getStringCellValue();
        String voucherDepartment = getCellData(bankToBankTransferDetailsSheet, dataRow, "voucherDepartment").getStringCellValue();
        String voucherFunction = getCellData(bankToBankTransferDetailsSheet, dataRow, "voucherFunction").getStringCellValue();
        String fromBank = getCellData(bankToBankTransferDetailsSheet, dataRow, "fromBank").getStringCellValue();
        String fromAccountNumber = getCellData(bankToBankTransferDetailsSheet, dataRow, "fromAccountNumber").getStringCellValue();
        String toFundId = getCellData(bankToBankTransferDetailsSheet, dataRow, "toFundId").getStringCellValue();
        String toBank = getCellData(bankToBankTransferDetailsSheet, dataRow, "toBank").getStringCellValue();
        String toAccountNumber = getCellData(bankToBankTransferDetailsSheet, dataRow, "toAccountNumber").getStringCellValue();

        Cell cell = getCellData(bankToBankTransferDetailsSheet, dataRow, "amount");
        cell.setCellType(Cell.CELL_TYPE_STRING);
        String amount = cell.getStringCellValue();

        return new FinancialBankToBankDetailsBuilder()
                .withFundId(fundId)
                .withVoucherDepartment(voucherDepartment)
                .withVoucherFunction(voucherFunction)
                .withFromBank(fromBank)
                .withFromAccountNumber(fromAccountNumber)
                .withToFundId(toFundId)
                .withToBank(toBank)
                .withToAccountNumber(toAccountNumber)
                .withAmount(amount)
                .build();
    }
}
