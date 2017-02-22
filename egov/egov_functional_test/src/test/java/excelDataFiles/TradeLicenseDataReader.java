package excelDataFiles;

import builders.tradeLicense.*;
import entities.tradeLicense.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class TradeLicenseDataReader extends ExcelReader {

    Sheet tradeOwnerDetailsSheet;
    Sheet tradeLocationDetailsSheet;
    Sheet tradeDetailsSheet;
    Sheet licenseClosureSheet;
    Sheet searchTradeDetailsSheet;

    public TradeLicenseDataReader(String testData) {
        super(testData);
        tradeOwnerDetailsSheet = workbook.getSheet("tradeOwnerDetails");
        tradeLocationDetailsSheet =workbook.getSheet("tradeLocationDetails");
        tradeDetailsSheet = workbook.getSheet("tradeDetails");
        licenseClosureSheet = workbook.getSheet("licenseClosure");
        searchTradeDetailsSheet = workbook.getSheet("searchTradeDeatils");
    }

    public TradeOwnerDetails getTradeOwnerDetails(String tradeOwnerDetailsDataId){
        Row dataRow = readDataRow(tradeOwnerDetailsSheet, tradeOwnerDetailsDataId);
        Cell aadhaarNumberCell = getCellData(tradeOwnerDetailsSheet, dataRow, "aadhaarNumber");
        aadhaarNumberCell.setCellType(Cell.CELL_TYPE_STRING);
        String  aadhaarNumber = aadhaarNumberCell.getStringCellValue();

        Cell mobileNumberCell = getCellData(tradeOwnerDetailsSheet, dataRow, "mobileNumber");
        mobileNumberCell.setCellType(Cell.CELL_TYPE_STRING);
        String  mobileNumber = mobileNumberCell.getStringCellValue();

        String tradeOwnerName = getCellData(tradeOwnerDetailsSheet, dataRow, "tradeOwnerName").getStringCellValue();
        String fatherSpouseName = getCellData(tradeOwnerDetailsSheet, dataRow, "fatherSpouseName").getStringCellValue();
        String emailId = getCellData(tradeOwnerDetailsSheet, dataRow, "emailId").getStringCellValue();
        String tradeOwnerAddress = getCellData(tradeOwnerDetailsSheet, dataRow, "tradeOwnerAddress").getStringCellValue();


        return new TradeOwnerDetailsBuilder()
                .withAadhaarNumber(aadhaarNumber)
                .withMobileNumber(mobileNumber)
                .withTradeOwnerName(tradeOwnerName)
                .withFatherSpouseName(fatherSpouseName)
                .withEmailId(emailId)
                .withTradeOwnerAddress(tradeOwnerAddress)
                .build();

    }

    public TradeLocationDetails getTradeLocationDetails(String tradeLocationDetailsDataId) {
        Row dataRow = readDataRow(tradeLocationDetailsSheet, tradeLocationDetailsDataId);

        String propertyAssessmentDetails = getCellData(tradeLocationDetailsSheet, dataRow, "propertyAssessmentDetails").getStringCellValue();
        String ownershipType = getCellData(tradeLocationDetailsSheet, dataRow, "ownershipType").getStringCellValue();
        String locality = getCellData(tradeLocationDetailsSheet, dataRow,"locality").getStringCellValue();
        String ward = getCellData(tradeLocationDetailsSheet,dataRow,"ward").getStringCellValue();

        return new TradeLocationDetailsBuilder()
                .withpropertyAssessmentNumber(propertyAssessmentDetails)
                .withownershipType(ownershipType)
                .withLocality(locality)
                .withWard(ward)
                .build();
    }

    public TradeDetails getTradeDetails(String tradeDetailsData) {
        Row dataRow = readDataRow(tradeDetailsSheet, tradeDetailsData);

        String tradeTitle = getCellData(tradeDetailsSheet, dataRow, "tradeTitle").getStringCellValue();
        String tradeType = getCellData(tradeDetailsSheet, dataRow, "tradeType").getStringCellValue();
        String tradeCategory = getCellData(tradeDetailsSheet, dataRow, "tradeCategory").getStringCellValue();
        String tradeSubCategory = getCellData(tradeDetailsSheet, dataRow, "tradeSubCategory").getStringCellValue();

        Cell tradeAreaWeightOfPremisesCell = getCellData(tradeDetailsSheet, dataRow, "tradeAreaWeightOfPremises");
        tradeAreaWeightOfPremisesCell.setCellType(Cell.CELL_TYPE_STRING);
        String tradeAreaWeightOfPremises = tradeAreaWeightOfPremisesCell.getStringCellValue();

        String remarks = getCellData(tradeDetailsSheet, dataRow, "remarks").getStringCellValue();

        Cell tradeCommencementDateCell = getCellData(tradeDetailsSheet, dataRow, "TradeCommencementDate");
        tradeCommencementDateCell.setCellType(Cell.CELL_TYPE_STRING);
        String tradeCommencementDate = tradeCommencementDateCell.getStringCellValue();

        return new TradeDetailsBuilder()
                .withtradeTitle(tradeTitle)
                .withtradeType(tradeType)
                .withtradeCategory(tradeCategory)
                .withtradeSubCategory(tradeSubCategory)
                .withtradeAreaWeightOfPremises(tradeAreaWeightOfPremises)
                .withremarks(remarks)
                .withtradeCommencementDate(tradeCommencementDate)
                .build();
    }
    public LicenseClosureDetails getDetailsForClosure(String closureData) {
        Row dataRow = readDataRow(licenseClosureSheet,closureData);

        String status = getCellData(licenseClosureSheet, dataRow, "status").getStringCellValue();
        String tradeCategory=getCellData(licenseClosureSheet, dataRow, "tradeCategory").getStringCellValue();

        return new LicenseClosureDetailsBuilder()
                .withStatusDetails(status)
                .withTradeCategory(tradeCategory)
                .build();
    }
    public SearchTradeDetails getTradeSearchDetails(String searchId) {
        Row dataRow = readDataRow(searchTradeDetailsSheet, searchId);
        SearchTradeDetails searchTradeDetails = new SearchTradeDetails();

        switch (searchId) {
            case "searchWithApplicationNumber":
                String applicationNumber = getCellData(searchTradeDetailsSheet, dataRow, "searchValue").getStringCellValue();

                searchTradeDetails = new SearchTradeDetailsBuilder()
                        .withApplicationNumber(applicationNumber)
                        .build();
                break;

            case "searchWithLicenseNumber":

                String licenseNumber= getCellData(searchTradeDetailsSheet, dataRow, "searchValue").getStringCellValue();

                searchTradeDetails = new SearchTradeDetailsBuilder()
                        .withLicenseNumber(licenseNumber)
                        .build();
                break;
        }
        return searchTradeDetails;
    }
}
