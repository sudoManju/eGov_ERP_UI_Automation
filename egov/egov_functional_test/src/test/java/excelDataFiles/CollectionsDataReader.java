package excelDataFiles;

import builders.collections.ChallanHeaderDetailsBuilder;
import builders.collections.PaymentMethodBuilder;
import entities.collections.ChallanHeaderDetails;
import entities.collections.PaymentMethod;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class CollectionsDataReader extends ExcelReader {

    Sheet paymentMethodSheet;
    Sheet challanHeaderDetailsSheet;

    public CollectionsDataReader(String testData) {
        super(testData);
        paymentMethodSheet = workbook.getSheet("paymentMethod");
        challanHeaderDetailsSheet = workbook.getSheet("challanHeaderDetails");
    }

    public PaymentMethod getPaymentMethodDetails(String paymentMethod) {
        Row dataRow = readDataRow(paymentMethodSheet, paymentMethod);

        Cell chequeNumberCell = getCellData(paymentMethodSheet, dataRow, "dd/chequeNum");
        chequeNumberCell.setCellType(Cell.CELL_TYPE_STRING);
        String chequeNumber = chequeNumberCell.getStringCellValue();

        Cell bankNameCell = getCellData(paymentMethodSheet, dataRow, "bankName");
        bankNameCell.setCellType(Cell.CELL_TYPE_STRING);
        String bankName = bankNameCell.getStringCellValue();

        Cell accountNumCell = getCellData(paymentMethodSheet, dataRow, "accountNum");
        accountNumCell.setCellType(Cell.CELL_TYPE_STRING);
        String accountNum = accountNumCell.getStringCellValue();

        return new PaymentMethodBuilder()
                .withChequeNumber(chequeNumber)
                .withBankName(bankName)
                .withAccountNumber(accountNum)
                .build();
    }

    public ChallanHeaderDetails getChallanHeader(String challanheaderid) {

        Row dataRow = readDataRow(challanHeaderDetailsSheet, challanheaderid);

        Cell dateCell = getCellData(challanHeaderDetailsSheet, dataRow, "date");
        dateCell.setCellType(Cell.CELL_TYPE_STRING);
        String date = dateCell.getStringCellValue();
        String payeeName = getCellData(challanHeaderDetailsSheet, dataRow, "payeeName").getStringCellValue();
        String payeeAddress = getCellData(challanHeaderDetailsSheet,dataRow,"payeeAddress").getStringCellValue();
        String narration = getCellData(challanHeaderDetailsSheet,dataRow,"narration").getStringCellValue();
        String serviceCategory = getCellData(challanHeaderDetailsSheet,dataRow,"serviceCategory").getStringCellValue();
        String sericeType = getCellData(challanHeaderDetailsSheet,dataRow,"serviceType").getStringCellValue();

        Cell amountCell = getCellData(challanHeaderDetailsSheet, dataRow, "amount");
        amountCell.setCellType(Cell.CELL_TYPE_STRING);
        String amount = amountCell.getStringCellValue();

        return new ChallanHeaderDetailsBuilder()
                .withDate(date)
                .withPayeeName(payeeName)
                .withPayeeAddress(payeeAddress)
                .withNarration(narration)
                .withServiceCategory(serviceCategory)
                .withServiceType(sericeType)
                .withAmount(amount)
                .build();
    }
}
