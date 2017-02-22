package excelDataFiles;

import builders.grievances.CreateComplaintDetailsBuilder;
import entities.grievances.CreateComplaintDetails;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class GrievanceDataReader extends ExcelReader {

    Sheet grievancesContactDetailsSheet;
    Sheet grievanceDetailsSheet;

    public GrievanceDataReader(String testData) {
        super(testData);
        grievancesContactDetailsSheet = workbook.getSheet("contactInfo");
        grievanceDetailsSheet = workbook.getSheet("grievanceDetails");
    }

    public CreateComplaintDetails getCitizenContactDetails(String contactInfo) {
        Row dataRow = readDataRow(grievancesContactDetailsSheet,contactInfo);

        Cell citizenNameCell= getCellData(grievancesContactDetailsSheet, dataRow, "citizenName");
        citizenNameCell.setCellType(Cell.CELL_TYPE_STRING);
        String citizenName=citizenNameCell.getStringCellValue();

        Cell citizenMobNoCell= getCellData(grievancesContactDetailsSheet, dataRow, "mobNo");
        citizenMobNoCell.setCellType(Cell.CELL_TYPE_STRING);
        String citizenMobNo= citizenMobNoCell.getStringCellValue();

        Cell emailIdCell= getCellData(grievancesContactDetailsSheet, dataRow, "emailId");
        emailIdCell.setCellType(Cell.CELL_TYPE_STRING);
        String emailId= emailIdCell.getStringCellValue();

        return new CreateComplaintDetailsBuilder()
                .withCitizenName(citizenName)
                .withCitizenMobNo(citizenMobNo)
                .withEmailId(emailId)
                .build();
    }

    public CreateComplaintDetails getGrievanceDetails(String grievanceDetails) {
        Row dataRow= readDataRow(grievanceDetailsSheet,grievanceDetails);

        String grievanceCategory= getCellData(grievanceDetailsSheet, dataRow,"grievanceCategory").getStringCellValue();

        String grievanceType=getCellData(grievanceDetailsSheet, dataRow,"grievanceType").getStringCellValue();

        String grievanceDetailsText= getCellData(grievanceDetailsSheet, dataRow, "grievanceDetails").getStringCellValue();

        String grievanceLocation= getCellData(grievanceDetailsSheet, dataRow, "grievanceLocation").getStringCellValue();

        String locationLandmark= getCellData(grievanceDetailsSheet, dataRow, "locationLandmark").getStringCellValue();

        return new CreateComplaintDetailsBuilder()
                .withGrievanceCategory(grievanceCategory)
                .withGrievanceType(grievanceType)
                .withGrievanceDetails(grievanceDetailsText)
                .withGrievanceLocation(grievanceLocation)
                .withLocationLandmark(locationLandmark)
                .build();
    }
}
