package excelDataFiles;

import builders.assetManagement.assetService.HeaderDetailsBuilder;
import entities.assetManagement.assetService.HeaderDetails;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class AssetServiceDataReader extends ExcelReader {

    Sheet headerDetailsSheet;

    public AssetServiceDataReader(String testData) {
        super(testData);
        headerDetailsSheet = workbook.getSheet("headerDetails");
    }

    public HeaderDetails getHeaderDetails(String headerDetails) {

        Row dataRow = readDataRow(headerDetailsSheet, headerDetails);

        String department = getCellData(headerDetailsSheet, dataRow, "department").getStringCellValue();
        String assetCategory = getCellData(headerDetailsSheet, dataRow, "assetCategory").getStringCellValue();
        String modeOfAcquisition = getCellData(headerDetailsSheet, dataRow, "modeOfAcquisition").getStringCellValue();
        String depreciationRate = convertNumericToString(headerDetailsSheet, dataRow, "depreciationRate");

        return new HeaderDetailsBuilder()
                .withDepartment(department)
                .withAssetCategory(assetCategory)
                .withModeOfAcquisition(modeOfAcquisition)
                .withDepreciationRate(depreciationRate)
                .build();
    }
}
