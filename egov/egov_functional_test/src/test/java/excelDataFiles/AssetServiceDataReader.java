package excelDataFiles;

import builders.assetManagement.assetService.HeaderDetailsBuilder;
import builders.assetManagement.assetService.LocationDetailsBuilder;
import entities.assetManagement.assetService.HeaderDetails;
import entities.assetManagement.assetService.LocationDetails;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class AssetServiceDataReader extends ExcelReader {

    Sheet headerDetailsSheet;
    Sheet locationDetailsSheet;

    public AssetServiceDataReader(String testData) {
        super(testData);
        headerDetailsSheet = workbook.getSheet("headerDetails");
    }


    public HeaderDetails getHeaderDetails(String headerDetails) {

        Row dataRow = readDataRow(headerDetailsSheet, headerDetails);

        String department = getCellData(headerDetailsSheet, dataRow, "department").getStringCellValue();
        String assetCategory = getCellData(headerDetailsSheet, dataRow, "assetCategory").getStringCellValue();
        String dateOfCreation = getCellData(headerDetailsSheet, dataRow, "dateOfCreation").getStringCellValue();
        String description = getCellData(headerDetailsSheet, dataRow, "description").getStringCellValue();
        String assetName = getCellData(headerDetailsSheet, dataRow, "assetName").getStringCellValue();
        String modeOfAcquisition = getCellData(headerDetailsSheet, dataRow, "modeOfAcquisition").getStringCellValue();

        return new HeaderDetailsBuilder()
                .withDepartment(department)
                .withAssetName(assetName)
                .withAssetCategory(assetCategory)
                .withDateOfCreation(dateOfCreation)
                .withDescription(description)
                .withModeOfAcquisition(modeOfAcquisition)
                .build();
    }

    public LocationDetails getLocationDetails(String locationDetails) {
        Row dataRow = readDataRow(headerDetailsSheet, locationDetails);

        String locality = getCellData(headerDetailsSheet, dataRow, "locality").getStringCellValue();
        return new LocationDetailsBuilder()
                .withLocality(locality)
                .build();
    }
}
